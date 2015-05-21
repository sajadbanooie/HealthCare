package ir.madjeed.healthcare.data;


public interface AuthenticationRepo {
    public int authenticate(String username, String password);
    public String getUserRole(String username, String password);
}
