package com.brainbeanapps.core.di.component;

import android.databinding.ViewDataBinding;

import com.brainbeanapps.core.mvp.BasePresenter;
import com.brainbeanapps.core.mvp.MvpView;
import com.brainbeanapps.core.ui.presentation.BaseActivity;
import com.brainbeanapps.core.ui.presentation.BaseFragment;

public interface UIComponent <P extends BasePresenter<V>, V extends MvpView, B extends ViewDataBinding> {

    void inject(BaseActivity<P, V, B> activity);
    void inject(BaseFragment<P, V, B> fragment);

    P p();
}
