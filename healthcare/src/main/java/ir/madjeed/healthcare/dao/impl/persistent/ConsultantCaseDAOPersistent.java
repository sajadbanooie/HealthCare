package ir.madjeed.healthcare.dao.impl.persistent;

import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import ir.madjeed.healthcare.dao.ConsultantCaseDAO;
import ir.madjeed.healthcare.logic.domain.impl.persistent.context.DatabaseHelper;
import ir.madjeed.healthcare.logic.entity.ConsultantCase;
import ir.madjeed.healthcare.logic.entity.impl.persistent.ConsultantCasePersistent;

import java.sql.SQLException;
import java.util.ArrayList;


public class ConsultantCaseDAOPersistent implements ConsultantCaseDAO {

    private final String LOG_TAG = getClass().getSimpleName();

    Dao<ConsultantCasePersistent, Integer> instanceDao;

    public ConsultantCaseDAOPersistent(DatabaseHelper db)
    {
        try {
            instanceDao = db.getInstanceDao(ConsultantCasePersistent.class);
        } catch (Exception e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        System.out.print(db);
        System.out.print(instanceDao.toString());
    }

    @Override
    public int create(ConsultantCase instance)
    {
        try {
            return instanceDao.create((ConsultantCasePersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int update(ConsultantCase instance)
    {
        try {
            return instanceDao.update((ConsultantCasePersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int delete(ConsultantCase instance)
    {
        try {
            return instanceDao.delete((ConsultantCasePersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public ConsultantCase getByID(Integer id)
    {
        try {
            QueryBuilder<ConsultantCasePersistent, Integer> qb = instanceDao.queryBuilder();
            qb.where().eq("pk_column", id);
            PreparedQuery<ConsultantCasePersistent> pq = qb.prepare();
            return instanceDao.queryForFirst(pq);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    @Override
    public ArrayList<ConsultantCase> getAll()
    {
        QueryBuilder<ConsultantCasePersistent, Integer> qb = instanceDao.queryBuilder();
        try {
            return new ArrayList<ConsultantCase>(instanceDao.queryForAll());
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

}

