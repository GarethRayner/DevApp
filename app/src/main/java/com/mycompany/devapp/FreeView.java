package com.mycompany.devapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FreeView extends FragmentActivity {
    FreePageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_view);

        List<FreePressArt> fragments = getFragments();
        pageAdapter = new FreePageAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager) findViewById(R.id.free_slider_pages);
        pager.setAdapter(pageAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_free_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<FreePressArt> getFragments() {
        List<FreePressArt> fragments = new ArrayList<FreePressArt>();
        Scanner freeArts = null;

        try {
            FileInputStream file = openFileInput("freePress.txt");
            freeArts = new Scanner(file);
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            while (freeArts.hasNextLine()) {
                String text = freeArts.nextLine();
                String[] contents = text.split(":");
                FreePressArt article = FreePressArt.newInstance(contents[0], contents[1]);
                fragments.add(article);
            }
        } catch(NullPointerException e) {
            Log.e("Error", "Files failing.");
        }

        return fragments;
    }
}
