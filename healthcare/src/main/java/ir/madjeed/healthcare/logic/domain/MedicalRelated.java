package ir.madjeed.healthcare.logic.domain;


import ir.madjeed.healthcare.logic.entity.Sickness;
import java.util.ArrayList;

public interface MedicalRelated {
    public ArrayList<Sickness> getPatientAllSickness(String did);
    public void addPatientSickness(String did, String pid, String subject, String detail);
}
