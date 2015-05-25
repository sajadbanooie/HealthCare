package ir.madjeed.healthcare.dao;

import ir.madjeed.healthcare.logic.entity.Drug;
import java.util.ArrayList;

public interface DrugDAO {

    public int create(Drug instance);
    public int update(Drug instance);
    public int delete(Drug instance);
    public Drug getByID(Integer id);
    public ArrayList<Drug> getAll();

}

