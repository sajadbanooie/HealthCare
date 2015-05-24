package ir.madjeed.healthcare.facade;

import android.content.Context;
import android.util.Pair;
import ir.madjeed.healthcare.logic.domain.Messaging;
import ir.madjeed.healthcare.logic.domain.PatientRelated;
import ir.madjeed.healthcare.logic.domain.impl.persistent.MessagingPersistent;
import ir.madjeed.healthcare.logic.domain.impl.persistent.PatientRelatedPersistent;
import ir.madjeed.healthcare.logic.entity.Message;
import ir.madjeed.healthcare.logic.entity.User;

import java.util.ArrayList;

/**
 * Created by admin on 5/21/2015.
 */
public class MessageFacade {
    private Messaging messaging;

    public MessageFacade(Context context) {
        this.messaging = new MessagingPersistent(context);
    }

    public ArrayList<Pair<String, String>> getUserMessages(String username){  // first = id, second = name
        ArrayList<Message> allMessages = messaging.getUserMessages(username);
        ArrayList<Pair<String, String>> result = new ArrayList<Pair<String, String>>();
        for (int i = 0; i < allMessages.size(); i++) {
            result.add(new Pair<String, String>(String.valueOf(allMessages.get(i).getId()), allMessages.get(i).getTitle()));
        }
        return result;
    }

}

