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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new PhysicalFacade(this);
        super.onCreate(savedInstanceState);
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
        finish();
    }

}
