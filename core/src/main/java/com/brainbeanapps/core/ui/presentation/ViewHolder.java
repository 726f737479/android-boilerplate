package com.brainbeanapps.core.ui.presentation;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Rosty on 10/19/2016.
 */

public class ViewHolder<VT extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private VT binding;

    public ViewHolder(View view) {

        super(view);
        binding = DataBindingUtil.bind(view);
    }

    public VT getBinding() {
        return binding;
    }
}