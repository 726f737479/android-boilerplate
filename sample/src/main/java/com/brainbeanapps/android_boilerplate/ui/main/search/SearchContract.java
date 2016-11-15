package com.brainbeanapps.android_boilerplate.ui.main.search;

import android.databinding.ObservableField;

import com.brainbeanapps.android_boilerplate.data.remote.model.UserResponse;
import com.brainbeanapps.core.mvp.MvpPresenter;
import com.brainbeanapps.core.mvp.MvpView;

import java.util.List;

import rx.Observable;

/**
 * Created by Rosty on 10/18/2016.
 */

interface SearchContract {

    interface View extends MvpView {
        void setSearchTermObservableField(ObservableField<String> searchTerm);

        void showSearchResults(List<UserResponse> githubUserResponseList);
        void showLoading();
        void hideLoading();
    }

    interface Presenter extends MvpPresenter<View> {


    }
}
