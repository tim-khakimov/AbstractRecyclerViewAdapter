package com.timkhakimov.abstractrecyclerviewadapter.dividers.drawables;

import android.graphics.drawable.Drawable;

/**
 * Created by Timur Khakimov on 18.08.2019
 * behavior for drawing dividers in adapter items
 */
public interface DividersDrawablesBehavior {
    Drawable getLeftDivider(int position);
    Drawable getTopDivider(int position);
    Drawable getRightDivider(int position);
    Drawable getBottomDivider(int position);
}
