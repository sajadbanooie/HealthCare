package ir.madjeed.healthcare.logic.domain.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.dao.impl.persistent.MessageDAOPersistent;
import ir.madjeed.healthcare.dao.impl.persistent.SupervisionRequestDAOPersistent;
import ir.madjeed.healthcare.dao.impl.persistent.UserDAOPersistent;
import ir.madjeed.healthcare.logic.domain.DoctorRelated;
import ir.madjeed.healthcare.logic.domain.PatientRelated;
import ir.madjeed.healthcare.logic.entity.Message;
import ir.madjeed.healthcare.logic.entity.SupervisionRequest;
import ir.madjeed.healthcare.logic.entity.User;
import ir.madjeed.healthcare.logic.entity.impl.persistent.MessagePersistent;
import ir.madjeed.healthcare.logic.entity.impl.persistent.SupervisionRequestPersistent;
import ir.madjeed.healthcare.logic.entity.impl.persistent.UserPersistent;

import java.util.ArrayList;


public class DoctorRelatedPersistent extends BasePersistent implements DoctorRelated {

    private UserDAOPersistent Users;
    private SupervisionRequestDAOPersistent SupervisionRequests;
    private MessageDAOPersistent Messages;


    public DoctorRelatedPersistent(Context context) {
        super(context);
    }

    @Override
    protected void makeNecessaryDAO() {
        Users = new UserDAOPersistent(getDatabaseHelper());
        SupervisionRequests = new SupervisionRequestDAOPersistent(getDatabaseHelper());
        Messages = new MessageDAOPersistent(getDatabaseHelper());
    }

    @Override
    public ArrayList<SupervisionRequest> getDoctorSupervisionRequests(String did) {
        ArrayList<SupervisionRequest> res = SupervisionRequests.getAll();
        for (int i = res.size()-1; i >= 0; i--) {
            if (!res.get(i).getDoctor().getUsername().equals(did))
                res.remove(i);
        }
        return res;
    }

}
