package com.timkhakimov.abstractrecyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Timur Khakimov on 04.02.2018.
 */

public class ItemHolder<M, V extends CustomListItemView<M>> extends RecyclerView.ViewHolder {

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