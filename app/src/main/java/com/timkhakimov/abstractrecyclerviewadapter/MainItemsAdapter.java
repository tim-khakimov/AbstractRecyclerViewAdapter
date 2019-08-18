package com.timkhakimov.abstractrecyclerviewadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.timkhakimov.abstractrecyclerviewadapter.model.ItemType;
import com.timkhakimov.abstractrecyclerviewadapter.model.ListItem;
import com.timkhakimov.abstractrecyclerviewadapter.view.CustomListItemView;

/**
 * Created by Timur Khakimov on 18.08.2019
 * adapter for using with various model types
 */
public abstract class MainItemsAdapter extends BaseListItemsAdapter<ListItem, CustomListItemView> {

    @Nullable
    protected abstract CustomListItemView getCustomView(@NonNull Context context, @NonNull ItemType itemType);

    @Override
    @Nullable
    protected CustomListItemView getCustomView(Context context, int viewType) {
        return getCustomView(context, ItemType.getItemTypeForPosition(viewType));
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(getCustomView(parent.getContext(), ItemType.getItemTypeForPosition(viewType)));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        final ListItem item = getItems().get(position);
        holder.bindItem(item);
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getItemType().getItemTypePosition();
    }

    public ItemType getListItemType(int position) {
        if(position < 0) {
            return null;
        }
        if(position >= getItemCount()) {
            return null;
        }
        return ItemType.getItemTypeForPosition(getItemViewType(position));
    }

    @SuppressWarnings("unchecked")
    protected CustomListItemView getItemView(ItemHolder holder) {
        return holder.getItemView();
    }
}
