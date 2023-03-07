package com.example.smarttravels;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.smarttravels.Adapters.MyStateAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
//    TabLayout tabLayout;
//    ViewPager viewPager;
        TabLayout tabLayout;
        ViewPager2 viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        viewPager=(ViewPager2)findViewById(R.id.view_pager);

//        tabLayout.addTab(tabLayout.newTab().setText("Home"));
//        tabLayout.addTab(tabLayout.newTab().setText("Sport"));
//        tabLayout.addTab(tabLayout.newTab().setText("Movie"));

        MyStateAdapter adapter = new MyStateAdapter(this,getSupportFragmentManager(), getLifecycle(),3);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // set the text for each tab
            switch (position) {
                case 0:
                    tab.setText("Home");
                    break;
                case 1:
                    tab.setText("Avail");
                    break;
                case 2:
                    tab.setText("Options");
                    break;
            }
        }).attach();
    }

//        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
//        viewPager=(ViewPager)findViewById(R.id.viewPager);
//
//        tabLayout.addTab(tabLayout.newTab().setText("Home"));
//        tabLayout.addTab(tabLayout.newTab().setText("Sport"));
//        tabLayout.addTab(tabLayout.newTab().setText("Movie"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
//
//        viewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

 //   }
}