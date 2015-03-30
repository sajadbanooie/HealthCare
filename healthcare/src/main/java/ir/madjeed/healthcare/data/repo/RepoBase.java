package ir.madjeed.healthcare.data.repo;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import ir.madjeed.healthcare.data.context.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

public class RepoBase<C, D> {

    private Class<C> typeC;

    Dao<C, D> instanceDao;

    public RepoBase(DatabaseHelper db, Class<C> typeC)
    {
        try {
            instanceDao = db.getInstanceDao(typeC);
            this.typeC = typeC;
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
    }

    public Class<C> getRepoEntityType(){
        return this.typeC;
    }

    public int create(C instance)
    {
        try {
            return instanceDao.create(instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(C instance)
    {
        try {
            return instanceDao.update(instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(C instance)
    {
        try {
            return instanceDao.delete(instance);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public C getByID(D id)
    {
        try {
            QueryBuilder<C, D> qb = instanceDao.queryBuilder();

            // should change it to sth like id_column n\
            qb.where().eq("username", id);
            PreparedQuery<C> pq = qb.prepare();
            return instanceDao.queryForFirst(pq);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<C> getAll()
    {
        try {
            return instanceDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

}

