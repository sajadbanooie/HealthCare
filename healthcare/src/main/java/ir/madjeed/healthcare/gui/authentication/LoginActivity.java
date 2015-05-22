package ir.madjeed.healthcare.gui.authentication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import butterknife.InjectView;
import butterknife.OnClick;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.AuthenticationFacade;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.menu.DoctorMenuActivity;
import ir.madjeed.healthcare.gui.menu.DrugStoreMenuActivity;
import ir.madjeed.healthcare.gui.menu.PatientMenuActivity;


public class LoginActivity extends BaseActivity {

    @InjectView(R.id.username)
    BootstrapEditText userBtn;

    @InjectView(R.id.password)
    BootstrapEditText passwordBtn;

    AuthenticationFacade facade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facade = new AuthenticationFacade(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }


    @OnClick(R.id.login)
    public void on_login() {
        boolean error = false;
        String username = userBtn.getText().toString();
        String password = passwordBtn.getText().toString();
        if (username.equals("")) {
            userBtn.setState(BootstrapEditText.TextState.DANGER);
            error = true;
        }if (password.equals("")) {
            passwordBtn.setState(BootstrapEditText.TextState.DANGER);
            error = true;
        }
        if (error)
            showMessage("error", getString(R.string.invalid_form));
        else{
            int result = facade.login(username, password);
            if (result == -1){
                showMessage("error", getString(R.string.user_not_found));
            }else if (result == -2){
                showMessage("error", getString(R.string.password_wrong));
            }else if (result == -3){
                showMessage("error", getString(R.string.not_actived_yet));
            }else{
                String role = facade.getUserRole(username);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("username", username);
                editor.putString("role", role);
                // Save the changes in SharedPreferences
                editor.commit(); // commit changes

                if (role.contains("پزشک")){
                    customStartActivity(DoctorMenuActivity.class);
                }else if (role.contains("بیمار")){
                    customStartActivity(PatientMenuActivity.class);
                }else if (role.contains("داروخانه")){
                    customStartActivity(DrugStoreMenuActivity.class);
                }
            }
        }
    }

    @OnClick(R.id.back)
    public void on_back() {
        super.onBackPressed();
    }
}
