package ir.madjeed.healthcare.facade;

import android.content.Context;
import android.util.Pair;
import ir.madjeed.healthcare.logic.domain.Administration;
import ir.madjeed.healthcare.logic.domain.Authentication;
import ir.madjeed.healthcare.logic.domain.impl.persistent.AdministrationPersistent;
import ir.madjeed.healthcare.logic.domain.impl.persistent.AuthenticationPersistent;
import ir.madjeed.healthcare.logic.entity.User;

import java.util.ArrayList;

/**
 * Created by admin on 5/21/2015.
 */
public class AdministrationFacade {
    private Administration administration;

    public AdministrationFacade(Context context) {
        this.administration = new AdministrationPersistent(context);
    }

    public ArrayList<Pair<String, String>> getAllUsers(){  // first = id, second = name
        ArrayList<User> allUsers = administration.getAllUsers();
        ArrayList<Pair<String, String>> result = new ArrayList<Pair<String, String>>();
        for (int i = 0; i < allUsers.size(); i++) {
            result.add(new Pair<String, String>(allUsers.get(i).getUsername(), allUsers.get(i).getName()+" "+allUsers.get(i).getFamily()));
        }
        return result;
    }
}

