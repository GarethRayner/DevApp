package com.mycompany.devapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class HomeScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        TextView duckSelect = (TextView) findViewById(R.id.duck_selectText);
        duckSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openArticle(0);
            }
        });

        TextView geeseSelect = (TextView) findViewById(R.id.geese_selectText);
        geeseSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openArticle(1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.action_settings:
                return true;
            case R.id.notes:
                openNotes();
                return true;
            case R.id.action_free_view:
                openFreeView();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openArticle(int article) {
        Intent intent = new Intent(this, ArticleRead.class);
        switch(article) {
            case 0:
                intent.putExtra("article", "duck");
                break;
            case 1:
                intent.putExtra("article", "geese");
                break;
        }

        startActivity(intent);
    }

    public void articleSlider(View v) {
        Intent intent = new Intent(this, ArticleSlider.class);
        startActivity(intent);
    }

    public void newArt(View v) {
        Intent intent = new Intent(this, NewArt.class);
        startActivity(intent);
    }

    public void openNotes() {
        Intent intent = new Intent(this, Notes.class);
        startActivity(intent);
    }

    public void freeP(View v) {
        Intent intent = new Intent(this, FreePress.class);
        startActivity(intent);
    }

    public void openFreeView() {
        Intent intent = new Intent(this, FreeView.class);
        startActivity(intent);
    }
}
