package com.example.sco.imuvo.Activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sco.imuvo.HelperClasses.Helper;
import com.example.sco.imuvo.HelperClasses.LectionDatabaseHelper;
import com.example.sco.imuvo.R;

import java.util.List;

public class testSelection extends AppCompatActivity {
    TextView speechbubble, headlineTextView;
    String nextIntentType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_selection);
        getElements();
        getInitialValuesFromIntent();
        setSpeechbubble();
    }

    private void setSpeechbubble() {
        speechbubble.setText("Sehr gut! Du möchtest Vokabeln üben. Bitte entscheide dich zwischen einer Abfrage oder einem Vokabeltest");
        headlineTextView.setText(Helper.colorsString(this,"Vokabeln abfragen", ContextCompat.getColor(this, R.color.colorMenuTextLeft),ContextCompat.getColor(this, R.color.colorMenuTextMiddle)));
    }


    private void getInitialValuesFromIntent(){
        //Bundle bundle = getIntent().getExtras();
        //nextIntentType = bundle.getString("type");

    }
    private void getElements() {
        speechbubble = (TextView) findViewById(R.id.speechbubble);
        headlineTextView = (TextView) findViewById(R.id.headline);
    }

     public void onClickStartTest(View v){
        //final Intent nextIntent;
        //nextIntent = new Intent(this,askVocabs.class);
        //startActivity(nextIntent);
    }

    public void onClickStartAsking(View v){
        final Intent menuIntent = new Intent(this,vocabReadingSelection.class);
        Bundle bundle = new Bundle();
        bundle.putString("type","test");
        menuIntent.putExtras(bundle);
        startActivity(menuIntent);
    }

    public void onClickBurgerMenu(View v){
        final Intent menuIntent = new Intent(this,MenuImuvo.class);
        startActivity(menuIntent);
    }
}
