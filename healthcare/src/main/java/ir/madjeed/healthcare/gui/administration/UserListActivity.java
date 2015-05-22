package ir.madjeed.healthcare.gui.administration;

import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.gui.base.BaseListActivity;
import ir.madjeed.healthcare.gui.base.BaseRowObject;

import java.util.ArrayList;

/**
 * Created by admin on 5/22/2015.
 */
public class UserListActivity extends BaseListActivity {

    @Override
    protected String getListTitle() {
        return getString(R.string.users);
    }

    @Override
    protected ArrayList<BaseRowObject> getListItems() {
        ArrayList<BaseRowObject> data= new ArrayList<BaseRowObject>();
        for (int i = 0; i < 5; i++) {
            data.add(new BaseRowObject(String.valueOf(i), "user "+String.valueOf(i)));
        }
        return data;
    }
}
