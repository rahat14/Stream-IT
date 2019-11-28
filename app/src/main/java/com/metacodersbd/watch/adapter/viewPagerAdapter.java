package com.metacodersbd.watch.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class viewPagerAdapter extends FragmentPagerAdapter {

    private  final List<Fragment> fragmentList = new ArrayList<>();
    private  final List <String> fragmnetListTitles = new ArrayList<>();

    public viewPagerAdapter(@NonNull FragmentManager fm)
    {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {

        return  fragmentList.size();

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  fragmnetListTitles.get(position);
    }

    public  void addFragment(Fragment fragment , String title)
    {

            fragmentList.add(fragment) ;
            fragmnetListTitles.add(title) ;


    }




}
