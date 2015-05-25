package ir.madjeed.healthcare.facade;

import android.content.Context;
import android.util.Pair;
import ir.madjeed.healthcare.logic.domain.DoctorRelated;
import ir.madjeed.healthcare.logic.domain.impl.persistent.DoctorRelatedPersistent;
import ir.madjeed.healthcare.logic.entity.SupervisionRequest;

import java.util.ArrayList;

/**
 * Created by admin on 5/21/2015.
 */
public class DoctorFacade {
    private DoctorRelated doctorRelated;

    public DoctorFacade(Context context) {
        this.doctorRelated = new DoctorRelatedPersistent(context);
    }

    public ArrayList<Pair<String, String>> getDoctorSupervisionRequests(String did){  // first = id, second = name
        ArrayList<SupervisionRequest> res = doctorRelated.getDoctorSupervisionRequests(did);
        ArrayList<Pair<String, String>> result = new ArrayList<Pair<String, String>>();
        for (int i = 0; i < res.size(); i++) {
            result.add(new Pair<String, String>(String.valueOf(res.get(i).getId()), res.get(i).getHead()));
        }
        return result;
    }

    public ArrayList<String> getSupervisionRequestDetail(int srid){
        ArrayList<String> res = new ArrayList<String>();
        SupervisionRequest sr = doctorRelated.getSupervisionRequest(srid);
        res.add(sr.getHead());
        res.add(sr.getFullDetail());
        res.add(sr.getStatus());
        return res;
    }

    public void setSupervisionRequestAnswer(int srid, String answerDetail, String status){
        doctorRelated.setSupervisionRequestAnswer(srid, answerDetail, status);
    }
}

