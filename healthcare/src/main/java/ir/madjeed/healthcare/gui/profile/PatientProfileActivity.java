package ir.madjeed.healthcare.gui.profile;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.ListOptions;
import ir.madjeed.healthcare.gui.doctor.ReferencePatientActivity;
import ir.madjeed.healthcare.gui.patient.SicknessHistoryActivity;


public class PatientProfileActivity extends BaseActivity {

    @InjectView(R.id.title) TextView title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String id = getIntent().getExtras().getString("ID");
        title.setText(id);

        // will add some buttons soon
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_profile_patient;
    }


    @OnClick(R.id.medical_records)
    public void medical_records(){
        customStartActivity(new ListOptions("medical record", "view", "mine")); // it needs id of it
    }

    @OnClick(R.id.sickness_history)
    public void sickness_history(){
        customStartActivity(SicknessHistoryActivity.class, title.getText().toString()); // it needs id of it
    }

    @OnClick(R.id.finish_supervision)
    public void finish_supervision(){
        // nothing to do
    }

    @OnClick(R.id.reference)
    public void reference(){
        customStartActivity(ReferencePatientActivity.class, title.getText().toString());
    }

    @OnClick(R.id.patient_doctors)
    public void patient_doctors(){
        customStartActivity(new ListOptions(DoctorProfileActivity.class, "doctor", "view", "mine")); // need it (his)
    }
}
