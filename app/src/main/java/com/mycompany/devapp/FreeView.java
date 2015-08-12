package com.mycompany.devapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;
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

        if(fragments.isEmpty()) {
            Toast toast = Toast.makeText(this, "No free press articles found.", Toast.LENGTH_SHORT);
            toast.show();

            finish();
        }

        pageAdapter = new FreePageAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager) findViewById(R.id.free_slider_pages);
        pager.setAdapter(pageAdapter);
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
