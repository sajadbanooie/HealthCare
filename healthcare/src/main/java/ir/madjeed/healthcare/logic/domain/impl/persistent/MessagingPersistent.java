package ir.madjeed.healthcare.logic.domain.impl.persistent;


import android.content.Context;
import ir.madjeed.healthcare.dao.impl.persistent.MessageDAOPersistent;
import ir.madjeed.healthcare.logic.domain.Messaging;
import ir.madjeed.healthcare.logic.entity.Message;
import java.util.ArrayList;
import java.util.Collections;


public class MessagingPersistent extends BasePersistent implements Messaging {

    private MessageDAOPersistent Messages;


    public MessagingPersistent(Context context) {
        super(context);
    }

    @Override
    protected void makeNecessaryDAO() {
        Messages = new MessageDAOPersistent(getDatabaseHelper());
    }


    @Override
    public ArrayList<Message> getUserMessages(String username){
        ArrayList<Message> res = Messages.getAll();
        for (int i = res.size()-1; i >= 0; i--) {
            if (!res.get(i).getOwner().getUsername().equals(username))
                res.remove(i);
        }
        Collections.reverse(res);
        return res;
    }

    @Override
    public Message getMessage(int mid) {
        return Messages.getByID(mid);
    }
}
