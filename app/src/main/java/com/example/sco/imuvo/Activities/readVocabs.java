package com.example.sco.imuvo.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sco.imuvo.HelperClasses.Helper;
import com.example.sco.imuvo.HelperClasses.LectionDatabaseHelper;
import com.example.sco.imuvo.HelperClasses.VocabDatabaseHelper;
import com.example.sco.imuvo.HelperClasses.WebServiceHelper;
import com.example.sco.imuvo.Model.Lection;
import com.example.sco.imuvo.Model.Vocab;
import com.example.sco.imuvo.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.SpeechDataEvent;
import com.voicerss.tts.SpeechDataEventListener;
import com.voicerss.tts.SpeechErrorEvent;
import com.voicerss.tts.SpeechErrorEventListener;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    ImageButton earButton;
    TextView text1Text, text2Text, headlineText, subHeadlineText;
    private long currentDirection;
    MediaPlayer mp = null;
    private String nextIntentType;
    private ImageView vocabPictureImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_vocabs);
        getInitialValuesFromIntent();
        findElements();
        getCurrentLection();
        setVocabs();
    }
    private void getInitialValuesFromIntent(){
        Bundle bundle = getIntent().getExtras();
        nextIntentType = bundle.getString("type");

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
        if(currentDirection == 0l) {
            text1Text.setText(vocab.getForeign());
            text2Text.setText(vocab.getGerman());
        }
        else{
            text2Text.setText(vocab.getForeign());
            text1Text.setText(vocab.getGerman());
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
        text1Text = (TextView) findViewById(R.id.text1);
        text2Text = (TextView) findViewById(R.id.text2);
        subHeadlineText = (TextView) findViewById(R.id.subHeadLine);
        headlineText = (TextView) findViewById(R.id.headline);
        nextButton = (Button) findViewById(R.id.next);
        previousButton = (Button) findViewById(R.id.previous);
        earButton = (ImageButton) findViewById(R.id.earButton);
        if(nextIntentType.contentEquals("read")){
            earButton.setVisibility(View.INVISIBLE);
        }
        else
        {
            earButton.setVisibility(View.VISIBLE);
        }
        vocabPictureImageView = (ImageView) findViewById(R.id.vocabImage);
    }

    private void getCurrentLection() {
        Bundle bundle = getIntent().getExtras();
        lectionDatabaseHelper = lectionDatabaseHelper.getInstance(this);
        currentLection = lectionDatabaseHelper.get(bundle.getLong("selectedLection") + 1l);
        vocabDatabaseHelper = vocabDatabaseHelper.getInstance(this);
        vocabList = vocabDatabaseHelper.getFromLection(currentLection.getNumber());
        if(bundle.getBoolean("isRandom")){
            Collections.shuffle(vocabList);
        }
        vocabIterator = vocabList.listIterator(0);
        currentDirection = bundle.getLong("selectedDirection");
        if(nextIntentType.contentEquals("read")){
            headlineText.setText(Helper.colorsString(this,"Vokabeln lesen", ContextCompat.getColor(this, R.color.colorMenuTextLeft),ContextCompat.getColor(this, R.color.colorMenuTextMiddle)));
        }
        else{
            headlineText.setText(Helper.colorsString(this,"Vokabeln vorlesen", ContextCompat.getColor(this, R.color.colorMenuTextLeft),ContextCompat.getColor(this, R.color.colorMenuTextMiddle)));
        }

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

    public void speakAloud(View v) {
            try {
                String fileName = getCacheDir() + "/voice.mp3";

                File file = new File(fileName);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(currVocab.getSpeech());
                fos.close();

                try {
                   MediaPlayer mediaPlayer = new MediaPlayer();

                    FileInputStream MyFile = new FileInputStream(file);
                    mediaPlayer.setDataSource(MyFile.getFD());

                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException ex) {
                    String s = ex.toString();
                    ex.printStackTrace();
                }
            } catch (Exception ex) {
                Log.i("Fehler3", ex.toString());

            }
    }



    public void onClickBurgerMenu(View v){
        final Intent menuIntent = new Intent(this,MenuImuvo.class);
        menuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(menuIntent);
        finish();
    }



}
