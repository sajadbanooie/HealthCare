package ir.madjeed.healthcare.logic.entity;

import java.util.Date;

public abstract class PhysicalState {
    public abstract int getId();
    public abstract User getPatient();
    public abstract void setPatient(User patient);
    public abstract Integer getGhand();
    public abstract void setGhand(Integer ghand);
    public abstract Integer getVazn();
    public abstract void setVazn(Integer vazn);
    public abstract Integer getFeshar();
    public abstract void setFeshar(Integer feshar);
    public abstract Integer getGhandeKhun();
    public abstract void setGhandeKhun(Integer ghandeKhun);
    public abstract Date getDate();
    public abstract void setDate(Date date);
}