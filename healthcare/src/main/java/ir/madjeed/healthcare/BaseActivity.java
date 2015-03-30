package ir.madjeed.healthcare;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v7.app.ActionBarActivity;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;



public abstract class BaseActivity<H extends OrmLiteSqliteOpenHelper> extends ActionBarActivity{

    private final String LOG_TAG = getClass().getSimpleName();
    /**
     * You'll need this in your class to cache the helper in the class.
     */
    private DatabaseHelper databaseHelper = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "creating " + getClass() + " at " + System.currentTimeMillis());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

		/*
		 * You'll need this in your class to release the helper when done.
		 */
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    /**
     * You'll need this in your class to get the helper from the manager once per class.
     */
    protected DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
