package com.mycompany.devapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrAdapter extends ArrayAdapter<String> {
    String[] contents;
    String[] titles;
    Context con;

    public MyArrAdapter(Context context, int textViewId, String[] title, String[] content) {
        super(context, textViewId, title);
        titles = title;
        contents = content;
        con = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(con).inflate(R.layout.news_pub_item, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.news_pub_title)).setText(titles[position]);
        ((TextView) convertView.findViewById(R.id.news_pub_content)).setText(contents[position]);

        return convertView;
    }
}
