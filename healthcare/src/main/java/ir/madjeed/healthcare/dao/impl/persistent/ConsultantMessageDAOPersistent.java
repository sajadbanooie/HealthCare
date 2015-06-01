package ir.madjeed.healthcare.dao.impl.persistent;

import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import ir.madjeed.healthcare.dao.ConsultantMessageDAO;
import ir.madjeed.healthcare.logic.domain.impl.persistent.context.DatabaseHelper;
import ir.madjeed.healthcare.logic.entity.ConsultantMessage;
import ir.madjeed.healthcare.logic.entity.impl.persistent.ConsultantMessagePersistent;
import java.sql.SQLException;
import java.util.ArrayList;


public class ConsultantMessageDAOPersistent implements ConsultantMessageDAO {

    private final String LOG_TAG = getClass().getSimpleName();

    Dao<ConsultantMessagePersistent, Integer> instanceDao;

    public ConsultantMessageDAOPersistent(DatabaseHelper db)
    {
        try {
            instanceDao = db.getInstanceDao(ConsultantMessagePersistent.class);
        } catch (Exception e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        System.out.print(db);
        System.out.print(instanceDao.toString());
    }

    @Override
    public int create(ConsultantMessage instance)
    {
        try {
            return instanceDao.create((ConsultantMessagePersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int update(ConsultantMessage instance)
    {
        try {
            return instanceDao.update((ConsultantMessagePersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int delete(ConsultantMessage instance)
    {
        try {
            return instanceDao.delete((ConsultantMessagePersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public ConsultantMessage getByID(Integer id)
    {
        try {
            QueryBuilder<ConsultantMessagePersistent, Integer> qb = instanceDao.queryBuilder();
            qb.where().eq("pk_column", id);
            PreparedQuery<ConsultantMessagePersistent> pq = qb.prepare();
            return instanceDao.queryForFirst(pq);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    @Override
    public ArrayList<ConsultantMessage> getAll()
    {
        QueryBuilder<ConsultantMessagePersistent, Integer> qb = instanceDao.queryBuilder();
        try {
            //(List<User>) (List<? extends User>) should be checked
            return new ArrayList<ConsultantMessage>(instanceDao.queryForAll());
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

}

