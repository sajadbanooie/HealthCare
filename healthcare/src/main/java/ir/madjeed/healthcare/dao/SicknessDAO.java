package ir.madjeed.healthcare.dao;

import ir.madjeed.healthcare.logic.entity.Sickness;
import java.util.ArrayList;

public interface SicknessDAO {

    public int create(Sickness instance);
    public int update(Sickness instance);
    public int delete(Sickness instance);
    public Sickness getByID(Integer id);
    public ArrayList<Sickness> getAll();

}

