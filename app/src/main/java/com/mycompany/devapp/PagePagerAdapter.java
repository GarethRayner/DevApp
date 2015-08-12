package com.mycompany.devapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagePagerAdapter extends FragmentPagerAdapter {
    private DuckArticleFragment duckArticle;
    private GeeseArticleFragment geeseArticle;

    public PagePagerAdapter(FragmentManager fragMan) {
        super(fragMan);

        duckArticle = new DuckArticleFragment();
        geeseArticle = new GeeseArticleFragment();
    }

    @Override
    public Fragment getItem(int i) {
        Bundle articles = new Bundle();
        articles.putInt("current_page", i);
        if(i == 0) {
            duckArticle.setArguments(articles);
            return duckArticle;
        } else {
            geeseArticle.setArguments(articles);
            return geeseArticle;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return "Duck Article";
        } else {
            return "Geese Article";
        }
    }
}
