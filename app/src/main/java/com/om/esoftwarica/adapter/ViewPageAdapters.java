package com.om.esoftwarica.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapters extends FragmentPagerAdapter {
    private List<Fragment> fragmentList=new ArrayList<>();
    private List<String> fragmentTitle=new ArrayList<>();
    public ViewPageAdapters(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitle.get(position);
    }

    @Override
    public int getCount() {
        return fragmentTitle.size();
    }
    public void addfragment(Fragment fragment,String title){
        fragmentList.add(fragment);
        fragmentTitle.add(title);
    }
}
