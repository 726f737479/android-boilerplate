package com.brainbeanapps.android_boilerplate.ui.main.saved;

import com.brainbeanapps.android_boilerplate.data.DataSource;
import com.brainbeanapps.core.mvp.BasePresenter;
import com.brainbeanapps.core.reactive.RxSchedulers;

import javax.inject.Inject;

/**
 * Created by Rosty on 10/19/2016.
 */

public class SavedPresenter extends BasePresenter<SavedContract.View> implements SavedContract.Presenter {

    private final DataSource   dataSource;
    private final RxSchedulers schedulers;

    @Inject public SavedPresenter(DataSource dataSource, RxSchedulers schedulers) {
        this.dataSource = dataSource;
        this.schedulers = schedulers;
    }
}

