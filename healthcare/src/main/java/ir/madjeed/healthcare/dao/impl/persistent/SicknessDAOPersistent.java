package ir.madjeed.healthcare.dao.impl.persistent;

import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import ir.madjeed.healthcare.dao.SicknessDAO;
import ir.madjeed.healthcare.logic.entity.impl.persistent.context.DatabaseHelper;
import ir.madjeed.healthcare.logic.entity.Sickness;
import ir.madjeed.healthcare.logic.entity.impl.persistent.SicknessPersistent;

import java.sql.SQLException;
import java.util.ArrayList;


public class SicknessDAOPersistent implements SicknessDAO{

    private final String LOG_TAG = getClass().getSimpleName();

    Dao<SicknessPersistent, Integer> instanceDao;

    public SicknessDAOPersistent(DatabaseHelper db)
    {
        try {
            instanceDao = db.getInstanceDao(SicknessPersistent.class);
        } catch (Exception e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        System.out.print(db);
        System.out.print(instanceDao.toString());
    }

    @Override
    public int create(Sickness instance)
    {
        try {
            return instanceDao.create((SicknessPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int update(Sickness instance)
    {
        try {
            return instanceDao.update((SicknessPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int delete(Sickness instance)
    {
        try {
            return instanceDao.delete((SicknessPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public Sickness getByID(Integer id)
    {
        try {
            QueryBuilder<SicknessPersistent, Integer> qb = instanceDao.queryBuilder();
            qb.where().eq("pk_column", id);
            PreparedQuery<SicknessPersistent> pq = qb.prepare();
            return instanceDao.queryForFirst(pq);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    @Override
    public ArrayList<Sickness> getAll()
    {
        QueryBuilder<SicknessPersistent, Integer> qb = instanceDao.queryBuilder();
        try {
            //(List<User>) (List<? extends User>) should be checked
            return new ArrayList<Sickness>(instanceDao.queryForAll());
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

}

