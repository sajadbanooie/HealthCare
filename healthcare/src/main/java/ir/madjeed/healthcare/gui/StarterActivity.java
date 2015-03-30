package ir.madjeed.healthcare.gui;

import ir.madjeed.healthcare.gui.base.BaseActivity;
import android.content.Intent;
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
        startActivity(new Intent(this, RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    @OnClick(R.id.registered_before)
    public void registered_before() {
        startActivity(new Intent(this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

}
