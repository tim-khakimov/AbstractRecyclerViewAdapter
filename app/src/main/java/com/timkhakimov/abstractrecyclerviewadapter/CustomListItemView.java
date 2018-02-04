package com.timkhakimov.abstractrecyclerviewadapter;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

/**
 * Created by Timur Khakimov on 04.02.2018.
 */

public abstract class CustomListItemView<M> extends FrameLayout {

    public CustomListItemView(@NonNull Context context) {
        super(context);
        build(getLayout());
    }

    public CustomListItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        build(getLayout());
    }

    public CustomListItemView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        build(getLayout());
    }

    protected abstract void build(FrameLayout v);

    protected FrameLayout getLayout(){
        return (FrameLayout) LayoutInflater.from(this.getContext()).inflate(getLayoutRes(), this);
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    public abstract void setData(M model);
}

