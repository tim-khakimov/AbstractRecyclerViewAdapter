package com.timkhakimov.abstractrecyclerviewadapter;

/**
 * Created by Timur Khakimov on 04.02.2018.
 */

public interface ICommand<T> {
    void execute(T value);
}