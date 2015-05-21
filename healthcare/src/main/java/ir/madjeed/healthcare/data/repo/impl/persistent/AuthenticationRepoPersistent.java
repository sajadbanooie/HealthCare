package ir.madjeed.healthcare.data.repo.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.data.AuthenticationRepo;
import ir.madjeed.healthcare.data.entity.User;


public class AuthenticationRepoPersistent extends BaseRepoPersistent implements AuthenticationRepo{

    private RepoUserPersistent Users;

    public AuthenticationRepoPersistent(Context context) {
        super(context);
    }

    @Override
    protected void makeNecessaryRepos() {
        Users = new RepoUserPersistent(getDatabaseHelper());
    }

    @Override
    public int authenticate(String username, String password) {
        User u = Users.getByID(username);
        if (u==null){
            return -1; // username not found
        }else if (!u.getPassword().equals(password)){
            return -2; // wrong password
        }
        return 1;
    }

    @Override
    public String getUserRole(String username){
        if (!userExist(username))
            return null;
        else{
            User u = Users.getByID(username);
            return u.getRole();
        }
    }

    @Override
    public boolean userExist(String username) {
        User u = Users.getByID(username);
        if (u==null)
            return false;
        return true;
    }

    @Override
    public void registerUser(String ... userInfo) {
        User user = new User(userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4], userInfo[5]);
        Users.create(user);
    }

}
