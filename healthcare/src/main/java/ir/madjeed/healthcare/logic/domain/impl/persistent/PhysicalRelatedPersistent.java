package ir.madjeed.healthcare.logic.domain.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.dao.*;
import ir.madjeed.healthcare.dao.impl.persistent.*;
import ir.madjeed.healthcare.logic.domain.PhysicalRelated;
import ir.madjeed.healthcare.logic.entity.PhysicalState;
import ir.madjeed.healthcare.logic.entity.User;
import ir.madjeed.healthcare.logic.entity.impl.persistent.PhysicalStatePersistent;

import java.util.ArrayList;


public class PhysicalRelatedPersistent extends BasePersistent implements PhysicalRelated {

    private PhysicalStateDAO PhysicalStates;
    private UserDAO Users;

    public PhysicalRelatedPersistent(Context context) {
        super(context);
    }

    @Override
    protected void makeNecessaryDAO() {
        Users = new UserDAOPersistent(getDatabaseHelper());
        PhysicalStates = new PhysicalStateDAOPersistent(getDatabaseHelper());    }


    @Override
    public void addPhysicalState(String pid, Integer ghand, Integer vazn, Integer feshar, Integer ghandeKhun) {
        User patient = Users.getByID(pid);
        PhysicalState p = new PhysicalStatePersistent(patient, ghand, vazn, feshar, ghandeKhun);
        PhysicalStates.create(p);
    }

    @Override
    public ArrayList<PhysicalState> getPatientAllPhysicalStates(String pid) {
        ArrayList <PhysicalState> result = PhysicalStates.getAll();
        for (int i = result.size()-1; i >= 0 ; i--) {
            if (!result.get(i).getPatient().getUsername().equals(pid)){
                result.remove(i);
            }
        }
        return result;
    }
}
