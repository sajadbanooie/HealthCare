package ir.madjeed.healthcare.gui.profile;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;


public class DoctorProfileActivity extends BaseActivity {

    @InjectView(R.id.title) TextView title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String id = getIntent().getExtras().getString("ID");
        title.setText(id);

        // will add some buttons soon
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_profile_doctor;
    }


}
