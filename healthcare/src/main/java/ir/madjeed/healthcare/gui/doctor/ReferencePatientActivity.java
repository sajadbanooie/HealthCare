package ir.madjeed.healthcare.gui.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.DoctorFacade;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.BaseListOptions;
import ir.madjeed.healthcare.gui.base.ListOptions;
import ir.madjeed.healthcare.gui.list.DoctorListActivity;
import ir.madjeed.healthcare.gui.list.MessageListActivity;
import ir.madjeed.healthcare.gui.profile.DoctorProfileActivity;
import ir.madjeed.healthcare.gui.profile.MessageProfileActivity;


public class ReferencePatientActivity extends BaseActivity {

    @InjectView(R.id.selected_doctor) TextView selected_doctor;
    @InjectView(R.id.detail) TextView detail;

    private DoctorFacade facade;
    private String patient_id;
    private String selected_doctor_id;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new DoctorFacade(this);
        super.onCreate(savedInstanceState);
        patient_id = getIntent().getExtras().getString("ID");
        selected_doctor.setText("پزشک انتخاب شده: ---");
        selected_doctor_id = "";
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_reference_patient;
    }

    @OnClick(R.id.approve)
    public void approve() {
        if (selected_doctor_id.equals("")){
            showMessage("error", "لطفا یک پزشک انتخاب کنید.");
        }else if (detail.getText().toString().equals("")){
            showMessage("error", "شرح درخواست را بنویسید.");
        }else if(facade.hasActiveSupervision(patient_id, selected_doctor_id)){
            showMessage("error", "این پزشک هم اکنون ناظر این بیمار می باشد.");
        }else{
            facade.makeReferRequest(patient_id, selected_doctor_id, detail.getText().toString());
            customStartActivity(MessageListActivity.class, new BaseListOptions(MessageProfileActivity.class, null, "view", "mine"));
        }
    }

    @OnClick(R.id.back)
    public void back() {
        super.onBackPressed();
    }

    @OnClick(R.id.select)
    public void select() {
        customStartActivity(DoctorListActivity.class, new BaseListOptions(DoctorProfileActivity.class, null, "select", "expert"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data!=null && data.hasExtra("ID")) {
            selected_doctor_id = data.getStringExtra("ID");
            String name = facade.getDoctorName(selected_doctor_id);
            selected_doctor.setText("پزشک انتخاب شده: "+name);
        }
    }
}
