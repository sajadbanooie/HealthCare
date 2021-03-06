package ir.madjeed.healthcare.logic.entity;

import java.util.Date;


public interface Message {

    public int getId();

    String getTitle();

    void setTitle(String title);

    public String getDetail();

    public void setDetail(String detail);

    public Date getDate();

    public void setDate(Date date);

    public User getOwner();

    public void setOwner(User owner);
}
