package ir.madjeed.healthcare.gui.patient;

import android.os.Bundle;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;


public class AddSicknessActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_sickness_add;
    }

    @OnClick(R.id.approve)
    public void approve() {
        // TODO saving data
        finish();
    }

    @OnClick(R.id.back)
    public void back() {
        super.onBackPressed();
    }

}
