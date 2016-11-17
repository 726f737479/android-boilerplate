package ${packageName};

import ${packageName}.data.DataSource;
import ${packageName}.data.DataSourceModule;
import com.brainbeanapps.core.di.component.ApplicationComponent;
import com.brainbeanapps.core.di.scope.ApplicationScope;
import com.brainbeanapps.core.reactive.RxSchedulers;
import com.brainbeanapps.core.ui.navigation.ScreenRouterManager;

import dagger.Component;

@ApplicationScope
@Component (modules = {DataSourceModule.class, ${extractLetters(appTitle)}AppModule.class})
public interface ${extractLetters(appTitle)}AppComponent extends ApplicationComponent<${extractLetters(appTitle)}AppComponent>{

    DataSource dataSource();
    ScreenRouterManager screenRouterManager();
    RxSchedulers rxSchedulers();

}
