package com.feicui.resourceviewdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.feicui.resourceviewdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DemoAActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private RecyclerAdapter recyclerAdapter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_a);
        unbinder = ButterKnife.bind(this);

        recyclerview.setLayoutManager(new GridLayoutManager(this,3));
        recyclerAdapter = new RecyclerAdapter();
        recyclerview.setAdapter(recyclerAdapter);

        for (int i = 0; i < 100; i++) {
            recyclerAdapter.addItem("我是第" + i + "条数据");
        }
        recyclerAdapter.notifyDataSetChanged();


        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(DemoAActivity.this, "点击：" + postion, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick(View view, int postion) {
                Toast.makeText(DemoAActivity.this, "长按：" + postion, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
