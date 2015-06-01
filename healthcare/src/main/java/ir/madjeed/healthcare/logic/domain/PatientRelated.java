package ir.madjeed.healthcare.logic.domain;


import ir.madjeed.healthcare.logic.entity.User;
import java.util.ArrayList;

public interface PatientRelated {
    public ArrayList<User> getAllNormalDoctors();
    public User getDoctor(String username);
    public void makeSupervisionRequest(String patient_username, String doctor_username, String detail);
    public User getPatientCurrentNormalDoctor(String pid);
    public void finishPatientCurrentNormalSupervision(String pid);
    public ArrayList<User> getPatientAllDoctors(String pid);
}
