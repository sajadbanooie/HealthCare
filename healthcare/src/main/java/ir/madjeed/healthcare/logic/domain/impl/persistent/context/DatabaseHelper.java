package ir.madjeed.healthcare.logic.domain.impl.persistent.context;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import ir.madjeed.healthcare.logic.entity.PrescriptionDrug;
import ir.madjeed.healthcare.logic.entity.impl.persistent.*;

// dont forget to add daos here.

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "db.sqlite";
    private static final int DATABASE_VERSION = 1;

    private Dao<UserPersistent, String> userDao = null;
    private Dao<SupervisionRequestPersistent, Integer> supervisionRequestDao = null;
    private Dao<SupervisionPersistent, Integer> supervisionDao = null;
    private Dao<MessagePersistent, Integer> messageDao = null;
    private Dao<SicknessPersistent, Integer> sicknessDao = null;
    private Dao<PrescriptionPersistent, Integer> prescriptionDao = null;
    private Dao<DrugPersistent, Integer> drugDao = null;
    private Dao<PrescriptionDrugPersistent, Integer> prescriptionDrugDao = null;
    private Dao<ConsultantCasePersistent, Integer> consultantCaseDao = null;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // it will be called as first query received
    // it wont be called when app reinstalled if db exist
    // don't use other helpers!
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, UserPersistent.class);
            TableUtils.createTable(connectionSource, SupervisionRequestPersistent.class);
            TableUtils.createTable(connectionSource, MessagePersistent.class);
            TableUtils.createTable(connectionSource, SupervisionPersistent.class);
            TableUtils.createTable(connectionSource, SicknessPersistent.class);
            TableUtils.createTable(connectionSource, PrescriptionPersistent.class);
            TableUtils.createTable(connectionSource, DrugPersistent.class);
            TableUtils.createTable(connectionSource, PrescriptionDrugPersistent.class);
            TableUtils.createTable(connectionSource, ConsultantCasePersistent.class);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }


    // it will be called when app upgraded to newer version
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");

            TableUtils.dropTable(connectionSource, UserPersistent.class, true);
            TableUtils.dropTable(connectionSource, SupervisionRequestPersistent.class, true);
            TableUtils.dropTable(connectionSource, SupervisionPersistent.class, true);
            TableUtils.dropTable(connectionSource, MessagePersistent.class, true);
            TableUtils.dropTable(connectionSource, SicknessPersistent.class, true);
            TableUtils.dropTable(connectionSource, PrescriptionPersistent.class, true);
            TableUtils.dropTable(connectionSource, DrugPersistent.class, true);
            TableUtils.dropTable(connectionSource, PrescriptionDrugPersistent.class, true);
            TableUtils.dropTable(connectionSource, ConsultantCasePersistent.class, true);

            onCreate(db);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }


    public <C, D> Dao<C, D> getInstanceDao(Class typeC) throws SQLException {
        if (typeC.equals(UserPersistent.class)) {
            if (userDao == null) userDao = DaoManager.createDao(getConnectionSource(), typeC );
            return (Dao<C, D>) userDao;
        }else if (typeC.equals(SupervisionRequestPersistent.class)) {
            if (supervisionRequestDao == null) supervisionRequestDao = DaoManager.createDao(getConnectionSource(), typeC );
            return (Dao<C, D>) supervisionRequestDao;
        }else if (typeC.equals(SupervisionPersistent.class)) {
            if (supervisionDao == null) supervisionDao = DaoManager.createDao(getConnectionSource(), typeC );
            return (Dao<C, D>) supervisionDao;
        }else if (typeC.equals(MessagePersistent.class)) {
            if (messageDao == null) messageDao = DaoManager.createDao(getConnectionSource(), typeC );
            return (Dao<C, D>) messageDao;
        }else if (typeC.equals(SicknessPersistent.class)) {
            if (sicknessDao == null) sicknessDao = DaoManager.createDao(getConnectionSource(), typeC );
            return (Dao<C, D>) sicknessDao;
        }else if (typeC.equals(PrescriptionPersistent.class)) {
            if (prescriptionDao == null) prescriptionDao = DaoManager.createDao(getConnectionSource(), typeC );
            return (Dao<C, D>) prescriptionDao;
        }else if (typeC.equals(DrugPersistent.class)) {
            if (drugDao == null) drugDao = DaoManager.createDao(getConnectionSource(), typeC );
            return (Dao<C, D>) drugDao;
        }else if (typeC.equals(PrescriptionDrugPersistent.class)) {
            if (prescriptionDrugDao == null) prescriptionDrugDao = DaoManager.createDao(getConnectionSource(), typeC );
            return (Dao<C, D>) prescriptionDrugDao;
        }else if (typeC.equals(ConsultantCasePersistent.class)) {
            if (consultantCaseDao == null) consultantCaseDao = DaoManager.createDao(getConnectionSource(), typeC );
            return (Dao<C, D>) consultantCaseDao;
        }
        return null;
    }


    @Override
    public void close() {
        super.close();
        userDao = null;
    }
}
