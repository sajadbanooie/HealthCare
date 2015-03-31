package ir.madjeed.healthcare.data;


public interface User {
    public void setUsername(String username);
    public String getUsername();
    public void setPassword(String password);
    public String getPassword();
    public void setEmail(String email);
    public String getEmail();
    public void setAlias(String alias);
    public String getAlias();
    public int save(Repo repo);
    public int delete(Repo repo);
    public String toString();
}
