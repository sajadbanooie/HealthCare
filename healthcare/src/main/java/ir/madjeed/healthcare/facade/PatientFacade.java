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
            result.add(new Pair<String, String>(allUsers.get(i).getUsername(), allUsers.get(i).getName()+" "+allUsers.get(i).getFamily()));
        }
        return result;
    }

    public String getMyDoctorName(String patient_username){
        return patient_username;
    }

    public String getDoctorName(String doctor_username){
        User u = patientRelated.getDoctor(doctor_username);
        return u.getName()+" "+u.getFamily();
    }

    public void makeSupervisionRequest(String patient_username, String doctor_username, String detail){
        patientRelated.makeSupervisionRequest(patient_username,doctor_username, detail);
    }
}

