package ir.madjeed.healthcare.logic.domain.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.dao.*;
import ir.madjeed.healthcare.dao.impl.persistent.*;
import ir.madjeed.healthcare.logic.domain.ConsultantRelated;
import ir.madjeed.healthcare.logic.entity.*;
import ir.madjeed.healthcare.logic.entity.impl.persistent.ConsultantCasePersistent;
import ir.madjeed.healthcare.logic.entity.impl.persistent.ConsultantMessagePersistent;

import java.util.ArrayList;


public class ConsultantRelatedPersistent extends BasePersistent implements ConsultantRelated {

    private UserDAO Users;
    private ConsultantCaseDAO ConsultantCases;
    private ConsultantMessageDAO ConsultantMessages;
    private SupervisionDAO Supervisions;


    public ConsultantRelatedPersistent(Context context) {
        super(context);
    }

    @Override
    protected void makeNecessaryDAO() {
        Users = new UserDAOPersistent(getDatabaseHelper());
        ConsultantCases = new ConsultantCaseDAOPersistent(getDatabaseHelper());
        Supervisions = new SupervisionDAOPersistent(getDatabaseHelper());
        ConsultantMessages = new ConsultantMessageDAOPersistent(getDatabaseHelper());
    }


    @Override
    public ArrayList<ConsultantCase> getPatientConsultantCases(String pid) {
        return null;
    }

    @Override
    public ArrayList<ConsultantCase> getDoctorConsultantCases(String did) {
        return null;
    }

    @Override
    public ArrayList<ConsultantMessage> getConsultantCaseMessages(String cid) {
        return null;
    }

    @Override
    public void addConsultantCase(String did, String pid, String subject, String initial_message, String sender) {
        User doctor = Users.getByID(did);
        User patient = Users.getByID(pid);
        ConsultantCase c = new ConsultantCasePersistent(patient, doctor, subject);
        ConsultantCases.create(c);
        ConsultantMessage m;
        if (sender.equals("doctor")){
            m = new ConsultantMessagePersistent(c, doctor.getFullName(), initial_message);
        }else{
            m = new ConsultantMessagePersistent(c, doctor.getFullName(), initial_message);
        }
        ConsultantMessages.create(m);
    }
}
