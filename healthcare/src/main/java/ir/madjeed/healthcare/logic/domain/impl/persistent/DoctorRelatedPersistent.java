package ir.madjeed.healthcare.logic.domain.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.dao.impl.persistent.MessageDAOPersistent;
import ir.madjeed.healthcare.dao.impl.persistent.SupervisionRequestDAOPersistent;
import ir.madjeed.healthcare.dao.impl.persistent.UserDAOPersistent;
import ir.madjeed.healthcare.logic.domain.DoctorRelated;
import ir.madjeed.healthcare.logic.entity.Message;
import ir.madjeed.healthcare.logic.entity.SupervisionRequest;
import ir.madjeed.healthcare.logic.entity.impl.persistent.MessagePersistent;

import java.util.ArrayList;
import java.util.Collections;


public class DoctorRelatedPersistent extends BasePersistent implements DoctorRelated {

    private UserDAOPersistent Users;
    private SupervisionRequestDAOPersistent SupervisionRequests;
    private MessageDAOPersistent Messages;


    public DoctorRelatedPersistent(Context context) {
        super(context);
    }

    @Override
    protected void makeNecessaryDAO() {
        Users = new UserDAOPersistent(getDatabaseHelper());
        SupervisionRequests = new SupervisionRequestDAOPersistent(getDatabaseHelper());
        Messages = new MessageDAOPersistent(getDatabaseHelper());
    }

    @Override
    public ArrayList<SupervisionRequest> getDoctorSupervisionRequests(String did) {
        ArrayList<SupervisionRequest> res = SupervisionRequests.getAll();
        for (int i = res.size()-1; i >= 0; i--) {
            if (!res.get(i).getDoctor().getUsername().equals(did))
                res.remove(i);
        }
        Collections.reverse(res);
        return res;
    }

    @Override
    public SupervisionRequest getSupervisionRequest(Integer srid) {
        return SupervisionRequests.getByID(srid);
    }

    @Override
    public void setSupervisionRequestAnswer(int srid, String answerDetail, String status){
        SupervisionRequest sr = SupervisionRequests.getByID(srid);
        sr.setStatus(status);
        sr.setRequestAnswer(answerDetail);
        SupervisionRequests.update(sr);
        // send message to patient
        Message m;
        if (status.equals("rejected")){
            m = new MessagePersistent(sr.getPatient(), "رد "+sr.getHead(), sr.getFullDetail());
        }else{
            m = new MessagePersistent(sr.getPatient(), "تایید "+sr.getHead(), sr.getFullDetail());
        }
        Messages.create(m);
    }
}
