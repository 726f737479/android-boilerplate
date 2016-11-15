package com.brainbeanapps.android_boilerplate.ui.details;

import com.brainbeanapps.android_boilerplate.data.DataSource;
import com.brainbeanapps.core.mvp.BasePresenter;
import com.brainbeanapps.core.reactive.RxSchedulers;

import javax.inject.Inject;

/**
 * Created by Rosty on 10/18/2016.
 */

public class DetailsPresenter extends BasePresenter<DetailsContract.View> implements DetailsContract.Presenter {

    private final DataSource   dataSource;
    private final RxSchedulers schedulers;

    @Inject public DetailsPresenter(DataSource dataSource, RxSchedulers schedulers) {
        this.dataSource = dataSource;
        this.schedulers = schedulers;
    }

    @Override public void setUserId(String id) {

    }
}
