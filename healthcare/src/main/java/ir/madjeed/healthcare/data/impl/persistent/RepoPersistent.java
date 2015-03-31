package ir.madjeed.healthcare.data.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.data.Repo;
import ir.madjeed.healthcare.data.RepoBase;
import ir.madjeed.healthcare.data.impl.persistent.context.DatabaseHelper;
import ir.madjeed.healthcare.data.impl.persistent.context.DatabaseManager;
import ir.madjeed.healthcare.data.impl.persistent.entity.UserPersistent;


public class RepoPersistent implements Repo{

    DatabaseHelper db;

    private RepoBasePersistent<UserPersistent, String> Users;

    public RepoPersistent(Context context)
    {
        DatabaseManager<DatabaseHelper> manager = new DatabaseManager<DatabaseHelper>();
        db = manager.getHelper(context);

//        Users = new RepoUsers(db);

        Users = new RepoBasePersistent<UserPersistent, String>(db, UserPersistent.class);

    }


    @Override
    public RepoBase<UserPersistent, String> getRepoUsers() {
        return Users;
    }
}
