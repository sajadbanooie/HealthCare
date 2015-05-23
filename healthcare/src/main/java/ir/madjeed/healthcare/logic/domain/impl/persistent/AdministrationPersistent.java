package ir.madjeed.healthcare.logic.domain.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.dao.impl.persistent.UserDAOPersistent;
import ir.madjeed.healthcare.logic.domain.Administration;
import ir.madjeed.healthcare.logic.entity.User;
import java.util.ArrayList;


public class AdministrationPersistent extends BasePersistent implements Administration {

    private UserDAOPersistent Users;

    public AdministrationPersistent(Context context) {
        super(context);
    }

    @Override
    protected void makeNecessaryDAO() {
        Users = new UserDAOPersistent(getDatabaseHelper());
    }


    @Override
    public ArrayList<User> getAllUsers() {
        return Users.getAll();
    }

    @Override
    public User getUser(String pk) {
        return Users.getByID(pk);
    }

    public void updateUser(User u){
        Users.update(u);
    }
}
