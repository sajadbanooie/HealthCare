package ir.madjeed.healthcare.dao.impl.persistent;

import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import ir.madjeed.healthcare.dao.DrugDAO;
import ir.madjeed.healthcare.logic.entity.impl.persistent.context.DatabaseHelper;
import ir.madjeed.healthcare.logic.entity.Drug;
import ir.madjeed.healthcare.logic.entity.impl.persistent.DrugPersistent;

import java.sql.SQLException;
import java.util.ArrayList;


public class DrugDAOPersistent implements DrugDAO{

    private final String LOG_TAG = getClass().getSimpleName();

    Dao<DrugPersistent, Integer> instanceDao;

    public DrugDAOPersistent(DatabaseHelper db)
    {
        try {
            instanceDao = db.getInstanceDao(DrugPersistent.class);
        } catch (Exception e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        System.out.print(db);
        System.out.print(instanceDao.toString());
    }

    @Override
    public int create(Drug instance)
    {
        try {
            return instanceDao.create((DrugPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int update(Drug instance)
    {
        try {
            return instanceDao.update((DrugPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int delete(Drug instance)
    {
        try {
            return instanceDao.delete((DrugPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public Drug getByID(Integer id)
    {
        try {
            QueryBuilder<DrugPersistent, Integer> qb = instanceDao.queryBuilder();
            qb.where().eq("pk_column", id);
            PreparedQuery<DrugPersistent> pq = qb.prepare();
            return instanceDao.queryForFirst(pq);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    @Override
    public ArrayList<Drug> getAll()
    {
        QueryBuilder<DrugPersistent, Integer> qb = instanceDao.queryBuilder();
        try {
            //(List<User>) (List<? extends User>) should be checked
            return new ArrayList<Drug>(instanceDao.queryForAll());
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

}

