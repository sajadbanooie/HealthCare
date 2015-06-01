package ir.madjeed.healthcare.logic.entity.impl.persistent;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import ir.madjeed.healthcare.logic.entity.ConsultantCase;
import ir.madjeed.healthcare.logic.entity.User;

import java.util.Calendar;
import java.util.Date;


public class ConsultantCasePersistent extends ConsultantCase {

    @DatabaseField(generatedId = true, columnName = "pk_column")
    private int id;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private UserPersistent patient;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private UserPersistent doctor;

    @DatabaseField
    private String status; //active or passive

    @DatabaseField
    private String subject;

    @DatabaseField(dataType = DataType.DATE_LONG)
    private Date date;


    public ConsultantCasePersistent() {
        // needed by ormlite
    }

    public ConsultantCasePersistent(User patient, User doctor, String subject) {
        this.patient = (UserPersistent) patient;
        this.doctor = (UserPersistent) doctor;
        this.status = "active";
        this.subject = subject;
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
    public String getSubject() {
        return subject;
    }

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
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
