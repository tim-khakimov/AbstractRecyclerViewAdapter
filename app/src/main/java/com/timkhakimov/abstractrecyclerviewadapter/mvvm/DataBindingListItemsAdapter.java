package com.timkhakimov.abstractrecyclerviewadapter.mvvm;

import com.timkhakimov.abstractrecyclerviewadapter.model.ItemType;
import com.timkhakimov.abstractrecyclerviewadapter.model.ListItem;

/**
 * Created by Timur Khakimov on 15.09.2019
 * adapter for using with various model types
 */
public abstract class DataBindingListItemsAdapter extends DataBindingRecyclerViewAdapter<ListItem> {

    @Override
    protected int getLayoutRes(int viewType) {
        return getLayoutRes(getListItemType(viewType));
    }

    @Override
    protected int getVariableId(int viewType) {
        return getVariableId(getListItemType(viewType));
    }

    protected abstract int getLayoutRes(ItemType viewType);

    protected abstract int getVariableId(ItemType viewType);

    protected ItemType getListItemType(int viewType) {
        return ItemType.getItemTypeForPosition(viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getItemType().getItemTypePosition();
    }
}
