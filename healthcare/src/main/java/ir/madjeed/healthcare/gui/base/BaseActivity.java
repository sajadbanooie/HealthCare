package ir.madjeed.healthcare.gui.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife;
import ir.madjeed.healthcare.R;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import ir.madjeed.healthcare.data.Repo;
import ir.madjeed.healthcare.data.repo.impl.persistent.RepoPersistent;


public abstract class BaseActivity extends ActionBarActivity {

    public Repo repo;
    private final String LOG_TAG = "khar";
    public String username, role;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // restoring user info
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        username = pref.getString("username", null);
        role = pref.getString("role", null);

        // display setting
        setContentView(getLayoutResourceId());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        ButterKnife.inject(this);

        //data base setting
        repo = new RepoPersistent(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }


    protected abstract int getLayoutResourceId();


    public void showMessage(String type, String message){
        if (type.equals("error")){
            View customView = getLayoutInflater().inflate(R.layout.custom_error_message, null);
            TextView alert_message = (TextView) customView.findViewById(R.id.message_detail);
            alert_message.setText(message);
            Crouton.cancelAllCroutons();
            Crouton.show(this, customView);
        }
    }

    public void customStartActivity(Class c){
        Intent i = new Intent(this, c).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        startActivity(i);
    }

}
