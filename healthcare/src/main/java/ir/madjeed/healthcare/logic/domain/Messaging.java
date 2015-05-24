package ir.madjeed.healthcare.logic.domain;


import ir.madjeed.healthcare.logic.entity.Message;
import java.util.ArrayList;

public interface Messaging {
    public ArrayList<Message> getUserMessages(String username);
    public Message getMessage(int mid);
}
