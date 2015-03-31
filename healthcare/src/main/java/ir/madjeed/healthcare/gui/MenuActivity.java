package ir.madjeed.healthcare.gui;

import android.os.Bundle;
import android.view.View;
import com.beardedhen.androidbootstrap.BootstrapButton;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;


public class MenuActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int getLayoutResourceId() {
        if (role.equals("پزشک متخصص") || role.equals("پزشک عمومی")){
            return R.layout.activity_menu_expert;
        }else if (role.equals("بیمار")){
            return R.layout.activity_menu_patient;
        }else if (role.equals("داروخانه")){
            return R.layout.activity_menu_drugstore;
        }else if (role.equals("مدیر")){
            return R.layout.activity_menu_admin;
        }
        return -1;
    }

    public void onMenuBtnPress(View w){
        BootstrapButton b = (BootstrapButton) w;

        try {
            customStartActivity(Class.forName(getApplicationContext().getPackageName()+".gui."+b.getTag().toString()));
        } catch (ClassNotFoundException e) {
            showMessage("error", "not implemented yet");
        }
    }

}
