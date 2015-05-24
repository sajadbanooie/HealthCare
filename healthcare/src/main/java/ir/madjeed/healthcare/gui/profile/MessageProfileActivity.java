package ir.madjeed.healthcare.gui.profile;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.AdministrationFacade;
import ir.madjeed.healthcare.facade.MessageFacade;
import ir.madjeed.healthcare.gui.base.BaseActivity;

import java.util.ArrayList;


public class MessageProfileActivity extends BaseActivity {

    @InjectView(R.id.head) TextView head;
    @InjectView(R.id.body) TextView body;

    private MessageFacade facade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new MessageFacade(this);
        super.onCreate(savedInstanceState);
        String messageID = getIntent().getExtras().getString("ID");
        ArrayList<String> info = facade.getMessageInfo(Integer.valueOf(messageID));
        head.setText(info.get(0));
        body.setText(info.get(1));
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_profile_message;
    }

}
