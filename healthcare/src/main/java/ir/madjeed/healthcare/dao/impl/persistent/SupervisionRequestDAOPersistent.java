package ir.madjeed.healthcare.dao.impl.persistent;

import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import ir.madjeed.healthcare.dao.SupervisionRequestDAO;
import ir.madjeed.healthcare.logic.domain.impl.persistent.context.DatabaseHelper;
import ir.madjeed.healthcare.logic.entity.SupervisionRequest;
import ir.madjeed.healthcare.logic.entity.impl.persistent.SupervisionRequestPersistent;

import java.sql.SQLException;
import java.util.ArrayList;


public class SupervisionRequestDAOPersistent implements SupervisionRequestDAO {

    private final String LOG_TAG = getClass().getSimpleName();

    Dao<SupervisionRequestPersistent, Integer> instanceDao;

    public SupervisionRequestDAOPersistent(DatabaseHelper db)
    {
        try {
            instanceDao = db.getInstanceDao(SupervisionRequestPersistent.class);
        } catch (Exception e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        System.out.print(db);
        System.out.print(instanceDao.toString());
    }

    @Override
    public int create(SupervisionRequest instance)
    {
        try {
            return instanceDao.create((SupervisionRequestPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int update(SupervisionRequest instance)
    {
        try {
            return instanceDao.update((SupervisionRequestPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int delete(SupervisionRequest instance)
    {
        try {
            return instanceDao.delete((SupervisionRequestPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public SupervisionRequest getByID(Integer id)
    {
        try {
            QueryBuilder<SupervisionRequestPersistent, Integer> qb = instanceDao.queryBuilder();
            qb.where().eq("pk_column", id);
            PreparedQuery<SupervisionRequestPersistent> pq = qb.prepare();
            return instanceDao.queryForFirst(pq);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    @Override
    public ArrayList<SupervisionRequest> getAll()
    {
        QueryBuilder<SupervisionRequestPersistent, Integer> qb = instanceDao.queryBuilder();
        try {
            //(List<User>) (List<? extends User>) should be checked
            return new ArrayList<SupervisionRequest>(instanceDao.queryForAll());
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

}

