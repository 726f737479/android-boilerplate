package com.brainbeanapps.core.ui.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.brainbeanapps.core.BaseException;
import com.brainbeanapps.core.di.context.ApplicationContext;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.PublishSubject;
import timber.log.Timber;

public class ScreenRouterManager {

    private PublishSubject<ActivityResult> activityResults  = PublishSubject.create();
    private int                            requestCodeCount = 100;

    private ScreenRouter router;
    private ScreenRouter appRouter;

    @Inject public ScreenRouterManager(@ApplicationContext Context context) {
        this.appRouter = new AppScreenRouter(context);
        this.router = appRouter;
    }

    /**
     * Setting current routing view
     *
     * @param router {@link com.brainbeanapps.core.mvp.MvpView} that implement {@link ScreenRouter}
     */
    public void setRouter(ScreenRouter router) {
        this.router = router;
    }

    /**
     * Removing current routing view. Automatically set {@link #appRouter} as current
     */
    public void removeRouter() {
        this.router = appRouter;
    }

    /**
     * Open activity from intent without finishing current one
     *
     * @param intent for activity
     */
    public void openScreen(Intent intent) {
        openScreen(intent, false);
    }

    /**
     * Open activity with shared element from intent without finishing current one
     *
     * @param intent for activity
     * @param sharedElement between current activity and next one
     */
    public void openScreen(Intent intent, View sharedElement) {
        openScreen(intent, sharedElement, false);
    }

    /**
     * Open activity from intent
     *
     * @param intent for activity
     * @param finishCurrent true - finish current, false - don't finish
     */
    public void openScreen(Intent intent, boolean finishCurrent) {

        router.changeScreen(intent);
        if (finishCurrent) router.finishScreen();
    }

    /**
     * Open activity with shared element from intent
     *
     * @param intent with activity
     * @param sharedElement between current activity and next one
     * @param finishCurrent true - finish current, false - don't finish
     */
    public void openScreen(Intent intent, View sharedElement, boolean finishCurrent) {

        router.changeScreen(intent, sharedElement);
        if (finishCurrent) router.finishScreen();
    }

    /**
     * Replace or show fragment with shared element
     *
     * @param fragment Fragment that need to be shown
     */
    public void openScreen(Fragment fragment) {
        router.changeScreen(fragment);
    }

    /**
     * Replace or show fragment
     *
     * @param fragment Fragment that need to be shown
     * @param sharedElement between current fragment and next one
     */
    public void openScreen(Fragment fragment, View sharedElement) {
        router.changeScreen(fragment, sharedElement);
    }

    /**
     * Open new Activity that have to return some result
     *
     * @param intent for activity
     * @return observable for activity result
     */
    public Observable<ActivityResult> openScreenWithResult(Intent intent) {
        return openScreenWithResult(intent, null);
    }

    /**
     * Open new Activity that have to return some result with shared element
     *
     * @param intent for activity
     * @param sharedElement between current activity and next one
     * @return observable for activity result
     */
    public Observable<ActivityResult> openScreenWithResult(Intent intent, View sharedElement) {

        int requestCode = getRequestCode();
        Observable<ActivityResult> activityResult = activityResults
                .filter(result -> result.getRequestCode() == requestCode)
                .switchMap(result -> {
                    if (result.getResultCode() == Activity.RESULT_CANCELED) {

                        return Observable.error(new BadResultCodeException(
                                result.getRequestCode(),
                                result.getData()));

                    } else return Observable.just(result);
                })
                .take(1)
                .doOnSubscribe(() -> {
                    if (sharedElement == null) router.changeScreenWithResult(intent, requestCode);
                    else router.changeScreenWithResult(intent, sharedElement, requestCode);
                });


        return activityResult;
    }

    /**
     * Setting {@link #requestCodeCount} after calling
     * {@link #openScreenWithResult(Intent)} or {@link #openScreenWithResult(Intent, View)}
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void activityResult(int requestCode, int resultCode, Intent data) {

        ActivityResult result = new ActivityResult(requestCode, resultCode, data);

        Timber.d("activityResult: " + result);
        activityResults.onNext(result);
    }

    private int getRequestCode() {
        return requestCodeCount++;
    }

    public class ActivityResult {

        private int    requestCode;
        private int    resultCode;
        private Intent data;

        public ActivityResult(int requestCode, int resultCode, Intent data) {
            this.requestCode = requestCode;
            this.resultCode = resultCode;
            this.data = data;
        }

        public int getRequestCode() {
            return requestCode;
        }

        public int getResultCode() {
            return resultCode;
        }

        public Intent getData() {
            return data;
        }
    }

    class BadResultCodeException extends BaseException {

        private int    requestCode;
        private Intent data;

        public BadResultCodeException(int requestCode, Intent data) {
            this.requestCode = requestCode;
            this.data = data;
        }

        public int getRequestCode() {
            return requestCode;
        }

        public Intent getData() {
            return data;
        }
    }
}
