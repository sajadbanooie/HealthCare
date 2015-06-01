package ir.madjeed.healthcare.gui.doctor;

import android.os.Bundle;
import butterknife.InjectView;
import butterknife.OnClick;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.PhysicalFacade;
import ir.madjeed.healthcare.gui.base.BaseActivity;


public class AddPhysicalStateActivity extends BaseActivity {

    @InjectView(R.id.ghand) BootstrapEditText ghand;
    @InjectView(R.id.vazn) BootstrapEditText vazn;
    @InjectView(R.id.feshar) BootstrapEditText feshar;
    @InjectView(R.id.ghandeKhun) BootstrapEditText ghandeKhun;

    private PhysicalFacade facade;

    private String patient_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new PhysicalFacade(this);
        super.onCreate(savedInstanceState);

        patient_id = getIntent().getExtras().getString("ID");
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_physical_state;
    }

    @OnClick(R.id.back)
    public void on_back() {
        super.onBackPressed();
    }

    @OnClick(R.id.approve)
    public void on_approve() {
        if (ghand.getText().toString().equals("") || vazn.getText().toString().equals("") ||
                feshar.getText().toString().equals("") || ghandeKhun.getText().toString().equals("")){
            showMessage("error", "باید همه موارد را پر کنید.");
        }else{
            facade.addPhysicalState(patient_id,
                    Integer.valueOf(ghand.getText().toString()), Integer.valueOf(vazn.getText().toString()),
                    Integer.valueOf(feshar.getText().toString()), Integer.valueOf(ghandeKhun.getText().toString()));
            finish();
        }
    }

}
