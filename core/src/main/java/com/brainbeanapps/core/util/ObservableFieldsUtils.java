package com.brainbeanapps.core.util;


import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.Subscriptions;

public final class ObservableFieldsUtils {
    public static <T> Observable<T> toObservable(@NonNull final ObservableField<T> observableField) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(final Subscriber<? super T> subscriber) {

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(observableField.get());
                }

                final android.databinding.Observable.OnPropertyChangedCallback callback = new android.databinding.Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(android.databinding.Observable dataBindingObservable, int propertyId) {
                        if (dataBindingObservable == observableField) {
                            subscriber.onNext(observableField.get());
                        }
                    }
                };

                observableField.addOnPropertyChangedCallback(callback);

                subscriber.add(Subscriptions.create(() -> observableField.removeOnPropertyChangedCallback(callback)));
            }
        });
    }
}
