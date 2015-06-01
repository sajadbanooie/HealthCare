package ir.madjeed.healthcare.gui.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import com.beardedhen.androidbootstrap.BootstrapButton;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.BaseListOptions;
import ir.madjeed.healthcare.gui.base.ListOptions;
import ir.madjeed.healthcare.gui.doctor.AddPhysicalStateActivity;
import ir.madjeed.healthcare.gui.doctor.PhysicalActivityReportActivity;
import ir.madjeed.healthcare.gui.doctor.ReferencePatientActivity;
import ir.madjeed.healthcare.gui.list.PatientSicknessListActivity;
import ir.madjeed.healthcare.gui.patient.AddSicknessActivity;
import ir.madjeed.healthcare.gui.patient.PhysicalStateActivity;
import ir.madjeed.healthcare.gui.patient.SicknessDetailActivity;


import java.util.List;


public class PatientProfileActivity extends BaseActivity {

    @InjectView(R.id.title) TextView title;

    @InjectViews({R.id.medical_records, R.id.sickness_history, R.id.finish_supervision, R.id.reference,
                  R.id.physical_state_btn, R.id.patient_doctors, R.id.physical_state_report_btn})
    List<BootstrapButton> btns;

    @InjectView(R.id.prescription_list) BootstrapButton prescription_list_btn;

    private String patient_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        patient_id = getIntent().getExtras().getString("ID");
        title.setText("منوی "+patient_id);

        if (role.equals("داروخانه")){
            for (BootstrapButton btn : btns) {
                btn.setVisibility(View.GONE);
            }
        }else{
            prescription_list_btn.setVisibility(View.GONE);
        }
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_profile_patient;
    }


    @OnClick(R.id.sickness_history)
    public void sickness_history(){
        customStartActivity(PatientSicknessListActivity.class, new BaseListOptions(SicknessDetailActivity.class, patient_id, "view", "mine"));
    }

    @OnClick(R.id.add_sickness)
    public void add_sickness(){
        customStartActivity(AddSicknessActivity.class, patient_id);
    }

    @OnClick(R.id.add_physical_state_btn)
    public void add_physical_state_btn(){
        customStartActivity(AddPhysicalStateActivity.class, patient_id);
    }

    @OnClick(R.id.physical_state_btn)
    public void physical_state_btn(){
        customStartActivity(PhysicalStateActivity.class, patient_id);
    }

    @OnClick(R.id.physical_state_report_btn)
    public void physical_state_report_btn(){
        customStartActivity(PhysicalActivityReportActivity.class, patient_id);
    }

    @OnClick(R.id.reference)
    public void reference(){
        customStartActivity(ReferencePatientActivity.class, patient_id);
    }


    @OnClick(R.id.medical_records)
    public void medical_records(){
        customStartActivity(new ListOptions("medical record", "view", "mine")); // it needs id of it
    }

    @OnClick(R.id.finish_supervision)
    public void finish_supervision(){
        // nothing to do
    }

    @OnClick(R.id.patient_doctors)
    public void patient_doctors(){
        customStartActivity(new ListOptions(DoctorProfileActivity.class, "doctor", "view", "mine")); // need it (his)
    }

    @OnClick(R.id.prescription_list)
    public void prescription_list(){
        customStartActivity(new ListOptions(PrescriptionDeliveryProfileActivity.class, "prescription", "view", "mine"));
    }

}
