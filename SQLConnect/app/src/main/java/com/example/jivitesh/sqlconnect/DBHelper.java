package com.example.jivitesh.sqlconnect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_ALTER_TABLE_1 = ""; // Can write  Alter, Drop,  truncate ETC
    private static final String DATABASE_DROP_TABLE_1 = ""; // Can write  Alter, Drop,
    private static final String DATABASE_TRUNCATE_TABLE_1 = ""; // Can write  Alter, Drop,

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name, factory, version);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE user (id integer primary key, name text, phone text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // You can update tables in the subsequent requests
        if (oldVersion < 2) {
            db.execSQL(DATABASE_ALTER_TABLE_1);
        }
        if (oldVersion < 3) {
            db.execSQL(DATABASE_DROP_TABLE_1);
        }
    }
}
