package com.example.bhavneet.trial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cab_service";

    private static final int DATABASE_VERSION = 1;

    //table names
    private static final String TABLE_USER = "user";
    private static final String TABLE_DRIVER = "driver";
    private static final String TABLE_RIDES = "rides";

    //common column name
    private static final String KEY_PHONE_NUMBER = "phone_number";

    //user table column names
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_NAME = "user_name";

    //driver table column names
    private static final String KEY_DRIVER_ID = "driver_id";
    private static final String KEY_DRIVER_NAME = "driver_name";

    //rides table column names
    private static final String KEY_START_LOC = "start_location";
    private static final String KEY_END_LOC = "end_location";
    private static final String KEY_TIME = "journey_time";
    private static final String KEY_STATUS = "status";

    //user table create statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" + KEY_USER_ID +
            "STRING PRIMARY KEY," + KEY_USER_NAME + "STRING," + KEY_PHONE_NUMBER +"NUMBER" + ")";

    //driver table create statement
    private static final String CREATE_TABLE_DRIVER = "CREATE TABLE " + TABLE_DRIVER + "(" + KEY_DRIVER_ID +
            "STRING PRIMARY KEY," + KEY_DRIVER_NAME + "STRING," + KEY_PHONE_NUMBER +"NUMBER" + ")";

    //user table create statement
    private static final String CREATE_TABLE_RIDES = "CREATE TABLE " + TABLE_RIDES + "(" + KEY_USER_ID +
            "STRING NOT NULL ," + KEY_DRIVER_ID + "STRING NOT NULL," + KEY_START_LOC +"STRING" + KEY_END_LOC+ "STRING"+
            KEY_STATUS + "STRING" + KEY_TIME + "DATETIME,"+ "PRIMARY KEY" + "(" + KEY_USER_ID +","+ KEY_DRIVER_ID+")" + ")";

    public DBHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_DRIVER);
        db.execSQL(CREATE_TABLE_RIDES);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRIVER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RIDES);

        onCreate(db);

    }



}
