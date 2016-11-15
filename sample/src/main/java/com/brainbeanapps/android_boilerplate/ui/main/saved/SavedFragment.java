package com.brainbeanapps.android_boilerplate.ui.main.saved;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.brainbeanapps.android_boilerplate.BoilerplateAppComponent;
import com.brainbeanapps.android_boilerplate.R;
import com.brainbeanapps.android_boilerplate.data.remote.model.UserResponse;
import com.brainbeanapps.android_boilerplate.databinding.FragmentSavedBinding;
import com.brainbeanapps.android_boilerplate.ui.UIModule;
import com.brainbeanapps.android_boilerplate.ui.details.DaggerDetailsComponent;
import com.brainbeanapps.core.di.component.ApplicationComponent;
import com.brainbeanapps.core.di.component.UIComponent;
import com.brainbeanapps.core.ui.presentation.BaseFragment;

import java.util.List;

/**
 * Created by Rosty on 10/19/2016.
 */

public class SavedFragment extends BaseFragment<SavedPresenter, SavedContract.View, FragmentSavedBinding> implements SavedContract.View {

    @Override protected UIComponent buildComponent(ApplicationComponent appComponent) {
        return DaggerDetailsComponent.builder()
                .uIModule(new UIModule(getContext()))
                .boilerplateAppComponent((BoilerplateAppComponent) appComponent)
                .build();
    }
    @Override protected SavedContract.View getMvpView() {
        return this;
    }
    @Override protected int getLayoutResource() {
        return R.layout.fragment_saved;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override public void showUserList(List<UserResponse> userResponses) {

    }
}