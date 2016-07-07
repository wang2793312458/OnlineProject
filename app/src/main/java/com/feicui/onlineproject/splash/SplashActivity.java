package com.feicui.onlineproject.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.feicui.onlineproject.home.MainActivity;
import com.feicui.onlineproject.R;
import com.feicui.onlineproject.commons.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页面,第一次启动时进入的页面
 */
public class SplashActivity extends AppCompatActivity {
    //初始化按键
    @Bind(R.id.btnLogin) Button btnLogin;
    @Bind(R.id.btnEnter) Button btnEnter;

    private ActivityUtils activityUtils;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        activityUtils=new ActivityUtils(this);
        ButterKnife.bind(this);
    }
    //点击事件
    @OnClick(R.id.btnLogin)
    public void login() {
//        activityUtils.startActivity(LoginActivity.class);
        Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.btnEnter)
    public void enter(){
        activityUtils.startActivity(MainActivity.class);
    }
}
