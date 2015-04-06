package ir.madjeed.healthcare.gui.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import com.beardedhen.androidbootstrap.BootstrapButton;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;


public class RequestProfileActivity extends BaseActivity {

    @InjectView(R.id.title) TextView title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String id = getIntent().getExtras().getString("ID");
        title.setText(id);

    }


    @OnClick(R.id.approve)
    public void approve_btn(){
        finish();
    }

    @OnClick(R.id.disapprove)
    public void disapprove_btn(){
        finish();
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_profile_request;
    }


}
