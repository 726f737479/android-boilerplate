package ${packageName}.${className?lower_case};

import com.brainbeanapps.core.di.component.UIComponent;
import com.brainbeanapps.core.di.scope.UIScope;

import dagger.Component;

import ${applicationPackage}.${appName}AppComponent;
import ${applicationPackage}.ui.UIModule;
import ${applicationPackage}.databinding.Fragment${className}Binding;

@UIScope
@Component (dependencies = {${appName}AppComponent.class}, modules = UIModule.class)
interface ${className}Component extends UIComponent<${className}Presenter, ${className}Contract.View, Fragment${className}Binding> {

}
