package com.brainbeanapps.core.ui.presentation;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brainbeanapps.core.CoreApp;
import com.brainbeanapps.core.di.component.ApplicationComponent;
import com.brainbeanapps.core.di.component.UIComponent;
import com.brainbeanapps.core.mvp.BasePresenter;
import com.brainbeanapps.core.mvp.MvpView;
import com.brainbeanapps.core.ui.cache.UIComponentCache;

import java.util.UUID;

import javax.inject.Inject;

public abstract class BaseFragment<P extends BasePresenter<V>, V extends MvpView, B extends ViewDataBinding> extends Fragment implements MvpView {

    private static final String KEY_COMPONENT_ID = "key_component_id";

    private UIComponentCache componentCache;
    private String           componentId;

    protected B binding;

    @Inject P presenter;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        componentCache = CoreApp
                .get(getContext().getApplicationContext())
                .getComponentCache();

        componentId = savedInstanceState == null
                ? UUID.randomUUID().toString()
                : savedInstanceState.getString(KEY_COMPONENT_ID);

        getComponent().inject(this);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false);

        return binding.getRoot();
    }

    @Override public void onStart() {

        super.onStart();
        presenter.attach(getMvpView());
    }

    @Override public void onStop() {

        presenter.detach();
        super.onStop();
    }

    @Override public void onSaveInstanceState(Bundle outState) {

        outState.putString(KEY_COMPONENT_ID, componentId);
        super.onSaveInstanceState(outState);
    }

    @Override public void onDestroy() {

        if (isRemoving() || (getActivity() != null && getActivity().isFinishing())) {

            presenter.destroy();
            componentCache.remove(componentId);
            presenter = null;
        }

        super.onDestroy();
    }

    @Override public void showInformationToast(String message) {

        if(!isDetached()) ((BaseActivity) getActivity()).showInformationToast(message);
    }

    @Override public void requestPermission(String[] missingPermission, int requestCode) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requestPermissions(missingPermission, requestCode);
    }

    @Override public void onRequestPermissionsResult(int requestCode,
                                                     @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(requestCode);
    }

    protected abstract UIComponent<P, V, B> buildComponent(ApplicationComponent appComponent);

    protected abstract V getMvpView();

    protected abstract @LayoutRes int getLayoutResource();

    protected P getPresenter(){
        return presenter;
    }

    private UIComponent<P, V, B> getComponent() {

        if (!componentCache.hasComponent(componentId)) {

            componentCache.put(componentId, buildComponent(
                    CoreApp.get(getContext().getApplicationContext()).getComponent()));
        }

        return componentCache.get(componentId);
    }
}
