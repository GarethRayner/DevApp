package com.mycompany.devapp;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ArticleRead extends FragmentActivity
    implements DuckArticleFragment.OnApproveListener {
    private boolean approvalWarned;
    private boolean approved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_read);

        FragmentManager articleLoader = getSupportFragmentManager();

        FragmentTransaction articleLoaderTrans = articleLoader.beginTransaction();

        Intent getChoice = getIntent();

        String choice = getChoice.getStringExtra("article");

        if(choice.compareTo("duck") == 0) {
            DuckArticleFragment duckArticle = new DuckArticleFragment();

            articleLoaderTrans.replace(R.id.articleContainer, duckArticle);
        } else {
            GeeseArticleFragment geeseArticle = new GeeseArticleFragment();

            articleLoaderTrans.replace(R.id.articleContainer, geeseArticle);
        }

        articleLoaderTrans.commit();

        getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        if (!approvalWarned) {
                            RelativeLayout container = (RelativeLayout) findViewById(R.id.approvalLay);
                            RelativeLayout.LayoutParams parameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                            TextView warning = new TextView(container.getContext());

                            warning.setText("You cannot disapprove after approving!");

                            parameters.addRule(RelativeLayout.BELOW, R.id.approvalText);

                            container.addView(warning, parameters);
                            approvalWarned = true;
                        } else {
                            approvalWarned = false;
                            approved = false;
                        }
                    }
                }
        );
    }

    @Override
    public void onApproval(boolean approve) {
        String test;
        try {
            TextView approveView = (TextView) findViewById(R.id.approvalText);
            test = approveView.getText().toString();
        } catch(NullPointerException e) {
            test = "";
        }
        if(test.compareTo("You approve of this article!") != 0) {
                approved = true;
                ApprovalFragment approveFrag = new ApprovalFragment();

                FragmentManager approveFragMan = getSupportFragmentManager();

                FragmentTransaction approveTrans = approveFragMan.beginTransaction();

                approveTrans.add(R.id.approvalCont, approveFrag).addToBackStack(null);

                approveTrans.commit();
        }
    }
}
