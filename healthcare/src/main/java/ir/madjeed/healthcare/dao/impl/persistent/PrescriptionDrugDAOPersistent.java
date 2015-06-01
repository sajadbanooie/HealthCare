package ir.madjeed.healthcare.dao.impl.persistent;

import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import ir.madjeed.healthcare.dao.PrescriptionDrugDAO;
import ir.madjeed.healthcare.logic.entity.impl.persistent.context.DatabaseHelper;
import ir.madjeed.healthcare.logic.entity.PrescriptionDrug;
import ir.madjeed.healthcare.logic.entity.impl.persistent.PrescriptionDrugPersistent;

import java.sql.SQLException;
import java.util.ArrayList;


public class PrescriptionDrugDAOPersistent implements PrescriptionDrugDAO{

    private final String LOG_TAG = getClass().getSimpleName();

    Dao<PrescriptionDrugPersistent, Integer> instanceDao;

    public PrescriptionDrugDAOPersistent(DatabaseHelper db)
    {
        try {
            instanceDao = db.getInstanceDao(PrescriptionDrugPersistent.class);
        } catch (Exception e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        System.out.print(db);
        System.out.print(instanceDao.toString());
    }

    @Override
    public int create(PrescriptionDrug instance)
    {
        try {
            return instanceDao.create((PrescriptionDrugPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int update(PrescriptionDrug instance)
    {
        try {
            return instanceDao.update((PrescriptionDrugPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public int delete(PrescriptionDrug instance)
    {
        try {
            return instanceDao.delete((PrescriptionDrugPersistent) instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return 0;
    }

    @Override
    public PrescriptionDrug getByID(Integer id)
    {
        try {
            QueryBuilder<PrescriptionDrugPersistent, Integer> qb = instanceDao.queryBuilder();
            qb.where().eq("pk_column", id);
            PreparedQuery<PrescriptionDrugPersistent> pq = qb.prepare();
            return instanceDao.queryForFirst(pq);
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    @Override
    public ArrayList<PrescriptionDrug> getAll()
    {
        QueryBuilder<PrescriptionDrugPersistent, Integer> qb = instanceDao.queryBuilder();
        try {
            //(List<User>) (List<? extends User>) should be checked
            return new ArrayList<PrescriptionDrug>(instanceDao.queryForAll());
        } catch (SQLException e) {
            // TODO: Exception Handling
            Log.e(LOG_TAG, Log.getStackTraceString(e));
        }
        return null;
    }

}

