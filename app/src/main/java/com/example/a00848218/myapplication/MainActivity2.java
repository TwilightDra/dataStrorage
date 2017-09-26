package com.example.a00848218.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity2 extends AppCompatActivity {
        TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text=(TextView) findViewById(R.id.test_message);

        text.setText(getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE));
    }
}
