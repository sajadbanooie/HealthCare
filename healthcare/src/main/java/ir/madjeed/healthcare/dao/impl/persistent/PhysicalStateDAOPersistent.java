package ir.madjeed.healthcare.dao.impl.persistent;

import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import ir.madjeed.healthcare.dao.PhysicalStateDAO;
import ir.madjeed.healthcare.logic.domain.impl.persistent.context.DatabaseHelper;
import ir.madjeed.healthcare.logic.entity.PhysicalState;
import ir.madjeed.healthcare.logic.entity.impl.persistent.PhysicalStatePersistent;

import java.sql.SQLException;
import java.util.ArrayList;


public class PhysicalStateDAOPersistent implements PhysicalStateDAO{

    private final String LOG_TAG = getClass().getSimpleName();

    Dao<PhysicalStatePersistent, Integer> instanceDao;

    public PhysicalStateDAOPersistent(DatabaseHelper db)
    {
        try {
            instanceDao = db.getInstanceDao(PhysicalStatePersistent.class);
        } catch (Exception e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        System.out.print(db);
        System.out.print(instanceDao.toString());
    }

    @Override
    public int create(PhysicalState instance)
    {
        try {
            return instanceDao.create((PhysicalStatePersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int update(PhysicalState instance)
    {
        try {
            return instanceDao.update((PhysicalStatePersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int delete(PhysicalState instance)
    {
        try {
            return instanceDao.delete((PhysicalStatePersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public PhysicalState getByID(Integer id)
    {
        try {
            QueryBuilder<PhysicalStatePersistent, Integer> qb = instanceDao.queryBuilder();
            qb.where().eq("pk_column", id);
            PreparedQuery<PhysicalStatePersistent> pq = qb.prepare();
            return instanceDao.queryForFirst(pq);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    @Override
    public ArrayList<PhysicalState> getAll()
    {
        QueryBuilder<PhysicalStatePersistent, Integer> qb = instanceDao.queryBuilder();
        try {
            //(List<User>) (List<? extends User>) should be checked
            return new ArrayList<PhysicalState>(instanceDao.queryForAll());
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

}

