package ir.madjeed.healthcare.logic.entity.impl.persistent;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import ir.madjeed.healthcare.logic.entity.Sickness;
import ir.madjeed.healthcare.logic.entity.User;
import java.util.Calendar;
import java.util.Date;


public class SicknessPersistent extends Sickness {

    @DatabaseField(generatedId = true, columnName = "pk_column")
    private int id;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private UserPersistent patient;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private UserPersistent doctor;

    @DatabaseField
    private String subject;

    @DatabaseField
    private String detail;

    @DatabaseField(dataType = DataType.DATE_LONG)
    private Date date;


    public SicknessPersistent() {
        // needed by ormlite
    }

    public SicknessPersistent(User patient, User doctor, String subject, String detail) {
        this.patient = (UserPersistent) patient;
        this.doctor = (UserPersistent) doctor;
        this.subject = subject;
        this.detail = detail;
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
    public String getSubject() {
        return subject;
    }

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String getDetail() {
        return detail;
    }

    @Override
    public void setDetail(String detail) {
        this.detail = detail;
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
