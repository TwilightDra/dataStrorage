package com.example.a00848218.myapplication;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;
import android.database.Cursor;
/**
 * Created by A00848218 on 2017-09-19.
 */

public class DBTestActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String dbName = "tempDB.db";
        SQLiteDatabase db = openOrCreateDatabase(dbName, 0, null);
        if (!db.isOpen()) {
            Toast.makeText(this, "Db Open Error", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        db.beginTransaction();
        try {

            db.execSQL("CREATE TABLE IF NOT EXISTS "
                    + "PhoneBook"
                    + " (name VARCHAR, phoneNumber VARCHAR);");

            db.execSQL("INSERT INTO PhoneBook (name , phoneNumber) VALUES ('Tom','6045555555');");
            db.setTransactionSuccessful();
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        Cursor dbCursor = null;
        StringBuilder builder = new StringBuilder();

        if (!db.isOpen()) {
            Toast.makeText(this, "Db Open Error", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        //Now retrieve and display the record
        try {
            dbCursor = db.query("PhoneBook", null, null, null, null, null, null);

            int nameCol = dbCursor.getColumnIndex("name");
            int numberCol = dbCursor.getColumnIndex("phoneNumber");

            if (dbCursor != null) {
                dbCursor.moveToFirst();
                if (dbCursor.getCount() != 0) {
                    int i = 0;
                    do {
                        i++;

                        String name = dbCursor.getString(nameCol);
                        String number = dbCursor.getString(numberCol);
                        Toast.makeText(this, name + "  " + number, Toast.LENGTH_SHORT).show();

                    } while (dbCursor.moveToNext());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
