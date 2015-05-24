package ir.madjeed.healthcare.logic.entity;

//import ir.madjeed.healthcare.logic.entity.impl.persistent.UserPersistent;

import java.util.Date;

public abstract class SupervisionRequest {
    public abstract int getId();
    public abstract User getPatient();
    public abstract void setPatient(User u);
    public abstract User getDoctor();
    public abstract void setDoctor(User u);
    public abstract String getStatus(); // accepted, rejected or pending
    public abstract void setStatus(String status);
    public abstract String getType(); // refer or request
    public abstract void setType(String type);
    public abstract String getRequestDetail();
    public abstract void setRequestDetail(String detail);
    public abstract String getRequestAnswer();
    public abstract void setRequestAnswer(String answer);
    public abstract Date getDate();
    public abstract void setDate(Date date);
    public String getHead(){
        return "درخواست ارجاع به شماره "+String.valueOf(getId());
    }
    public String getBody(){
        return "پزشک مربوطه: "+getDoctor().getName()+" "+getDoctor().getFamily()+"\n"+
                "بیمار: "+getPatient().getName()+" "+getPatient().getFamily()+"\n"+
                "متن درخواست: "+"\n"+getRequestDetail();
    }

}
