package com.timkhakimov.abstractrecyclerviewadapter.model;

/**
 * Created by Timur Khakimov on 18.08.2019
 * constants for defining data types
 */
public enum ItemType {

    UNDEFINED;

    private int itemTypePosition;

    ItemType() {
        this.itemTypePosition = this.ordinal();
    }

    public int getItemTypePosition() {
        return itemTypePosition;
    }

    public static ItemType getItemTypeForPosition(int typePosition) {
        for (int i = 0; i < ItemType.values().length; i++) {
            if (ItemType.values()[i].getItemTypePosition() == typePosition) return ItemType.values()[i];
        }
        return UNDEFINED;
    }
}
