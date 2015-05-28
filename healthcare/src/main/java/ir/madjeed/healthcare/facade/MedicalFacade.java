package ir.madjeed.healthcare.facade;

import android.content.Context;
import android.util.Pair;
import ir.madjeed.healthcare.logic.domain.MedicalRelated;
import ir.madjeed.healthcare.logic.domain.impl.persistent.MedicalRelatedPersistent;
import ir.madjeed.healthcare.logic.entity.Drug;
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

}

