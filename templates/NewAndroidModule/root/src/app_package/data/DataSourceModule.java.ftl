package ${packageName}.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ${packageName}.data.local.DbHelper;
import ${packageName}.data.remote.ApiService;
import ${packageName}.BuildConfig;
import ${packageName}.data.local.Entity;
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

@Module
public class DataSourceModule {

    @ApplicationScope
    @Provides SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @ApplicationScope
    @Provides DataSource provideDataSource(DataSource dataSource){
        return dataSource;
    }

    @ApplicationScope
    @Provides ApiService provideApiService() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

            okHttpClient.newBuilder().addInterceptor(logging).build();
        }

        Retrofit.Builder builder = new Retrofit.Builder().client(okHttpClient)
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        Retrofit retrofit = builder.build();

        return retrofit.create(ApiService.class);
    }

    @ApplicationScope
    @Provides Dao<Entity, Integer> provideEntityDao(DbHelper dbHelper){

        try {
            return DaoManager.createDao(dbHelper.getConnectionSource(), Entity.class);
        } catch (SQLException e) {
            Timber.e(e.getMessage());
        }

        return null;
    }
}
