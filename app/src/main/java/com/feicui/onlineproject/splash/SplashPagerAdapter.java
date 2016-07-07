package com.feicui.onlineproject.splash;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.feicui.onlineproject.splash.pager.Pager0;
import com.feicui.onlineproject.splash.pager.Pager1;
import com.feicui.onlineproject.splash.pager.Pager2;

/**
 * Created by AAAAA on 2016/7/7.
 */
public class SplashPagerAdapter extends PagerAdapter {
    private final View[] views;


    public SplashPagerAdapter(Context context){
        views=new View[]{//自定义了三个页面用添加到集合中
                new Pager0(context),
                new Pager1(context),
                new Pager2(context)

        };
    }

    /**
     * 获取view页的个数
     * @return
     */
    @Override
    public int getCount() {
        return views.length;
    }
    //次方法写死的 视图的第几个等于项目的个数
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return  view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=views[position];
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view=views[position];
        container.removeView(view);
    }
    //获取视图的位置
    public View getView(int position){
        return views[position];
    }
}
