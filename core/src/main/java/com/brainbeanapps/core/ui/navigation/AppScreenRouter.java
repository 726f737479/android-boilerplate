package com.brainbeanapps.core.ui.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.brainbeanapps.core.di.context.ApplicationContext;
import com.brainbeanapps.core.ui.navigation.ScreenRouter;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Rosty on 10/19/2016.
 */

public class AppScreenRouter implements ScreenRouter {

    private Context context;

    @Inject public AppScreenRouter(@ApplicationContext Context context) {
        this.context = context;
    }

    @Override public void finishScreen() {
        Timber.w("Finish Screen: illegal state error");
    }

    @Override public void changeScreen(Intent intent) {
        context.startActivity(intent);
    }

    @Override public void changeScreen(Intent intent, View sharedElement) {
        Timber.w("Change Screen: illegal state error");
    }

    @Override public void changeScreen(Fragment fragment) {
        Timber.w("Change Screen: illegal state error");
    }

    @Override public void changeScreen(Fragment fragment, View sharedElement) {
        Timber.w("Change Screen: illegal state error");
    }

    @Override public void changeScreenWithResult(Intent intent, int requestCode) {
        Timber.w("Change Screen: illegal state error");
    }

    @Override public void changeScreenWithResult(Intent intent, View sharedElement, int requestCode) {
        Timber.w("Change Screen: illegal state error");
    }
}