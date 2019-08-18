package com.timkhakimov.abstractrecyclerviewadapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.timkhakimov.abstractrecyclerviewadapter.model.ListItem;
import com.timkhakimov.abstractrecyclerviewadapter.view.CustomListItemView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Timur Khakimov on 18.08.2019
 */
public abstract class BaseListItemsAdapter<M extends ListItem, V extends CustomListItemView> extends RecyclerView.Adapter<ItemHolder> {

    private static final String TAG = BaseListItemsAdapter.class.toString();
    protected List<M> items;

    public BaseListItemsAdapter() {
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
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    protected V getItemView(ItemHolder holder){
        return (V) holder.getItemView();
    }

    public void setItems(List<M> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItems(List<M> itemList){
        int startPosition = getItemCount();
        items.addAll(itemList);
        notifyItemRangeChanged(startPosition, itemList.size());
    }

    public void addItems(int position, List<M> itemList){
        if(position < 0 || position > getItemCount()) {
            return;
        }
        items.addAll(position, itemList);
        notifyItemRangeChanged(position, itemList.size());
    }

    public void addItem(M item){
        items.add(item);
        notifyItemInserted(items.size()-1);
    }

    public void addItem(int position, M item){
        if(position < 0 || position > getItemCount()) {
            return;
        }
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void removeAt(int position) {
        if(position < 0 || position >= getItemCount()) {
            return;
        }
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(@Nullable M item) {
        int position = findItemPosition(item);
        if (position != -1) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * Удалить часть элементов из списка
     * @param positionStart - индекс первого удаляемого элемента
     * @param itemCount - количество удаляемых элементов
     */
    public void removeRange(int positionStart, int itemCount) {
        if(positionStart > getItemCount() - 1 || positionStart < 0) {
            return;
        }
        if(positionStart + itemCount > getItemCount()) {
            itemCount = getItemCount() - positionStart;
        }
        items.subList(positionStart, positionStart + itemCount).clear();
        notifyItemRangeRemoved(positionStart, itemCount);
    }

    public void removeAll(){
        items.clear();
        notifyDataSetChanged();
    }

    public M getItem(int position) {
        return items.get(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        if(getItemCount() <= fromPosition || getItemCount() <= toPosition || fromPosition < 0 || toPosition < 0) {
            return;
        }
        M item = items.remove(fromPosition);
        items.add(toPosition, item);
        notifyItemMoved(fromPosition, toPosition);
    }

    public int findItemPosition(@Nullable ListItem item) {
        if (item == null) return -1;
        return items.indexOf(item);
    }

    public List<M> getItems(){
        return items;
    }
}
