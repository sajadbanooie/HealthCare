package ir.madjeed.healthcare.logic.entity;

public abstract class User {
    public abstract String getUsername();
    public abstract  void setUsername(String username);
    public abstract  String getPassword();
    public abstract  void setPassword(String password);
    public abstract  String getName();
    public abstract  void setName(String name);
    public abstract  String getFamily();
    public abstract  void setFamily(String family);
    public abstract  String getNationalID();
    public abstract  void setNationalID(String nationalID);
    public abstract  String getRole();
    public abstract  void setRole(String role);
    public abstract  String getRegistrationStatus();
    public abstract  void setRegistrationStatus(String status);
    public String getFullName(){
        return getName()+" "+getFamily();
    }
}
