package ir.madjeed.healthcare.logic.domain.impl.persistent.context;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import ir.madjeed.healthcare.logic.entity.impl.persistent.UserPersistent;

// dont forget to add daos here.

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "db.sqlite";
    private static final int DATABASE_VERSION = 1;

    private Dao<UserPersistent, String> userDao = null;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // it will be called as first query received
    // it wont be called when app reinstalled if db exist
    // don't use other helpers!
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, UserPersistent.class);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }


    // it will be called when app upgraded to newer version
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");

            TableUtils.dropTable(connectionSource, UserPersistent.class, true);

            onCreate(db);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }


    public <C, D> Dao<C, D> getInstanceDao(Class typeC) throws SQLException {
        if (typeC.equals(UserPersistent.class)) {
            if (userDao == null) userDao = DaoManager.createDao(getConnectionSource(), typeC );
            return (Dao<C, D>) userDao;
        }
        return null;
    }


    @Override
    public void close() {
        super.close();
        userDao = null;
    }
}
