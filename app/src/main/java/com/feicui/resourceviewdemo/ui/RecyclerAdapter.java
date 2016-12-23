package com.feicui.resourceviewdemo.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feicui.resourceviewdemo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/22 0022.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DemoViewHolder> {

    private ArrayList<String> datas = new ArrayList<>();

    public void addItem(String data){
        datas.add(data);
    }

    public void clear(){
        datas.clear();
    }

    @Override
    public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_a_item, parent, false);
        return new DemoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DemoViewHolder holder, final int position) {
        String data = datas.get(position);
        holder.textView.setText(data);
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(holder.itemView,position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemLongClick(holder.itemView, position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas == null?0:datas.size();
    }

    static class DemoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView)
        TextView textView;

        public DemoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int postion);

        void onItemLongClick(View view,int postion);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        this.listener = onItemClickListener;
    }
}

