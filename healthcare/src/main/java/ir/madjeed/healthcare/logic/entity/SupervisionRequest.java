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
        return "درخواست ارجاع شماره "+String.valueOf(getId());
    }
    public String getBody(){
        return "پزشک مربوطه: "+getDoctor().getName()+" "+getDoctor().getFamily()+"\n"+
                "بیمار: "+getPatient().getName()+" "+getPatient().getFamily()+"\n"+
                "متن درخواست: "+"\n"+getRequestDetail();
    }

    public String getFullDetailForDoctor(){
        String typo, status;
        if (getType().equals("refer"))
            typo = "درخواست ارجاع";
        else
            typo = "درخواست نظارت";
        if (getStatus().equals("pending"))
            status = "در حال انتظار";
        else if (getStatus().equals("rejected"))
            status = "رد شده";
        else
            status = "تایید شده";
        return "نوع درخواست: "+typo+"\n\n"+
                "وضعیت پاسخ: "+status+"\n\n"+
//                "پزشک مربوطه: "+getDoctor().getName()+" "+getDoctor().getFamily()+"\n"+
                "بیمار: "+getPatient().getName()+" "+getPatient().getFamily()+"\n\n"+
                "متن درخواست: "+"\n"+getRequestDetail()+"\n\n"+
                "پاسخ: "+"\n"+getRequestAnswer()+"\n\n";
    }
}