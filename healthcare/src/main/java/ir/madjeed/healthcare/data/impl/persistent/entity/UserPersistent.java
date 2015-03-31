package ir.madjeed.healthcare.data.impl.persistent.entity;

import ir.madjeed.healthcare.data.Repo;
import com.j256.ormlite.field.DatabaseField;
import ir.madjeed.healthcare.data.User;


// id column name should be same among entities because of my implementation
public class UserPersistent implements User {

    @DatabaseField(id = true)
    String username;
    @DatabaseField
    String password;
    @DatabaseField
    String email;
    @DatabaseField
    String alias;

    public UserPersistent() {
        // needed by ormlite
    }

    public UserPersistent(String alias, String username, String password, String email) {
        this.alias = alias;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public int save(Repo repo)
    {
        if(repo.getRepoUsers().getByID(username) == null)
        {
            return repo.getRepoUsers().create(this);
        }
        else
        {
            return repo.getRepoUsers().update(this);
        }
    }

    @Override
    public int delete(Repo repo)
    {
        return repo.getRepoUsers().delete(this);
    }

    @Override
    public String toString()
    {
        return alias;
    }

}
