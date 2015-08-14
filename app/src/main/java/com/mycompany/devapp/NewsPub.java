package com.mycompany.devapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class NewsPub extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_pub);

        String file1name = "newsPub.txt";
        String file2name = "newsPubUpdated.txt";
        File file1 = new File(file1name);
        File file2 = new File(file2name);

        if(!file1.exists()) {
            String content = "News 24:A news company priding itself on news 24 hours a day.\nNews 12:A news company that only operates for 12 hours a day.\nAvian Articles:A newsgroup that focuses their news solely on avian activity and society.";
            try {
                FileOutputStream fileOutput = openFileOutput(file1name, MODE_PRIVATE);
                fileOutput.write(content.getBytes());
            } catch (Exception e) {
                Log.e("Error", "Error writing file 1.");
            }
        }

        if(!file2.exists()) {
            String content = "News 24:A news company priding itself on news 24 hours a day.\nNews 12:A news company that only operates for 12 hours a day.\nAvian Articles:A newsgroup that focuses their news solely on avian activity and society.\nDuck Weekly:This magazine follows quite closely the news coming from the local duck communities.\nGeese Weekly:A rival magazine to Duck Weekly yet owned by the same company. Focusing heavily on goose activity.";
            try {
                FileOutputStream fileOutput = openFileOutput(file2name, MODE_PRIVATE);
                fileOutput.write(content.getBytes());
            } catch (Exception e) {
                Log.e("Error", "Error writing file 2.");
            }
        }

        String[] companies = new String[3];
        Scanner newsPubs = null;

        try {
            FileInputStream file = openFileInput(file1name);
            newsPubs = new Scanner(file);
        } catch(Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 3; i++) {
            if(newsPubs.hasNextLine()) {
                String temp = newsPubs.nextLine();
                String[] tempArr = temp.split(":");
                companies[i] = tempArr[0];
            }
        }

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.new_article_display, companies));
    }
}
