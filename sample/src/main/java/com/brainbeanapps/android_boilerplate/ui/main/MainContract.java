package com.brainbeanapps.android_boilerplate.ui.main;

import com.brainbeanapps.core.mvp.MvpPresenter;
import com.brainbeanapps.core.mvp.MvpView;

/**
 * Created by Rosty on 10/13/2016.
 */

public interface MainContract {

    interface View extends MvpView {

    }

    interface Presenter extends MvpPresenter<View> {

        void onSearchSelected();
        void onSavedSelected();
        void onSettingsSelected();

    }
}
