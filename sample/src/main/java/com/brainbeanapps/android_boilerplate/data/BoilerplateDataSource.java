package com.brainbeanapps.android_boilerplate.data;

import com.brainbeanapps.android_boilerplate.data.remote.GithubApiService;
import com.brainbeanapps.android_boilerplate.data.remote.model.UserResponse;
import com.brainbeanapps.core.net.MapResponseHelper;
import com.brainbeanapps.core.reactive.RxSchedulers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;;

import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import timber.log.Timber;

public class BoilerplateDataSource implements DataSource {

    private final GithubApiService githubApiService;
    private final RxSchedulers schedulers;

    @Inject public BoilerplateDataSource(GithubApiService githubApiService, RxSchedulers rxSchedulers) {
        this.githubApiService = githubApiService;
        this.schedulers = rxSchedulers;
    }

    @Override public Observable<List<UserResponse>> searchUsers(final String searchTerm) {

        return Observable.defer(() -> githubApiService.searchGithubUsers(searchTerm)
                .subscribeOn(schedulers.io())
                .compose(MapResponseHelper.convertResponse())
                .concatMap(usersList -> Observable.from(usersList.getItems())
                        .concatMap(user -> githubApiService.getUser(user.getLogin()))
                        .compose((MapResponseHelper.convertResponse()))
                        .toList()))
                .retryWhen(observable -> observable
                        .flatMap(o -> Observable.error(o instanceof IOException ? null : o)));
    }
}
