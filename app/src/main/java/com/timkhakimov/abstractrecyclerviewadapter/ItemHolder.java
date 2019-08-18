package com.timkhakimov.abstractrecyclerviewadapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.timkhakimov.abstractrecyclerviewadapter.model.ListItem;
import com.timkhakimov.abstractrecyclerviewadapter.view.CustomListItemView;

/**
 * Created by Timur Khakimov on 04.02.2018.
 * Common ViewHolder for all views
 */
public class ItemHolder<B extends ViewDataBinding, M extends ListItem, V extends CustomListItemView<B, M>> extends RecyclerView.ViewHolder {

    protected V customListItemView;

    public ItemHolder(V customListItemView) {
        super(customListItemView);
        this.customListItemView = customListItemView;
    }

    public void bindItem(M model) {
        customListItemView.setData(model);
    }

    public void setItemClickListener(View.OnClickListener listener) {
        customListItemView.setOnClickListener(listener);
    }

    public V getItemView(){
        return customListItemView;
    }
}