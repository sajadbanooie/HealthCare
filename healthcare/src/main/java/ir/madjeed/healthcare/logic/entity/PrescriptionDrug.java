package ir.madjeed.healthcare.logic.entity;


public abstract class PrescriptionDrug {
    public abstract int getId();
    public abstract Prescription getPrescription();
    public abstract void setPrescription(Prescription p);
    public abstract Drug getDrug();
    public abstract void setDrug(Drug d);
    public abstract Integer getNum();
    public abstract void setNum(Integer status);
}