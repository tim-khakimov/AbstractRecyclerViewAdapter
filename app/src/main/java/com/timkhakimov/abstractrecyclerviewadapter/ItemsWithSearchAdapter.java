package com.timkhakimov.abstractrecyclerviewadapter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timur Khakimov on 04.02.2018.
 */

public abstract class ItemsWithSearchAdapter<M, V extends CustomListItemView> extends ItemsAdapter<M, V> {

    private static final String TAG = ItemsWithSearchAdapter.class.toString();
    protected boolean isSearch = false;
    protected String searchQuery;
    protected List<M> searchList;

    public ItemsWithSearchAdapter() {
        super();
        searchList = new ArrayList<>();
    }

    @Override
    public List<M> getItems() {
        if(isSearch) {
            return searchList;
        } else {
            return items;
        }
    }

    public void search(String query) {
        this.searchQuery = query;
        if(query.length()==0) {
            isSearch = false;
        } else {
            isSearch = true;
            setSearchList(query);
        }
        Log.i(TAG, "search, searchItems.size()= " + searchList.size());
        notifyDataSetChanged();
    }

    protected void setSearchList(String query) {
        Log.i(TAG, "setSearchList, items.size() =  " + items.size());
        searchList.clear();
        for (M item : items) {
            if(checkForSearch(item, query)){
                searchList.add(item);
            }
        }
    }

    protected abstract boolean checkForSearch(M item, String query);
}
