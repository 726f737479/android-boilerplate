package com.brainbeanapps.core.ui.presentation;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.brainbeanapps.core.CoreApp;
import com.brainbeanapps.core.R;
import com.brainbeanapps.core.di.component.ApplicationComponent;
import com.brainbeanapps.core.di.component.UIComponent;
import com.brainbeanapps.core.mvp.BasePresenter;
import com.brainbeanapps.core.mvp.MvpView;
import com.brainbeanapps.core.ui.cache.UIComponentCache;
import com.brainbeanapps.core.ui.navigation.ScreenRouter;
import com.brainbeanapps.core.ui.navigation.ScreenRouterManager;

import java.util.UUID;

import javax.inject.Inject;

import timber.log.Timber;

public abstract class BaseActivity<P extends BasePresenter<V>, V extends MvpView, B extends ViewDataBinding> extends AppCompatActivity implements MvpView, ScreenRouter {

    private static final String KEY_COMPONENT_ID = "key_component_id";

    private UIComponentCache componentCache;
    private String           componentId;
    private Toast            informationToast;

    protected B binding;

    @Inject ScreenRouterManager screenRouterManager;
    @Inject P                   presenter;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        componentCache = CoreApp
                .get(getApplicationContext())
                .getComponentCache();

        componentId = savedInstanceState == null
                ? UUID.randomUUID().toString()
                : savedInstanceState.getString(KEY_COMPONENT_ID);

        binding = DataBindingUtil.setContentView(this, getLayoutResource());

        getComponent().inject(this);
    }

    @Override public void onStart() {

        super.onStart();
        screenRouterManager.setRouter(this);
        presenter.attach(getMvpView());
    }

    @Override public void onStop() {

        presenter.detach();
        screenRouterManager.removeRouter();
        super.onStop();
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_COMPONENT_ID, componentId);
        super.onSaveInstanceState(outState);
    }

    @Override public void onDestroy() {

        if (isFinishing()) {

            presenter.destroy();
            componentCache.remove(componentId);
            presenter = null;
        }

        super.onDestroy();
    }

    @Override public Context getContext() {
        return this;
    }

    @Override public void showInformationToast(String message) {

        if (informationToast != null) informationToast.cancel();
        else informationToast = Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);

        informationToast.setText(message);
        informationToast.setDuration(Toast.LENGTH_SHORT);
        informationToast.show();
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

    @Override public void finishScreen() {
        finish();
    }

    @Override public void changeScreen(Intent intent) {
        startActivity(intent);
    }

    @Override public void changeScreen(Intent intent, View sharedElement) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(this, sharedElement, getString(R.string.shared_element));

            startActivity(intent, options.toBundle());

        } else startActivity(intent);
    }

    @Override public void changeScreen(Fragment fragment) {
        Timber.w("Change Screen: this screen don't support fragment transaction");
    }

    @Override public void changeScreen(Fragment fragment, View sharedElement) {
        Timber.w("Change Screen: this screen don't support fragment transaction");
    }

    @Override public void changeScreenWithResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    @Override public void changeScreenWithResult(Intent intent, View sharedElement, int requestCode) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(this, sharedElement, getString(R.string.shared_element));

            startActivityForResult(intent, requestCode, options.toBundle());

        } else startActivityForResult(intent, requestCode);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        screenRouterManager.activityResult(requestCode, resultCode, data);
    }

    protected abstract UIComponent<P, V, B> buildComponent(ApplicationComponent appComponent);

    protected abstract V getMvpView();

    protected abstract @LayoutRes int getLayoutResource();

    protected P getPresenter() {
        return presenter;
    }

    protected Fragment getPreviousFragmentIfExist(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();

        Fragment prevFragment = manager.findFragmentByTag(fragment.getClass().getSimpleName());

        return prevFragment != null ? prevFragment : fragment;
    }

    private UIComponent<P, V, B> getComponent() {

        if (!componentCache.hasComponent(componentId))
            componentCache.put(componentId, buildComponent(CoreApp.get(getApplicationContext()).getComponent()));

        return componentCache.get(componentId);
    }
}
