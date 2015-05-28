package ir.madjeed.healthcare.gui.list;

import android.os.Bundle;
import android.util.Pair;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.MedicalFacade;
import ir.madjeed.healthcare.gui.base.BaseListActivity;

import java.util.ArrayList;

/**
 * Created by admin on 5/22/2015.
 */
public class DrugListActivity extends BaseListActivity {

    private MedicalFacade facade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new MedicalFacade(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getListTitle() {
        return getString(R.string.drugs_list);
    }

    @Override
    protected ArrayList<Pair<String, String>> getRowItems() {
        ArrayList<Pair<String, String>> sickness_records = facade.getAllDrugs();
        return sickness_records;
    }
}
