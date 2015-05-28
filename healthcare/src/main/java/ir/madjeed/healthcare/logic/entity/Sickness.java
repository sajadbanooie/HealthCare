package ir.madjeed.healthcare.logic.entity;

import java.util.Date;

public abstract class Sickness {
    public abstract int getId();
    public abstract User getPatient();
    public abstract void setPatient(User u);
    public abstract User getDoctor();
    public abstract void setDoctor(User u);
    public abstract String getSubject();
    public abstract void setSubject(String subject);
    public abstract String getDetail();
    public abstract void setDetail(String detail);
    public abstract Date getDate();
    public abstract void setDate(Date date);

    public String getFullDetail(){
        return "عنوان بیماری: "+getSubject()+"\n\n"+
                "دکتر مربوطه: "+getDoctor().getFullName()+"\n\n"+
                "شرح بیماری: "+"\n\n"+getDetail();
    }
}