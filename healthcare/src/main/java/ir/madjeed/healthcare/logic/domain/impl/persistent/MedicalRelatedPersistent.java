package ir.madjeed.healthcare.logic.domain.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.dao.impl.persistent.*;
import ir.madjeed.healthcare.logic.domain.MedicalRelated;
import ir.madjeed.healthcare.logic.entity.*;
import ir.madjeed.healthcare.logic.entity.impl.persistent.DrugPersistent;
import ir.madjeed.healthcare.logic.entity.impl.persistent.SicknessPersistent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MedicalRelatedPersistent extends BasePersistent implements MedicalRelated {

    private SicknessDAOPersistent Sicknesses;
    private DrugDAOPersistent Drugs;
    private UserDAOPersistent Users;

    public MedicalRelatedPersistent(Context context) {
        super(context);
    }

    @Override
    protected void makeNecessaryDAO() {
        Sicknesses = new SicknessDAOPersistent(getDatabaseHelper());
        Users = new UserDAOPersistent(getDatabaseHelper());
        Drugs = new DrugDAOPersistent(getDatabaseHelper());
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
    public ArrayList<Drug> getAllDrugs() {
        Random rnd = new Random();
        Drugs.create(new DrugPersistent("دارو شماره"+String.valueOf(rnd.nextInt(100)), rnd.nextInt(10000)));
        return Drugs.getAll();
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
