package com.example.sco.imuvo.Activities;

import android.app.ListActivity;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.sco.imuvo.HelperClasses.VocabCursorAdapter;
import com.example.sco.imuvo.HelperClasses.VocabDatabaseHelper;
import com.example.sco.imuvo.Model.Vocab;
import com.example.sco.imuvo.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class VocabList extends AppCompatActivity {
    int lectionNo = 0;
    ListView vocabListView;
    FloatingActionButton addNewVocab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_list);
        popoulateListView();
    }

    private void popoulateListView() {
        lectionNo = getLectionFromIntent();
        vocabListView = (ListView) findViewById(R.id.list);
        VocabDatabaseHelper db = VocabDatabaseHelper.getInstance(this);
        Cursor cursor = db.getAll(lectionNo);
        VocabCursorAdapter vocabCursorAdapter = new VocabCursorAdapter(this,cursor,0);
        vocabListView.setAdapter(vocabCursorAdapter);
    }

    private int getLectionFromIntent() {
        Bundle bundle = getIntent().getExtras();
        return(bundle.getInt("lectionNumber"));
    }

    public void addVocab(View v){
        //TODO
    }


}
