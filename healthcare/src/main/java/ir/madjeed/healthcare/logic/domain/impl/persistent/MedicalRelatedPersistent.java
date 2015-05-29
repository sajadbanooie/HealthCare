package ir.madjeed.healthcare.logic.domain.impl.persistent;


import android.content.Context;
import android.util.Log;
import ir.madjeed.healthcare.dao.impl.persistent.*;
import ir.madjeed.healthcare.logic.domain.MedicalRelated;
import ir.madjeed.healthcare.logic.entity.*;
import ir.madjeed.healthcare.logic.entity.impl.persistent.DrugPersistent;
import ir.madjeed.healthcare.logic.entity.impl.persistent.PrescriptionDrugPersistent;
import ir.madjeed.healthcare.logic.entity.impl.persistent.PrescriptionPersistent;
import ir.madjeed.healthcare.logic.entity.impl.persistent.SicknessPersistent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MedicalRelatedPersistent extends BasePersistent implements MedicalRelated {

    private SicknessDAOPersistent Sicknesses;
    private DrugDAOPersistent Drugs;
    private PrescriptionDrugDAOPersistent PrescriptionDrugs;
    private PrescriptionDAOPersistent Prescriptions;
    private UserDAOPersistent Users;

    public MedicalRelatedPersistent(Context context) {
        super(context);
    }

    @Override
    protected void makeNecessaryDAO() {
        Sicknesses = new SicknessDAOPersistent(getDatabaseHelper());
        Users = new UserDAOPersistent(getDatabaseHelper());
        Drugs = new DrugDAOPersistent(getDatabaseHelper());
        Prescriptions = new PrescriptionDAOPersistent(getDatabaseHelper());
        PrescriptionDrugs = new PrescriptionDrugDAOPersistent(getDatabaseHelper());
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
    public Drug getDrug(String did) {
        return Drugs.getByID(Integer.valueOf(did));
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

    @Override
    public void addPrescription(String sickness_id, ArrayList<Drug> selected_drugs, ArrayList<Integer> amount) {
        Sickness s = Sicknesses.getByID(Integer.valueOf(sickness_id));
        Prescription p = new PrescriptionPersistent(s);
        Prescriptions.create(p);
        for (int i = 0; i < selected_drugs.size(); i++) {
            PrescriptionDrug pd = new PrescriptionDrugPersistent(p, selected_drugs.get(i), amount.get(i));
            PrescriptionDrugs.create(pd);
        }
    }

    @Override
    public ArrayList<Prescription> getSicknessPrescriptions(String sid) {
        ArrayList<Prescription> res = Prescriptions.getAll();
        for (int i = res.size()-1; i >= 0; i--) {
            if (res.get(i).getSickness().getId()!=Integer.valueOf(sid))
                res.remove(i);
        }
        Collections.reverse(res);
        return res;
    }

    @Override
    public ArrayList<PrescriptionDrug> getPrescriptionPrescriptionDrugs(String prescription_id) {
        ArrayList<PrescriptionDrug> res = PrescriptionDrugs.getAll();
        for (int i = res.size()-1; i >= 0; i--) {
            if (res.get(i).getPrescription()==null || // because of last wrong data, i can remove it later
                    res.get(i).getPrescription().getId()!=Integer.valueOf(prescription_id))
                res.remove(i);
        }
        Collections.reverse(res);
        return res;
    }

    @Override
    public PrescriptionDrug getPrescriptionDrug(String pd_id) {
        return PrescriptionDrugs.getByID(Integer.valueOf(pd_id));
    }
}
