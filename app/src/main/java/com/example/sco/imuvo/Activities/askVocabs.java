package com.example.sco.imuvo.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sco.imuvo.HelperClasses.LectionDatabaseHelper;
import com.example.sco.imuvo.HelperClasses.VocabDatabaseHelper;
import com.example.sco.imuvo.Model.AskingSingleton;
import com.example.sco.imuvo.Model.Lection;
import com.example.sco.imuvo.Model.Vocab;
import com.example.sco.imuvo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class askVocabs extends AppCompatActivity {

    VocabDatabaseHelper vocabDatabaseHelper;
    LectionDatabaseHelper lectionDatabaseHelper;
    ArrayList<Vocab> vocabList;
    Lection currentLection;
    ListIterator<?> vocabIterator;
    Vocab currVocab;
    Button nextButton;

    TextView bubbleTextView, questionTextView, headlineText, subHeadlineText;
    EditText answerEditText;
    private long currentDirection;
    private AskingSingleton askingSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_vocabs);
        findElements();
        getCurrentLection();
        nextVocab();
        askingSingleton = AskingSingleton.getInstance();
        askingSingleton.resetData();
    }

    private void nextVocab() {
        try {
            setCurrVocab((Vocab) vocabIterator.next());
        } catch (NoSuchElementException e) {
            final Intent intent = new Intent(this,resultAfterAsking.class);
            startActivity(intent);
        }
        answerEditText.setText("");

    }

    private void setCurrVocab(Vocab vocab) {
        currVocab = vocab;
        if (currentDirection == 1l){
            questionTextView.setText(vocab.getForeign());
        }
        else{
            questionTextView.setText(vocab.getGerman());
        }

        subHeadlineText.setText("Lektion " + Integer.toString(vocab.getLection()));

    }

    private void findElements() {
        questionTextView = (TextView) findViewById(R.id.question);
        answerEditText = (EditText) findViewById(R.id.answer);
        subHeadlineText = (TextView) findViewById(R.id.subHeadLine);
        headlineText = (TextView) findViewById(R.id.headline);
        nextButton = (Button) findViewById(R.id.next);
        bubbleTextView = (TextView) findViewById(R.id.bubbleTextAsk);

    }

    private void getCurrentLection() {
        Bundle bundle = getIntent().getExtras();
        lectionDatabaseHelper = lectionDatabaseHelper.getInstance(this);
        currentLection = lectionDatabaseHelper.get(bundle.getLong("selectedLection") + 1l);
        vocabDatabaseHelper = vocabDatabaseHelper.getInstance(this);
        vocabList = vocabDatabaseHelper.getFromLection(currentLection.getNumber());
        vocabIterator = vocabList.listIterator(0);
        if(bundle.getBoolean("isRandom")){
            Collections.shuffle(vocabList);
        }
        currentDirection = bundle.getLong("selectedDirection");
        if (currentDirection == 1l){
            answerEditText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        }
        else{
            answerEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }

    public void onClickButtonNext(View v) {
        if (checkVocabCorrect()) {
            vocabIsCorrect();

        }
        else {
            vocabIsFalse();
        }
        nextVocab();
    }

    private void vocabIsFalse() {
        if(!AskingSingleton.wrongVocabs.contains(currVocab)){
            AskingSingleton.wrongVocabs.add(currVocab);
        }

    }

    private void vocabIsCorrect() {
        AskingSingleton.rightVocabs.add(currVocab);
        if(positiveFeedbackRelevant())
        setBubbleTextAndAnimate();
    }

    private boolean positiveFeedbackRelevant() {
        Random rand = new Random();
        int val = rand.nextInt(4) + 1;
        if (val == 1)
        {
            return true;  //25%
        }
        else
        {
            return false; //75%
        }
    }

    private boolean checkVocabCorrect() {
        String answer = answerEditText.getText().toString();
        if (answer.contentEquals(getAnswer())) {
            return true;
        } else {
            return false;
        }

    }

    private void setBubbleTextAndAnimate() {
        bubbleTextView.setText(getPositiveFeedbackText());
        bubbleTextView.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_fade_in);
        bubbleTextView.startAnimation(animation);

        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_fade_out);
                        bubbleTextView.startAnimation(animation);
                        bubbleTextView.setVisibility(View.INVISIBLE);
                    }
                });
            }
        };
        t.start();
    }

    private String getPositiveFeedbackText() {
        return("Gut gemacht!");
    }


    private String getAnswer() {
        if(currentDirection == 1l){
            return(currVocab.getGerman());
        }
        else{
            return(currVocab.getForeign());
        }
    }

    public void onClickBurgerMenu(View v){
        final Intent menuIntent = new Intent(this,MenuImuvo.class);
        startActivity(menuIntent);
    }

    public void onClickButtonSkip(View v){
        skipVocab();
    }

    private void skipVocab() {
        AskingSingleton.skippedVocabs.add(currVocab);
        nextVocab();

    }
}
