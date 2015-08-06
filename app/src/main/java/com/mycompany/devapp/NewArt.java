package com.mycompany.devapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

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

        String[] articles = new String[6];
        articles[0] = "Ducks all time best pet.";
        articles[1] = "Geese all time worst pet.";
        articles[2] = "Ducks and Geese fight for best pet spot.";
        articles[3] = "Air traffic increasing as avian activity increases";
        articles[4] = "Record consumption of E-Cola";
        articles[5] = "Ducks mutually gather with Geese against E-Cola";

        mAdapter = new ArtAdapter(articles);
        mRecyclerView.setAdapter(mAdapter);
    }
}
