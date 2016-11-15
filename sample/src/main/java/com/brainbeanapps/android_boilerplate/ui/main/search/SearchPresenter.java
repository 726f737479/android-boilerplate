package com.brainbeanapps.android_boilerplate.ui.main.search;

import android.databinding.ObservableField;

import com.brainbeanapps.android_boilerplate.data.DataSource;
import com.brainbeanapps.core.di.scope.UIScope;
import com.brainbeanapps.core.mvp.BasePresenter;
import com.brainbeanapps.core.reactive.RxSchedulers;
import com.brainbeanapps.core.util.ObservableFieldsUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;


@UIScope
public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {

    private final DataSource   dataSource;
    private final RxSchedulers schedulers;

    private ObservableField<String> searchTerm = new ObservableField<>();

    @Inject @UIScope public SearchPresenter(DataSource dataSource, RxSchedulers rxSchedulers) {
        this.dataSource = dataSource;
        this.schedulers = rxSchedulers;

        ObservableFieldsUtils.toObservable(searchTerm)

                .filter(term -> term != null)
                .filter(term -> !term.isEmpty())
                .debounce(750, TimeUnit.MILLISECONDS)

                .observeOn(schedulers.main())
                .doOnNext(term -> getView().showLoading())

                .observeOn(schedulers.io())
                .switchMap(dataSource::searchUsers)
                .compose(latestCache())

                .observeOn(schedulers.main())
                .subscribe(
                        users -> {
                            getView().hideLoading();
                            getView().showSearchResults(users);
                        },
                        error -> {
                            getView().hideLoading();
                            getView().showInformationToast(error.getMessage());
                        }
                );
    }

    @Override protected void onAttach() {
        super.onAttach();

        getView().setSearchTermObservableField(searchTerm);
    }
}
