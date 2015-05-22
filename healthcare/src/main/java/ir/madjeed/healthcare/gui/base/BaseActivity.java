package ir.madjeed.healthcare.gui.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife;
import ir.madjeed.healthcare.R;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import ir.madjeed.healthcare.logic.entity.User;
import ir.madjeed.healthcare.gui.BasicListActivity;
import org.parceler.Parcels;


public abstract class BaseActivity extends ActionBarActivity {

    private final String LOG_TAG = "maz_maz";
    protected String username, role;
//    protected User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // restoring user info if available
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        username = pref.getString("username", null);
        role = pref.getString("role", null);

        // display setting
        setContentView(getLayoutResourceId());

        // action bar settings
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        ButterKnife.inject(this);
    }

    protected abstract int getLayoutResourceId();

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

    public void customStartActivity(Class c, String id){
        Intent i = new Intent(this, c).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        i.putExtra("ID", id);
        startActivity(i);
    }

    public void customStartActivity(ListOptions listOptions){
        Intent i = new Intent(this, BasicListActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        Bundle mBundle = new Bundle();
        Parcelable wrapped = Parcels.wrap(listOptions);
        mBundle.putParcelable("listOptions", wrapped);
        i.putExtras(mBundle);
        if (listOptions.getPurpose().contains("select")){
            startActivityForResult(i, 1);
        }else{
            startActivity(i);
        }
    }


    // this should be used for lists
    public void customStartActivity(Class listClass, BaseListOptions listOptions){
        Intent i = new Intent(this, listClass).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        Bundle mBundle = new Bundle();
        Parcelable wrapped = Parcels.wrap(listOptions);
        mBundle.putParcelable("listOptions", wrapped);
        i.putExtras(mBundle);
        if (listOptions.getPurpose().contains("select")){
            startActivityForResult(i, 1);
        }else{
            startActivity(i);
        }
    }

}
