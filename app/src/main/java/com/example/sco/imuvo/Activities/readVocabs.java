package com.example.sco.imuvo.Activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class readVocabs extends AppCompatActivity {

    VocabDatabaseHelper vocabDatabaseHelper;
    LectionDatabaseHelper lectionDatabaseHelper;
    ArrayList<Vocab> vocabList;
    Lection currentLection;
    ListIterator<?> vocabIterator;
    Vocab currVocab;
    Button nextButton, previousButton;
    TextView text1Text, text2Text, headlineText, subHeadlineText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_vocabs);
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
        text1Text.setText(vocab.getForeign());
        text2Text.setText(vocab.getGerman());
        subHeadlineText.setText("Lektion " + Integer.toString(vocab.getLection()));

    }

    private void findElements() {
        text1Text = (TextView) findViewById(R.id.text1);
        text2Text = (TextView) findViewById(R.id.text2);
        subHeadlineText = (TextView) findViewById(R.id.subHeadLine);
        headlineText = (TextView) findViewById(R.id.headline);
        nextButton = (Button) findViewById(R.id.next);
        previousButton = (Button) findViewById(R.id.previous);
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
        try {
            setCurrVocab((Vocab) vocabIterator.next());
        } catch (NoSuchElementException e) {
            Helper.makeShortToast(this, "Letzte Vokabel erreicht!");
        }

    }

    public void onClickButtonPrevious(View v) {
        try {
            setCurrVocab((Vocab) vocabIterator.previous());
        } catch (NoSuchElementException e) {
            Helper.makeShortToast(this, "Es gibt keine vorherige Vokabel.");
        }

    }
}
