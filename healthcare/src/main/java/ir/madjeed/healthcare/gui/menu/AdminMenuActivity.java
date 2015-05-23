package ir.madjeed.healthcare.gui.menu;

import android.content.Intent;
import android.os.Bundle;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.list.UserListActivity;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.BaseListOptions;
import ir.madjeed.healthcare.gui.profile.UserProfileActivity;


public class AdminMenuActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_menu_admin;
    }


//    @OnClick(R.id.edit_info_btn)
//    public void edit_info_btn() {
//        customStartActivity(EditInfoActivity.class);
//    }
//
//    @OnClick(R.id.messages_btn)
//    public void messages_btn() {
//        customStartActivity(new ListOptions("message", "view", "mine"));
//    }

    @OnClick(R.id.exit_btn)
    public void exit_btn() {
        startActivity(new Intent(this, StarterActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @OnClick(R.id.users_btn)
    public void users_btn() {
        customStartActivity(UserListActivity.class, new BaseListOptions(UserProfileActivity.class, null, "view", "mine"));
    }

}
