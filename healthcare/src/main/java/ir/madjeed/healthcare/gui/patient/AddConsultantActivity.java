package ir.madjeed.healthcare.gui.patient;

import android.util.Pair;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import butterknife.InjectView;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import ir.madjeed.healthcare.facade.DoctorFacade;
import ir.madjeed.healthcare.facade.PatientFacade;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import android.os.Bundle;
import ir.madjeed.healthcare.R;
import butterknife.OnClick;
import ir.madjeed.healthcare.logic.domain.DoctorRelated;
import ir.madjeed.healthcare.logic.domain.PatientRelated;

import java.util.ArrayList;


public class AddConsultantActivity extends BaseActivity {

    @InjectView(R.id.audience) Spinner audience_spinner;
    @InjectView(R.id.subject) BootstrapEditText subject;
    @InjectView(R.id.your_message) BootstrapEditText your_message;

    private DoctorFacade doctorFacade;
    private PatientFacade patientFacade;

    private ArrayList<Pair<String, String>> audiences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        doctorFacade = new DoctorFacade(this);
        patientFacade = new PatientFacade(this);
        super.onCreate(savedInstanceState);

        //TODO loading from db
        ArrayList<String> spinnerArray = new ArrayList<String>();
        if (role.equals("بیمار")){
//            audiences = doctorFacade.getDoctorPatients(username);
        }else{
            audiences = doctorFacade.getDoctorPatients(username);
        }
        for (int i = 0; i < audiences.size(); i++) {
            spinnerArray.add(audiences.get(i).second);
        }

        ArrayAdapter<String> spinnerArrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        audience_spinner.setAdapter(spinnerArrayAdapter);

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_consultant_add;
    }

    @OnClick(R.id.approve)
    public void approve() {
        String sender = "doctor";
        if (role.equals("بیمار"))
            sender = "patient";
        if (audiences.size()==0){
            showMessage("error", "هیچ مخاطبی انتخاب نشده است.");
        }else if(subject.getText().toString().equals("")){
            showMessage("error", "موضوع نمی تواند خالی باشد.");
        }else if(your_message.getText().toString().equals("")){
            showMessage("error", "پیام نمی تواند خالی باشد.");
        }else{
            String audience_id = audiences.get(audience_spinner.getSelectedItemPosition()).first;
            doctorFacade.addConsultant(username, audience_id,
                    subject.getText().toString(), your_message.getText().toString(), sender);
            finish();
        }
    }

    @OnClick(R.id.back)
    public void back() {
        super.onBackPressed();
    }

}
