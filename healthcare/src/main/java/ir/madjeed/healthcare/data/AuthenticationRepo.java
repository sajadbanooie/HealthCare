package ir.madjeed.healthcare.data;


public interface AuthenticationRepo {
    public int authenticate(String username, String password);
    public String getUserRole(String username);
    public boolean userExist(String username);
    public void registerUser(String ... userInfo);
}
