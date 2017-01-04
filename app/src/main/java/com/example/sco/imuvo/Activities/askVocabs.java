package com.example.sco.imuvo.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sco.imuvo.HelperClasses.Helper;
import com.example.sco.imuvo.HelperClasses.LectionDatabaseHelper;
import com.example.sco.imuvo.HelperClasses.VocabDatabaseHelper;
import com.example.sco.imuvo.Model.AskingSingleton;
import com.example.sco.imuvo.Model.Lection;
import com.example.sco.imuvo.Model.Vocab;
import com.example.sco.imuvo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;



public class askVocabs extends AppCompatActivity {

    public static final String ASKWRONGVOCABSAGAIN = "askWrongVocabsAgain" ;
    public static final String ISRANDOM = "isRandom";
    VocabDatabaseHelper vocabDatabaseHelper;
    LectionDatabaseHelper lectionDatabaseHelper;
    ArrayList<Vocab> vocabList;
    Lection currentLection;
    ListIterator<?> vocabIterator;
    Vocab currVocab;
    Button nextButton, skipButton;

    TextView bubbleTextView, questionTextView, headlineText, subHeadlineText;
    EditText answerEditText;
    ImageView vocabPictureImageView;
    private long currentDirection;
    private AskingSingleton askingSingleton;
    private boolean doNotCheckAnswer = false;

    private void vocabIsFalse() {
        if (getIntent().getExtras().getBoolean("isMultipleLection")){
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before

            dialog.setContentView(R.layout.activity_custom_dialog);

            Button repeatButton = (Button) dialog.findViewById(R.id.repeat);
            TextView tv = (TextView) dialog.findViewById(R.id.textView);
            tv.setText("Leider noch nicht ganz richtig.\n\n" + "Lösung: " + getAnswer());
            repeatButton.setVisibility(View.GONE);
            Button showSolutionButton = (Button) dialog.findViewById(R.id.solution);
            showSolutionButton.setText("Ok");
            showSolutionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSolution();
                    doNotCheckAnswer = true;
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        else{
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before

            dialog.setContentView(R.layout.activity_custom_dialog);

            Button repeatButton = (Button) dialog.findViewById(R.id.repeat);
            repeatButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    repeatVocab();
                    dialog.dismiss();
                }
            });
            Button showSolutionButton = (Button) dialog.findViewById(R.id.solution);
            showSolutionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSolution();
                    doNotCheckAnswer = true;
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

    }

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
        nextButton.setText("Prüfen");

    }

    private void setCurrVocab(Vocab vocab) {
        currVocab = vocab;
        if (currentDirection == 1l){
            questionTextView.setText(vocab.getForeign());
        }
        else{
            questionTextView.setText(vocab.getGerman());
        }
        char firstChar = getAnswer().charAt(0);
        if (Character.isUpperCase(firstChar)){
            answerEditText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        }
        else{
            answerEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        subHeadlineText.setText("Lektion " + Integer.toString(vocab.getLection()));
        if(currVocab.getPicture() != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(currVocab.getPicture(), 0, currVocab.getPicture().length);
            vocabPictureImageView.setImageBitmap(bitmap);
            vocabPictureImageView.setVisibility(View.VISIBLE);
        }
        else{
            vocabPictureImageView.setVisibility(View.GONE);
        }

    }

    private void findElements() {
        questionTextView = (TextView) findViewById(R.id.text2);
        answerEditText = (EditText) findViewById(R.id.answer);
        subHeadlineText = (TextView) findViewById(R.id.subHeadLine);
        headlineText = (TextView) findViewById(R.id.headline);
        nextButton = (Button) findViewById(R.id.next);
        bubbleTextView = (TextView) findViewById(R.id.bubbleTextAsk);
        skipButton = (Button) findViewById(R.id.skip);
        vocabPictureImageView = (ImageView) findViewById(R.id.vocabImage);
    }

    private void getCurrentLection() {
        Bundle bundle = getIntent().getExtras();
        lectionDatabaseHelper = lectionDatabaseHelper.getInstance(this);

        currentLection = lectionDatabaseHelper.get(bundle.getLong("selectedLection") + 1l);
        vocabDatabaseHelper = vocabDatabaseHelper.getInstance(this);
        if(bundle.getBoolean(ASKWRONGVOCABSAGAIN)){
            vocabList = AskingSingleton.wrongVocabs;
        }
        else{
            if(bundle.getBoolean("isMultipleLection")){
                List<Integer> indices = AskingSingleton.selectedLections;
                vocabList = vocabDatabaseHelper.getFromMultipleLection(indices);
                subHeadlineText.setVisibility(View.INVISIBLE);
                headlineText.setText("Vokabeltest");
                skipButton.setVisibility(View.INVISIBLE);

            }
            else{
                vocabList = vocabDatabaseHelper.getFromLection(currentLection.getNumber());
                headlineText.setText(Helper.colorsString(this,"Vokabeln abfragen", ContextCompat.getColor(this, R.color.colorMenuTextLeft),ContextCompat.getColor(this, R.color.colorMenuTextMiddle)));
            }

        }

        vocabIterator = vocabList.listIterator(0);
        if(bundle.getBoolean(ISRANDOM)){
            Collections.shuffle(vocabList);
        }
        currentDirection = bundle.getLong("selectedDirection");


    }

    public void onClickButtonNext(View v) {
        if(doNotCheckAnswer) {
            doNotCheckAnswer = false;
            answerEditText.setEnabled(true);
            nextVocab();

        }
        else{
            if (checkVocabCorrect()) {
                vocabIsCorrect();
            }
            else {
                vocabIsFalse();
            }
        }
    }

    private void showSolution() {
        answerEditText.setText(getAnswer());
        answerEditText.setEnabled(false);
        AskingSingleton.wrongVocabs.add(currVocab);
        nextButton.setText("Nächste");
    }

    private void repeatVocab() {
        answerEditText.setText("");
    }

    private void vocabIsCorrect() {
        AskingSingleton.rightVocabs.add(currVocab);
        if(positiveFeedbackRelevant())
        setBubbleTextAndAnimate();
        nextVocab();
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
        String[] appreciation = new String[]{"Gut gemacht!", "Sehr gut!", "Weiter so!", "Du wirst immer besser!"};
        return(appreciation[new Random().nextInt(appreciation.length)]);
    }

    private String getAnswer() {
        if(currentDirection == 1l){
            return(currVocab.getGerman().trim());
        }
        else{
            return(currVocab.getForeign().trim());
        }
    }

    public void onClickBurgerMenu(View v){
        final Intent menuIntent = new Intent(this,MenuImuvo.class);
        menuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(menuIntent);
        finish();
    }

    public void onClickButtonSkip(View v){
        skipVocab();
    }

    private void skipVocab() {
        AskingSingleton.wrongVocabs.add(currVocab);
        nextVocab();

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
}
