package com.brainbeanapps.android_boilerplate.ui.main.saved;

import com.brainbeanapps.android_boilerplate.BoilerplateAppComponent;
import com.brainbeanapps.android_boilerplate.databinding.FragmentSavedBinding;
import com.brainbeanapps.android_boilerplate.ui.UIModule;
import com.brainbeanapps.core.di.component.UIComponent;
import com.brainbeanapps.core.di.scope.UIScope;

import dagger.Component;

@UIScope
@Component (dependencies = {BoilerplateAppComponent.class}, modules = UIModule.class)
interface SavedComponent extends UIComponent<SavedPresenter, SavedContract.View, FragmentSavedBinding> {

}