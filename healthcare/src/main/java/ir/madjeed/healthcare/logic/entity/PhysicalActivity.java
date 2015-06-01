package ir.madjeed.healthcare.logic.entity;

import java.util.Date;

public abstract class PhysicalActivity {
    public abstract int getId();
    public abstract User getPatient();
    public abstract void setPatient(User patient);
    public abstract Integer getWalk();
    public abstract void setWalk(Integer walk);
    public abstract Date getDate();
    public abstract void setDate(Date date);
}