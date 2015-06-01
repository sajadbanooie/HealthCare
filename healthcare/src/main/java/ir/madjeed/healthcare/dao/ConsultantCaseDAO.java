package ir.madjeed.healthcare.dao;

import ir.madjeed.healthcare.logic.entity.ConsultantCase;
import java.util.ArrayList;

public interface ConsultantCaseDAO {
    public int create(ConsultantCase instance);
    public int update(ConsultantCase instance);
    public int delete(ConsultantCase instance);
    public ConsultantCase getByID(Integer id);
    public ArrayList<ConsultantCase> getAll();
}

