package ir.madjeed.healthcare.gui.list;

import android.os.Bundle;
import android.util.Pair;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.AdministrationFacade;
import ir.madjeed.healthcare.gui.base.BaseListActivity;
import ir.madjeed.healthcare.gui.base.BaseRowObject;

import java.util.ArrayList;

/**
 * Created by admin on 5/22/2015.
 */
public class UserListActivity extends BaseListActivity {

    private AdministrationFacade facade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new AdministrationFacade(this); // it should be top cause used in getListItems which called in super.onCreate
        super.onCreate(savedInstanceState);
    }

        @Override
    protected String getListTitle() {
        return getString(R.string.users);
    }

    @Override
    protected ArrayList<Pair<String, String>> getRowItems() {
        ArrayList<Pair<String, String>> allUsers = facade.getAllUsers();
        return allUsers;
    }
}
