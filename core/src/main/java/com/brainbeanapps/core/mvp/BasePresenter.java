package com.brainbeanapps.core.mvp;

import android.support.annotation.IntDef;

import com.brainbeanapps.core.ui.PermissionManager;
import com.brainbeanapps.core.util.ArrayUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import rx.Notification;
import rx.Observable;
import rx.Subscription;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

import static com.brainbeanapps.core.mvp.BasePresenter.Lifecycle.DESTROY;


public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    protected interface Lifecycle {

        @IntDef ({CREATE, ATTACH, DETACH, DESTROY})
        @Retention (RetentionPolicy.SOURCE) @interface Event {}

        int CREATE  = 1;
        int ATTACH  = 2;
        int DETACH  = 3;
        int DESTROY = 4;
    }

    private final PublishSubject<Integer>  lifecycle      = PublishSubject.create();
    private final BehaviorSubject<V>       views          = BehaviorSubject.create();
    private final BehaviorSubject<MvpView> presenterViews = BehaviorSubject.create();

    private V view;

    protected PermissionManager permissionManager;

    public BasePresenter() {

        changLifecycleEvent(Lifecycle.CREATE);
        onCreate();
    }

    @Override public final void attach(V view) {
        views.onNext(this.view = view);

        changLifecycleEvent(Lifecycle.ATTACH);
        onAttach();
    }

    @Override public final void detach() {
        views.onNext(this.view = null);

        changLifecycleEvent(Lifecycle.DETACH);
        onDetach();
    }

    @Override public final void destroy() {
        views.onCompleted();

        changLifecycleEvent(DESTROY);
        onDestroy();
    }

    @Override public void onRequestPermissionsResult(int requestCode) {
        permissionManager.onRequestPermissionsResult(requestCode);
    }

    protected void onCreate() {

    }

    protected void onAttach() {

    }

    protected void onDetach() {

    }

    protected void onDestroy() {

    }

    protected final V getView() {
        return view;
    }

    private final boolean isAttached() {
        return view != null;
    }

    /**
     * A Transformer that reemits TWO last events emitted by source Observable on change of view state (@see attach, detach).
     * If event is emitted while your view is detached, this event will be reemited again when your view will be attached.
     * If view will be destroyed after detach, the subscriber will be unsubscribed.
     * CAUTION: don't use this transformer for source Observables that emmit more that one onNext of useful data.
     * Group elements before passing here {@see toList}.
     *
     * @return a Transformer that emits items emitted by source Observable.
     *         Always remembers the last onNext event, if such emitted.
     */
    protected final <T> Observable.Transformer<T, T> latestCache() {
        return source -> {
            ReplaySubject<Notification<T>> subject      = ReplaySubject.createWithSize(2);
            Subscription                   subscription = source.materialize().subscribe(subject);

            return views.filter(v -> v != null).switchMap(v -> subject.filter(item -> {

                if (!item.isOnNext()) return true;

                @SuppressWarnings ("unchecked")
                Notification<T>[] items = subject.getValues(new Notification[subject.size()]);

                int lastOnNextIndex = items[items.length - 1].isOnCompleted()
                        ? items.length - 2
                        : items.length - 1;

                return ArrayUtils.lastIndexOf(items, item) == lastOnNextIndex;

            }))
                    .flatMap(notification -> {
                        switch (notification.getKind()) {
                            case OnNext:
                                return Observable.just(notification.getValue());

                            case OnError:
                                return Observable.error(notification.getThrowable());

                            default:
                            case OnCompleted:
                                return Observable.empty();
                        }
                    })
                    .doOnUnsubscribe(subscription::unsubscribe)
                    .compose(bindUntilEvent(DESTROY));
        };
    }

    /**
     * @param event
     *            lifecycle event {@link Lifecycle.Event}
     * @return a Transformer that unsubscribes from the source Observable when lifecycle event equals to {@see event}
     */
    protected final <T> Observable.Transformer<T, T> bindUntilEvent(@Lifecycle.Event int event) {
        return source -> source.takeUntil(lifecycle.takeFirst(event1 -> event1 == event));
    }

    /**
     * {@link PermissionManager}
     *
     * @param permission - {@link String} permission
     * @return Transformer returns {@link PermissionManager.PermissionObtained} only if permission is granted
     */

    protected  <T> Observable.Transformer<T, T> requirePermissionsTransformer(String permission) {
        return requirePermissionsTransformer(new String[]{permission});
    }

    protected  <T> Observable.Transformer<T, T> requirePermissionsTransformer(String[] permissions) {
        return source -> permissionManager.requestPermissions(permissions, presenterViews).switchMap(perm -> source);
    }

    private void changLifecycleEvent(@Lifecycle.Event int event) {
        lifecycle.onNext(event);
    }
}
