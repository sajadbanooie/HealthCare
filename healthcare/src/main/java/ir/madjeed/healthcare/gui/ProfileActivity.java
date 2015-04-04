package ir.madjeed.healthcare.gui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.InjectView;
import com.beardedhen.androidbootstrap.BootstrapButton;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;


public class ProfileActivity extends BaseActivity {

    @InjectView(R.id.title) TextView title;
    private String profile_role, profile_owner;
    private String name;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        profile_owner = getIntent().getExtras().getString("ID");

        profile_role = null; // needed to select correct layout
        // TODO
        if (profile_owner.contains("بیمار")){
            profile_role = "بیمار";
        }else if (profile_owner .contains("پزشک عمومی")){
            profile_role = "پزشک عمومی";
        }else if (profile_owner .contains("پزشک متخصص")){
            profile_role = "پزشک متخصص";
        }
        super.onCreate(savedInstanceState);

        title.setText(profile_owner);

        // hide some accesses if admin viewing patient profile
        if(profile_role.equals("بیمار")){
            if (role.equals("مدیر سامانه")){
                findViewById(R.id.finish_supervision).setVisibility(View.GONE);
                findViewById(R.id.reference).setVisibility(View.GONE);
            }
        }
    }


    @Override
    protected int getLayoutResourceId() {
        if (profile_role.contains(getResources().getString(R.string.patient))){
            return R.layout.activity_profile_patient;
        }
        return -1;
    }

    public void onMenuBtnPress(View w){
        BootstrapButton b = (BootstrapButton) w;

        try {
            customStartActivity(
                    Class.forName(getApplicationContext().getPackageName()+".gui."+b.getTag().toString()),
                    profile_owner);
        } catch (ClassNotFoundException e) {
            showMessage("error", "not implemented yet");
        }
    }

}
