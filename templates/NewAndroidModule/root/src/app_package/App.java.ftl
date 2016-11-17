package ${packageName};

import ${packageName}.data.DataSourceModule;
import com.brainbeanapps.core.*;
import com.brainbeanapps.core.log.LogcatTree;

import timber.log.Timber;

public class ${extractLetters(appTitle)}App extends CoreApp<${extractLetters(appTitle)}AppComponent> {

    @Override public void onCreate() {
        super.onCreate();

        initLogging();
        logDeviceInfo();
    }

    @Override protected ${extractLetters(appTitle)}AppComponent getApplicationComponent() {
        return Dagger${extractLetters(appTitle)}AppComponent.builder()
                .applicationModule(new ApplicationModule((this)))
                .dataSourceModule(new DataSourceModule())
                .build();
    }

    private void logDeviceInfo() {
        Timber.i("version: ".concat(BuildConfig.VERSION_NAME));
        Timber.i("build type: ".concat(BuildConfig.BUILD_TYPE));

        Timber.i("SystemInfo: Board: ".concat(Build.BOARD));
        Timber.i("SystemInfo: Brand: ".concat(Build.BRAND));
        Timber.i("SystemInfo: Device: ".concat(Build.DEVICE));
        Timber.i("SystemInfo: Model: ".concat(Build.MODEL));
        Timber.i("SystemInfo: Product: ".concat(Build.PRODUCT));
        Timber.i("SystemInfo: Display: ".concat(Build.DISPLAY));
        Timber.i("SystemInfo: SDK: ".concat(Build.VERSION.SDK_INT));
    }

    private void initLogging() {
        Timber.plant(new LogcatTree(BuildConfig.LOG_LEVEL));
    }
}
