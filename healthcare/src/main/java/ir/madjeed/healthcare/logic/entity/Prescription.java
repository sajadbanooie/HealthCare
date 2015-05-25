package ir.madjeed.healthcare.logic.entity;

import java.util.Date;

public abstract class Prescription {
    public abstract int getId();
    public abstract Sickness getSickness();
    public abstract void setSickness(Sickness s);
    public abstract Date getDate();
    public abstract void setDate(Date date);
}