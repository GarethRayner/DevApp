package com.mycompany.devapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class FreePageAdapter extends FragmentPagerAdapter {
    private List<FreePressArt> fragments;

    public FreePageAdapter(FragmentManager fm, List<FreePressArt> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        FreePressArt article = fragments.get(position);
        return article.getTitle();
    }
}
