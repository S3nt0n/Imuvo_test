package com.example.sco.imuvo.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sco.imuvo.CustomViews.ButtonJokerman;
import com.example.sco.imuvo.CustomViews.TextViewITCKRIST;
import com.example.sco.imuvo.Model.SingletonUser;
import com.example.sco.imuvo.R;
import com.example.sco.imuvo.Model.User;

public class MenuImuvo extends AppCompatActivity {

    ButtonJokerman playButton, readButton, vocabsButton, readAloudButton, testButton, taskButton;
    User user;
    ImageView bubbleImageView;
    TextViewITCKRIST bubbleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        getElements();
        getInitialValuesFromIntent();
        getBubble();
    }

    @Override
    public  void onBackPressed(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before

        dialog.setContentView(R.layout.activity_custom_dialog);
        TextView textView = (TextView) dialog.findViewById(R.id.textView);
        textView.setText("Willst du dich ausloggen?");
        Button repeatButton = (Button) dialog.findViewById(R.id.solution);
        repeatButton.setText("Ja");
        repeatButton.setTextColor(Color.GREEN);
        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();

            }
        });

        Button showSolutionButton = (Button) dialog.findViewById(R.id.repeat);
        showSolutionButton.setText("Nein");
        showSolutionButton.setTextColor(Color.RED);
        showSolutionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    private void getInitialValuesFromIntent(){
        user = new User(0,SingletonUser.data,"");
    }
    private void getElements() {
        playButton = (ButtonJokerman) findViewById(R.id.play);
        readButton = (ButtonJokerman) findViewById(R.id.read);
        vocabsButton = (ButtonJokerman) findViewById(R.id.vocabs);
        readAloudButton = (ButtonJokerman) findViewById(R.id.readAloud);
        testButton = (ButtonJokerman) findViewById(R.id.test);
        taskButton = (ButtonJokerman) findViewById(R.id.task);
        bubbleTextView = (TextViewITCKRIST) findViewById(R.id.bubbleText);
    }

    public void getBubble(){
        bubbleTextView.setText("Hallo " + user.getUserName() + ", mein Name ist Imuvo, ich freue mich heute mit Dir zu lernen!\n" +
               "Lass uns loslegen. Bitte wähle ein Symbol unter meinen Füßen!");
        bubbleTextView.setTextColor(Color.parseColor("#FFFFFF"));
    }
    public void onClickPlay(View v){

        Button t = (Button) v;
        Toast.makeText(this,t.getText(),Toast.LENGTH_LONG).show();
    }
    public void onClickRead(View v){
        final Intent menuIntent = new Intent(this,vocabReadingSelection.class);
        Bundle bundle = new Bundle();
        bundle.putString("type","read");
        menuIntent.putExtras(bundle);
        startActivity(menuIntent);
    }

    public void onClickVocabs(View v){
        Button t = (Button) v;
        Toast.makeText(this,t.getText(),Toast.LENGTH_LONG).show();

    }
    public void onClickReadAloud(View v){
        Button t = (Button) v;
        Toast.makeText(this,t.getText(),Toast.LENGTH_LONG).show();

    }
    public void onClickTest(View v){
        final Intent menuIntent = new Intent(this,testSelection.class);
        startActivity(menuIntent);

    }
    public void onClickTask(View v){
        Button t = (Button) v;
        Toast.makeText(this,t.getText(),Toast.LENGTH_LONG).show();

    }
}
