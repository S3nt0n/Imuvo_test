package com.example.sco.imuvo.HelperClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TabLayout;
import android.util.Log;

import com.example.sco.imuvo.Model.User;
import com.example.sco.imuvo.Model.Vocab;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by sco on 05.12.2016.
 */

public class VocabDatabaseHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "user_imuvo";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "vocabs_imuvo";
    private static final String[] USER_COLUMNS = { "_id", "german", "translation", "lection", "speech" };
    private static final String USER_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS vocabs_imuvo "
            + "(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, german TEXT, translation TEXT, lection INTEGER, speech BLOB)";
    private static final String USER_DROP_Table = "DROP TABLE IF EXISTS vocabs_imuvo";

    private String DB_PATH = null;
    private static VocabDatabaseHelper instance;
    private SQLiteDatabase db;

    private VocabDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
    }

    public static VocabDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new VocabDatabaseHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            db.execSQL(USER_CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(USER_DROP_Table);
        onCreate(db);
    }

    private void open() {
        if (db == null)
            db = getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    public Vocab get(long id) {
        open();
        Vocab vocab = null;
        Cursor cursor = db.query(TABLE_NAME, USER_COLUMNS, "_id = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor.moveToFirst()) {
            vocab = new Vocab(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3),cursor.getBlob(4));
        }
        cursor.close();
        return vocab;
    }

    public ArrayList<Vocab> getFromLection(Integer lectionNo) {
        open();
        ArrayList<Vocab> vocabs = new ArrayList<Vocab>();
        Vocab vocab = null;
        //Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        Cursor cursor = db.query(TABLE_NAME, USER_COLUMNS, "lection=?",
                new String[] { Integer.toString(lectionNo)}, null, null, null);


        if (cursor.moveToFirst()) {
            do {
                Log.i("Vocab",cursor.getString(1));
                vocab = new Vocab(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3),cursor.getBlob(4));
                vocabs.add(vocab);
            } while (cursor.moveToNext());

        }
        cursor.close();
        return vocabs;
    }



    public Cursor getAll() {
        open();
        Cursor cursor = db.query(TABLE_NAME, USER_COLUMNS, null,
               null, null, null, null);
        return cursor;
    }
    public Cursor getAll(int lectionNo) {
        open();
        Cursor cursor = db.query(TABLE_NAME, USER_COLUMNS, "lection=?",
                new String[] { Integer.toString(lectionNo)}, null, null, null);
        return cursor;
    }

    public long insert(Vocab vocab) {
        open();
        ContentValues values = new ContentValues();
        values.put("german",vocab.getGerman());
        values.put("translation",vocab.getForeign());
        values.put("speech",vocab.getSpeech());
        values.put("lection",vocab.getLection());
        long id = db.insert(TABLE_NAME, null, values);

        return id;
    }

    public int update(Vocab vocab) {
        open();
        ContentValues values = new ContentValues();
        values.put("german",vocab.getGerman());
        values.put("translation",vocab.getForeign());
        values.put("speech",vocab.getSpeech());
        values.put("lection",vocab.getLection());
        int rows = db.update(TABLE_NAME, values, "_id = ?", new String[] { String.valueOf(vocab.getSqlID()) });
        return rows;
    }

    public int delete(long id) {
        open();
        return db.delete(TABLE_NAME, "_id = ?", new String[]{String.valueOf(id)});
    }

    public void Create() {
        open();
        onCreate(db);
    }

    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            // database does't exist yet.
        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }


}
