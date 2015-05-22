package ir.madjeed.healthcare.logic.domain.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.logic.domain.impl.persistent.context.DatabaseHelper;
import ir.madjeed.healthcare.logic.domain.impl.persistent.context.DatabaseManager;


public abstract class BasePersistent {

    private DatabaseHelper db;

    public BasePersistent(Context context)
    {
        DatabaseManager<DatabaseHelper> manager = new DatabaseManager<DatabaseHelper>();
        db = manager.getHelper(context);
        makeNecessaryDAO();
    }

    protected abstract void makeNecessaryDAO();

    protected DatabaseHelper getDatabaseHelper(){
        return db;
    }
}
