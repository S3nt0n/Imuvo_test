package com.example.sco.imuvo.Activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sco.imuvo.HelperClasses.Helper;
import com.example.sco.imuvo.HelperClasses.LectionDatabaseHelper;
import com.example.sco.imuvo.HelperClasses.VocabDatabaseHelper;
import com.example.sco.imuvo.Model.Lection;
import com.example.sco.imuvo.Model.Vocab;
import com.example.sco.imuvo.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class askVocabs extends AppCompatActivity {

    VocabDatabaseHelper vocabDatabaseHelper;
    LectionDatabaseHelper lectionDatabaseHelper;
    ArrayList<Vocab> vocabList;
    Lection currentLection;
    ListIterator<?> vocabIterator;
    Vocab currVocab;
    Button nextButton, previousButton;
    TextView questionTextView, text2Text, headlineText, subHeadlineText;
    EditText answerEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_vocabs);
        findElements();
        getCurrentLection();
        setVocabs();
    }

    private void setVocabs() {
        try {
            setCurrVocab((Vocab) vocabIterator.next());
        } catch (NoSuchElementException e) {
            Helper.makeShortToast(this, "Es gibt keine Vokabeln für diese Lektion.");
        }

    }

    private void setCurrVocab(Vocab vocab) {
        currVocab = vocab;
        questionTextView.setText(vocab.getForeign());

        subHeadlineText.setText("Lektion " + Integer.toString(vocab.getLection()));

    }

    private void findElements() {
        questionTextView = (TextView) findViewById(R.id.question);
        answerEditText = (EditText) findViewById(R.id.answer);
        subHeadlineText = (TextView) findViewById(R.id.subHeadLine);
        headlineText = (TextView) findViewById(R.id.headline);
        nextButton = (Button) findViewById(R.id.next);

    }

    private void getCurrentLection() {
        Bundle bundle = getIntent().getExtras();
        lectionDatabaseHelper = lectionDatabaseHelper.getInstance(this);
        currentLection = lectionDatabaseHelper.get(bundle.getLong("selectedLection") + 1l);
        Helper.makeShortToast(this, Integer.toString(currentLection.getNumber()));
        vocabDatabaseHelper = vocabDatabaseHelper.getInstance(this);
        vocabList = vocabDatabaseHelper.getFromLection(currentLection.getNumber());
        vocabIterator = vocabList.listIterator(0);

    }

    public void onClickButtonNext(View v) {
        if (checkVocabRight()) {
            Helper.makeShortToast(this, "Vokabel war richtig!");
            try {
                setCurrVocab((Vocab) vocabIterator.next());
            } catch (NoSuchElementException e) {
                Helper.makeShortToast(this, "Letzte Vokabel erreicht!");
            }
        }
        else{
            Helper.makeShortToast(this,"Leider falsch");
        }

    }

    private boolean checkVocabRight() {
        String answer = answerEditText.getText().toString();
        if (answer.contentEquals(currVocab.getGerman())) {
            return true;
        } else {
            return false;
        }
    }
}
