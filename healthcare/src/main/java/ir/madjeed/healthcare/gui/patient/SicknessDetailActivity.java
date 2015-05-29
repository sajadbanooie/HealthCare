package ir.madjeed.healthcare.gui.patient;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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

        final ArrayList<Pair<String, String>> sicknessPrescriptions = facade.getSicknessPrescriptions(sickness_id);
        LinearLayout layout = (LinearLayout) findViewById(R.id.prescriptions_layout);
        for (int i = 0; i < sicknessPrescriptions.size(); i++) {
            LinearLayout row = new LinearLayout(this);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,20,0,0);
            row.setLayoutParams(layoutParams);

            Button prescription_btn = new Button(this);
            prescription_btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            prescription_btn.setText(sicknessPrescriptions.get(i).second);

            final int finalI = i;
            prescription_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customStartActivity(PrescriptionDetailActivity.class, sicknessPrescriptions.get(finalI).first);
                }
            });

            row.addView(prescription_btn);
            layout.addView(row);
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.sickness_detail;
    }

    @OnClick(R.id.add_prescription)
    public void add_prescription_btn(){
        customStartActivity(AddPrescriptionActivity.class, sickness_id);
    }
}
