package ir.madjeed.healthcare.logic.domain.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.dao.impl.persistent.*;
import ir.madjeed.healthcare.logic.domain.MedicalRelated;
import ir.madjeed.healthcare.logic.entity.*;
import ir.madjeed.healthcare.logic.entity.impl.persistent.SicknessPersistent;

import java.util.ArrayList;
import java.util.Collections;

public class MedicalRelatedPersistent extends BasePersistent implements MedicalRelated {

    private SicknessDAOPersistent Sicknesses;
    private UserDAOPersistent Users;

    public MedicalRelatedPersistent(Context context) {
        super(context);
    }

    @Override
    protected void makeNecessaryDAO() {
        Sicknesses = new SicknessDAOPersistent(getDatabaseHelper());
        Users = new UserDAOPersistent(getDatabaseHelper());
    }

    @Override
    public ArrayList<Sickness> getPatientAllSickness(String pid) {
        ArrayList<Sickness> res = Sicknesses.getAll();
        for (int i = res.size()-1; i >= 0; i--) {
            if (!res.get(i).getPatient().getUsername().equals(pid))
                res.remove(i);
        }
        Collections.reverse(res);
        return res;
    }

    @Override
    public Sickness getSickness(String sid) {
        return Sicknesses.getByID(Integer.valueOf(sid));
    }

    @Override
    public void addPatientSickness(String did, String pid, String subject, String detail) {
        User doctor = Users.getByID(did);
        User patient = Users.getByID(pid);
        Sickness s = new SicknessPersistent(patient, doctor, subject, detail);
        Sicknesses.create(s);
    }
}
