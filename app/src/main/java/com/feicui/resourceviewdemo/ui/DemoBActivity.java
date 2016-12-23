package com.feicui.resourceviewdemo.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.feicui.resourceviewdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DemoBActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_b);
        ButterKnife.bind(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter();
        recyclerview.setAdapter(recyclerAdapter);

        swipeRefresh.setOnRefreshListener(this);
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
}
