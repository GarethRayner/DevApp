package com.mycompany.devapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ArticleSlider extends FragmentActivity
        implements DuckArticleFragment.OnApproveListener {
    PageAdapter pageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_article_slider);
        List<Fragment> articles = getArticles();

        pageAdapter = new PageAdapter(getSupportFragmentManager(), articles);
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
            TextView approval = new TextView(this);
            approval.setText("You approve of this article!");

            setContentView(approval);
        }
    }
}