package com.feicui.resourceviewdemo.demod;


import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.feicui.resourceviewdemo.R;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import butterknife.BindView;
import butterknife.ButterKnife;

// 带下拉刷新和上拉加载的自定义列表视图,完成数据加载和数据适配显示的业务流程
// 列表视图 -> RecyclerView 实现
// 下拉刷新 -> SwipeRefreshLayout 实现
//上拉加载 -> Mugen + ProgressBar 实现
public abstract class BaseResourceView extends FrameLayout implements MugenCallbacks, SwipeRefreshLayout.OnRefreshListener {


    public BaseResourceView(Context context) {
        this(context, null);
    }

    public BaseResourceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseResourceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout swiperefreshLayout;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private SimpleAdapter simpleAdapter;

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.base_resource_view, this, true);
        ButterKnife.bind(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        simpleAdapter = new SimpleAdapter();
        recyclerview.setAdapter(simpleAdapter);
        //下拉刷新
        swiperefreshLayout.setOnRefreshListener(this);
        //上拉加载
        Mugen.with(recyclerview, this).start();
    }

    protected abstract class ModelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }


}
