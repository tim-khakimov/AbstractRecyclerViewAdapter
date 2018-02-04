package com.timkhakimov.abstractrecyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timur Khakimov on 04.02.2018.
 */

public abstract class ItemsAdapter<M, V extends CustomListItemView> extends RecyclerView.Adapter<ItemHolder> {

    private static final String TAG = ItemsAdapter.class.toString();
    protected List<M> items;
    protected ICommand<M> itemClickListener;

    public ItemsAdapter() {
        items = new ArrayList<>();
    }

    protected abstract V getCustomView(Context context, int viewType);

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(getCustomView(parent.getContext(), viewType));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        final M item = getItems().get(position);
        holder.bindItem(item);
        holder.setItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null) {
                    itemClickListener.execute(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    protected V getItemView(ItemHolder holder){
        return (V) holder.getItemView();
    }

    public void setItemClickListener(ICommand<M> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setItems(List<M> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItems(List<M> itemList){
        items.addAll(itemList);
        notifyDataSetChanged();
    }

    public void addItems(int position, List<M> itemList){
        items.addAll(position, itemList);
        notifyDataSetChanged();
    }

    public void addItem(M item){
        items.add(item);
        notifyItemInserted(items.size()-1);
    }

    public void addItem(int position, M item){
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void removeAt(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void removeAll(){
        items.clear();
        notifyDataSetChanged();
    }

    public M getItem(int position) {
        return items.get(position);
    }

    public List<M> getItems(){
        return items;
    }
}