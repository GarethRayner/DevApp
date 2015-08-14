package com.mycompany.devapp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ArtAdapter extends RecyclerView.Adapter<ArtAdapter.ViewHolder> {
    private String[] header;
    private String[] content;

    ArtOnClickListener onClickListener = new ArtOnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), NewArtRead.class);
            TextView article = (TextView) v.findViewById(R.id.content);
            String title = article.getText().toString();
            intent.putExtra("title", title);

            v.getContext().startActivity(intent);
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout display;
        public ViewHolder(RelativeLayout v) {
            super(v);
            display = v;
        }
    }

    public ArtAdapter(String[] headers, String[] contents) {
        header = headers;
        content = contents;
    }

    @Override
    public ArtAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_article_display, parent, false);
        v.setOnClickListener(onClickListener);
        ViewHolder vh = new ViewHolder((RelativeLayout) v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView tv = (TextView) holder.display.findViewById(R.id.title);
        TextView tvC = (TextView) holder.display.findViewById(R.id.content);

        tv.setText(header[position]);
        tvC.setText(content[position]);
    }

    @Override
    public int getItemCount() {
        return header.length;
    }
}
