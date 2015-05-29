package ir.madjeed.healthcare.logic.domain;


import ir.madjeed.healthcare.logic.entity.Drug;
import ir.madjeed.healthcare.logic.entity.Prescription;
import ir.madjeed.healthcare.logic.entity.PrescriptionDrug;
import ir.madjeed.healthcare.logic.entity.Sickness;
import java.util.ArrayList;

public interface MedicalRelated {
    public ArrayList<Sickness> getPatientAllSickness(String did);
    public ArrayList<Drug> getAllDrugs();
    public Drug getDrug(String did);
    public Sickness getSickness(String sid);
    public void addPatientSickness(String did, String pid, String subject, String detail);
    public void addPrescription(String sickness_id, ArrayList<Drug> selected_drugs, ArrayList<Integer> amount);
    public ArrayList<Prescription> getSicknessPrescriptions(String sid);

    public ArrayList<PrescriptionDrug> getPrescriptionPrescriptionDrugs(String prescription_id);
    public PrescriptionDrug getPrescriptionDrug(String pd_id);
}
