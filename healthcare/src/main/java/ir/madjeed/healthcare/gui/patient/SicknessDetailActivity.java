package ir.madjeed.healthcare.gui.patient;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;

public class SicknessDetailActivity extends BaseActivity {

    @InjectView(R.id.title) TextView title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String id = getIntent().getExtras().getString("ID");
        title.setText(id);
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
