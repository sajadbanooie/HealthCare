package ir.madjeed.healthcare.logic.entity;

import org.parceler.Parcel;

@Parcel
public interface User {
    public String getUsername();
    public void setUsername(String username);
    public String getPassword();
    public void setPassword(String password);
    public String getName();
    public void setName(String name);
    public String getFamily();
    public void setFamily(String family);
    public String getNationalID();
    public void setNationalID(String nationalID);
    public String getRole();
    public void setRole(String role);
}