package com.brainbeanapps.core;

import android.app.Application;
import android.content.Context;

import com.brainbeanapps.core.di.component.ApplicationComponent;
import com.brainbeanapps.core.ui.cache.UIComponentCache;

import javax.inject.Inject;

public abstract class CoreApp<T extends ApplicationComponent> extends Application {

    @Inject UIComponentCache componentCache;

    private T component;

    @Override public void onCreate() {
        super.onCreate();

        component = getApplicationComponent();
        component.inject(this);
    }

    /**
     * Core App have to be used only for obtaining {@link #component} and {@link #componentCache}
     *
     * @param context - {@link Context}
     * @return instance of Application
     */
    public static CoreApp get(Context context) {
        return (CoreApp) context.getApplicationContext();
    }

    /**
     * @return {@link ApplicationComponent}
     */
    public T getComponent() {
        return component;
    }

    /**
     * @return {@link UIComponentCache}
     */
    public UIComponentCache getComponentCache() {
        return componentCache;
    }

    protected abstract T getApplicationComponent();
}
