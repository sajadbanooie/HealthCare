package ir.madjeed.healthcare.logic.domain.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.dao.impl.persistent.MessageDAOPersistent;
import ir.madjeed.healthcare.dao.impl.persistent.SupervisionRequestDAOPersistent;
import ir.madjeed.healthcare.dao.impl.persistent.UserDAOPersistent;
import ir.madjeed.healthcare.logic.domain.PatientRelated;
import ir.madjeed.healthcare.logic.entity.Message;
import ir.madjeed.healthcare.logic.entity.SupervisionRequest;
import ir.madjeed.healthcare.logic.entity.User;
import ir.madjeed.healthcare.logic.entity.impl.persistent.MessagePersistent;
import ir.madjeed.healthcare.logic.entity.impl.persistent.SupervisionRequestPersistent;
import ir.madjeed.healthcare.logic.entity.impl.persistent.UserPersistent;

import java.util.ArrayList;
import java.util.Calendar;


public class PatientRelatedPersistent extends BasePersistent implements PatientRelated {

    private UserDAOPersistent Users;
    private SupervisionRequestDAOPersistent SupervisionRequests;
    private MessageDAOPersistent Messages;


    public PatientRelatedPersistent(Context context) {
        super(context);
    }

    @Override
    protected void makeNecessaryDAO() {
        Users = new UserDAOPersistent(getDatabaseHelper());
        SupervisionRequests = new SupervisionRequestDAOPersistent(getDatabaseHelper());
        Messages = new MessageDAOPersistent(getDatabaseHelper());
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
        UserPersistent patient = (UserPersistent) Users.getByID(patient_username);
        UserPersistent doctor = (UserPersistent) Users.getByID(doctor_username);
        SupervisionRequest sr = new SupervisionRequestPersistent(patient, doctor, "pending", "request", detail);
        SupervisionRequests.create(sr);
        Message m = new MessagePersistent(patient, sr.getHead(), sr.getBody());
        Messages.create(m);
    }
}
