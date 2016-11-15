package com.brainbeanapps.core.di.component;

import android.content.Context;

import com.brainbeanapps.core.CoreApp;
import com.brainbeanapps.core.di.context.ApplicationContext;
import com.brainbeanapps.core.reactive.RxSchedulers;


public interface ApplicationComponent<T extends ApplicationComponent> {

    @ApplicationContext Context context();
    RxSchedulers rxSchedulers();

    void inject(CoreApp<T> app);
}
