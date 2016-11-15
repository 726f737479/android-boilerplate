package com.brainbeanapps.android_boilerplate.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.brainbeanapps.core.net.model.BaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rosty on 10/18/2016.
 */

public class UsersListResponse implements BaseResponse {

    @SerializedName ("total_count")
    @Expose private Integer totalCount;

    @SerializedName ("incomplete_results")
    @Expose private Boolean incompleteResults;

    @SerializedName ("items")
    @Expose private List<UserResponse> items = new ArrayList<UserResponse>();

    public UsersListResponse(final List<UserResponse> githubUserResponses) {
        this.items = githubUserResponses;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public List<UserResponse> getItems() {
        return items;
    }
}
