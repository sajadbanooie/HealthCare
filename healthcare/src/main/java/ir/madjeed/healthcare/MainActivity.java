package ir.madjeed.healthcare;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;

import java.util.List;


public class MainActivity extends BaseActivity {

    private final String LOG_TAG = getClass().getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        TextView tv = new TextView(this);
        doSampleDatabaseStuff("onCreate", tv);
        setContentView(tv);

    }

    /**
     * Do our sample database stuff as an example.
     */
    private void doSampleDatabaseStuff(String action, TextView tv) {
        try {
            // get our dao
            Dao<SimpleData, Integer> simpleDao = getHelper().getSimpleDataDao();
            // query for all of the data objects in the database
            List<SimpleData> list = simpleDao.queryForAll();
            // our string builder for building the content-view
            StringBuilder sb = new StringBuilder();
            sb.append("got ").append(list.size()).append(" entries in ").append(action).append("\n");

            // if we already have items in the database
            int simpleC = 0;
            for (SimpleData simple : list) {
                sb.append("------------------------------------------\n");
                sb.append("[").append(simpleC).append("] = ").append(simple).append("\n");
                simpleC++;
            }
            sb.append("------------------------------------------\n");
            for (SimpleData simple : list) {
                simpleDao.delete(simple);
                sb.append("deleted id ").append(simple.id).append("\n");
                Log.i(LOG_TAG, "deleting simple(" + simple.id + ")");
                simpleC++;
            }

            int createNum;
            do {
                createNum = 10;
            } while (createNum == list.size());
            for (int i = 0; i < createNum; i++) {
                // create a new simple object
                long millis = System.currentTimeMillis();
                SimpleData simple = new SimpleData(millis);
                // store it in the database
                simpleDao.create(simple);
                Log.i(LOG_TAG, "created simple(" + millis + ")");
                // output it
                sb.append("------------------------------------------\n");
                sb.append("created new entry #").append(i + 1).append(":\n");
                sb.append(simple).append("\n");
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    // ignore
                }
            }

            tv.setText(sb.toString());
            Log.i(LOG_TAG, "Done with page at " + System.currentTimeMillis());
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Database exception", e);
            tv.setText("Database exeption: " + e);
            return;
        }
    }


}
