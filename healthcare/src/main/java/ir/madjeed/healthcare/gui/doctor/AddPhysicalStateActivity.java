package ir.madjeed.healthcare.gui.doctor;

import android.os.Bundle;
import butterknife.InjectViews;
import butterknife.OnClick;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;

import java.util.List;


public class AddPhysicalStateActivity extends BaseActivity {

    @InjectViews({ R.id.username, R.id.password, R.id.name, R.id.family, R.id.nationalID})
    List<BootstrapEditText> editTexts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
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

}
