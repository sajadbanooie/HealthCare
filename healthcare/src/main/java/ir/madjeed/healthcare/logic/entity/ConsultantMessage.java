package ir.madjeed.healthcare.logic.entity;

import java.util.Date;

public abstract class ConsultantMessage {
    public abstract int getId();
    public abstract ConsultantCase getConsultantCase();
    public abstract void setConsultantCase(ConsultantCase c);
    public abstract String getDetail();
    public abstract void setDetail(String detail);
    public abstract String getSender();
    public abstract void setSender(String sender);
    public abstract Date getDate();
    public abstract void setDate(Date date);
}