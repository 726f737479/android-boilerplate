package com.brainbeanapps.android_boilerplate.ui;

import android.content.Context;

import com.brainbeanapps.core.di.context.UIContext;

import dagger.Module;
import dagger.Provides;

@Module
public class UIModule {

    @UIContext private Context context;

    public UIModule(@UIContext Context context) {
        this.context = context;
    }

    @Provides
    @UIContext Context provideContext(){
        return context;
    }
}
