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
import ir.madjeed.healthcare.logic.domain.PatientRelated;
import ir.madjeed.healthcare.logic.entity.Message;
import ir.madjeed.healthcare.logic.entity.Supervision;
import ir.madjeed.healthcare.logic.entity.SupervisionRequest;
import ir.madjeed.healthcare.logic.entity.User;
import ir.madjeed.healthcare.logic.entity.impl.persistent.MessagePersistent;
import ir.madjeed.healthcare.logic.entity.impl.persistent.SupervisionRequestPersistent;
import ir.madjeed.healthcare.logic.entity.impl.persistent.UserPersistent;

import java.util.ArrayList;


public class PatientRelatedPersistent extends BasePersistent implements PatientRelated {

    private UserDAO Users;
    private SupervisionRequestDAO SupervisionRequests;
    private SupervisionDAO Supervisions;
    private MessageDAO Messages;


    public PatientRelatedPersistent(Context context) {
        super(context);
    }

    @Override
    protected void makeNecessaryDAO() {
        Users = new UserDAOPersistent(getDatabaseHelper());
        SupervisionRequests = new SupervisionRequestDAOPersistent(getDatabaseHelper());
        Messages = new MessageDAOPersistent(getDatabaseHelper());
        Supervisions = new SupervisionDAOPersistent(getDatabaseHelper());
    }


    @Override
    public ArrayList<User> getAllNormalDoctors() {
        ArrayList<User> res = Users.getAll();
        for (int i = res.size()-1; i >= 0; i--) {
            if (!res.get(i).getRole().equals("پزشک عمومی"))  // it is not valid
                res.remove(i);
        }
        return res;
    }

    @Override
    public User getDoctor(String username){
        return Users.getByID(username);
    }

    @Override
    public void makeSupervisionRequest(String patient_username, String doctor_username, String detail){
        User patient = Users.getByID(patient_username);
        User doctor = Users.getByID(doctor_username);
        SupervisionRequest sr = new SupervisionRequestPersistent(patient, doctor, "request", detail);
        SupervisionRequests.create(sr);
        Message m = new MessagePersistent(patient, sr.getHead(), sr.getBody());
        Messages.create(m);
    }

    @Override
    public User getPatientCurrentNormalDoctor(String pid) {
        ArrayList<Supervision> s = Supervisions.getAll();
        for (int i = 0; i < s.size(); i++) {
            if(s.get(i).getPatient().getUsername().equals(pid) &&
                    s.get(i).getDoctor().getRole().equals("پزشک عمومی") &&
                    s.get(i).getStatus().equals("active")){
                return s.get(i).getDoctor();
            }
        }
        return null;
    }

    @Override
    public void finishPatientCurrentNormalSupervision(String pid) {
        ArrayList<Supervision> s = Supervisions.getAll();
        for (int i = 0; i < s.size(); i++) {
            if(s.get(i).getPatient().getUsername().equals(pid) &&
                    s.get(i).getDoctor().getRole().equals("پزشک عمومی")){
                s.get(i).setStatus("passive");
                Supervisions.update(s.get(i));
                // send messages to corresponding doctor and patient
                Message m1 = new MessagePersistent(s.get(i).getDoctor(),
                        "پایان نظارت شماره "+s.get(i).getId()+"با درخواست بیمار",
                        "نظارت مربوطه:\n"+ s.get(i).getFullDetail());
                Messages.create(m1);
                Message m2 = new MessagePersistent(s.get(i).getPatient(),
                        "پایان نظارت شماره "+s.get(i).getId()+"با درخواست بیمار",
                        "نظارت مربوطه:\n"+ s.get(i).getFullDetail());
                Messages.create(m2);
                break; // no need
            }
        }

    }

    @Override
    public ArrayList<User> getPatientAllDoctors(String pid) {
        ArrayList<Supervision> s = Supervisions.getAll();
        ArrayList<User> supervised_doctors = new ArrayList<User>();
        for (int i = 0; i < s.size(); i++) {
            if(s.get(i).getPatient().getUsername().equals(pid) &&
                    s.get(i).getStatus().equals("active")){
                supervised_doctors.add(s.get(i).getDoctor());
            }
        }
        return supervised_doctors;
    }
}
