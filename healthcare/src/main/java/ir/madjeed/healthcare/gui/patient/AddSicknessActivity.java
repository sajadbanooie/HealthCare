package ir.madjeed.healthcare.gui.patient;

import android.os.Bundle;
import butterknife.InjectView;
import butterknife.OnClick;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.MedicalFacade;
import ir.madjeed.healthcare.gui.base.BaseActivity;


public class AddSicknessActivity extends BaseActivity {

    private MedicalFacade facade;

    @InjectView(R.id.approve) BootstrapButton approve_btn;
    @InjectView(R.id.back) BootstrapButton back_btn;
    @InjectView(R.id.subject) BootstrapEditText subject;
    @InjectView(R.id.detail) BootstrapEditText detail;

    private String patient_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new MedicalFacade(this);
        super.onCreate(savedInstanceState);
        patient_id = getIntent().getExtras().getString("ID");
        subject.setText(getString(R.string.add_sickness_record));
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_sickness_add;
    }

    @OnClick(R.id.approve)
    public void approve() {
        facade.addPatientSickness(username, patient_id,
                subject.getText().toString(), detail.getText().toString());
        finish();
    }

    @OnClick(R.id.back)
    public void back() {
        super.onBackPressed();
    }

}
