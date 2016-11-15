package com.brainbeanapps.android_boilerplate.data.local;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = User.TABLE_NAME)
public class User {

    public static final String TABLE_NAME = "user";

    @DatabaseField private String  login;
    @DatabaseField private Integer id;
    @DatabaseField private String  avatarUrl;
    @DatabaseField private String  url;
    @DatabaseField private String  htmlUrl;
    @DatabaseField private String  name;
    @DatabaseField private String  company;
    @DatabaseField private String  blog;
    @DatabaseField private String  location;
    @DatabaseField private String  email;
    @DatabaseField private String  bio;
    @DatabaseField private Integer publicRepos;
    @DatabaseField private Integer publicGists;
    @DatabaseField private Integer followers;
    @DatabaseField private Integer following;
    @DatabaseField private String  createdAt;

    public User() {
    }
}
