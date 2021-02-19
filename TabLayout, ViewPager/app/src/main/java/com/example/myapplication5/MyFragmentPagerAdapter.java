package com.example.myapplication5;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * PagerAdapter 는 양옆에 page를 미리 띄워둠
 * FragmentPagerAdapter 는 한번 뜬 객체는 지우지 않음
 * FragmentPagerStateAdapter 화면에서 보이지 않으면 날림
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    //Page가 들어갈 리스트
    private List<Fragment> mFragments = new ArrayList<>();

    public MyFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void addFragment(Fragment fragment){
        mFragments.add(fragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
