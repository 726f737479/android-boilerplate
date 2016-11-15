package com.brainbeanapps.android_boilerplate.ui.details;

import com.brainbeanapps.android_boilerplate.BoilerplateAppComponent;
import com.brainbeanapps.android_boilerplate.databinding.FragmentDetailsBinding;
import com.brainbeanapps.android_boilerplate.databinding.FragmentSearchBinding;
import com.brainbeanapps.core.di.component.UIComponent;
import com.brainbeanapps.android_boilerplate.ui.UIModule;
import com.brainbeanapps.core.di.scope.UIScope;

import dagger.Component;

@UIScope
@Component (dependencies = {BoilerplateAppComponent.class}, modules = UIModule.class)
interface DetailsComponent extends UIComponent<DetailsPresenter, DetailsContract.View, FragmentDetailsBinding> {
}
