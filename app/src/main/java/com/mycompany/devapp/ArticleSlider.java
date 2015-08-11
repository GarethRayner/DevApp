package com.mycompany.devapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ArticleSlider extends FragmentActivity
        implements DuckArticleFragment.OnApproveListener {
    FragmentPagerAdapter pageAdapter;
    private boolean approvalWarned = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_article_slider);
        List<Fragment> articles = getArticles();

        pageAdapter = new PagePagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.article_slider_pages);

        pager.setAdapter(pageAdapter);
    }

    private List<Fragment> getArticles() {
        List<Fragment> aList = new ArrayList<Fragment>();

        DuckArticleFragment firstArticle = new DuckArticleFragment();
        GeeseArticleFragment secondArticle = new GeeseArticleFragment();

        aList.add(firstArticle);
        aList.add(secondArticle);

        return aList;
    }

    @Override
    public void onApproval(boolean approve) {
        if(approve) {
            ApprovalFragment approveFrag = new ApprovalFragment();

            FragmentManager approveFragMan = getSupportFragmentManager();

            FragmentTransaction approveTrans = approveFragMan.beginTransaction();

            approveTrans.add(R.id.approvalCont, approveFrag).addToBackStack(null);

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
                                approvalWarned = !approvalWarned;
                            }
                        }
                    }
            );

            approveTrans.commit();
        }
    }
}
