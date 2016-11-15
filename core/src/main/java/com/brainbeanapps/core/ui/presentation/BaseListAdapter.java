package com.brainbeanapps.core.ui.presentation;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rosty on 8/9/2016.
 */
public abstract class BaseListAdapter<T, VT extends ViewDataBinding> extends RecyclerView.Adapter<ViewHolder<VT>> {

    protected List<T> data;

    public BaseListAdapter() {
        this.data = new ArrayList<>();
    }

    @Override public int getItemCount() {
        return data.size();
    }

    public T getItem(int position) {
        return data != null && position < data.size() ? data.get(position) : null;
    }

    public List<T> getData() {
        return data;
    }

    public void updateData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    protected LayoutInflater getLayoutInflater(Context context) {
        return (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
}