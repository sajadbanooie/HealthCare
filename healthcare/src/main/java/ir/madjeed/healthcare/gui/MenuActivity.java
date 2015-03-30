package ir.madjeed.healthcare.gui;

import android.content.Intent;
import android.os.Bundle;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import butterknife.OnClick;


public class MenuActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_menu;
    }



}
