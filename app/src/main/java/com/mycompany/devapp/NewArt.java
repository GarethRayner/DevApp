package com.mycompany.devapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class NewArt extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_art);
        mRecyclerView = (RecyclerView) findViewById(R.id.newArtDisplay);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        String[] articles = new String[3];
        articles[0] = "Ducks all time best pet.";
        articles[1] = "Geese all time worst pet.";
        articles[2] = "Ducks and Geese fight for best pet spot.";

        mAdapter = new ArtAdapter(articles);
        mRecyclerView.setAdapter(mAdapter);
    }
}
