package ir.madjeed.healthcare.gui;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import butterknife.InjectView;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import android.os.Bundle;
import ir.madjeed.healthcare.R;
import butterknife.OnClick;

import java.util.ArrayList;


public class AddConsultantActivity extends BaseActivity {

    @InjectView(R.id.audience) Spinner audience_spinner;
    @InjectView(R.id.subject) BootstrapEditText subject;
    @InjectView(R.id.your_message) BootstrapEditText your_message;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO loading from db
        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("one");
        spinnerArray.add("two");
        spinnerArray.add("three");
        spinnerArray.add("four");
        spinnerArray.add("five");


        ArrayAdapter<String> spinnerArrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        audience_spinner.setAdapter(spinnerArrayAdapter);

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_consultant_add;
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
