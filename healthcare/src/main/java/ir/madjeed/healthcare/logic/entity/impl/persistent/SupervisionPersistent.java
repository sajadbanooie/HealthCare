package ir.madjeed.healthcare.logic.entity.impl.persistent;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import ir.madjeed.healthcare.logic.entity.Supervision;
import ir.madjeed.healthcare.logic.entity.User;

import java.util.Calendar;
import java.util.Date;


public class SupervisionPersistent extends Supervision {

    @DatabaseField(generatedId = true, columnName = "pk_column")
    private int id;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private UserPersistent patient;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private UserPersistent doctor;

    @DatabaseField
    private String status; //active or passive

    @DatabaseField(dataType = DataType.DATE_LONG)
    private Date date;


    public SupervisionPersistent() {
        // needed by ormlite
    }

    public SupervisionPersistent(User patient, User doctor) {
        this.patient = (UserPersistent) patient;
        this.doctor = (UserPersistent) doctor;
        this.status = "active";
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
    public void setPatient(User u) {
        this.patient = (UserPersistent) u;
    }

    @Override
    public User getDoctor() {
        return doctor;
    }

    @Override
    public void setDoctor(User u) {
        this.doctor = (UserPersistent) u;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
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
