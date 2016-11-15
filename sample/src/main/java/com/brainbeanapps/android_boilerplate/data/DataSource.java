package com.brainbeanapps.android_boilerplate.data;

import com.brainbeanapps.android_boilerplate.data.remote.model.UserResponse;

import java.util.List;

import rx.Observable;

public interface DataSource {

    Observable<List<UserResponse>> searchUsers(String searchTerm);
}
