package com.brainbeanapps.android_boilerplate.ui.main.saved;

import android.view.ViewGroup;

import com.brainbeanapps.android_boilerplate.R;
import com.brainbeanapps.android_boilerplate.data.remote.model.UserResponse;
import com.brainbeanapps.android_boilerplate.databinding.ItemUserBinding;
import com.brainbeanapps.core.ui.presentation.BaseListAdapter;
import com.brainbeanapps.core.ui.presentation.ViewHolder;

/**
 * Created by Rosty on 10/19/2016.
 */

public class SavedUsersAdapter extends BaseListAdapter<UserResponse, ItemUserBinding> {

    @Override public ViewHolder<ItemUserBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getLayoutInflater(parent.getContext()).inflate(
                R.layout.item_user, parent, false));
    }

    @Override public void onBindViewHolder(ViewHolder<ItemUserBinding> holder, int position) {
        holder.getBinding().setUserResponse(getItem(position));
    }
}