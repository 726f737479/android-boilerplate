package com.brainbeanapps.core.reactive;

import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by okorsun on 19.10.16.
 */

public class RxNotifier {

    private final ConcurrentHashMap<Class<?>, BehaviorSubject<Integer>> notifiers = new ConcurrentHashMap<Class<?>, BehaviorSubject<Integer>>();

    private BehaviorSubject<Integer> getOrCreateNotifier(Class<?> cls) {

        BehaviorSubject<Integer> subject = notifiers.get(cls);
        if (subject != null)
            return subject;

        subject = BehaviorSubject.create(0);

        if (notifiers.putIfAbsent(cls, subject) == null)
            return subject;
        else
            return notifiers.get(cls);
    }

    public void notifyChange(Class<?> cls) {

        BehaviorSubject<Integer> subject = getOrCreateNotifier(cls);
        subject.onNext(subject.getValue() + 1);
    }

    public <T> Observable.Transformer<T, T> notifyOnNext(Class<?> cls) {

        return source -> source.doOnNext(value -> notifyChange(cls));
    }

    public <T> Observable<T> withRefetch(Class<?> lock, CallObservable f) {

        return getOrCreateNotifier(lock).switchMap((i) -> f.call());
    }

    public <T> Observable<T> withNotify(Class<?> lock, CallObservable f) {

        return f.<T>call().compose(notifyOnNext(lock));
    }

    public void notify(Class<?> lock, CallVoid f){

        f.call();
        notifyChange(lock);
    }

    interface CallObservable {

        <T> Observable<T> call();
    }

    interface CallVoid {

        void call();
    }
}
