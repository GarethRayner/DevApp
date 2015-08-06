package com.mycompany.devapp;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ArtAdapter extends RecyclerView.Adapter<ArtAdapter.ViewHolder> {
    private String[] articles;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView display;
        public ViewHolder(TextView v) {
            super(v);
            display = v;
        }
    }

    public ArtAdapter(String[] article) {
        articles = article;
    }

    @Override
    public ArtAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_article_display, parent, false);

        ViewHolder vh = new ViewHolder((TextView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.display.setText(articles[position]);
    }

    @Override
    public int getItemCount() {
        return articles.length;
    }
}
