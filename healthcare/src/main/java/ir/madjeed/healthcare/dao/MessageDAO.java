package ir.madjeed.healthcare.dao;
import ir.madjeed.healthcare.logic.entity.Message;
import java.util.ArrayList;

public interface MessageDAO {

    public int create(Message instance);
    public int update(Message instance);
    public int delete(Message instance);
    public Message getByID(String id);
    public ArrayList<Message> getAll();

}

