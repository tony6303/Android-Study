package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager vpContainer;
    private TabLayout tabs;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TabLayout은 menu.xml이 없음!!
        vpContainer = findViewById(R.id.vp_container);
        tabs = findViewById(R.id.tabs);

        // 어댑터 고정 문법법
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),1);
        myFragmentPagerAdapter.addFragment(new Frag1());
        myFragmentPagerAdapter.addFragment(new Frag2());
        myFragmentPagerAdapter.addFragment(new Frag3());

        vpContainer.setAdapter(myFragmentPagerAdapter);

        // tab 이랑 ViewPager 랑 연결되어야함
        tabs.setupWithViewPager(vpContainer);

        // tab 에 아이템 그리기
        tabs.getTabAt(0).setText("1");
        tabs.getTabAt(1).setText("2");
        tabs.getTabAt(2).setText("3");
    }
}