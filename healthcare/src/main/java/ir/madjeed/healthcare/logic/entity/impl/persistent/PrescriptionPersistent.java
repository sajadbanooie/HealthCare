package ir.madjeed.healthcare.logic.entity.impl.persistent;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import ir.madjeed.healthcare.logic.entity.Prescription;
import ir.madjeed.healthcare.logic.entity.Sickness;
import ir.madjeed.healthcare.logic.entity.User;

import java.util.Calendar;
import java.util.Date;


public class PrescriptionPersistent extends Prescription {

    @DatabaseField(generatedId = true, columnName = "pk_column")
    private int id;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true,
            foreignAutoCreate = true)
    private SicknessPersistent sickness;

    @DatabaseField(dataType = DataType.DATE_LONG)
    private Date date;


    public PrescriptionPersistent() {
        // needed by ormlite
    }

    public PrescriptionPersistent(Sickness sickness) {
        this.sickness = (SicknessPersistent) sickness;
        Calendar cal = Calendar.getInstance();
        this.date = cal.getTime();
    }

    @Override
    public int getId(){
        return this.id;
    }

    @Override
    public Sickness getSickness() {
        return sickness;
    }

    @Override
    public void setSickness(Sickness s) {
        this.sickness = (SicknessPersistent) s;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }
}
