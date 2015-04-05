package ir.madjeed.healthcare.gui.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.InjectView;
import com.beardedhen.androidbootstrap.BootstrapButton;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;


public class DoctorProfileActivity extends BaseActivity {

    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.finish_supervision) BootstrapButton finish_supervision;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String id = getIntent().getExtras().getString("ID");
        title.setText(id);

        // will add some buttons soon
        if (!role.equals("بیمار")){
            System.out.println(role);
            finish_supervision.setVisibility(View.GONE);
        }
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_profile_doctor;
    }


}
