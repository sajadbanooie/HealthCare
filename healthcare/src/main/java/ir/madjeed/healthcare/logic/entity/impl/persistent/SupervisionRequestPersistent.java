package ir.madjeed.healthcare.logic.entity.impl.persistent;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import ir.madjeed.healthcare.logic.entity.SupervisionRequest;
import ir.madjeed.healthcare.logic.entity.User;

import java.util.Calendar;
import java.util.Date;


public class SupervisionRequestPersistent extends SupervisionRequest {

    @DatabaseField(generatedId = true, columnName = "pk_column")
    private int id;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private UserPersistent patient;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private UserPersistent doctor;

    @DatabaseField
    private String status;

    @DatabaseField
    private String type;

    @DatabaseField
    private String requestDetail;

    @DatabaseField
    private String requestAnswer;

    @DatabaseField(dataType = DataType.DATE_LONG)
    private Date date;


    public SupervisionRequestPersistent() {
        // needed by ormlite
    }

    public SupervisionRequestPersistent(User patient, User doctor, String type, String requestDetail) {
        this.patient = (UserPersistent) patient;
        this.doctor = (UserPersistent) doctor;
        this.status = "pending";
        this.type = type;
        this.requestDetail = requestDetail;
        this.requestAnswer = "پاسخ داده نشده است.";
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
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getRequestDetail() {
        return requestDetail;
    }

    @Override
    public void setRequestDetail(String detail) {
        this.requestDetail = detail;
    }

    @Override
    public String getRequestAnswer() {
        return requestAnswer;
    }

    @Override
    public void setRequestAnswer(String answer) {
        this.requestAnswer = answer;
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
