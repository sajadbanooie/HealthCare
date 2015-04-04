package ir.madjeed.healthcare.gui.menu;

import ir.madjeed.healthcare.gui.authentication.LoginActivity;
import ir.madjeed.healthcare.gui.authentication.RegisterActivity;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import android.os.Bundle;
import ir.madjeed.healthcare.R;
import butterknife.OnClick;


public class StarterActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_starter;
    }

    @OnClick(R.id.register)
    public void register() {
        customStartActivity(RegisterActivity.class);
//        customStartActivity(new ListOptions(ProfileActivity.class, "doctor", "select", "all"));
    }

    @OnClick(R.id.registered_before)
    public void registered_before() {
        customStartActivity(LoginActivity.class);
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        String id = data.getStringExtra("ID");
//        showMessage("error", id);
//    }
}
