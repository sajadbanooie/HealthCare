package ir.madjeed.healthcare.dao;

import ir.madjeed.healthcare.logic.entity.Supervision;
import java.util.ArrayList;

public interface SupervisionDAO {

    public int create(Supervision instance);
    public int update(Supervision instance);
    public int delete(Supervision instance);
    public Supervision getByID(Integer id);
    public ArrayList<Supervision> getAll();

}

