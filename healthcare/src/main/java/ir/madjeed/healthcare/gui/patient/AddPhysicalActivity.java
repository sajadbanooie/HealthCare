package ir.madjeed.healthcare.gui.patient;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import butterknife.InjectView;
import butterknife.OnClick;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;

import java.util.ArrayList;


public class AddPhysicalActivity extends BaseActivity {

    @InjectView(R.id.audience) Spinner audience_spinner;
    @InjectView(R.id.subject) BootstrapEditText subject;
    @InjectView(R.id.your_message) BootstrapEditText your_message;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO loading from db
        ArrayList<String> spinnerArray = new ArrayList<String>();
        if (role.equals("بیمار")){
            spinnerArray.add("پزشک 1");
            spinnerArray.add("پزشک 325");
        }else{
            spinnerArray.add("بیمار 232");
            spinnerArray.add("بیمار 82");
        }
        ArrayAdapter<String> spinnerArrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        audience_spinner.setAdapter(spinnerArrayAdapter);

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_physical;
    }

    @OnClick(R.id.approve)
    public void approve() {
        // TODO saving data

        finish();
    }

    @OnClick(R.id.back)
    public void back() {
        super.onBackPressed();
    }

}
