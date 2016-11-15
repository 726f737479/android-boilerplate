package com.brainbeanapps.android_boilerplate.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;

import com.brainbeanapps.android_boilerplate.BoilerplateAppComponent;
import com.brainbeanapps.android_boilerplate.R;
import com.brainbeanapps.android_boilerplate.databinding.ActivityMainBinding;
import com.brainbeanapps.android_boilerplate.ui.UIModule;
import com.brainbeanapps.android_boilerplate.ui.main.saved.SavedFragment;
import com.brainbeanapps.android_boilerplate.ui.main.search.SearchFragment;
import com.brainbeanapps.core.di.component.ApplicationComponent;
import com.brainbeanapps.core.di.component.UIComponent;
import com.brainbeanapps.core.ui.presentation.BaseActivity;
import com.brainbeanapps.core.ui.presentation.BasePagerAdapter;

public class MainActivity extends BaseActivity<MainPresenter, MainContract.View, ActivityMainBinding>
        implements MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener {

    private BasePagerAdapter basePagerAdapter = new BasePagerAdapter(getSupportFragmentManager());

    @Override protected UIComponent buildComponent(ApplicationComponent appComponent) {
        return DaggerMainComponent.builder()
                .uIModule(new UIModule(getContext()))
                .boilerplateAppComponent((BoilerplateAppComponent) appComponent)
                .build();
    }

    @Override protected MainContract.View getMvpView() {
        return this;
    }
    @Override protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(binding.toolbar);

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setHomeButtonEnabled(false);
            bar.setDisplayHomeAsUpEnabled(false);
        }

        basePagerAdapter.addFragment(new SearchFragment());
        basePagerAdapter.addFragment(new SavedFragment());

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);
        binding.viewpager.setAdapter(basePagerAdapter);
        binding.viewpager.setPageTransformer(false, new FadePageTransformer());
        binding.viewpager.setOnTouchListener((view, motionEvent) -> true);
    }

    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                binding.viewpager.setCurrentItem(0);
                break;

            case R.id.action_favorites:
                binding.viewpager.setCurrentItem(1);
                break;

            case R.id.action_settings:

                break;
        }

        return false;
    }

    private static class FadePageTransformer implements ViewPager.PageTransformer {
        public void transformPage(View view, float position) {
            if(position <= -1.0F || position >= 1.0F) {
                view.setTranslationX(view.getWidth() * position);
                view.setAlpha(0.0F);
            } else if( position == 0.0F ) {
                view.setTranslationX(view.getWidth() * position);
                view.setAlpha(1.0F);
            } else {
                // position is between -1.0F & 0.0F OR 0.0F & 1.0F
                view.setTranslationX(view.getWidth() * -position);
                view.setAlpha(1.0F - Math.abs(position));
            }
        }
    }
}
