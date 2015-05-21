package ir.madjeed.healthcare.facade;

import android.content.Context;
import ir.madjeed.healthcare.data.AuthenticationRepo;
import ir.madjeed.healthcare.data.repo.impl.persistent.AuthenticationRepoPersistent;

/**
 * Created by admin on 5/21/2015.
 */
public class AuthenticationFacade {
    private AuthenticationRepo authenticationRepo;

    public AuthenticationFacade(Context context) {
        this.authenticationRepo = new AuthenticationRepoPersistent(context);
    }

    public int login(String user, String pass){
        return authenticationRepo.authenticate(user, pass);
    }

    public String getUserRole(String username, String password){
        return authenticationRepo.getUserRole(username, password);
    }
}

