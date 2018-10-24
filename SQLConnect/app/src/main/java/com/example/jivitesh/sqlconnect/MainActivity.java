package com.example.jivitesh.sqlconnect;

        import android.content.ContentValues;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Message;
        import android.support.v4.app.NotificationCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Field names
    private EditText name;
    private EditText phone;
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDb;
    private EditText deleteByName;
    private EditText updataById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        name = (EditText)findViewById(R.id.editText);
        phone = (EditText)findViewById(R.id.editText2);
        deleteByName = (EditText)findViewById(R.id.editText3);
        updataById = (EditText)findViewById(R.id.editText4);

        // Creating a database
        dbHelper = new DBHelper(MainActivity.this, "UserDB",null, 1 );
        sqLiteDb = dbHelper.getWritableDatabase();
    }

    // function for inserting data
    public void insertData(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name.getText().toString());
        contentValues.put("phone",phone.getText().toString());

        long id = sqLiteDb.insert("user", null, contentValues);

        Toast.makeText(MainActivity.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
        name.setText("");
        phone.setText("");
    }

    //function for reading data
    public void readData(View view) {
        Cursor cursor = sqLiteDb.query("user", null, null,null,null,null,null);
        while (cursor.moveToNext()){
            Toast.makeText(MainActivity.this, cursor.getString(cursor.getColumnIndex("name")),Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, cursor.getString(cursor.getColumnIndex("phone")),Toast.LENGTH_SHORT).show();
        }
    }

    // Function for deleting data
    public void deleteData(View view) {
        int a= sqLiteDb.delete("user", name + "=" + deleteByName.getText().toString(),null);
        if(a<=0)
        {
            Toast.makeText(MainActivity.this, "Unsucessful",Toast.LENGTH_SHORT).show();
            deleteByName.setText("");
        }
        else
        {
            Toast.makeText(MainActivity.this, "Sucessfull",Toast.LENGTH_SHORT).show();
            deleteByName.setText("");
        }
    }

    public void updateData(View view) {

        ContentValues cv = new ContentValues();
        cv.put("name","Bob"); //These Fields should be your String values of actual column names
        cv.put("phone","123");

        sqLiteDb.update("user", cv, "id="+ updataById.getText().toString() , null);
    }
}