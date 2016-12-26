package com.example.sco.imuvo.Activities;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sco.imuvo.HelperClasses.Helper;
import com.example.sco.imuvo.HelperClasses.LectionDatabaseHelper;
import com.example.sco.imuvo.Model.Lection;
import com.example.sco.imuvo.Model.User;
import com.example.sco.imuvo.R;

import java.util.List;

public class vocabReadingSelection extends AppCompatActivity {
    Button startButton;
    Spinner lectionSpinner,directionSpinner;
    TextView speechbubble, headline;
    String nextIntentType;
    CheckBox randomCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_reading_selection);

        getElements();
        getInitialValuesFromIntent();
        loadDirectionSpinnerData();
        loadLectionSpinnerData();
        setSpeechbubble();
    }

    private void setSpeechbubble() {
        if(nextIntentType.contentEquals("read")){
            speechbubble.setText("Super! Du möchtest Vokabeln lesen. Unter der Sprechlase hast Du verschiedene Einstellmöglichkeiten bevor du mit dem Lesen beginnst.");
            headline.setText(Helper.colorsString(this,"Vokabeln lesen", ContextCompat.getColor(this, R.color.colorMenuTextLeft),ContextCompat.getColor(this, R.color.colorMenuTextMiddle)));
        }
        else{
            speechbubble.setText("Du möchtest Vokabeln mit einer Abfrage üben. Unter der Sprechlase hast Du verschiedene Einstellmöglichkeiten bevor du mit dem Üben beginnst.");

            headline.setText(Helper.colorsString(this,"Vokabeln abfragen", ContextCompat.getColor(this, R.color.colorMenuTextLeft),ContextCompat.getColor(this, R.color.colorMenuTextMiddle)));
        }


    }


    private void getInitialValuesFromIntent(){
        Bundle bundle = getIntent().getExtras();
        nextIntentType = bundle.getString("type");

    }
    private void getElements() {
        lectionSpinner = (Spinner) findViewById(R.id.lectionSpinner);
        directionSpinner = (Spinner) findViewById(R.id.directionSpinner);
        startButton = (Button) findViewById(R.id.start);
        speechbubble = (TextView) findViewById(R.id.speechbubble);
        headline = (TextView) findViewById(R.id.headline);
        randomCheckBox = (CheckBox) findViewById(R.id.random);
    }

    private void loadLectionSpinnerData() {
        LectionDatabaseHelper db = LectionDatabaseHelper.getInstance(this);
        db.Create();
        LectionDatabaseHelper lectionDatabaseHelper = LectionDatabaseHelper.getInstance(this);
        //Cursor cursor = db.getAll();
        //String[] from = {"number"};
        //int[] to = {R.id.lectionSpinner};
        //SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,R.layout.support_simple_spinner_dropdown_item,cursor,from,to,0);
        //lectionSpinner.setAdapter(cursorAdapter);
        List<String> lables = db.getAllLabels();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.customspinner, lables);
        dataAdapter.setDropDownViewResource(R.layout.customspinner);
        lectionSpinner.setAdapter(dataAdapter);
    }

    private void loadDirectionSpinnerData() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.direction_array, R.layout.customspinner);
        adapter.setDropDownViewResource(R.layout.customspinner);
        directionSpinner.setAdapter(adapter);

    }
    public void onClickStartReading(View v){
        if(checkEverythingSelected() == true){
            final Intent nextIntent;
            if(nextIntentType.contentEquals("read")){
                nextIntent = new Intent(this,readVocabs.class);
            }
            else{
                nextIntent = new Intent(this,askVocabs.class);
            }

            Bundle bundle = new Bundle();
            bundle.putLong("selectedLection",lectionSpinner.getSelectedItemId());
            bundle.putLong("selectedDirection",directionSpinner.getSelectedItemId());
            bundle.putBoolean("isRandom",randomCheckBox.isChecked());
            nextIntent.putExtras(bundle);
            startActivity(nextIntent);


        }
        else {

        }
    }

    private boolean checkEverythingSelected() {
        return true;
    }

    public void onClickBurgerMenu(View v){
        final Intent menuIntent = new Intent(this,MenuImuvo.class);
        startActivity(menuIntent);
    }
}
