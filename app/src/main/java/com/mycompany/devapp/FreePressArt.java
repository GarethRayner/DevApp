package com.mycompany.devapp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FreePressArt extends Fragment {

    public static FreePressArt newInstance(String title, String contents) {
        FreePressArt article = new FreePressArt();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("contents", contents);
        article.setArguments(args);
        return article;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_free_press_art, container, false);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView contents = (TextView) view.findViewById(R.id.content);
        title.setText(getArguments().getString("title"));
        contents.setText(getArguments().getString("contents"));
        // Inflate the layout for this fragment
        return view;
    }
}
