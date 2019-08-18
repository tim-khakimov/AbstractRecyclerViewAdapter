package com.timkhakimov.abstractrecyclerviewadapter.dividers.offsets;

/**
 * Created by Timur Khakimov on 18.08.2019
 * behavior for offsets in adapter items
 */
public interface DividersOffsetsBehavior {
    int getLeftOffset(int position);
    int getTopOffset(int position);
    int getRightOffset(int position);
    int getBottomOffset(int position);
}
