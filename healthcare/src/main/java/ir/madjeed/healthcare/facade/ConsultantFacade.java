package ir.madjeed.healthcare.facade;

import android.content.Context;
import ir.madjeed.healthcare.gui.base.CustomRowObject;
import ir.madjeed.healthcare.logic.domain.ConsultantRelated;
import ir.madjeed.healthcare.logic.domain.impl.persistent.ConsultantRelatedPersistent;
import ir.madjeed.healthcare.logic.entity.ConsultantCase;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by admin on 5/21/2015.
 */
public class ConsultantFacade {
    private ConsultantRelated consultantRelated;

    public ConsultantFacade(Context context) {
        this.consultantRelated = new ConsultantRelatedPersistent(context);
    }

    public void addConsultant(String did, String pid, String subject, String initialMessage, String sender){
        consultantRelated.addConsultantCase(did, pid, subject, initialMessage, sender);
    }

    public ArrayList<CustomRowObject> getConsultantCases(String id, String owner){ // owner is doctor or patient
        ArrayList<ConsultantCase> res;
        if(owner.equals("doctor")){
            res = consultantRelated.getDoctorConsultantCases(id);
        }else{
            res = consultantRelated.getPatientConsultantCases(id);
        }
        ArrayList<CustomRowObject> result = new ArrayList<CustomRowObject>();
        for (int i = 0; i < res.size(); i++) {
            if (owner.equals("doctor")){
                result.add(new CustomRowObject(String.valueOf(res.get(i).getId()), res.get(i).getStatus(),
                    res.get(i).getPatient().getFullName(), res.get(i).getSubject()));
            }else{
                result.add(new CustomRowObject(String.valueOf(res.get(i).getId()), res.get(i).getStatus(),
                    res.get(i).getDoctor().getFullName(), res.get(i).getSubject()));
            }
        }
        Collections.reverse(result);
        return result;
    }
}

