package ${packageName}.${className?lower_case};

import com.brainbeanapps.core.ui.presentation.BaseFragment;
import com.brainbeanapps.core.di.component.UIComponent;
import com.brainbeanapps.core.di.component.ApplicationComponent;

import ${applicationPackage}.R;
import ${applicationPackage}.${appName}AppComponent;
import ${applicationPackage}.databinding.Fragment${className}Binding;
import ${applicationPackage}.ui.UIModule;

public class ${className}Fragment extends BaseFragment<${className}Presenter, ${className}Contract.View, Fragment${className}Binding>
implements ${className}Contract.View {
 
@Override protected UIComponent buildComponent(ApplicationComponent appComponent) {
    return Dagger${className}Component.builder()
            .uIModule(new UIModule(getContext()))
            .talkRemitAppComponent((${appName}AppComponent)appComponent)
            .build();
}

@Override protected ${className}Contract.View getMvpView() {
    return this;
}

@Override protected int getLayoutResource() {
    return R.layout.fragment_${classToResource(className)};
}

}
