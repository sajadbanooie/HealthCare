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


public class SelectDoctorActivity extends BaseActivity {

    @InjectView(R.id.current_doctor) TextView current_doctor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        current_doctor.setText("دکتر فعلی: علیرضا مظلومی");

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_select_doctor;
    }

    @OnClick(R.id.approve)
    public void approve() {
        // TODO go to medical records
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
        current_doctor.setText(id);
    }
}
