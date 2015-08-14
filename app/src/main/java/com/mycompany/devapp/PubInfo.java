package com.mycompany.devapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class PubInfo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pub_info);

        Intent intent = getIntent();
        String selected = intent.getStringExtra("contents");

        TextView title = (TextView) findViewById(R.id.pub_title);
        TextView content = (TextView) findViewById(R.id.pub_content);

        String[] contents = selected.split(":");

        title.setText(contents[0]);
        content.setText(contents[1]);
    }
}
