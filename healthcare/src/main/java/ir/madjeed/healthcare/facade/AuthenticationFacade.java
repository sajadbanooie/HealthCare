package ir.madjeed.healthcare.facade;

import android.content.Context;
import ir.madjeed.healthcare.logic.domain.Authentication;
import ir.madjeed.healthcare.logic.domain.impl.persistent.AuthenticationPersistent;

/**
 * Created by admin on 5/21/2015.
 */
public class AuthenticationFacade {
    private Authentication authentication;

    public AuthenticationFacade(Context context) {
        this.authentication = new AuthenticationPersistent(context);
    }

    public int login(String user, String pass){
        return authentication.authenticate(user, pass);
    }

    public String getUserRole(String username){
        return authentication.getUserRole(username);
    }

    public boolean userExists(String username){
        return authentication.userExist(username);
    }

    public void registerUser(String ... userInfo){
        authentication.registerUser(userInfo);
    }
}

