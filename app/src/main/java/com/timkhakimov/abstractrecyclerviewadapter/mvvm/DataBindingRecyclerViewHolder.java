package com.timkhakimov.abstractrecyclerviewadapter.mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Timur Khakimov on 15.09.2019
 * ViewHolder for using with data binding
 */
class DataBindingRecyclerViewHolder extends RecyclerView.ViewHolder {

    protected ViewDataBinding binding;

    public DataBindingRecyclerViewHolder(View view) {
        super(view);
        binding = DataBindingUtil.bind(view);
    }

    public <M> void bind(int variableId, M model) {
        binding.setVariable(variableId, model);
        binding.executePendingBindings();
    }
}