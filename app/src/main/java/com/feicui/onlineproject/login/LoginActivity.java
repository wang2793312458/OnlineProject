package com.feicui.onlineproject.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.feicui.onlineproject.R;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

/**
 * Created by AAAAA on 2016/7/7.
 */
public class LoginActivity extends MvpActivity<LoginView,LoginPresenter>implements LoginView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void resetWeb() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void navigateToMain() {

    }
}
