package com.example.sco.imuvo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sco.imuvo.Model.AskingSingleton;
import com.example.sco.imuvo.Model.Vocab;
import com.example.sco.imuvo.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class resultAfterAsking extends AppCompatActivity {

    TextView bubbleTextView, rightTextView, skippedTextView, wrongTextView, durationTextView;
    ArrayList<Vocab> rightVocabList, skippedVocabList, wrongVocabList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_after_asking);
        getElements();
        getValuesFromSingleton();
        setSpeechbubble();
        setValuesToTextView();
    }

    private void setValuesToTextView() {
        rightTextView.setText("Richtig: " + Integer.toString(rightVocabList.size()));
        //skippedTextView.setText("Übersprungen: " + Integer.toString(skippedVocabList.size()));
        wrongTextView.setText("Falsch: " + Integer.toString(wrongVocabList.size()));
        AskingSingleton.endingDate = (Date) Calendar.getInstance().getTime();

        long duration = ((long) (AskingSingleton.endingDate.getTime() - AskingSingleton.startingDate.getTime()));
        durationTextView.setText("Dauer: " + Long.toString(duration / 1000 / 60) + " min");
    }

    private void getValuesFromSingleton() {
        rightVocabList = AskingSingleton.rightVocabs;
        wrongVocabList = AskingSingleton.wrongVocabs;
        skippedVocabList = AskingSingleton.skippedVocabs;


    }

    private void setSpeechbubble() {
        //speechbubble.setText("Sehr gut! Du möchtest Vokabeln üben. Bitte entscheide dich zwischen einer Abfrage oder einem Vokabeltest");
    }


    private void getElements() {
        //speechbubble = (TextView) findViewById(R.id.speechbubble);
        rightTextView = (TextView) findViewById(R.id.rightVocabs);
        wrongTextView = (TextView) findViewById(R.id.wrongVocabs);
        //skippedTextView = (TextView) findViewById(R.id.skippedVocabs);
        durationTextView = (TextView) findViewById(R.id.duration);
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
        menuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(menuIntent);
        finish();
    }

    public void onClickButtonMoreAsking(View v){
        final Intent menuIntent = new Intent(this,vocabReadingSelection.class);
        Bundle bundle = new Bundle();
        bundle.putString("type","test");
        menuIntent.putExtras(bundle);
        menuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(menuIntent);
        finish();
    }

    public void onClickButtonOverview(View v){
        final Intent menuIntent = new Intent(this,MenuImuvo.class);
        startActivity(menuIntent);
    }

    public void onClickShowWrongVocabs(View v){
        final Intent intent = new Intent(this, askVocabs.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(askVocabs.ASKWRONGVOCABSAGAIN,true);
        bundle.putBoolean(askVocabs.ISRANDOM,true);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
