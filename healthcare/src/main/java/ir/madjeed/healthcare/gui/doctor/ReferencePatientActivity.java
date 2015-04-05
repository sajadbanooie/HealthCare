package ir.madjeed.healthcare.gui.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.ListOptions;
import ir.madjeed.healthcare.gui.profile.DoctorProfileActivity;


public class ReferencePatientActivity extends BaseActivity {

    @InjectView(R.id.reference) TextView reference;
    @InjectView(R.id.detail) TextView detail;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_reference_patient;
    }

    @OnClick(R.id.approve)
    public void approve() {
    }

    @OnClick(R.id.back)
    public void back() {
        super.onBackPressed();
    }

    @OnClick(R.id.select)
    public void select() {
        customStartActivity(new ListOptions(DoctorProfileActivity.class, "doctor", "view|select", "all"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String id = data.getStringExtra("ID");
        detail.setText(id);
    }
}
