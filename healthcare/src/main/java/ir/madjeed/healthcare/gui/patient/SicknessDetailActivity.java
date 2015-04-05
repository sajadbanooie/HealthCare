package ir.madjeed.healthcare.gui.patient;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import com.beardedhen.androidbootstrap.BootstrapButton;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;

public class SicknessDetailActivity extends BaseActivity {

    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.add_prescription) BootstrapButton add_prescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String id = getIntent().getExtras().getString("ID");
        title.setText(id);

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
