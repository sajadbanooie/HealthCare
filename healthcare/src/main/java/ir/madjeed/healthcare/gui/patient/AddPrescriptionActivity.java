package ir.madjeed.healthcare.gui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.ListOptions;
import ir.madjeed.healthcare.gui.profile.DoctorProfileActivity;


public class AddPrescriptionActivity extends BaseActivity {

    @InjectView(R.id.drugs) TextView drugs;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_prescription;
    }

    @OnClick(R.id.approve)
    public void approve() {
        finish();
    }

    @OnClick(R.id.back)
    public void back() {
        super.onBackPressed();
    }

    @OnClick(R.id.select_drug)
    public void select() {
        customStartActivity(new ListOptions(DoctorProfileActivity.class, "drug", "select", "all"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data!=null && data.hasExtra("ID")){
            String id = data.getStringExtra("ID");
            drugs.setText(drugs.getText().toString()+ " " + id);
        }
    }
}
