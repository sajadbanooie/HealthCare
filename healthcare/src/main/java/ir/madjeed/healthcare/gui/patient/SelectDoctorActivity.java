package ir.madjeed.healthcare.gui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.PatientFacade;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.BaseListOptions;
import ir.madjeed.healthcare.gui.list.DoctorListActivity;
import ir.madjeed.healthcare.gui.profile.DoctorProfileActivity;


public class SelectDoctorActivity extends BaseActivity {

    @InjectView(R.id.current_doctor) TextView current_doctor;

    PatientFacade facade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facade = new PatientFacade(this);
        current_doctor.setText("دکتر فعلی: "+facade.getMyDoctorName(username));

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_select_doctor;
    }

    @OnClick(R.id.approve)
    public void approve() {
    }

    @OnClick(R.id.back)
    public void back_btn() {
        super.onBackPressed();
    }

    @OnClick(R.id.select)
    public void select() {
//        customStartActivity(new ListOptions(DoctorProfileActivity.class, "doctor", "view|select", "all"));
        customStartActivity(DoctorListActivity.class, new BaseListOptions(DoctorProfileActivity.class, null, "view|select", "all"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data!=null && data.hasExtra("ID")){
            String id = data.getStringExtra("ID");
            current_doctor.setText(id);
        }
    }
}
