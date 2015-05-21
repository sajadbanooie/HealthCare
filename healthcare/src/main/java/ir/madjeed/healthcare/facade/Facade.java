package ir.madjeed.healthcare.facade;

import android.content.Context;
import ir.madjeed.healthcare.data.AuthenticationRepo;
import ir.madjeed.healthcare.data.repo.impl.persistent.AuthenticationRepoPersistent;

/**
 * Created by admin on 5/21/2015.
 */
public class Facade {
    private Context mContext;

    public Facade(Context context) {
        this.mContext = context;
    }

}

