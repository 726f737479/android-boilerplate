package com.brainbeanapps.android_boilerplate.ui.main.search;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.brainbeanapps.android_boilerplate.BoilerplateAppComponent;
import com.brainbeanapps.android_boilerplate.R;
import com.brainbeanapps.android_boilerplate.data.remote.model.UserResponse;
import com.brainbeanapps.android_boilerplate.databinding.FragmentSearchBinding;
import com.brainbeanapps.android_boilerplate.ui.UIModule;
import com.brainbeanapps.core.di.component.ApplicationComponent;
import com.brainbeanapps.core.di.component.UIComponent;
import com.brainbeanapps.core.ui.presentation.BaseFragment;

import java.util.List;


public class SearchFragment extends BaseFragment<SearchPresenter, SearchContract.View, FragmentSearchBinding> implements SearchContract.View {

    public final SearchResultsAdapter searchAdapter = new SearchResultsAdapter();

    public ObservableField<String> searchTerm;


    @Override protected UIComponent buildComponent(ApplicationComponent appComponent) {
        return DaggerSearchComponent.builder()
                .uIModule(new UIModule(getContext()))
                .boilerplateAppComponent((BoilerplateAppComponent) appComponent)
                .build();
    }
    @Override protected SearchContract.View getMvpView() {
        return this;
    }
    @Override protected int getLayoutResource() {
        return R.layout.fragment_search;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.setSearchVM(this);

        binding.searchList.setAdapter(searchAdapter);
        binding.searchList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override public void setSearchTermObservableField(ObservableField<String> searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override public void showSearchResults(List<UserResponse> githubUserResponseList) {
        searchAdapter.updateData(githubUserResponseList);
    }

    @Override public void showLoading() {
        Toast.makeText(getContext(), "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override public void hideLoading() {
        Toast.makeText(getContext(), "Loaded", Toast.LENGTH_SHORT).show();
    }
}
