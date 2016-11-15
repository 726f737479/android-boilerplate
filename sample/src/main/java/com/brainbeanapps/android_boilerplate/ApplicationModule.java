package com.brainbeanapps.android_boilerplate;

import android.content.Context;

import com.brainbeanapps.core.di.context.ApplicationContext;
import com.brainbeanapps.core.di.scope.ApplicationScope;
import com.brainbeanapps.core.reactive.BoilerplateRxSchedulers;
import com.brainbeanapps.core.reactive.RxSchedulers;
import com.brainbeanapps.core.ui.cache.UIComponentCache;
import com.brainbeanapps.core.ui.navigation.ScreenRouterManager;

import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {

    @ApplicationContext private Context context;

    public ApplicationModule(@ApplicationContext Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationContext Context provideContext(){
        return context;
    }

    @Provides
    @ApplicationScope UIComponentCache provideUIComponentProvider(){
        return new UIComponentCache();
    }

    @Provides
    @ApplicationScope public RxSchedulers provideRxSchedulers(){
        return new BoilerplateRxSchedulers();
    }

    @Provides
    @ApplicationScope public ScreenRouterManager provideRouterManager(@ApplicationContext Context context){
        return new ScreenRouterManager(context);
    }
}