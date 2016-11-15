package com.brainbeanapps.android_boilerplate.ui.main.saved;

import com.brainbeanapps.android_boilerplate.data.remote.model.UserResponse;
import com.brainbeanapps.core.mvp.MvpPresenter;
import com.brainbeanapps.core.mvp.MvpView;

import java.util.List;

/**
 * Created by Rosty on 10/19/2016.
 */

interface SavedContract {

    interface View extends MvpView {

        void showUserList(List<UserResponse> userResponses);
    }

    interface Presenter extends MvpPresenter<View> {
    }
}
