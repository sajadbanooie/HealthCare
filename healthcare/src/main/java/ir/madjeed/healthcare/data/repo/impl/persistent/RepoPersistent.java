package ir.madjeed.healthcare.data.repo.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.data.Repo;
import ir.madjeed.healthcare.data.repo.impl.persistent.context.DatabaseHelper;
import ir.madjeed.healthcare.data.repo.impl.persistent.context.DatabaseManager;


public class RepoPersistent implements Repo{

    DatabaseHelper db;

    private RepoUserPersistent Users;

    public RepoPersistent(Context context)
    {
        DatabaseManager<DatabaseHelper> manager = new DatabaseManager<DatabaseHelper>();
        db = manager.getHelper(context);
        Users = new RepoUserPersistent(db);

    }

    @Override
    public RepoUserPersistent getRepoUsers() {
        return Users;
    }
}
