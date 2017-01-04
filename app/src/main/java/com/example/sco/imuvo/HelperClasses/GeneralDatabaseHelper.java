package com.example.sco.imuvo.HelperClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sco.imuvo.Model.User;

public class GeneralDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "user_imuvo";
    private static final int DB_VERSION = 1;


    private static final String USER_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS user_imuvo "
            + "(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, username TEXT, password TEXT)";

    private static final String VOCAB_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS vocabs_imuvo " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, german TEXT, translation TEXT, speech BLOB, lection INTEGER, picture BLOB)";

    private static final String LECTION_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS lection_imuvo " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, number INTEGER, language TEXT)";
    private static final String USER_DROP_Table = "DROP TABLE IF EXISTS user_imuvo";

    private String DB_PATH = null;
    private static GeneralDatabaseHelper instance;
    private SQLiteDatabase db;

    private GeneralDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
    }

    public static GeneralDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new GeneralDatabaseHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            db.execSQL(USER_CREATE_TABLE);
            db.execSQL(VOCAB_CREATE_TABLE);
            db.execSQL(LECTION_CREATE_TABLE);


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

    public void Create() {
        open();
        onCreate(db);
    }

    public boolean checkDataBase() {

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

    public void dropDatabase(Context context){
        context.deleteDatabase(DB_NAME);

    }
}
