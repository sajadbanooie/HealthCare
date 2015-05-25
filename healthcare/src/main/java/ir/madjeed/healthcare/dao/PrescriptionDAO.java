package ir.madjeed.healthcare.dao;

import ir.madjeed.healthcare.logic.entity.Prescription;

import java.util.ArrayList;

public interface PrescriptionDAO {

    public int create(Prescription instance);
    public int update(Prescription instance);
    public int delete(Prescription instance);
    public Prescription getByID(Integer id);
    public ArrayList<Prescription> getAll();

}

