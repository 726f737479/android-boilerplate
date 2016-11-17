package com.brainbeanapps.android_boilerplate.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.brainbeanapps.core.net.model.BaseResponse;

/**
 * Created by Rosty on 10/18/2016.
 */

public class UserResponse implements BaseResponse {

    @SerializedName ("login")
    @Expose private String login;

    @SerializedName("id")
    @Expose private Integer id;

    @SerializedName("avatar_url")
    @Expose private String avatarUrl;

    @SerializedName("url")
    @Expose private String url;

    @SerializedName("html_url")
    @Expose private String htmlUrl;

    @SerializedName("name")
    @Expose private String name;

    @SerializedName("company")
    @Expose private String company;

    @SerializedName("blog")
    @Expose private String blog;

    @SerializedName("location")
    @Expose private String location;

    @SerializedName("email")
    @Expose private String email;

    @SerializedName("bio")
    @Expose private String bio;

    @SerializedName("public_repos")
    @Expose private Integer publicRepos;

    @SerializedName("public_gists")
    @Expose private Integer publicGists;

    @SerializedName("followers")
    @Expose private Integer followers;

    @SerializedName("following")
    @Expose private Integer following;

    @SerializedName("created_at")
    @Expose private String createdAt;

    @SerializedName("is_saved")
    @Expose private boolean isSaved;

    public UserResponse() {
    }

    public UserResponse(final String userLogin, final String name, final String avatarUrl, final String bio) {
        this.login = userLogin;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.bio = bio;
    }

    public UserResponse(final String userLogin) {
        this.login = userLogin;
    }

    public String getLogin() {
        return login;
    }

    public Integer getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getBlog() {
        return blog;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public Integer getPublicRepos() {
        return publicRepos;
    }

    public Integer getPublicGists() {
        return publicGists;
    }

    public Integer getFollowers() {
        return followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }
}
