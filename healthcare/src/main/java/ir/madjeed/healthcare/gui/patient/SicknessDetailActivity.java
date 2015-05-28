package ir.madjeed.healthcare.gui.patient;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import com.beardedhen.androidbootstrap.BootstrapButton;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.MedicalFacade;
import ir.madjeed.healthcare.gui.base.BaseActivity;

import java.util.ArrayList;

public class SicknessDetailActivity extends BaseActivity {

    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.detail) TextView detail;
    @InjectView(R.id.add_prescription) BootstrapButton add_prescription;

    private String sickness_id;
    private MedicalFacade facade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new MedicalFacade(this);
        super.onCreate(savedInstanceState);
        sickness_id = getIntent().getExtras().getString("ID");

        ArrayList<String> info = facade.getSicknessInfo(sickness_id);

        title.setText("بیماری شماره "+sickness_id);
        detail.setText(info.get(1));

        if (role.equals("بیمار")){
            add_prescription.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.sickness_detail;
    }

    @OnClick(R.id.add_prescription)
    public void add_prescription_btn(){
        customStartActivity(AddPrescriptionActivity.class, title.getText().toString());
    }
}
