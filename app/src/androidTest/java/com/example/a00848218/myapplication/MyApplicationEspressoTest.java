package com.example.a00848218.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.EspressoKey;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;
import android.widget.Toast;
import android.content.Context;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MyApplicationEspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);
    @Test
    public void ensureTextChangesWork() {
        // Type text and then press the button.
        onView(withId(R.id.edit_message))
                .perform(typeText("HELLO"), closeSoftKeyboard());
        onView(withId(R.id.send_message)).perform(click());
        // Check that the text was changed.
        onView(withId(R.id.test_message)).check(matches(withText("HELLO")));
        //onView(withContentDescription("Navigate up")).perform(click());
        Cursor dbCursor = null;
        StringBuilder builder = new StringBuilder();

        String dbName = "tempDB.db";
        SQLiteDatabase db = openOrCreateDatabase(dbName, 0, null);
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
