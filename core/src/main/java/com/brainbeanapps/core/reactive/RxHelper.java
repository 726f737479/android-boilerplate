package com.brainbeanapps.core.reactive;

import rx.Observable;
import rx.functions.Func0;

public final class RxHelper {

    /**
     * Method that create observable for single item data
     *
     * @param function methods that return data item
     * @param <T> type of item
     *
     * @return Observable of specific type
     */
    public static <T> Observable<T> createSingleData(Func0<T> function) {
        return Observable.create(subscriber -> {
            try {
                T data = function.call();
                subscriber.onNext(data);
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }
}
