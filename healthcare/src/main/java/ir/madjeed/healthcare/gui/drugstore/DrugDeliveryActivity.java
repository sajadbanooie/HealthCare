package ir.madjeed.healthcare.gui.drugstore;

import android.os.Bundle;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.ListOptions;
import ir.madjeed.healthcare.gui.profile.PatientProfileActivity;
import ir.madjeed.healthcare.gui.profile.PrescriptionDeliveryProfileActivity;


public class DrugDeliveryActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_drugstore;
    }


    @OnClick (R.id.find_prescription_by_name)
    public void find_prescription_by_name(){
        customStartActivity(new ListOptions(PatientProfileActivity.class, "patient", "view", "all"));
    }

    @OnClick (R.id.find_prescription_by_id)
    public void find_prescription_by_id(){
        customStartActivity(new ListOptions(PrescriptionDeliveryProfileActivity.class, "prescription", "view", "mine"));
    }

    @OnClick({R.id.back, R.id.back2})
    public void on_back_btn() {
        super.onBackPressed();
    }

}
