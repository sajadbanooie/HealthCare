package ir.madjeed.healthcare.logic.domain;


import ir.madjeed.healthcare.logic.entity.PhysicalActivity;
import ir.madjeed.healthcare.logic.entity.PhysicalState;

import java.util.ArrayList;

public interface PhysicalRelated {
    public void addPhysicalState(String pid, Integer ghand, Integer vazn, Integer feshar, Integer ghandeKhun);
    public ArrayList<PhysicalState> getPatientAllPhysicalStates(String pid);
    public ArrayList<PhysicalActivity> getPatientAllPhysicalActivities(String pid);
}
