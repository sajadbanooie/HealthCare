package ir.madjeed.healthcare.gui.menu;

import android.content.Intent;
import android.os.Bundle;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.EditInfoActivity;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.ListOptions;
import ir.madjeed.healthcare.gui.patient.AddPhysicalActivity;
import ir.madjeed.healthcare.gui.patient.ConsultantActivity;
import ir.madjeed.healthcare.gui.patient.SelectDoctorActivity;
import ir.madjeed.healthcare.gui.patient.SicknessHistoryActivity;
import ir.madjeed.healthcare.gui.profile.DoctorProfileActivity;


public class DoctorMenuActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_menu_doctor;
    }


    @OnClick(R.id.edit_info_btn)
    public void edit_info_btn() {
        customStartActivity(EditInfoActivity.class);
    }

    @OnClick(R.id.messages_btn)
    public void messages_btn() {
        customStartActivity(new ListOptions("message", "view", "mine"));
    }

    @OnClick(R.id.exit_btn)
    public void exit_btn() {
        startActivity(new Intent(this, StarterActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @OnClick(R.id.consultant_btn)
    public void consultant_btn() {
        customStartActivity(ConsultantActivity.class);
    }

    @OnClick(R.id.requests_btn)
    public void requests_btn() {
        showMessage("error", "not implemented yet");
    }

    @OnClick(R.id.my_patients_btn)
    public void my_doctors_btn() {
        customStartActivity(new ListOptions(DoctorProfileActivity.class, "patient", "view", "mine"));
    }

}
