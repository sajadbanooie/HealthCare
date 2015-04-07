package ir.madjeed.healthcare.gui.profile;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.ListOptions;


public class PrescriptionDeliveryProfileActivity extends BaseActivity {

    @InjectView(R.id.title) TextView title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String id = getIntent().getExtras().getString("ID");
        title.setText(id);

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_profile_prescription_delivery;
    }


    @OnClick(R.id.deliver_add)
    public void deliver_add(){
        customStartActivity(new ListOptions("drug delivery", "view", "mine"));
    }
}
