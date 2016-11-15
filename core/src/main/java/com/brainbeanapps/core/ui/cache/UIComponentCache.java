package com.brainbeanapps.core.ui.cache;

import android.databinding.ViewDataBinding;

import com.brainbeanapps.core.di.component.UIComponent;
import com.brainbeanapps.core.mvp.BasePresenter;
import com.brainbeanapps.core.mvp.MvpView;

import java.util.HashMap;
import java.util.Map;


public class UIComponentCache {

    private final Map<String, UIComponent<?, ?, ?>> components = new HashMap<>();

    public <P extends BasePresenter<V>, V extends MvpView, B extends ViewDataBinding> UIComponent<P, V, B> get(String key) {

        if (!hasComponent(key)){

            throw new IllegalStateException("presenter with given key " + key + " doesn't exist");

        } else return (UIComponent<P, V, B>) components.get(key);
    }

    public void put(String key, UIComponent<?, ?, ?> component){
        components.put(key, component);
    }

    public void remove(String key) {
        components.remove(key);
    }

    public boolean hasComponent(String key) {
        return components.containsKey(key);
    }
}
