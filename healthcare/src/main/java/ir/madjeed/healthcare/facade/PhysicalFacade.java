package ir.madjeed.healthcare.facade;

import android.content.Context;
import ir.madjeed.healthcare.gui.base.CustomRowObject;
import ir.madjeed.healthcare.logic.domain.PhysicalRelated;
import ir.madjeed.healthcare.logic.domain.impl.persistent.PhysicalRelatedPersistent;
import ir.madjeed.healthcare.logic.entity.PhysicalState;

import java.util.ArrayList;

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

    public ArrayList<CustomRowObject> getPatientAllPhysicalStates(String pid){
        ArrayList<PhysicalState> res = physicalRelated.getPatientAllPhysicalStates(pid);
        ArrayList<CustomRowObject> result = new ArrayList<CustomRowObject>();
        for (int i = 0; i < res.size(); i++) {
            result.add(new CustomRowObject(String.valueOf(res.get(i).getGhand()), String.valueOf(res.get(i).getVazn()),
                    String.valueOf(res.get(i).getFeshar()), String.valueOf(res.get(i).getGhandeKhun())));
        }
        return result;
    }

}

