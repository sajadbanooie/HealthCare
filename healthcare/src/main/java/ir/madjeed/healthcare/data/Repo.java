package ir.madjeed.healthcare.data;


import ir.madjeed.healthcare.data.context.DatabaseHelper;
import ir.madjeed.healthcare.data.context.DatabaseManager;
import ir.madjeed.healthcare.data.entity.User;
import ir.madjeed.healthcare.data.repo.RepoBase;
//import ir.madjeed.healthcare.data.repo.RepoUsers;
import android.content.Context;

public class Repo {

    DatabaseHelper db;

//    public RepoUsers Users;
    public RepoBase<User, String> Users;

    public Repo(Context context)
    {
        DatabaseManager<DatabaseHelper> manager = new DatabaseManager<DatabaseHelper>();
        db = manager.getHelper(context);

//        Users = new RepoUsers(db);

        Users = new RepoBase<User, String>(db, User.class);

    }

}
