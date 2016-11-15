package com.brainbeanapps.android_boilerplate.data.remote;

import com.brainbeanapps.android_boilerplate.data.remote.model.UserResponse;
import com.brainbeanapps.android_boilerplate.data.remote.model.UsersListResponse;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Rosty on 10/18/2016.
 */

public interface GithubApiService {

    String BASE_URL = "https://api.github.com";

    @GET ("/search/users?per_page=2")
    Observable<Response<UsersListResponse>> searchGithubUsers(@Query ("q") String searchTerm);

    @GET("/users/{username}")
    Observable<Response<UserResponse>> getUser(@Path ("username") String username);
}
