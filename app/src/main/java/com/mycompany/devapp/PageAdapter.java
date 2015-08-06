package com.mycompany.devapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class PageAdapter extends FragmentPagerAdapter{
    private List<Fragment> articles;

    public PageAdapter(FragmentManager fragMan, List<Fragment> articles) {
        super(fragMan);

        this.articles = articles;
    }

    @Override
    public Fragment getItem(int position) {
        return this.articles.get(position);
    }

    @Override
    public int getCount() {
        return this.articles.size();
    }
}
