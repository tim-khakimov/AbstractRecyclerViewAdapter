package com.timkhakimov.abstractrecyclerviewadapter

import android.content.Context
import android.util.Log
import com.timkhakimov.abstractrecyclerviewadapter.model.ItemType
import com.timkhakimov.abstractrecyclerviewadapter.model.ListItem
import com.timkhakimov.abstractrecyclerviewadapter.model.loading.*
import com.timkhakimov.abstractrecyclerviewadapter.util.Command
import com.timkhakimov.abstractrecyclerviewadapter.view.CustomListItemView

/**
 * Created by Timur Khakimov on 18.08.2019
 * base adapter with often used features:
 * - showing loading process
 * - showing network error
 * - reload items from user's action
 * - showing empty list claps
 * - load more items (paging)
 */
abstract class AbstractItemsWithLoadingStateAdapter<T> : MainItemsAdapter() {

    protected var loadingStateListItem : LoadingStateListItem
    protected var emptyListClapItem : EmptyListClapItem
    var loadMoreCommand : Command?
    var reloadCommand : Command?

    init {
        loadingStateListItem = initLoadingStateListItem()
        emptyListClapItem = initEmptyListClapItem()
        loadMoreCommand = null
        reloadCommand = null
    }

    /**
     * override if necessary
     * @return
     */
    protected open fun initLoadingStateListItem() : LoadingStateListItem {
        var loadingStateItem = LoadingStateListItem()
        loadingStateItem.reloadCommand = Command { callReload() }
        return loadingStateItem
    }

    /**
     * override if necessary
     * @return
     */
    protected open fun initEmptyListClapItem() : EmptyListClapItem {
        return EmptyListClapItem()
    }

    override fun getCustomView(context: Context, itemType: ItemType): CustomListItemView<*, *>? {
        return when(itemType) {
            ItemType.LOADING_STATE -> getLoadingStateView(context)
            ItemType.EMPTY_LIST_CLAP -> getEmptyListClapView(context)
            else -> getMainItemsCustomView(context, itemType)
        }
    }

    abstract fun getLoadingStateView(context: Context): CustomListItemView<*, *>

    abstract fun getEmptyListClapView(context: Context): CustomListItemView<*, *>

    abstract fun getMainItemsCustomView(context: Context, itemType: ItemType): CustomListItemView<*, *>?

    abstract fun withPaging(): Boolean

    override fun onBindViewHolder(holder: ItemHolder<*, *, out CustomListItemView<*, *>>, position: Int) {
        super.onBindViewHolder(holder, position)
        Log.d("WithClapLoadingAdapter", "onBindViewHolder position = " + position)
        if (isTriggerForLoadingNexPage(position) && !isStatusItem(getItem(position)) && canLoadMore(getItem(position))) {
            callLoadMore()
        }
    }

    /**
     * override if necessary
     * @return
     */
    protected open fun isTriggerForLoadingNexPage(position: Int) : Boolean {
        return position == itemCount - 1
    }

    protected open fun isStatusItem(listItem: ListItem) : Boolean {
        var itemType = listItem.getItemType()
        return itemType == ItemType.LOADING_STATE || itemType == ItemType.EMPTY_LIST_CLAP
    }

    protected abstract fun canLoadMore(listItem: ListItem) : Boolean

    private fun callLoadMore() {
        if (withPaging()) {
            loadMoreCommand?.execute()
        }
    }

    private fun callReload() {
        reloadCommand?.let {
            resetList()
            it.execute()
        }
    }

    /**
     * override if necessary
     * @return
     */
    open fun resetList() {
        removeAll()
    }

    /**
     * override if necessary
     * @return
     */
    open fun getClapForEmptyList(): ClapType {
        return ClapType.DEFAULT
    }

    fun resetLoadingState() {
        loadingStateListItem.loadingState = LoadingStateType.NONE
        loadingStateListItem.throwable = null
        while (getItems().contains(loadingStateListItem)) {
            remove(loadingStateListItem)
        }
    }

    fun resetEmptyListClap() {
        while (getItems().contains(emptyListClapItem)) {
            remove(emptyListClapItem)
        }
    }

    open fun setLoadingErrorState(throwable : Throwable) {
        loadingStateListItem.throwable = throwable
        loadingStateListItem.loadingState = LoadingStateType.ERROR
        refreshLoadingStateItem()
    }

    open fun setLoadingProgressState() {
        loadingStateListItem.throwable = null
        loadingStateListItem.loadingState = LoadingStateType.LOADING_PROGRESS
        refreshLoadingStateItem()
    }

    private fun refreshLoadingStateItem() {
        val loadingStatePosition = findItemPosition(loadingStateListItem)
        if (loadingStatePosition != -1) {
            notifyItemChanged(loadingStatePosition)
            moveItem(loadingStatePosition, itemCount - 1)
        } else {
            addItem(loadingStateListItem)
        }
    }

    abstract fun getMainItemsCount(): Int

    fun addData(list: List<T>?) {
        resetLoadingState()
        resetLoadingState()
        if (list == null || list.size == 0) {
            handleAddingEmptyData()
        } else {
            handleAddingData(list)
        }
    }

    protected open fun handleAddingData(list: List<T>) {
        addItems(generateListItemsFromInputItems(list))
    }

    /**
     * create headers, sort items, create wrappers or what's necessary for showing
     * @return
     */
    abstract fun generateListItemsFromInputItems(list: List<T>): List<ListItem>

    /**
     * override if necessary
     * @return
     */
    protected open fun handleAddingEmptyData() {
        if(getMainItemsCount() == 0) {
            setEmptyListState()
        }
    }

    /**
     * override if necessary
     * @return
     */
    open fun setEmptyListState() {
        resetList()
        emptyListClapItem.clapType = getClapForEmptyList()
        addItem(emptyListClapItem)
    }
}