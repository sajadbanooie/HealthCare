package ir.madjeed.healthcare.dao.impl.persistent;

import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import ir.madjeed.healthcare.dao.PrescriptionDAO;
import ir.madjeed.healthcare.logic.domain.impl.persistent.context.DatabaseHelper;
import ir.madjeed.healthcare.logic.entity.Prescription;
import ir.madjeed.healthcare.logic.entity.impl.persistent.PrescriptionPersistent;

import java.sql.SQLException;
import java.util.ArrayList;


public class PrescriptionDAOPersistent implements PrescriptionDAO{

    private final String LOG_TAG = getClass().getSimpleName();

    Dao<PrescriptionPersistent, Integer> instanceDao;

    public PrescriptionDAOPersistent(DatabaseHelper db)
    {
        try {
            instanceDao = db.getInstanceDao(PrescriptionPersistent.class);
        } catch (Exception e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        System.out.print(db);
        System.out.print(instanceDao.toString());
    }

    @Override
    public int create(Prescription instance)
    {
        try {
            return instanceDao.create((PrescriptionPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int update(Prescription instance)
    {
        try {
            return instanceDao.update((PrescriptionPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int delete(Prescription instance)
    {
        try {
            return instanceDao.delete((PrescriptionPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public Prescription getByID(Integer id)
    {
        try {
            QueryBuilder<PrescriptionPersistent, Integer> qb = instanceDao.queryBuilder();
            qb.where().eq("pk_column", id);
            PreparedQuery<PrescriptionPersistent> pq = qb.prepare();
            return instanceDao.queryForFirst(pq);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    @Override
    public ArrayList<Prescription> getAll()
    {
        QueryBuilder<PrescriptionPersistent, Integer> qb = instanceDao.queryBuilder();
        try {
            //(List<User>) (List<? extends User>) should be checked
            return new ArrayList<Prescription>(instanceDao.queryForAll());
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

}

