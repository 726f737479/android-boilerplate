package ${packageName}.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.brainbeanapps.core.di.context.ApplicationContext;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Rosty on 10/19/2016.
 */

public class DbHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME    = "${packageName}";
    private static final int    DB_VERSION = 1;

    @Inject public DbHelper(@ApplicationContext Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.clearTable(connectionSource, Entity.class);
        } catch (SQLException e) {
            Timber.e(e.getMessage());
        }
    }

    @Override public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
