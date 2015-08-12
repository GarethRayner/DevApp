package com.mycompany.devapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class NewArtRead extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_art_read);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        TextView titleBox = (TextView) findViewById(R.id.title);
        titleBox.setText(title);
    }
}
