package com.example.sco.imuvo.Activities;

import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.sco.imuvo.HelperClasses.VocabDatabaseHelper;
import com.example.sco.imuvo.Model.Vocab;
import com.example.sco.imuvo.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class VocabList extends ListActivity {

    ListView vocabListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_list);

        popoulateListView();
    }

    private void popoulateListView() {
        vocabListView = (ListView) findViewById(R.id.list);
        VocabDatabaseHelper db = VocabDatabaseHelper.getInstance(this);
        Cursor cursor = db.getAll();
        ArrayList<String> vocabList = new ArrayList<>();
        if (cursor.getCount() == 0){

        }
        else{
            while(cursor.moveToNext()){
                vocabList.add(cursor.getString(2));
            }
            ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,vocabList);
            vocabListView.setAdapter(listAdapter);
        }
    }
}
