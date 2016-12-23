package com.feicui.resourceviewdemo.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.feicui.resourceviewdemo.R;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DemoCActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,MugenCallbacks {
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private RecyclerAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_c);
        ButterKnife.bind(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter();
        recyclerview.setAdapter(recyclerAdapter);

        swipeRefresh.setOnRefreshListener(this);

        Mugen.with(recyclerview, this).start();
    }

    @Override
    public void onRefresh() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                    recyclerAdapter.clear();
                    for (int i = 0; i < 20; i++) {

                        recyclerAdapter.addItem("添加新数据第"+i+"条");
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }


    @Override
    public void onLoadMore() {
        //显示加载布局
        progressBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    recyclerAdapter.addItem("我是新加载到的第" + i + "条数据");
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    @Override
    public boolean isLoading() {
        return progressBar.getVisibility()==View.VISIBLE;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return false;
    }
}
