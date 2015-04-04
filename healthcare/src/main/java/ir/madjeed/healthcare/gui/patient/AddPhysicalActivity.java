package ir.madjeed.healthcare.gui.patient;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import butterknife.InjectView;
import butterknife.OnClick;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseActivity;
import ir.madjeed.healthcare.gui.base.ListOptions;

import java.util.ArrayList;


public class AddPhysicalActivity extends BaseActivity {

    @InjectView(R.id.sensor) Spinner sensor;

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

    @OnClick(R.id.stop)
    public void stop() {
        // TODO ....
        customStartActivity(new ListOptions("medical record", "view", "mine"));
    }

}
