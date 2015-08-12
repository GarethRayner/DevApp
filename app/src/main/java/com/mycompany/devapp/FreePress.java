package com.mycompany.devapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        boolean completed = true;
        EditText titleForm = (EditText) findViewById(R.id.free_press_title_add);
        EditText contentForm = (EditText) findViewById(R.id.free_content_add);

        if(titleForm.getText().toString().compareTo("") == 0) {
            completed = false;
        } else if(contentForm.getText().toString().compareTo("") == 0) {
            completed = false;
        }
        String entry = titleForm.getText().toString() + ":" + contentForm.getText().toString() + "\n";

        if(completed) {
            try {
                outputStream = openFileOutput(filename, MODE_APPEND);
                outputStream.write(entry.getBytes());
                outputStream.close();

                Intent intent = new Intent(this, FreeView.class);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast toast = Toast.makeText(this, "Please complete both fields.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
