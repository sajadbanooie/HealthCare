package ir.madjeed.healthcare.logic.entity;

import java.util.Date;

public abstract class Supervision {
    public abstract int getId();
    public abstract User getPatient();
    public abstract void setPatient(User u);
    public abstract User getDoctor();
    public abstract void setDoctor(User u);
    public abstract String getStatus(); // active, passive
    public abstract void setStatus(String status);
    public abstract Date getDate();
    public abstract void setDate(Date date);
    public String getFullDetail(){
        return "نظارت شماره "+String.valueOf(getId())+"\n\n"+
                "بیمار: "+getPatient().getFullName()+"\n\n"+
                "پزشک: "+getDoctor().getFullName()+"\n\n";
    }
}