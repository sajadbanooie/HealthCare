package ir.madjeed.healthcare.dao;

import ir.madjeed.healthcare.logic.entity.ConsultantMessage;
import java.util.ArrayList;

public interface ConsultantMessageDAO {
    public int create(ConsultantMessage instance);
    public int update(ConsultantMessage instance);
    public int delete(ConsultantMessage instance);
    public ConsultantMessage getByID(Integer id);
    public ArrayList<ConsultantMessage> getAll();
}

