package ir.madjeed.healthcare.data.repo.impl.persistent;

import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import ir.madjeed.healthcare.data.entity.User;
import ir.madjeed.healthcare.data.repo.RepoUser;
import ir.madjeed.healthcare.data.repo.impl.persistent.context.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

public class RepoUserPersistent implements RepoUser {

    private final String LOG_TAG = getClass().getSimpleName();

    Dao<User, String> instanceDao;

    public RepoUserPersistent(DatabaseHelper db)
    {
        try {
            instanceDao = db.getInstanceDao(User.class);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
    }

    @Override
    public int create(User instance)
    {
        try {
            return instanceDao.create(instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int update(User instance)
    {
        try {
            return instanceDao.update(instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int delete(User instance)
    {
        try {
            return instanceDao.delete(instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public User getByID(String id)
    {
        try {
            QueryBuilder<User, String> qb = instanceDao.queryBuilder();

            // should change it to sth like id_column n\
            qb.where().eq("pk_column", id);
            PreparedQuery<User> pq = qb.prepare();
            return instanceDao.queryForFirst(pq);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    @Override
    public List<User> getAll()
    {
        try {
            return instanceDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

}

