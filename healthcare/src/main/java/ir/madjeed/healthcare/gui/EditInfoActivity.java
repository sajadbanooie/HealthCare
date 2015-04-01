package ir.madjeed.healthcare.gui;

import android.os.Bundle;
import butterknife.InjectViews;
import butterknife.OnClick;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;

import java.util.List;


public class EditInfoActivity extends BaseActivity {

    @InjectViews({ R.id.username, R.id.password, R.id.name, R.id.family, R.id.nationalID})
    List<BootstrapEditText> editTexts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editTexts.get(0).setText(user.getUsername());
        editTexts.get(1).setText(user.getPassword());
        editTexts.get(2).setText(user.getName());
        editTexts.get(3).setText(user.getFamily());
        editTexts.get(4).setText(user.getNationalID());
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_edit_info;
    }

    @OnClick(R.id.back)
    public void on_back() {
        super.onBackPressed();
    }

}
