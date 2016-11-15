package com.brainbeanapps.android_boilerplate.ui.details;

import com.brainbeanapps.android_boilerplate.data.remote.model.UserResponse;
import com.brainbeanapps.core.mvp.MvpPresenter;
import com.brainbeanapps.core.mvp.MvpView;

/**
 * Created by Rosty on 10/18/2016.
 */

interface DetailsContract {

    interface View extends MvpView {

        void showUserDetails(UserResponse userResponse);
    }

    interface Presenter extends MvpPresenter<View> {

        void setUserId(String id);
    }
}
