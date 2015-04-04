package ir.madjeed.healthcare.gui.patient;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import butterknife.InjectView;
import butterknife.OnClick;
import com.beardedhen.androidbootstrap.BootstrapButton;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;

import java.util.ArrayList;


public class AddPhysicalActivity extends BaseActivity {

    @InjectView(R.id.sensor) Spinner sensor;
    @InjectView(R.id.start) BootstrapButton start;
    @InjectView(R.id.start) BootstrapButton finish;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("سنسور ضربان قلب");
        spinnerArray.add("سنسور فشار سنج");
        ArrayAdapter<String> spinnerArrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        sensor.setAdapter(spinnerArrayAdapter);

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_physical;
    }

    @OnClick(R.id.start)
    public void start() {
        // nothing
    }

    @OnClick(R.id.finish)
    public void finish() {
        // TODO ....
        // go to medical record
    }

}
