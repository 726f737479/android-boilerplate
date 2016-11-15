package com.brainbeanapps.android_boilerplate;

import com.brainbeanapps.android_boilerplate.data.DataSourceModule;
import com.brainbeanapps.core.*;
import com.brainbeanapps.core.log.LogcatTree;

import timber.log.Timber;

/**
 * Created by Rosty on 10/19/2016.
 */

public class BoilerplateApp extends CoreApp<BoilerplateAppComponent> {

    @Override public void onCreate() {
        super.onCreate();

        initLogging();
        logDeviceInfo();
    }

    @Override protected BoilerplateAppComponent getApplicationComponent() {
        return DaggerBoilerplateAppComponent.builder()
                .applicationModule(new ApplicationModule((this)))
                .dataSourceModule(new DataSourceModule())
                .build();
    }

    private void logDeviceInfo() {
        Timber.i("version: ${BuildConfig.VERSION_NAME}");
        Timber.i("build type: ${BuildConfig.BUILD_TYPE}");

        Timber.i("SystemInfo: Board: ${Build.BOARD}");
        Timber.i("SystemInfo: Brand: ${Build.BRAND}");
        Timber.i("SystemInfo: Device: ${Build.DEVICE}");
        Timber.i("SystemInfo: Model: ${Build.MODEL}");
        Timber.i("SystemInfo: Product: ${Build.PRODUCT}");
        Timber.i("SystemInfo: Display: ${Build.DISPLAY}");
        Timber.i("SystemInfo: SDK: ${Build.VERSION.SDK_INT}");
    }

    private void initLogging() {
        Timber.plant(new LogcatTree(BuildConfig.LOG_LEVEL));
    }
}
