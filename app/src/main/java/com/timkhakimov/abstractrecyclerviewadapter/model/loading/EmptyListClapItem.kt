package com.timkhakimov.abstractrecyclerviewadapter.model.loading

import com.timkhakimov.abstractrecyclerviewadapter.model.ItemType
import com.timkhakimov.abstractrecyclerviewadapter.model.ListItem

/**
 * Created by Timur Khakimov on 18.08.2019
 * clap for showing in empty list in adapters
 */
open class EmptyListClapItem : ListItem {

    var clapType : ClapType = ClapType.DEFAULT

    override fun getItemType(): ItemType {
        return ItemType.EMPTY_LIST_CLAP
    }
}