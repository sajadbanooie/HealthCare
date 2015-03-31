package ir.madjeed.healthcare.data;


public interface User {
    public void setUsername(String username);
    public String getUsername();
    public void setPassword(String password);
    public String getPassword();
    public void setName(String email);
    public String getName();
    public void setFamily(String alias);
    public String getFamily();
    public void setNationalID(String alias);
    public String getNationalID();
    public int save(Repo repo);
    public void setRole(String alias);
    public String getRole();
    public int delete(Repo repo);
    public String toString();
}
