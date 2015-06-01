package ir.madjeed.healthcare.dao.impl.persistent;

import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import ir.madjeed.healthcare.dao.PhysicalActivityDAO;
import ir.madjeed.healthcare.logic.entity.PhysicalActivity;
import ir.madjeed.healthcare.logic.entity.impl.persistent.PhysicalActivityPersistent;
import ir.madjeed.healthcare.logic.entity.impl.persistent.context.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;


public class PhysicalActivityDAOPersistent implements PhysicalActivityDAO{

    private final String LOG_TAG = getClass().getSimpleName();

    Dao<PhysicalActivityPersistent, Integer> instanceDao;

    public PhysicalActivityDAOPersistent(DatabaseHelper db)
    {
        try {
            instanceDao = db.getInstanceDao(PhysicalActivityPersistent.class);
        } catch (Exception e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        System.out.print(db);
        System.out.print(instanceDao.toString());
    }

    @Override
    public int create(PhysicalActivity instance)
    {
        try {
            return instanceDao.create((PhysicalActivityPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int update(PhysicalActivity instance)
    {
        try {
            return instanceDao.update((PhysicalActivityPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int delete(PhysicalActivity instance)
    {
        try {
            return instanceDao.delete((PhysicalActivityPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public PhysicalActivity getByID(Integer id)
    {
        try {
            QueryBuilder<PhysicalActivityPersistent, Integer> qb = instanceDao.queryBuilder();
            qb.where().eq("pk_column", id);
            PreparedQuery<PhysicalActivityPersistent> pq = qb.prepare();
            return instanceDao.queryForFirst(pq);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    @Override
    public ArrayList<PhysicalActivity> getAll()
    {
        QueryBuilder<PhysicalActivityPersistent, Integer> qb = instanceDao.queryBuilder();
        try {
            //(List<User>) (List<? extends User>) should be checked
            return new ArrayList<PhysicalActivity>(instanceDao.queryForAll());
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

}

