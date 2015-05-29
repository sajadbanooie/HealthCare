package ir.madjeed.healthcare.facade;

import android.content.Context;
import android.util.Pair;
import ir.madjeed.healthcare.logic.domain.MedicalRelated;
import ir.madjeed.healthcare.logic.domain.impl.persistent.MedicalRelatedPersistent;
import ir.madjeed.healthcare.logic.entity.Drug;
import ir.madjeed.healthcare.logic.entity.Prescription;
import ir.madjeed.healthcare.logic.entity.PrescriptionDrug;
import ir.madjeed.healthcare.logic.entity.Sickness;

import java.util.ArrayList;

/**
 * Created by admin on 5/21/2015.
 */
public class MedicalFacade {
    private MedicalRelated medicalRelated;

    public MedicalFacade(Context context) {
        this.medicalRelated = new MedicalRelatedPersistent(context);
    }

    public ArrayList<Pair<String, String>> getPatientAllSickness(String pid){  // first = id, second = name
        ArrayList<Sickness> res = medicalRelated.getPatientAllSickness(pid);
        ArrayList<Pair<String, String>> result = new ArrayList<Pair<String, String>>();
        for (int i = 0; i < res.size(); i++) {
            result.add(new Pair<String, String>(String.valueOf(res.get(i).getId()), res.get(i).getSubject()));
        }
        return result;
    }

    public void addPatientSickness(String did, String pid, String subject, String detail){
        medicalRelated.addPatientSickness(did, pid, subject, detail);
    }

    public ArrayList<String> getSicknessInfo(String sid){
        ArrayList<String> info = new ArrayList<String>();
        Sickness s = medicalRelated.getSickness(sid);
        info.add(s.getSubject());
        info.add(s.getFullDetail());
        return info;
    }

    public ArrayList<Pair<String, String>> getAllDrugs(){  // first = id, second = name
        ArrayList<Drug> res = medicalRelated.getAllDrugs();
        ArrayList<Pair<String, String>> result = new ArrayList<Pair<String, String>>();
        for (int i = 0; i < res.size(); i++) {
            result.add(new Pair<String, String>(String.valueOf(res.get(i).getId()), res.get(i).getName()));
        }
        return result;
    }


    public ArrayList<String> getDrugInfo(String did){
        Drug d = medicalRelated.getDrug(did);
        ArrayList<String> info = new ArrayList<String>();
        info.add(String.valueOf(d.getId()));
        info.add(d.getName());
        info.add(String.valueOf(d.getPrice()));
        return info;
    }

    public void addPrescription(String sickness_id, ArrayList<Pair<String, String>> selected_drugs){
        // each pair contain drug id and amount
        ArrayList<Drug> drugs = new ArrayList<Drug>();
        ArrayList<Integer> amounts = new ArrayList<Integer>();
        for (int i = 0; i < selected_drugs.size(); i++) {
            drugs.add(medicalRelated.getDrug(selected_drugs.get(i).first));
            amounts.add(Integer.valueOf(selected_drugs.get(i).second));
        }
        medicalRelated.addPrescription(sickness_id, drugs, amounts);
    }


    public ArrayList<Pair<String, String>> getSicknessPrescriptions(String sid){  // first = id, second = name
        ArrayList<Prescription> res = medicalRelated.getSicknessPrescriptions(sid);
        ArrayList<Pair<String, String>> result = new ArrayList<Pair<String, String>>();
        for (int i = 0; i < res.size(); i++) {
            result.add(new Pair<String, String>(String.valueOf(res.get(i).getId()),
                    "نسخه شماره "+String.valueOf(res.get(i).getId())));
        }
        return result;
    }


    public ArrayList<String> getPrescriptionPrescriptionDrugs(String pid){  // list of prescriptionDrugs id
        ArrayList<PrescriptionDrug> res = medicalRelated.getPrescriptionPrescriptionDrugs(pid);
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < res.size(); i++) {
            result.add(String.valueOf(res.get(i).getId()));
        }
        return result;
    }

    public ArrayList<String> getPrescriptionDrugInfo(String pd_id){
        PrescriptionDrug d = medicalRelated.getPrescriptionDrug(pd_id);
        ArrayList<String> info = new ArrayList<String>();
        info.add(String.valueOf(d.getId()));
        info.add(d.getDrug().getName());
        info.add(String.valueOf(d.getNum()));
        info.add(String.valueOf(d.getDrug().getPrice()));
        return info;
    }
}

