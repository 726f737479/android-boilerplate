package com.brainbeanapps.core.mvp;

import android.content.Context;

import com.brainbeanapps.core.di.context.UIContext;


/**
 * Created by Rosty on 10/12/2016.
 */

public interface MvpView {

    @UIContext Context getContext();

    void requestPermission(String[] missingPermission, int requestCode);
    void showInformationToast(String message);
}
