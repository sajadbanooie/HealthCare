package ir.madjeed.healthcare.data.repo.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.data.Repo;
import ir.madjeed.healthcare.data.repo.impl.persistent.context.DatabaseHelper;
import ir.madjeed.healthcare.data.repo.impl.persistent.context.DatabaseManager;


public abstract class BaseRepoPersistent{

    private DatabaseHelper db;

    public BaseRepoPersistent(Context context)
    {
        DatabaseManager<DatabaseHelper> manager = new DatabaseManager<DatabaseHelper>();
        db = manager.getHelper(context);
        makeNecessaryRepos();
    }

    protected abstract void makeNecessaryRepos();

    protected DatabaseHelper getDatabaseHelper(){
        return db;
    }
}
