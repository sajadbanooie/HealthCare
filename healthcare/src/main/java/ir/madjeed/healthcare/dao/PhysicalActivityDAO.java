package ir.madjeed.healthcare.dao;

import ir.madjeed.healthcare.logic.entity.PhysicalActivity;
import java.util.ArrayList;

public interface PhysicalActivityDAO {

    public int create(PhysicalActivity instance);
    public int update(PhysicalActivity instance);
    public int delete(PhysicalActivity instance);
    public PhysicalActivity getByID(Integer id);
    public ArrayList<PhysicalActivity> getAll();

}

