package ir.madjeed.healthcare.gui.list;

import android.os.Bundle;
import android.util.Pair;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.DoctorFacade;
import ir.madjeed.healthcare.facade.PatientFacade;
import ir.madjeed.healthcare.gui.base.BaseListActivity;
import ir.madjeed.healthcare.gui.base.BaseRowObject;

import java.util.ArrayList;

/**
 * Created by admin on 5/22/2015.
 */
public class DoctorListActivity extends BaseListActivity {

    private PatientFacade patientFacade;
    private DoctorFacade doctorFacade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        patientFacade = new PatientFacade(this);
        doctorFacade = new DoctorFacade(this);
        super.onCreate(savedInstanceState);
    }

        @Override
    protected String getListTitle() {
        if (listOptions.getCategory().equals("normal")) {
            return getString(R.string.doctors_list);
        }else{
            return getString(R.string.expert_list);
        }
    }

    @Override
    protected ArrayList<Pair<String, String>> getRowItems() {
        ArrayList<Pair<String, String>> doctors;
        if (listOptions.getCategory().equals("normal")){
            doctors = patientFacade.getAllNormalDoctors();
        }else{ // expert
            doctors = doctorFacade.getAllExpertDoctors();
        }
        return doctors;
    }
}
