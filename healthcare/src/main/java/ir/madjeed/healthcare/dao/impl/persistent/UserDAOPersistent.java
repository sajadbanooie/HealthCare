package ir.madjeed.healthcare.dao.impl.persistent;

import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import ir.madjeed.healthcare.logic.entity.User;
import ir.madjeed.healthcare.dao.UserDAO;
import ir.madjeed.healthcare.logic.domain.impl.persistent.context.DatabaseHelper;
import ir.madjeed.healthcare.logic.entity.impl.persistent.UserPersistent;

import java.sql.SQLException;
import java.util.ArrayList;


public class UserDAOPersistent implements UserDAO {

    private final String LOG_TAG = getClass().getSimpleName();

    Dao<UserPersistent, String> instanceDao;

    public UserDAOPersistent(DatabaseHelper db)
    {
        try {
            instanceDao = db.getInstanceDao(UserPersistent.class);
        } catch (Exception e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        System.out.print(db);
        System.out.print(instanceDao.toString());
    }

    @Override
    public int create(User instance)
    {
        try {
            return instanceDao.create((UserPersistent) instance);
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
            return instanceDao.update((UserPersistent) instance);
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
            return instanceDao.delete((UserPersistent) instance);
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
            QueryBuilder<UserPersistent, String> qb = instanceDao.queryBuilder();
            qb.where().eq("pk_column", id);
            PreparedQuery<UserPersistent> pq = qb.prepare();
            return instanceDao.queryForFirst(pq);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    @Override
    public ArrayList<User> getAll()
    {
        try {
            //(List<User>) (List<? extends User>) should be checked
            return new ArrayList<User>(instanceDao.queryForAll());
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

}

