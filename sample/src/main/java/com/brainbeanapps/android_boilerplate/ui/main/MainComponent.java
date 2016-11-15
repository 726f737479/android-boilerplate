package com.brainbeanapps.android_boilerplate.ui.main;

import com.brainbeanapps.android_boilerplate.BoilerplateAppComponent;
import com.brainbeanapps.core.di.component.UIComponent;
import com.brainbeanapps.android_boilerplate.databinding.ActivityMainBinding;
import com.brainbeanapps.android_boilerplate.ui.UIModule;
import com.brainbeanapps.core.di.scope.UIScope;

import dagger.Component;

@UIScope
@Component (dependencies = {BoilerplateAppComponent.class}, modules = UIModule.class)
interface MainComponent extends UIComponent<MainPresenter, MainContract.View, ActivityMainBinding> {

}
