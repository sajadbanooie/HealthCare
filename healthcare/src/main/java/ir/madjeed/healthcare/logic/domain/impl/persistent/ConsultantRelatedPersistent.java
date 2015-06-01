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


    public ConsultantRelatedPersistent(Context context) {
        super(context);
    }

    @Override
    protected void makeNecessaryDAO() {
        Users = new UserDAOPersistent(getDatabaseHelper());
        ConsultantCases = new ConsultantCaseDAOPersistent(getDatabaseHelper());
        ConsultantMessages = new ConsultantMessageDAOPersistent(getDatabaseHelper());
    }


    @Override
    public ArrayList<ConsultantCase> getPatientConsultantCases(String pid) {
        ArrayList<ConsultantCase> res = ConsultantCases.getAll();
        for (int i = res.size()-1; i >= 0 ; i--) {
            if (!res.get(i).getPatient().getUsername().equals(pid))
                res.remove(i);
        }
        return res;
    }

    @Override
    public ArrayList<ConsultantCase> getDoctorConsultantCases(String did) {
        ArrayList<ConsultantCase> res = ConsultantCases.getAll();
        for (int i = res.size()-1; i >= 0 ; i--) {
            if (!res.get(i).getDoctor().getUsername().equals(did))
                res.remove(i);
        }
        return res;
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

    @Override
    public void addConsultantMessage(String cid, String detail, String sender_id) {
        String sender = Users.getByID(sender_id).getFullName();
        ConsultantCase c = ConsultantCases.getByID(Integer.valueOf(cid));
        ConsultantMessage m = new ConsultantMessagePersistent(c, sender, detail);
        ConsultantMessages.create(m);
    }

    @Override
    public ArrayList<ConsultantMessage> getConsultantCaseMessages(String cid) {
        ArrayList<ConsultantMessage> messages = ConsultantMessages.getAll();
        for (int i = messages.size()-1; i >= 0 ; i--) {
            if (messages.get(i).getConsultantCase().getId()!=Integer.valueOf(cid))
                messages.remove(i);
        }
        return messages;
    }
}
