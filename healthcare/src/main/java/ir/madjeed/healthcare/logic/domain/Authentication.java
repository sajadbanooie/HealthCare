package ir.madjeed.healthcare.logic.domain;


public interface Authentication {
    public int authenticate(String username, String password);
    public String getUserRole(String username);
    public boolean userExist(String username);
    public void registerUser(String ... userInfo);
}
