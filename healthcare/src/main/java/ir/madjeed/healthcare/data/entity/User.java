package ir.madjeed.healthcare.data.entity;

import ir.madjeed.healthcare.data.Repo;
import com.j256.ormlite.field.DatabaseField;
import org.parceler.Parcel;


// id column name should be same among entities because of my implementation (pk_column)
@Parcel
public class User {

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


    public User() {
        // needed by ormlite
    }

    public User(String username, String password, String name, String family, String nationalID, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.family = family;
        this.nationalID = nationalID;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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
