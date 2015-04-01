package ir.madjeed.healthcare.data.repo;
import ir.madjeed.healthcare.data.entity.User;

import java.util.List;

public interface RepoUser{

    public int create(User instance);
    public int update(User instance);
    public int delete(User instance);
    public User getByID(String id);
    public List<User> getAll();

}

