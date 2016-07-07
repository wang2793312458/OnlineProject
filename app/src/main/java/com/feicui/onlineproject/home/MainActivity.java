package com.feicui.onlineproject.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.feicui.onlineproject.R;
import com.feicui.onlineproject.commons.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.drawerLayout)DrawerLayout drawerLayout;//抽屉
    @Bind(R.id.navigationView)NavigationView mNavigationView;//侧滑菜单试图
    @Bind(R.id.toolbar) Toolbar mToolbar;
    private ActivityUtils mActivityUtils;
    private MenuItem mMenuItem;
    //最热门仓库页面Fragment
    private HotRepoFragment mHotRepoFragment;

    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        mActivityUtils=new ActivityUtils(this);
        //ActionBar 处理
        setSupportActionBar(mToolbar);
        //设置navigationView的监听器
       // mNavigationView.setNavigationItemSelectedListener(this);
        //设置ToolBar左上角切换侧滑菜单的按钮
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this,drawerLayout,mToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //设置navigationView的监听器
        mNavigationView.setNavigationItemSelectedListener(this);
        //默认第一个menu项为选中(最热门)
        mMenuItem=mNavigationView.getMenu().findItem(R.id.github_hot_repo);
        mMenuItem.setChecked(true);
        //默认显示的是mHotRepoFragment热门仓库
        mHotRepoFragment=new HotRepoFragment();

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.container,mHotRepoFragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //将默认选中项"手动"设置为false
        if (mMenuItem.isChecked()){
            mMenuItem.setChecked(false);
        }
        switch (item.getItemId()){
            case R.id.github_hot_repo:
                mActivityUtils.showToast(R.string.hot_repo);
                break;
            case R.id.arsenal_my_repo:
                mActivityUtils.showToast(R.string.my_repo);
                break;
            case R.id.tips_daily:
                mActivityUtils.showToast(R.string.tips_daily);
                break;
        }
        //返回true,代表将该菜单项变为checked状态
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //如navigationView是开的，则关闭
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            //如navigationView是关的，则退出
            super.onBackPressed();
        }
    }
}
