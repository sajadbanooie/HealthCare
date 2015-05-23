package ir.madjeed.healthcare.gui.list;

import android.os.Bundle;
import android.util.Pair;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.PatientFacade;
import ir.madjeed.healthcare.gui.base.BaseListActivity;
import ir.madjeed.healthcare.gui.base.BaseRowObject;

import java.util.ArrayList;

/**
 * Created by admin on 5/22/2015.
 */
public class DoctorListActivity extends BaseListActivity {

    private PatientFacade facade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new PatientFacade(this); // it should be top cause used in getListItems which called in super.onCreate
        super.onCreate(savedInstanceState);
    }

        @Override
    protected String getListTitle() {
        return getString(R.string.doctors_list);
    }

    @Override
    protected ArrayList<BaseRowObject> getListItems() {
        ArrayList<BaseRowObject> data= new ArrayList<BaseRowObject>();
        ArrayList<Pair<String, String>> allDoctors = facade.getAllNormalDoctors();
        for (int i = 0; i < allDoctors.size(); i++) {
            data.add(new BaseRowObject(allDoctors.get(i).first, allDoctors.get(i).second));
        }
        return data;
    }
}
