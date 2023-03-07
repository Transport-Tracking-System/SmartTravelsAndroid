package com.example.smarttravels.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.smarttravels.Fragments.HomeFragment;
import com.example.smarttravels.Fragments.AvailFragment;
import com.example.smarttravels.Fragments.OptionsFragment;

public class MyStateAdapter extends FragmentStateAdapter {

    Context myContext;
    int totalTabs;

    public MyStateAdapter(Context context, FragmentManager fm,Lifecycle lf, int totalTabs) {
        super(fm, lf);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                HomeFragment homeFragment = new HomeFragment(myContext);
                return homeFragment;

            case 1:
                AvailFragment availFragment = new AvailFragment();
                return availFragment;
            case 2:
                OptionsFragment optionsFragment = new OptionsFragment();
                return optionsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return totalTabs;
    }
}