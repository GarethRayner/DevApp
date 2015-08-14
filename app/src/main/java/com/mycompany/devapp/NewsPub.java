package com.mycompany.devapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class NewsPub extends Activity {
    SwipeRefreshLayout layout;
    String file2name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_pub);

        layout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("Refresh", "Refresh action triggered...");
                refreshList();
            }
        });

        AdapterView.OnItemClickListener pubClickedHandler = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView title = (TextView) view.findViewById(R.id.news_pub_title);
                TextView contents = (TextView) view.findViewById(R.id.news_pub_content);

                String content = title.getText().toString() + ":" + contents.getText().toString();

                Intent intent  = new Intent(view.getContext(), PubInfo.class);
                intent.putExtra("contents", content);
                startActivity(intent);
            }
        };

        ((ListView) findViewById(R.id.list)).setOnItemClickListener(pubClickedHandler);

        String file1name = "newsPub.txt";
        file2name = "newsPubUpdated.txt";
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
        String[] descs = new String[3];
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
                descs[i] = tempArr[1];
            }
        }

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new MyArrAdapter(this.getApplicationContext(), R.layout.news_pub_item, companies, descs));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news_pub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.action_refresh:
                layout.setRefreshing(true);
                refreshList();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void refreshList() {
        String[] companies = new String[5];
        String[] descs = new String[5];
        Scanner newsPubs = null;

        try {
            FileInputStream file = openFileInput(file2name);
            newsPubs = new Scanner(file);
        } catch(Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 5; i++) {
            if(newsPubs.hasNextLine()) {
                String temp = newsPubs.nextLine();
                String[] tempArr = temp.split(":");
                companies[i] = tempArr[0];
                descs[i] = tempArr[1];
            }
        }

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new MyArrAdapter(this.getApplicationContext(), R.layout.news_pub_item, companies, descs));
        layout.setRefreshing(false);
    }
}
