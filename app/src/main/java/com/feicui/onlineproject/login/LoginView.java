package com.feicui.onlineproject.login;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by AAAAA on 2016/7/7.
 */
public interface LoginView extends MvpView {
    // 显示进度条
    void showProgress();

    // 重置webView
    void resetWeb();

    void showMessage(String msg);

    // 导航至主页面
    void navigateToMain();
}
