package ir.madjeed.healthcare.dao;
import ir.madjeed.healthcare.logic.entity.User;

import java.util.ArrayList;
import java.util.List;

public interface UserDAO {

    public int create(User instance);
    public int update(User instance);
    public int delete(User instance);
    public User getByID(String id);
    public ArrayList<User> getAll();

}

