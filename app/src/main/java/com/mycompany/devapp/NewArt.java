package com.mycompany.devapp;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

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

        String[] articles = new String[6];
        articles[0] = "Ducks all time best pet.";
        articles[1] = "Geese all time worst pet.";
        articles[2] = "Ducks and Geese fight for best pet spot.";
        articles[3] = "Air traffic increasing as avian activity increases";
        articles[4] = "Record consumption of E-Cola";
        articles[5] = "Ducks mutually gather with Geese against E-Cola";

        articleNav.setAdapter(new ArrayAdapter<String>(this, R.layout.new_article_display, articles));
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
