package ir.madjeed.healthcare.logic.domain.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.dao.impl.persistent.UserDAOPersistent;
import ir.madjeed.healthcare.logic.domain.Authentication;
import ir.madjeed.healthcare.logic.entity.User;
import ir.madjeed.healthcare.logic.entity.impl.persistent.UserPersistent;


public class AuthenticationPersistent extends BasePersistent implements Authentication {

    private UserDAOPersistent Users;

    public AuthenticationPersistent(Context context) {
        super(context);
    }

    @Override
    protected void makeNecessaryDAO() {
        Users = new UserDAOPersistent(getDatabaseHelper());
    }

    @Override
    public int authenticate(String username, String password) {
        User u = Users.getByID(username);
        if (u==null){
            return -1; // username not found
        }else if (!u.getPassword().equals(password)){
            return -2; // wrong password
        }else if (!u.getRegistrationStatus()){
            return -3; // means user is not active yet
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
        User user = new UserPersistent(userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4], userInfo[5]);
        Users.create(user);
    }

}
