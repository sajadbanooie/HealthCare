package ir.madjeed.healthcare.logic.entity.impl.persistent;

import com.j256.ormlite.field.DatabaseField;
import ir.madjeed.healthcare.logic.entity.*;


public class PrescriptionDrugPersistent extends PrescriptionDrug {

    @DatabaseField(generatedId = true, columnName = "pk_column")
    private int id;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private PrescriptionPersistent prescription;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private DrugPersistent drug;

    @DatabaseField
    private Integer num;

    public PrescriptionDrugPersistent() {
        // needed by ormlite
    }

    public PrescriptionDrugPersistent(Prescription p, Drug d, Integer num) {
        prescription = (PrescriptionPersistent) p;
        drug = (DrugPersistent) d;
    }

    @Override
    public int getId(){
        return this.id;
    }

    @Override
    public Prescription getPrescription() {
        return prescription;
    }

    @Override
    public void setPrescription(Prescription p) {
        this.prescription = (PrescriptionPersistent) p;
    }

    @Override
    public Drug getDrug() {
        return drug;
    }

    @Override
    public void setDrug(Drug d) {
        this.drug = (DrugPersistent) d;
    }

    @Override
    public Integer getNum() {
        return num;
    }

    @Override
    public void setNum(Integer num) {
        this.num = num;
    }


}
