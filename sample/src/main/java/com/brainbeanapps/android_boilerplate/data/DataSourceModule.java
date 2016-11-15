package com.brainbeanapps.android_boilerplate.data;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.brainbeanapps.android_boilerplate.data.local.DbHelper;
import com.brainbeanapps.android_boilerplate.data.local.User;
import com.brainbeanapps.android_boilerplate.data.remote.GithubApiService;
import com.brainbeanapps.core.di.context.ApplicationContext;
import com.brainbeanapps.core.di.scope.ApplicationScope;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by Rosty on 6/1/2016.
 */

@Module
public class DataSourceModule {

    @ApplicationScope
    @Provides SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @ApplicationScope
    @Provides DataSource provideDataSource(BoilerplateDataSource dataSource){
        return dataSource;
    }

    @ApplicationScope
    @Provides GithubApiService provideGithubApiService() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging).build();

        Retrofit.Builder builder = new Retrofit.Builder().client(okHttpClient)
                .baseUrl(GithubApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        Retrofit retrofit = builder.build();

        return retrofit.create(GithubApiService.class);
    }

    @ApplicationScope
    @Provides Dao<User, Integer> provideNumberDao(DbHelper dbHelper){

        try {
            return DaoManager.createDao(dbHelper.getConnectionSource(), User.class);
        } catch (SQLException e) {
            Timber.e(e.getMessage());
        }

        return null;
    }
}
