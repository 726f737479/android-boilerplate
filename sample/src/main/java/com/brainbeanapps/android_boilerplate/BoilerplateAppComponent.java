package com.brainbeanapps.android_boilerplate;

import com.brainbeanapps.android_boilerplate.data.DataSource;
import com.brainbeanapps.android_boilerplate.data.DataSourceModule;
import com.brainbeanapps.core.di.component.ApplicationComponent;
import com.brainbeanapps.core.di.scope.ApplicationScope;
import com.brainbeanapps.core.reactive.RxSchedulers;
import com.brainbeanapps.core.ui.navigation.ScreenRouterManager;

import dagger.Component;

/**
 * Created by Rosty on 10/19/2016.
 */
@ApplicationScope
@Component (modules = {DataSourceModule.class, ApplicationModule.class})
public interface BoilerplateAppComponent extends ApplicationComponent<BoilerplateAppComponent>{

    DataSource dataSource();
    ScreenRouterManager screenRouterManager();
    RxSchedulers rxSchedulers();

}
