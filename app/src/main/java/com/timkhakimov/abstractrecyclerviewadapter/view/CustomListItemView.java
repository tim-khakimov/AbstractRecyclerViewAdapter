package com.timkhakimov.abstractrecyclerviewadapter.view;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.timkhakimov.abstractrecyclerviewadapter.model.ListItem;

/**
 * Created by Timur Khakimov on 18.08.2019
 * base view for using in adapters
 */
public abstract class CustomListItemView<B extends ViewDataBinding, M extends ListItem> extends CustomView<B> {

    public CustomListItemView(@NonNull Context context) {
        super(context);
    }

    public CustomListItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void setData(M model);
}
