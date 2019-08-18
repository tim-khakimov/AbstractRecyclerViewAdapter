package com.timkhakimov.abstractrecyclerviewadapter.model.loading

import com.timkhakimov.abstractrecyclerviewadapter.model.ItemType
import com.timkhakimov.abstractrecyclerviewadapter.model.ListItem
import com.timkhakimov.abstractrecyclerviewadapter.util.Command

/**
 * Created by Timur Khakimov on 18.08.2019
 * showing loading progress or error state in lists
 */
open class LoadingStateListItem : ListItem {

    var loadingState : LoadingStateType = LoadingStateType.NONE
    var throwable : Throwable? = null
    var reloadCommand : Command? = null     //call execute() for retry load items

    override fun getItemType(): ItemType {
        return ItemType.LOADING_STATE
    }
}