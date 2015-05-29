package ir.madjeed.healthcare.gui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Pair;
import android.widget.*;
import butterknife.InjectView;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.MedicalFacade;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.BaseListOptions;
import ir.madjeed.healthcare.gui.base.CustomRowObject;
import ir.madjeed.healthcare.gui.list.DrugListActivity;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;


public class AddPrescriptionActivity extends BaseActivity {

    @InjectView(R.id.drugs) TextView drugs;
    @InjectView(R.id.title) TextView title;

    private String sickness_id;
    private MedicalFacade facade;
    private ArrayList<CustomRowObject> selected_drugs; // id, name, price

    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new MedicalFacade(this);
        super.onCreate(savedInstanceState);
        sickness_id = getIntent().getExtras().getString("ID");
        title.setText(getString(R.string.add_prescription)+" برای نسخه شماره"+sickness_id);
        selected_drugs = new ArrayList<CustomRowObject>();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_prescription;
    }

    @OnClick(R.id.approve)
    public void approve() {
        if (selected_drugs.size()==0){
            showMessage("error", "باید حداقل یک دارو انتخاب کنید.");
        }else {
            ArrayList<Pair<String, String>> result = new ArrayList<Pair<String, String>>();
            for (int i = 0; i < selected_drugs.size(); i++) {
                EditText tmp = (EditText) findViewById(i);
                result.add(new Pair<String, String>(selected_drugs.get(i).getColumn(0), tmp.getText().toString()));
            }
            facade.addPrescription(sickness_id, result);
            finish();
        }
    }

    @OnClick(R.id.back)
    public void back() {
        super.onBackPressed();
    }

    @OnClick(R.id.select_drug)
    public void select() {
        customStartActivity(DrugListActivity.class, new BaseListOptions(null, null, "select", "mine"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data!=null && data.hasExtra("ID")){
            String id = data.getStringExtra("ID");

            ArrayList<String> selected_drug_info = facade.getDrugInfo(id);
            CustomRowObject cro = new CustomRowObject(selected_drug_info.get(0), selected_drug_info.get(1),
                                                        selected_drug_info.get(2));
            selected_drugs.add(cro);

            LinearLayout layout = (LinearLayout) findViewById(R.id.drugs_layout);
            for (int i = selected_drugs.size()-1; i < selected_drugs.size(); i++) {
                LinearLayout row = new LinearLayout(this);

                LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0,20,0,0);
                row.setLayoutParams(layoutParams);

                TextView name = new TextView(this);
                name.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f));
                name.setText(selected_drugs.get(i).getColumn(1));

                EditText count = new EditText(this);
                count.setInputType(InputType.TYPE_CLASS_NUMBER);
                count.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f));
                count.setText("1");
                count.setId(i);

                row.addView(count);
                row.addView(name);
                layout.addView(row);
            }
        }
    }
}
