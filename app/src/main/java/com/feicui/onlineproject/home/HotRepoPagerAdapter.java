package com.feicui.onlineproject.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.feicui.onlineproject.rope.RepoListFrament;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AAAAA on 2016/7/7.
 * 适配器
 */
public class HotRepoPagerAdapter extends FragmentPagerAdapter{
    private final List<String> languages;//列表
    public HotRepoPagerAdapter(FragmentManager fm) {
        super(fm);
        languages=new ArrayList<>();
        languages.add("java 1");
        languages.add("java 2");
        languages.add("java 3");
        languages.add("java 4");
        languages.add("java 5");
        languages.add("java 6");
        languages.add("java 7");

    }

    @Override
    public Fragment getItem(int position) {
        return RepoListFrament.getInstance(languages.get(position));
    }

    @Override
    public int getCount() {
        return languages.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return languages.get(position);
    }
}
