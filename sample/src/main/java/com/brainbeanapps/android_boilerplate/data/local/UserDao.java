package com.brainbeanapps.android_boilerplate.data.local;

import com.j256.ormlite.dao.Dao;

import javax.inject.Inject;

/**
 * Created by Rosty on 10/19/2016.
 */

public class UserDao {

    private Dao<User, Integer> userDao;

    @Inject public UserDao(Dao<User, Integer> userDao) {
        this.userDao = userDao;
    }

    public Dao<User, Integer> get() {
        return userDao;
    }
}
