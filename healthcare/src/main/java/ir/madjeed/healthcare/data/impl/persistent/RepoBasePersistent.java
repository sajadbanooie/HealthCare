package ir.madjeed.healthcare.data.impl.persistent;

import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import ir.madjeed.healthcare.data.RepoBase;
import ir.madjeed.healthcare.data.impl.persistent.context.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

public class RepoBasePersistent<C, D> implements RepoBase<C, D> {

    private Class<C> typeC;
    private final String LOG_TAG = getClass().getSimpleName();

    Dao<C, D> instanceDao;

    public RepoBasePersistent(DatabaseHelper db, Class<C> typeC)
    {
        try {
            instanceDao = db.getInstanceDao(typeC);
            this.typeC = typeC;
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
    }

    public Class<C> getRepoEntityType(){
        return this.typeC;
    }

    @Override
    public int create(C instance)
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
    public int update(C instance)
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
    public int delete(C instance)
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
    public C getByID(D id)
    {
        try {
            QueryBuilder<C, D> qb = instanceDao.queryBuilder();

            // should change it to sth like id_column n\
            qb.where().eq("pk_column", id);
            PreparedQuery<C> pq = qb.prepare();
            return instanceDao.queryForFirst(pq);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    @Override
    public List<C> getAll()
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

