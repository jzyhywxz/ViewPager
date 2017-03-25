package com.zzw.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zzw on 2017/3/25.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> datas;

    public FragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        datas=list;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }
}
