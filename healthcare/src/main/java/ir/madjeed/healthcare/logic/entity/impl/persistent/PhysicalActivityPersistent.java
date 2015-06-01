package ir.madjeed.healthcare.logic.entity.impl.persistent;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import ir.madjeed.healthcare.logic.entity.PhysicalActivity;
import ir.madjeed.healthcare.logic.entity.User;

import java.util.Calendar;
import java.util.Date;


public class PhysicalActivityPersistent extends PhysicalActivity {

    @DatabaseField(generatedId = true, columnName = "pk_column")
    private int id;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private UserPersistent patient;

    @DatabaseField
    private Integer walk;

    @DatabaseField(dataType = DataType.DATE_LONG)
    private Date date;


    public PhysicalActivityPersistent() {
        // needed by ormlite
    }

    public PhysicalActivityPersistent(User patient, Integer walk) {
        this.patient = (UserPersistent) patient;
        this.walk = walk;
        Calendar cal = Calendar.getInstance();
        this.date = cal.getTime();
    }

    @Override
    public int getId(){
        return this.id;
    }

    @Override
    public User getPatient() {
        return patient;
    }

    @Override
    public void setPatient(User patient) {
        this.patient = (UserPersistent) patient;
    }

    @Override
    public Integer getWalk() {
        return walk;
    }

    @Override
    public void setWalk(Integer walk) {
        this.walk = walk;
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
