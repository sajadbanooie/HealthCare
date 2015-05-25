package ir.madjeed.healthcare.logic.domain;


import ir.madjeed.healthcare.logic.entity.SupervisionRequest;

import java.util.ArrayList;

public interface DoctorRelated {
    public ArrayList<SupervisionRequest> getDoctorSupervisionRequests(String did);
    public SupervisionRequest getSupervisionRequest(Integer srid);
    void setSupervisionRequestAnswer(int srid, String answerDetail, String status);
}
