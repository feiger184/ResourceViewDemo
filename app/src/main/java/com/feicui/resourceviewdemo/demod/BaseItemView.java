package com.feicui.resourceviewdemo.demod;

import android.content.Context;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2016/12/23 0023.
 */

public abstract class BaseItemView<Model> extends FrameLayout {
    public BaseItemView(Context context) {
        super(context);
        initView();
    }

    protected abstract void initView();

    protected abstract void bindModel(Model model);
}
