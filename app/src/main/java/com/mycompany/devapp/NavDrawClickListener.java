package com.mycompany.devapp;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class NavDrawClickListener implements ListView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        Intent intent = new Intent(view.getContext(), NewArtRead.class);
        TextView article = (TextView) view.findViewById(R.id.content);
        String title = article.getText().toString();
        intent.putExtra("title", title);

        view.getContext().startActivity(intent);
    }
}
