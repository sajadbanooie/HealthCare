package ir.madjeed.healthcare.gui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Pair;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.InjectView;
import butterknife.OnClick;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.MedicalFacade;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.ListOptions;
import ir.madjeed.healthcare.gui.profile.DoctorProfileActivity;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;


public class AddPrescriptionActivity extends BaseActivity {

    @InjectView(R.id.drugs) TextView drugs;
    @InjectView(R.id.title) TextView title;

    private String sickness_id;
    private MedicalFacade facade;
    private ArrayList<Pair<String, String>> selected_drugs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new MedicalFacade(this);
        super.onCreate(savedInstanceState);
        sickness_id = getIntent().getExtras().getString("ID");
        title.setText(getString(R.string.add_prescription)+" برای نسخه شماره"+sickness_id);
        selected_drugs = new ArrayList<Pair<String, String>>();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_prescription;
    }

    @OnClick(R.id.approve)
    public void approve() {
        finish();
    }

    @OnClick(R.id.back)
    public void back() {
        super.onBackPressed();
    }

    @OnClick(R.id.select_drug)
    public void select() {
        customStartActivity(new ListOptions(DoctorProfileActivity.class, "drug", "select", "all"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data!=null && data.hasExtra("ID")){
            String id = data.getStringExtra("ID");
            selected_drugs.add(new Pair<String, String>(id, id));

            LinearLayout layout = (LinearLayout) findViewById(R.id.drugs_layout);
            for (int i = selected_drugs.size()-1; i < selected_drugs.size(); i++) {
                LinearLayout row = new LinearLayout(this);

                LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0,20,0,0);
                row.setLayoutParams(layoutParams);

                TextView name = new TextView(this);
                name.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f));
                name.setText(selected_drugs.get(i).first);
                row.addView(name);

                EditText count = new EditText(this);
                count.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f));
                count.setText("1");
                row.addView(count);
//                    btnTag.setId(j + 1 + (i * 4));
                layout.addView(row);
            }
        }
    }
}
