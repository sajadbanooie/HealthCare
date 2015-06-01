package ir.madjeed.healthcare.dao;

import ir.madjeed.healthcare.logic.entity.PhysicalState;
import java.util.ArrayList;

public interface PhysicalStateDAO {

    public int create(PhysicalState instance);
    public int update(PhysicalState instance);
    public int delete(PhysicalState instance);
    public PhysicalState getByID(Integer id);
    public ArrayList<PhysicalState> getAll();

}

