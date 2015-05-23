package ir.madjeed.healthcare.dao;
import ir.madjeed.healthcare.logic.entity.SupervisionRequest;
import java.util.ArrayList;

public interface SupervisionRequestDAO {

    public int create(SupervisionRequest instance);
    public int update(SupervisionRequest instance);
    public int delete(SupervisionRequest instance);
    public SupervisionRequest getByID(String id);
    public ArrayList<SupervisionRequest> getAll();

}

