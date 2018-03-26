package com.akinadude.cloudfactory.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.akinadude.cloudfactory.R;
import com.akinadude.cloudfactory.ui.adapters.PagerAdapter;
import com.akinadude.cloudfactory.ui.fragments.EmptyFragment;
import com.akinadude.cloudfactory.ui.fragments.TickersFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = findViewById(R.id.view_pager);

        List<Fragment> fragments = new ArrayList<>();
        if (savedInstanceState == null) {
            fragments.add(new TickersFragment());
            fragments.add(new EmptyFragment());
        } else {
            Fragment f0 = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + 0);
            Fragment f1 = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + 1);
            fragments.add(f0);
            fragments.add(f1);
        }
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(pagerAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                TickersFragment tickersFragment = (TickersFragment) pagerAdapter.getItem(0);
                if (position == 0) {
                    tickersFragment.start();
                } else if (position == 1) {
                    tickersFragment.stop();
                } else
                    throw new RuntimeException("No fragment is supposed for position " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
