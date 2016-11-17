package ${packageName}.${className?lower_case};

import com.brainbeanapps.core.di.component.UIComponent;
import com.brainbeanapps.core.di.scope.UIScope;
import ${applicationPackage}.${appName}AppComponent;
import ${applicationPackage}.databinding.Activity${className}Binding;
import ${applicationPackage}.ui.UIModule;

import dagger.Component;


@UIScope
@Component (dependencies = {${appName}AppComponent.class}, modules = UIModule.class)
interface ${className}Component extends UIComponent<${className}Presenter, ${className}Contract.View, Activity${className}Binding> {

}

