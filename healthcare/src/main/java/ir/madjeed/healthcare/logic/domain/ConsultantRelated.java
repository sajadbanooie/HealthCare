package ir.madjeed.healthcare.logic.domain;


import ir.madjeed.healthcare.logic.entity.ConsultantCase;
import ir.madjeed.healthcare.logic.entity.ConsultantMessage;

import java.util.ArrayList;

public interface ConsultantRelated {
    public ArrayList<ConsultantCase> getPatientConsultantCases(String pid);
    public ArrayList<ConsultantCase> getDoctorConsultantCases(String did);
    public ArrayList<ConsultantMessage> getConsultantCaseMessages(String cid);
    public void addConsultantCase(String did, String pid, String subject, String initial_message, String sender);
    public void addConsultantMessage(String cid, String detail, String sender_id);
}
