package com.example.sco.imuvo.HelperClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sco.imuvo.Model.User;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "user_imuvo";
    private static final int DB_VERSION = 1;

    private static final String[] USER_COLUMNS = { "_id", "username", "password" };
    private static final String USER_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS user_imuvo "
            + "(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, username TEXT, password TEXT)";
    private static final String USER_DROP_Table = "DROP TABLE IF EXISTS user_imuvo";

    private String DB_PATH = null;
    private static UserDatabaseHelper instance;
    private SQLiteDatabase db;

    private UserDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
    }

    public static UserDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new UserDatabaseHelper(context);
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

    public User get(long id) {
        open();
        User user = null;
        Cursor cursor = db.query("user_imuvo", USER_COLUMNS, "_id = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor.moveToFirst()) {
            user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
        cursor.close();
        return user;
    }

    public User get(String name) {
        open();
        User user = null;
        Cursor cursor = db.query("user_imuvo", USER_COLUMNS, "username = ?",
                new String[] { name }, null, null, null);
        if (cursor.moveToFirst()) {
            user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
        cursor.close();
        return user;
    }

    public Cursor getAll() {
        open();
        return db.query("user_imuvo", USER_COLUMNS, null, null, null, null, "username");
    }

    public long insert(User user) {
        open();
        db.execSQL(USER_CREATE_TABLE);
        ContentValues values = new ContentValues();
        values.put("username", user.getUserName());
        values.put("password", user.getPassword());
        long id = db.insert("user_imuvo", null, values);
        return id;
    }

    public int update(User user) {
        open();
        ContentValues values = new ContentValues();
        values.put("username", user.getUserName());
        values.put("password", user.getPassword());
        int rows = db.update("user_imuvo", values, "_id = ?", new String[] { String.valueOf(user.getId()) });
        return rows;
    }

    public int delete(long id) {
        open();
        return db.delete("user", "_id = ?", new String[]{String.valueOf(id)});
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
