package com.brainbeanapps.android_boilerplate.ui.main.search;

import android.databinding.BindingAdapter;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.brainbeanapps.android_boilerplate.R;
import com.brainbeanapps.android_boilerplate.data.remote.model.UserResponse;
import com.brainbeanapps.android_boilerplate.databinding.ItemUserBinding;
import com.brainbeanapps.core.ui.presentation.BaseListAdapter;
import com.brainbeanapps.core.ui.presentation.ViewHolder;
import com.squareup.picasso.Picasso;

/**
 * Created by Rosty on 10/18/2016.
 */

public class SearchResultsAdapter extends BaseListAdapter<UserResponse, ItemUserBinding>{

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String v) {
        Picasso.with(imageView.getContext()).load(v).fit().centerInside().into(imageView);
    }

    @Override public ViewHolder<ItemUserBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getLayoutInflater(parent.getContext()).inflate(
                R.layout.item_user, parent, false));
    }

    @Override public void onBindViewHolder(ViewHolder<ItemUserBinding> holder, int position) {
        holder.getBinding().setUserResponse(getItem(position));
    }
}
