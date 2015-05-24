package ir.madjeed.healthcare.logic.entity.impl.persistent;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import ir.madjeed.healthcare.logic.entity.SupervisionRequest;
import ir.madjeed.healthcare.logic.entity.User;
import java.util.Date;


public class SupervisionRequestPersistent implements SupervisionRequest {

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

    public SupervisionRequestPersistent(UserPersistent patient, UserPersistent doctor, String status, String type, String requestDetail,
                                        String requestAnswer, Date date) {
        this.patient = patient;
        this.doctor = doctor;
        this.status = status;
        this.type = type;
        this.requestDetail = requestDetail;
        this.requestAnswer = requestAnswer;
        this.date = date;
    }

    @Override
    public UserPersistent getPatient() {
        return patient;
    }

    @Override
    public void setPatient(UserPersistent u) {
        this.patient = u;
    }

    @Override
    public UserPersistent getDoctor() {
        return doctor;
    }

    @Override
    public void setDoctor(UserPersistent u) {
        this.doctor = u;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
