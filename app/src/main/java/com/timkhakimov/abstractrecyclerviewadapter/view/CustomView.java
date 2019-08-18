package com.timkhakimov.abstractrecyclerviewadapter.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

/**
 * Created by Timur Khakimov on 18.08.2019
 * base custom view
 */
public abstract class CustomView<B extends ViewDataBinding> extends FrameLayout {

    protected B binding;

    public CustomView(@NonNull Context context) {
        super(context);
        initBinding();
    }

    public CustomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBinding();
    }

    public CustomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBinding();
    }

    private void initBinding(){
        binding = DataBindingUtil.inflate(LayoutInflater.from(this.getContext()), getLayoutRes(), this, true);
    }

    @LayoutRes
    protected abstract int getLayoutRes();
}
