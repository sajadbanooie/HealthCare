package ir.madjeed.healthcare.dao.impl.persistent;

import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import ir.madjeed.healthcare.dao.SupervisionDAO;
import ir.madjeed.healthcare.logic.domain.impl.persistent.context.DatabaseHelper;
import ir.madjeed.healthcare.logic.entity.Supervision;
import ir.madjeed.healthcare.logic.entity.impl.persistent.SupervisionPersistent;
import java.sql.SQLException;
import java.util.ArrayList;


public class SupervisionDAOPersistent implements SupervisionDAO {

    private final String LOG_TAG = getClass().getSimpleName();

    Dao<SupervisionPersistent, Integer> instanceDao;

    public SupervisionDAOPersistent(DatabaseHelper db)
    {
        try {
            instanceDao = db.getInstanceDao(SupervisionPersistent.class);
        } catch (Exception e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        System.out.print(db);
        System.out.print(instanceDao.toString());
    }

    @Override
    public int create(Supervision instance)
    {
        try {
            return instanceDao.create((SupervisionPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int update(Supervision instance)
    {
        try {
            return instanceDao.update((SupervisionPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int delete(Supervision instance)
    {
        try {
            return instanceDao.delete((SupervisionPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public Supervision getByID(Integer id)
    {
        try {
            QueryBuilder<SupervisionPersistent, Integer> qb = instanceDao.queryBuilder();
            qb.where().eq("pk_column", id);
            PreparedQuery<SupervisionPersistent> pq = qb.prepare();
            return instanceDao.queryForFirst(pq);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    @Override
    public ArrayList<Supervision> getAll()
    {
        QueryBuilder<SupervisionPersistent, Integer> qb = instanceDao.queryBuilder();
        try {
            //(List<User>) (List<? extends User>) should be checked
            return new ArrayList<Supervision>(instanceDao.queryForAll());
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

}

