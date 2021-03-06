package ir.madjeed.healthcare.gui.menu;

import android.content.Intent;
import android.os.Bundle;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.EditInfoActivity;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.BaseListOptions;
import ir.madjeed.healthcare.gui.list.MessageListActivity;
import ir.madjeed.healthcare.gui.list.PatientListActivity;
import ir.madjeed.healthcare.gui.list.RequestListActivity;
import ir.madjeed.healthcare.gui.consultant.ConsultantActivity;
import ir.madjeed.healthcare.gui.profile.*;


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
        customStartActivity(MessageListActivity.class, new BaseListOptions(MessageProfileActivity.class, null, "view", "mine"));
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
        customStartActivity(RequestListActivity.class, new BaseListOptions(RequestProfileActivity.class, null, "view", "mine"));
    }

    @OnClick(R.id.my_patients_btn)
    public void my_patients_btn() {
        customStartActivity(PatientListActivity.class, new BaseListOptions(PatientProfileActivity.class, null, "view", "mine"));
    }

}
