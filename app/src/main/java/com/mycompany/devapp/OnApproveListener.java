package com.mycompany.devapp;

import android.view.View;

/**
 * Created by gareth.rayner on 10/08/2015.
 */
public class OnApproveListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        approve(v);
    }

    public interface OnApprovalListener {
        public void onApproval(boolean approve);
    }

    public void approve(View view) {

    }
}
