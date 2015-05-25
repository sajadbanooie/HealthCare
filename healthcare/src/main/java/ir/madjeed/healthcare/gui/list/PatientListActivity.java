package ir.madjeed.healthcare.gui.list;

import android.os.Bundle;
import android.util.Pair;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.AdministrationFacade;
import ir.madjeed.healthcare.facade.DoctorFacade;
import ir.madjeed.healthcare.gui.base.BaseListActivity;

import java.util.ArrayList;

/**
 * Created by admin on 5/22/2015.
 */
public class PatientListActivity extends BaseListActivity {

    private DoctorFacade doctorFacade;
    private AdministrationFacade administrationFacade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        doctorFacade = new DoctorFacade(this); // it should be top cause used in getListItems which called in super.onCreate
        administrationFacade = new AdministrationFacade(this); // it should be top cause used in getListItems which called in super.onCreate
        super.onCreate(savedInstanceState);
    }

        @Override
    protected String getListTitle() {
        return getString(R.string.patients_list);
    }

    @Override
    protected ArrayList<Pair<String, String>> getRowItems() {
        ArrayList<Pair<String, String>> patients;
        if (listOptions.getCategory().equals("all")){
            patients = administrationFacade.getAllPatients();
        }else{
            patients = doctorFacade.getDoctorPatients(username);
        }
        return patients;
    }
}
