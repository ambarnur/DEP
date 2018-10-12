package com.net2software.dep;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    private String[] tabtitle = {"Bayar", "Selesai"};
    Context context;
    private int pagecount = 2;
    public FragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        //Tab1Fragment tab1Fragment = new Tab1Fragment();

        return Tab1Fragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return tabtitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitle[position];
    }
}

