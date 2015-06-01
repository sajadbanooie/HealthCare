package ir.madjeed.healthcare.logic.domain.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.dao.MessageDAO;
import ir.madjeed.healthcare.dao.SupervisionDAO;
import ir.madjeed.healthcare.dao.SupervisionRequestDAO;
import ir.madjeed.healthcare.dao.UserDAO;
import ir.madjeed.healthcare.dao.impl.persistent.MessageDAOPersistent;
import ir.madjeed.healthcare.dao.impl.persistent.SupervisionDAOPersistent;
import ir.madjeed.healthcare.dao.impl.persistent.SupervisionRequestDAOPersistent;
import ir.madjeed.healthcare.dao.impl.persistent.UserDAOPersistent;
import ir.madjeed.healthcare.logic.domain.DoctorRelated;
import ir.madjeed.healthcare.logic.entity.Message;
import ir.madjeed.healthcare.logic.entity.Supervision;
import ir.madjeed.healthcare.logic.entity.SupervisionRequest;
import ir.madjeed.healthcare.logic.entity.User;
import ir.madjeed.healthcare.logic.entity.impl.persistent.MessagePersistent;
import ir.madjeed.healthcare.logic.entity.impl.persistent.SupervisionPersistent;
import ir.madjeed.healthcare.logic.entity.impl.persistent.SupervisionRequestPersistent;

import java.util.ArrayList;
import java.util.Collections;


public class DoctorRelatedPersistent extends BasePersistent implements DoctorRelated {

    private UserDAO Users;
    private SupervisionRequestDAO SupervisionRequests;
    private MessageDAO Messages;
    private SupervisionDAO Supervisions;


    public DoctorRelatedPersistent(Context context) {
        super(context);
    }

    @Override
    protected void makeNecessaryDAO() {
        Users = new UserDAOPersistent(getDatabaseHelper());
        SupervisionRequests = new SupervisionRequestDAOPersistent(getDatabaseHelper());
        Supervisions = new SupervisionDAOPersistent(getDatabaseHelper());
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

        if (status.equals("rejected")){
            Message m = new MessagePersistent(sr.getPatient(), "رد "+sr.getHead(), sr.getFullDetail());
            Messages.create(m);
        }else{
            // send message to the patient
            Message m1 = new MessagePersistent(sr.getPatient(), "تایید "+sr.getHead(), sr.getFullDetail());
            Messages.create(m1);
            Message m2 = new MessagePersistent(sr.getDoctor(), "تایید "+sr.getHead(), sr.getFullDetail());
            Messages.create(m2);
            Supervision s = new SupervisionPersistent(sr.getPatient(), sr.getDoctor());
            Supervisions.create(s);
        }
    }

    @Override
    public String getDoctorName(String did) {
        return Users.getByID(did).getFullName();
    }

    @Override
    public ArrayList<User> getAllExpertDoctors() {
        ArrayList<User> res = Users.getAll();
        for (int i = res.size()-1; i >= 0; i--) {
            if (!res.get(i).getRole().equals("پزشک متخصص")
                    || !res.get(i).getRegistrationStatus().equals("accepted"))
                res.remove(i);
        }
        return res;
    }

    @Override
    public void makeReferRequest(String pid, String did, String detail) {
        User patient = Users.getByID(pid);
        User doctor = Users.getByID(did);
        SupervisionRequest sr = new SupervisionRequestPersistent(patient, doctor, "refer", detail);
        SupervisionRequests.create(sr);
        Message m = new MessagePersistent(patient, sr.getHead(), sr.getBody());
        Messages.create(m);

    }

    @Override
    public ArrayList<User> getDoctorPatients(String did) {
        ArrayList<Supervision> supervisions = Supervisions.getAll();
        ArrayList<User> supervised_patients = new ArrayList<User>();
        for (int i = supervisions.size()-1; i >= 0; i--) {
            if (supervisions.get(i).getDoctor().getUsername().equals(did)
                    && supervisions.get(i).getStatus().equals("active")){
                supervised_patients.add(supervisions.get(i).getPatient());
            }
        }
        return supervised_patients;
    }

    @Override
    public boolean hasActiveSupervision(String pid, String did) {
        boolean flag = false;
        ArrayList<Supervision> s = Supervisions.getAll();
        for (int i = 0; i < s.size(); i++) {
            if (s.get(i).getStatus().equals("active") && s.get(i).getPatient().getUsername().equals(pid)
                    && s.get(i).getDoctor().getUsername().equals(did))
                flag = true;
        }
        return flag;
    }

}
