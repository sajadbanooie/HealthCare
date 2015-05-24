package ir.madjeed.healthcare.gui.list;

import android.os.Bundle;
import android.util.Pair;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.DoctorFacade;
import ir.madjeed.healthcare.facade.MessageFacade;
import ir.madjeed.healthcare.gui.base.BaseListActivity;

import java.util.ArrayList;

/**
 * Created by admin on 5/22/2015.
 */
public class RequestListActivity extends BaseListActivity {

    private DoctorFacade facade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new DoctorFacade(this); // it should be top cause used in getListItems which called in super.onCreate
        super.onCreate(savedInstanceState);
    }

        @Override
    protected String getListTitle() {
        return getString(R.string.requests);
    }

    @Override
    protected ArrayList<Pair<String, String>> getRowItems() {
        ArrayList<Pair<String, String>> messages;
        messages = facade.getDoctorSupervisionRequests(username);
        return messages;
    }
}
