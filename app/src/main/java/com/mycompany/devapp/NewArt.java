package com.mycompany.devapp;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class NewArt extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout DrawerRelative;
    private ActionBarDrawerToggle navDrawToggle;
    private CharSequence navDrawTitle;
    private CharSequence title;
    private ListView articleNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_art);
        mRecyclerView = (RecyclerView) findViewById(R.id.newArtDisplay);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.navDraw);
        articleNav = (ListView) findViewById(R.id.navElem);
        title = navDrawTitle = getTitle();
        navDrawToggle = new ActionBarDrawerToggle(this, mDrawerLayout, new Toolbar(this), R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                try {
                    getActionBar().setTitle(title);
                } catch (NullPointerException e) {
                    Log.e("ActionBar Error", "NullPtr");
                }
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                getActionBar().setTitle(navDrawTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(navDrawToggle);

        try {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        } catch(NullPointerException e) {
            Log.e("ActionBar Error", "NullPtr");
        }

        getActionBar().setHomeButtonEnabled(true);

        articleNav = (ListView) findViewById(R.id.navElem);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //New JSON parsing.
        String filename="NewArtJson.txt";
        File file = new File(filename);

        if(!file.exists()) {
            String json = "{\"titles\":[{\"0\": \"Duck all time Best Pet.\"},{\"1\": \"Goose all time Worst Pet\"},{\"2\": \"Ducks and Geese fight over Best Pet spot.\"},{\"3\": \"Air Traffic increasing as Avian activity increases.\"},{\"4\": \"Record consumption of E-Cola.\"},{\"5\": \"Ducks mutually gather with Geese against E-Cola over pollution rows.\"}],\"contents\":[{\"0\": \"Today, ducks were announced as the best kind of creature to keep as a pet. This is owing to several reasons, such as the fact that ducks do not eat much food, rarely leave much noticeable mess and are generally quite docile in nature. This has naturally caused quite a stir among the community of Geese.\"},{\"1\": \"In a scene of outrage, today it was announced that a Goose is now the word's Worst Pet. The explanation given provided rationalisation of the propensity for geese to randomly attack passers-by, gathering in threatening groups and generally being quite anti-social.\"},{\"2\": \"Due to the recent changes in positions of pets and the status changes that occurred, Geese and Ducks are competing quite fiercely over the prized status of being the world's Best Pet. This has caused quite a disturbance in the natural community.\"},{\"3\": \"With the latest news about the Best Pet and Worst Pet declarations, the fighting currently occurring due to these changes has caused a remarkable increase in avian activity - and consequently, a lot of air traffic. Pilots have commented on the sudden need to change direction quickly to avoid travelling avian beasts.\"},{\"4\": \"With the world suddenly experiencing flight delays due to avian disruption, air travel patrons have been consuming more and more of E-Cola. This has required the company to significantly advance their production, producing much more than average. A company spokesperson was pleased to state that it spelled good news for the business.\"},{\"5\": \"In a rare display of community, Ducks and Geese are banding together to ensure the sudden surge of E-Cola buyers are bad news for the company. Claiming the pollution produced affects both communities severely, they are conducting boycotts against the company in protest.\"}],\"publisher\": [{\"0\": \"by G. Rayner\"},{\"1\": \"by G. Rayner\"},{\"2\": \"by A. Bird\"},{\"3\": \"by A. Traveller\"},{\"4\": \"by T. Raffic\"},{\"5\": \"by G. Community\"}]}";

            try {
                FileOutputStream fileOutput = openFileOutput(filename, MODE_PRIVATE);
                fileOutput.write(json.getBytes());
            } catch (Exception e) {
                Log.e("Error", "AAAAARRRRGGHHH! IT BURNS!");
            }
        }

        String[] articles = new String[6];
        String[] headers = new String[6];

        Scanner newArt = null;

        try {
            FileInputStream JSONFile = openFileInput(filename);
            newArt = new Scanner(JSONFile);
        } catch(Exception e) {
            e.printStackTrace();
        }

        String JSONContent;

        if(newArt.hasNextLine()) {
            JSONContent = newArt.nextLine();
        } else {
            JSONContent = "{}";
        }

        JSONObject json;
        JSONArray titles;
        JSONArray content;
        JSONArray author;

        try {
            json = new JSONObject(JSONContent);
            titles = json.optJSONArray("titles");
            content = json.optJSONArray("contents");
            author = json.optJSONArray("publisher");
        } catch(JSONException e) {
            Log.e("JSON", "Error obtaining JSONs.");
            titles = null;
            content = null;
            author = null;
        }

        try {
            for(int i = 0; i < 6; i++) {
                JSONObject objT = titles.optJSONObject(i);
                JSONObject objC = content.optJSONObject(i);
                JSONObject objA = author.optJSONObject(i);

                String temp = objT.getString(String.valueOf(i)) + "\n\n" + objC.getString(String.valueOf(i)) + "\n\n" + objA.getString(String.valueOf(i));
                articles[i] = temp;
                headers[i] = objT.getString(String.valueOf(i));
            }
        } catch(JSONException e) {
            Log.e("JSON", "Error parsing JSON.");
        }

        articleNav.setAdapter(new ArrayAdapter<String>(this, R.layout.new_article_display, headers));
        articleNav.setOnItemClickListener(new NavDrawClickListener());

        mAdapter = new ArtAdapter(articles);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        navDrawToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        navDrawToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(navDrawToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
}
