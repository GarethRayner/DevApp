package com.mycompany.devapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileOutputStream;

public class FreePress extends Activity {
    String filename="freePress.txt";
    FileOutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_press);
    }

    public void addFree(View view) {
        EditText titleForm = (EditText) findViewById(R.id.free_press_title_add);
        EditText contentForm = (EditText) findViewById(R.id.free_content_add);

        String entry = titleForm.getText().toString() + ":" + contentForm.getText().toString() + "\n";

        try {
            outputStream = openFileOutput(filename, MODE_APPEND);
            outputStream.write(entry.getBytes());
            outputStream.close();

            Intent intent = new Intent(this, FreeView.class);
            startActivity(intent);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
