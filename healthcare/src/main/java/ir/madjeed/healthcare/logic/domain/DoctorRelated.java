package ir.madjeed.healthcare.logic.domain;


import ir.madjeed.healthcare.logic.entity.SupervisionRequest;
import ir.madjeed.healthcare.logic.entity.User;

import java.util.ArrayList;

public interface DoctorRelated {
    public ArrayList<SupervisionRequest> getDoctorSupervisionRequests(String did);
    public ArrayList<User> getDoctorPatients(String did);
    public SupervisionRequest getSupervisionRequest(Integer srid);
    public void setSupervisionRequestAnswer(int srid, String answerDetail, String status);
    public String getDoctorName(String did);
    public ArrayList<User> getAllExpertDoctors();
    public void makeReferRequest(String pid, String did, String detail);
    public boolean hasActiveSupervision(String pid, String did);
}
