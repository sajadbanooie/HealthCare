package ir.madjeed.healthcare.gui.patient;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.InjectView;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.MedicalFacade;
import ir.madjeed.healthcare.gui.base.BaseActivity;

import java.util.ArrayList;

public class PrescriptionDetailActivity extends BaseActivity {

    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.detail) TextView detail;

    private String prescription_id;
    private MedicalFacade facade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new MedicalFacade(this);
        super.onCreate(savedInstanceState);
        prescription_id = getIntent().getExtras().getString("ID");

        title.setText("نسخه شماره "+ prescription_id);

        final ArrayList<String> prescriptionDrugs = facade.getPrescriptionPrescriptionDrugs(prescription_id);
        LinearLayout layout = (LinearLayout) findViewById(R.id.drugs_layout);
        for (int i = 0; i < prescriptionDrugs.size(); i++) {
            ArrayList<String> prescriptionDrugInfo = facade.getPrescriptionDrugInfo(prescriptionDrugs.get(i));

            LinearLayout row = new LinearLayout(this);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,20,0,0);
            row.setLayoutParams(layoutParams);

            TextView name = new TextView(this);
            name.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
            name.setText(prescriptionDrugInfo.get(1));

            TextView count = new TextView(this);
            count.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
            count.setText(prescriptionDrugInfo.get(2));

            row.addView(count);
            row.addView(name);
            layout.addView(row);
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.prescription_detail;
    }
}
