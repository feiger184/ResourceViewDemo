package com.feicui.resourceviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.feicui.resourceviewdemo.ui.DemoAActivity;
import com.feicui.resourceviewdemo.ui.DemoBActivity;
import com.feicui.resourceviewdemo.ui.DemoCActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @BindView(R.id.listview)
    ListView listview;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        intdata();


    }

    private void intdata() {
        String[] data = new String[]{
                "RecyclerView基本运用",
                "下拉刷新",
                "上拉加载",
                "视图封装"
        };

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = null;

        switch (i) {
            case 0:
                intent = new Intent(this, DemoAActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, DemoBActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, DemoCActivity.class);
                startActivity(intent);
                break;
            case 3:
                break;

        }
    }
}
