package com.brainbeanapps.android_boilerplate.ui.main;

import android.support.v4.app.Fragment;

import com.brainbeanapps.android_boilerplate.ui.main.saved.SavedFragment;
import com.brainbeanapps.android_boilerplate.ui.main.search.SearchFragment;
import com.brainbeanapps.core.di.scope.UIScope;
import com.brainbeanapps.core.mvp.BasePresenter;
import com.brainbeanapps.core.ui.navigation.ScreenRouterManager;

import javax.inject.Inject;

@UIScope
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{

    private ScreenRouterManager screenRouterManager;

    private Fragment searchFragment;
    private Fragment savedFragment;

    @Inject public MainPresenter(ScreenRouterManager manager) {
        screenRouterManager = manager;
    }

    @Override protected void onAttach() {
        super.onAttach();

        searchFragment = new SearchFragment();
        savedFragment = new SavedFragment();

        screenRouterManager.openScreen(searchFragment);
    }

    @Override public void onSearchSelected() {
        screenRouterManager.openScreen(searchFragment);
    }

    @Override public void onSavedSelected() {
        screenRouterManager.openScreen(savedFragment);
    }

    @Override public void onSettingsSelected() {

    }
}
