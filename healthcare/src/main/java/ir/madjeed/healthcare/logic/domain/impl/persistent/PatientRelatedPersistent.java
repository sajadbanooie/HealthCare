package ir.madjeed.healthcare.logic.domain.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.dao.impl.persistent.UserDAOPersistent;
import ir.madjeed.healthcare.logic.domain.PatientRelated;
import ir.madjeed.healthcare.logic.entity.User;
import java.util.ArrayList;


public class PatientRelatedPersistent extends BasePersistent implements PatientRelated {

    private UserDAOPersistent Users;

    public PatientRelatedPersistent(Context context) {
        super(context);
    }

    @Override
    protected void makeNecessaryDAO() {
        Users = new UserDAOPersistent(getDatabaseHelper());
    }


    @Override
    public ArrayList<User> getAllNormalDoctors() {
        ArrayList<User> res = Users.getAll();
        for (int i = res.size()-1; i >= 0; i--) {
            if (!res.get(i).getRole().equals("پزشک عمومی"))  // it is not valid
                res.remove(i);
        }
        return res;
    }
}
