package ir.madjeed.healthcare.facade;

import android.content.Context;
import android.util.Pair;
import ir.madjeed.healthcare.logic.domain.PatientRelated;
import ir.madjeed.healthcare.logic.domain.impl.persistent.PatientRelatedPersistent;
import ir.madjeed.healthcare.logic.entity.User;

import java.util.ArrayList;

/**
 * Created by admin on 5/21/2015.
 */
public class PatientFacade {
    private PatientRelated patientRelated;

    public PatientFacade(Context context) {
        this.patientRelated = new PatientRelatedPersistent(context);
    }

    public ArrayList<Pair<String, String>> getAllNormalDoctors(){  // first = id, second = name
        ArrayList<User> allUsers = patientRelated.getAllNormalDoctors();
        ArrayList<Pair<String, String>> result = new ArrayList<Pair<String, String>>();
        for (int i = 0; i < allUsers.size(); i++) {
            result.add(new Pair<String, String>(allUsers.get(i).getUsername(), allUsers.get(i).getFullName()));
        }
        return result;
    }

    public String getMyDoctorName(String patient_username){
        User u = patientRelated.getPatientCurrentNormalDoctor(patient_username);
        if (u ==null){
            return null;
        }else{
            return u.getFullName();
        }
    }

    public String getDoctorName(String doctor_username){
        User u = patientRelated.getDoctor(doctor_username);
        return u.getFullName();
    }

    public void makeSupervisionRequest(String patient_username, String doctor_username, String detail){
        if (getMyDoctorName(patient_username)!=null){
            patientRelated.finishPatientCurrentNormalSupervision(patient_username);
        }
        patientRelated.makeSupervisionRequest(patient_username,doctor_username, detail);
    }

    public ArrayList<Pair<String, String>> getPatientAllDoctors(String pid){ // including normal and expert
        ArrayList<User> allUsers = patientRelated.getPatientAllDoctors(pid);
        ArrayList<Pair<String, String>> result = new ArrayList<Pair<String, String>>();
        for (int i = 0; i < allUsers.size(); i++) {
            result.add(new Pair<String, String>(allUsers.get(i).getUsername(), allUsers.get(i).getFullName()));
        }
        return result;
    }
}

