package ir.madjeed.healthcare.gui;

import android.content.Intent;
import android.os.Bundle;
import butterknife.InjectView;
import butterknife.OnClick;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.data.entity.User;
import ir.madjeed.healthcare.gui.base.BaseActivity;


public class LoginActivity extends BaseActivity {

    @InjectView(R.id.username)
    BootstrapEditText userBtn;

    @InjectView(R.id.password)
    BootstrapEditText passwordBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.login)
    public void on_login() {
        boolean error = false;
        if (userBtn.getText().toString().equals("")) {
            userBtn.setState(BootstrapEditText.TextState.DANGER);
            error = true;
        }if (passwordBtn.getText().toString().equals("")) {
            passwordBtn.setState(BootstrapEditText.TextState.DANGER);
            error = true;
        }
        if (error)
            showMessage("error", getString(R.string.invalid_form));
        else{
            User u = repo.Users.getByID(userBtn.getText().toString());
            if (u == null){
                showMessage("error", getString(R.string.user_not_found));
            }else if (!u.getPassword().equals(passwordBtn.getText().toString())){
                showMessage("error", getString(R.string.password_wrong));
            }else{
                startActivity(new Intent(this, MenuActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        }
    }

    @OnClick(R.id.back)
    public void on_back() {
        super.onBackPressed();
    }
}
