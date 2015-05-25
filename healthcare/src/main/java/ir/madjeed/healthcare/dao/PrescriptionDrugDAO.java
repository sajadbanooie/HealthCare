package ir.madjeed.healthcare.dao;

import ir.madjeed.healthcare.logic.entity.PrescriptionDrug;
import java.util.ArrayList;

public interface PrescriptionDrugDAO {

    public int create(PrescriptionDrug instance);
    public int update(PrescriptionDrug instance);
    public int delete(PrescriptionDrug instance);
    public PrescriptionDrug getByID(Integer id);
    public ArrayList<PrescriptionDrug> getAll();

}

