package com.brainbeanapps.android_boilerplate.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.brainbeanapps.android_boilerplate.BoilerplateAppComponent;
import com.brainbeanapps.android_boilerplate.R;
import com.brainbeanapps.android_boilerplate.data.remote.model.UserResponse;
import com.brainbeanapps.android_boilerplate.databinding.FragmentSearchBinding;
import com.brainbeanapps.android_boilerplate.ui.UIModule;
import com.brainbeanapps.core.di.component.ApplicationComponent;
import com.brainbeanapps.core.di.component.UIComponent;
import com.brainbeanapps.core.ui.presentation.BaseActivity;

/**
 * Created by Rosty on 10/18/2016.
 */

public class DetailsFragment extends BaseActivity<DetailsPresenter, DetailsContract.View, FragmentSearchBinding> implements DetailsContract.View {

    private static final String KEY_USER_ID = "user id";

    public static Intent getIntent(Context context, String id){

        Intent intent = new Intent(context, DetailsFragment.class);
        intent.putExtra(KEY_USER_ID, id);

        return intent;
    }

    @Override protected UIComponent buildComponent(ApplicationComponent appComponent) {
        return DaggerDetailsComponent.builder()
                .uIModule(new UIModule(getContext()))
                .boilerplateAppComponent((BoilerplateAppComponent) appComponent)
                .build();
    }
    @Override protected DetailsContract.View getMvpView() {
        return this;
    }
    @Override protected int getLayoutResource() {
        return R.layout.fragment_search;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getPresenter().setUserId(getIntent().getStringExtra(KEY_USER_ID));
    }

    @Override public void showUserDetails(UserResponse userResponse) {

    }
}
