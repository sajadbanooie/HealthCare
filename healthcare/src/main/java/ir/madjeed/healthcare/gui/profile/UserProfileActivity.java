package ir.madjeed.healthcare.gui.profile;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.AdministrationFacade;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import java.util.ArrayList;


public class UserProfileActivity extends BaseActivity {

    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.registrationStatus) TextView registrationStatus;
    @InjectView(R.id.role) TextView role;
    @InjectView(R.id.nationalID) TextView nationalID;
    @InjectView(R.id.registration_status_array) Spinner registration_status_array;

    private AdministrationFacade facade;
    private String username;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new AdministrationFacade(this);
        super.onCreate(savedInstanceState);
        username = getIntent().getExtras().getString("ID");
        ArrayList<String> info = facade.getUserInfo(username);
        title.setText(info.get(0));
        nationalID.setText("شماره ملی: "+info.get(1));
        role.setText("نقش: "+info.get(2));
        registrationStatus.setText("وضعیت ثبت نام: "+info.get(3));

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_profile_user;
    }


    @OnClick(R.id.save_registration_status)
    public void save_registration_status(){
        String selected = registration_status_array.getSelectedItem().toString();
        String result;
        if (selected.contains("رد شده"))
            result = "rejected";
        else if (selected.equals("در انتظار"))
            result = "pending";
        else
            result = "accepted";
        facade.setUserRegistrationStatus(username, result);
        registrationStatus.setText("وضعیت ثبت نام: "+selected);
    }

}
