package com.mycompany.devapp;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DuckArticleFragment extends Fragment implements View.OnClickListener {
    OnApproveListener mCallback;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.duck_article_fragment, container, false);
        Button approveButton = (Button) view.findViewById(R.id.approve);
        approveButton.setOnClickListener(this);
        return view;
    }
    
    @Override
    public void onClick(View v) {
        approve(v);
    }

    public interface OnApproveListener {
        public void onApproval(boolean approve);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnApproveListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnApproveListener");
        }
    }

    public void approve(View view) {
        mCallback.onApproval(true);
    }
}
