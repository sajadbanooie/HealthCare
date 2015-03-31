package ir.madjeed.healthcare.data.impl.persistent.entity;

import ir.madjeed.healthcare.data.Repo;
import com.j256.ormlite.field.DatabaseField;
import ir.madjeed.healthcare.data.User;


// id column name should be same among entities because of my implementation (pk_column)
public class UserPersistent implements User {

    @DatabaseField(id = true, columnName = "pk_column")
    String username;

    @DatabaseField
    String password;

    @DatabaseField
    String name;

    @DatabaseField
    String family;

    @DatabaseField
    String nationalID;

    @DatabaseField
    String role;


    public UserPersistent() {
        // needed by ormlite
    }

    public UserPersistent(String username, String password, String name, String family, String nationalID, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.family = family;
        this.nationalID = nationalID;
        this.role = role;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getFamily() {
        return family;
    }

    @Override
    public void setFamily(String family) {
        this.family = family;
    }

    @Override
    public String getNationalID() {
        return nationalID;
    }

    @Override
    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
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
        return username;
    }

}
