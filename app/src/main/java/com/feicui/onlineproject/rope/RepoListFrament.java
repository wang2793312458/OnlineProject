package com.feicui.onlineproject.rope;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.feicui.onlineproject.R;
import com.feicui.onlineproject.components.FooterView;
import com.feicui.onlineproject.rope.view.PtrPageView;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by AAAAA on 2016/7/7.
 */
public class RepoListFrament extends MvpFragment<PtrPageView, ReopListPresenter> implements PtrPageView {
    public static RepoListFrament getInstance(String language) {
        RepoListFrament fragment = new RepoListFrament();
        Bundle args = new Bundle();
        args.putSerializable("key_language", language);
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.lvRepos)
    ListView mListView;
    @Bind(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout mPtrFrameLayout;
    @Bind(R.id.emptyView)
    TextView emptyView;
    @Bind(R.id.errorView)
    TextView errorView;
    private ArrayAdapter<String> mAdapter;
    private List<String> datas = new ArrayList<String>();

    private FooterView footerView; // 上拉加载更多的视图

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repo_list, container, false);
    }

    @Override
    public ReopListPresenter createPresenter() {
        return new ReopListPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        //
        mAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        mListView.setAdapter(mAdapter);
        // 下拉刷新
        mPtrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getPresenter().loadData();
            }
        });
        footerView = new FooterView(getContext());
        // 上拉加载更多(listview滑动动最后的位置了，就可以loadmore)
        Mugen.with(mListView, new MugenCallbacks() {
            @Override
            public void onLoadMore() {
                Toast.makeText(getContext(), "loadmore", Toast.LENGTH_SHORT).show();
                getPresenter().loadMore();
            }

            // 是否正在加载，此方法用来避免重复加载
            @Override
            public boolean isLoading() {
                return mListView.getFooterViewsCount() > 0 && footerView.isLoading();

            }

            @Override
            public boolean hasLoadedAllItems() {
                return mListView.getFooterViewsCount() > 0 && footerView.isComplete();

            }
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    // 这是上拉加载更多视图层实现------------------------------------------------------
    @Override
    public void addMoreData(List<String> datas) {
        mAdapter.addAll(datas);
    }

    @Override
    public void hideLoadMore() {
        mListView.removeFooterView(footerView);
    }

    @Override
    public void showLoadMoreLoading() {
        if (mListView.getFooterViewsCount() == 0) {
            mListView.addFooterView(footerView);
        }
        footerView.showLoading();
    }

    @Override
    public void showLoadMoreErro(String msg) {
        if (mListView.getFooterViewsCount() == 0) {
            mListView.addFooterView(footerView);
        }
        footerView.showError(msg);
    }

    @Override
    public void showLoadMoreEnd() {
        if (mListView.getFooterViewsCount() == 0) {
            mListView.addFooterView(footerView);
        }
        footerView.showComplete();
    }

    // 这是下拉刷新视图的实现----------------------------------------------------------------
    @Override
    public void showContentView() {
        mPtrFrameLayout.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showErroView(String msg) {
        mPtrFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {
        mPtrFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void refreshData(List<String> datas) {
        mAdapter.clear();
        mAdapter.addAll(datas);
    }

    @Override
    public void stopRefresh() {
        mPtrFrameLayout.refreshComplete(); // 下拉刷新完成
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
