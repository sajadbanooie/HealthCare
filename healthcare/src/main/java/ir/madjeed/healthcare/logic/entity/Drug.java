package ir.madjeed.healthcare.logic.entity;

import java.util.Date;

public abstract class Drug {
    public abstract int getId();
    public abstract String getName();
    public abstract void setName(String name);
    public abstract Integer getPrice();
    public abstract void setPrice(Integer name);
    public abstract Date getDate();
    public abstract void setDate(Date date);
}