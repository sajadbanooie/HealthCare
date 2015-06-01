package ir.madjeed.healthcare.facade;

import android.content.Context;
import ir.madjeed.healthcare.logic.domain.PhysicalRelated;
import ir.madjeed.healthcare.logic.domain.impl.persistent.PhysicalRelatedPersistent;

/**
 * Created by admin on 5/21/2015.
 */
public class PhysicalFacade {
    private PhysicalRelated physicalRelated;

    public PhysicalFacade(Context context) {
        this.physicalRelated = new PhysicalRelatedPersistent(context);
    }

    public void addPhysicalState(String pid, Integer ghand, Integer vazn, Integer feshar, Integer ghandeKhun){
        physicalRelated.addPhysicalState(pid, ghand, vazn, feshar, ghandeKhun);
    }

}

