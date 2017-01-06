package com.example.sco.imuvo.Activities;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sco.imuvo.CustomViews.CustomSpinnerMultiSelection;
import com.example.sco.imuvo.HelperClasses.Helper;
import com.example.sco.imuvo.HelperClasses.LectionDatabaseHelper;
import com.example.sco.imuvo.Model.AskingSingleton;
import com.example.sco.imuvo.Model.Lection;
import com.example.sco.imuvo.Model.LectionSelectionSingleton;
import com.example.sco.imuvo.Model.User;
import com.example.sco.imuvo.R;

import java.util.List;

public class vocabReadingSelection extends AppCompatActivity {
    public static final String ASKING = "asking";
    public static final String TEST = "test";
    public static final String READING = "read";
    public static final String READALOUD = "readAloud";
    Button startButton;
    Spinner lectionSpinner,directionSpinner;
    CustomSpinnerMultiSelection multipleLectionSpinner;
    TextView speechbubble, headline;
    String nextIntentType;
    CheckBox randomCheckBox;
    List<Integer> selectedLections = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_reading_selection);

        getElements();
        getInitialValuesFromIntent();
        loadDirectionSpinnerData();
        loadLectionSpinnerData();
        setSpeechbubble();
        setVisibility();
    }

    private void setVisibility() {
        if (nextIntentType.contentEquals(TEST)){
            multipleLectionSpinner.setVisibility(View.VISIBLE);
            lectionSpinner.setVisibility(View.GONE);
        }
        else{
            multipleLectionSpinner.setVisibility(View.GONE);
            lectionSpinner.setVisibility(View.VISIBLE);
        }
    }

    private void setSpeechbubble() {
        if(nextIntentType.contentEquals(READING)){
            speechbubble.setText("Super! Du möchtest Vokabeln lesen. Unter der Sprechlase hast Du verschiedene Einstellmöglichkeiten bevor du mit dem Lesen beginnst.");
            headline.setText(Helper.colorsString(this,"Vokabeln lesen", ContextCompat.getColor(this, R.color.colorMenuTextLeft),ContextCompat.getColor(this, R.color.colorMenuTextMiddle)));
        }
        else if(nextIntentType.contentEquals(ASKING)){
            speechbubble.setText("Du möchtest Vokabeln mit einer Abfrage üben. Unter der Sprechlase hast Du verschiedene Einstellmöglichkeiten bevor du mit dem Üben beginnst.");
            headline.setText(Helper.colorsString(this,"Vokabeln abfragen", ContextCompat.getColor(this, R.color.colorMenuTextLeft),ContextCompat.getColor(this, R.color.colorMenuTextMiddle)));
        }
        else if(nextIntentType.contentEquals(TEST)){
            speechbubble.setText("Du möchtest Vokabeln mit einem Vokabeltest üben. Unter der Sprechlase hast Du verschiedene Einstellmöglichkeiten bevor du mit dem Üben beginnst.");
            headline.setText(Helper.colorsString(this,"Vokabeln testen", ContextCompat.getColor(this, R.color.colorMenuTextLeft),ContextCompat.getColor(this, R.color.colorMenuTextMiddle)));
        }
        else if(nextIntentType.contentEquals(READALOUD)){
            speechbubble.setText("Du möchtest Dir Vokabeln anhören. Unter der Sprechlase hast Du verschiedene Einstellmöglichkeiten bevor du mit dem Vorlesen beginnst.");
            headline.setText(Helper.colorsString(this,"Vokabeln vorlesen", ContextCompat.getColor(this, R.color.colorMenuTextLeft),ContextCompat.getColor(this, R.color.colorMenuTextMiddle)));
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
        multipleLectionSpinner = (CustomSpinnerMultiSelection) findViewById(R.id.lectionSpinnerMultiple);

    }

    private void loadLectionSpinnerData() {
        LectionDatabaseHelper db = LectionDatabaseHelper.getInstance(this);
        db.Create();
        LectionDatabaseHelper lectionDatabaseHelper = LectionDatabaseHelper.getInstance(this);
        Cursor cursor = db.getAll();
        String[] from = {"number"};
        int[] to = {R.id.lectionSpinner};
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,R.layout.support_simple_spinner_dropdown_item,cursor,from,to,0);
        lectionSpinner.setAdapter(cursorAdapter);
        List<String> lables = db.getAllLabels();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.customspinner, lables);
        dataAdapter.setDropDownViewResource(R.layout.customspinner);
        lectionSpinner.setAdapter(dataAdapter);
        multipleLectionSpinner.setListener(new CustomSpinnerMultiSelection.OnMultipleItemsSelectedListener() {
            @Override
            public void selectedIndices(List<Integer> indices) {
                AskingSingleton.selectedLections = indices;
            }

            @Override
            public void selectedStrings(List<String> strings) {

            }
        });
        multipleLectionSpinner.setItems(lables);
        AskingSingleton.selectedLections = multipleLectionSpinner.getSelectedIndices();
    }

    private void loadDirectionSpinnerData() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.direction_array, R.layout.customspinner);
        adapter.setDropDownViewResource(R.layout.customspinner);
        directionSpinner.setAdapter(adapter);

    }
    public void onClickStartReading(View v){
        if(checkEverythingSelected() == true){
            final Intent nextIntent;
            if(nextIntentType.contentEquals(READING)){
                nextIntent = new Intent(this,readVocabs.class);
            }
            else if(nextIntentType.contentEquals(READALOUD)){
                nextIntent = new Intent(this,readVocabs.class);
            }
            else{
                nextIntent = new Intent(this,askVocabs.class);
            }

            Bundle bundle = new Bundle();
            if(nextIntentType.contentEquals(TEST)){
                bundle.putBoolean("isMultipleLection",true);
            }
            bundle.putString("type",nextIntentType);
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
        menuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(menuIntent);
        finish();
    }
}
