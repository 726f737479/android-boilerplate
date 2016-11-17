package ${packageName}.${className?lower_case};

import android.os.Bundle;

import com.brainbeanapps.core.di.component.ApplicationComponent;
import com.brainbeanapps.core.di.component.UIComponent;
import com.brainbeanapps.core.ui.presentation.BaseActivity;
import ${applicationPackage}.R;
import ${applicationPackage}.${appName}AppComponent;
import ${applicationPackage}.databinding.Activity${className}Binding;
import ${applicationPackage}.ui.UIModule;

public class ${className}Activity extends BaseActivity<${className}Presenter, ${className}Contract.View, Activity${className}Binding>
        implements ${className}Contract.View {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override protected UIComponent buildComponent(ApplicationComponent appComponent) {
        return Dagger${className}Component.builder()
                .uIModule(new UIModule(getContext()))
                .talkRemitAppComponent((${appName}AppComponent) appComponent)
                .build();
    }

    @Override protected ${className}Contract.View getMvpView() {
        return this;
    }

    @Override protected int getLayoutResource() {
            return R.layout.activity_${classToResource(className)};

    }
}
