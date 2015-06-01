package ir.madjeed.healthcare.logic.entity.impl.persistent;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import ir.madjeed.healthcare.logic.entity.ConsultantCase;
import ir.madjeed.healthcare.logic.entity.ConsultantMessage;
import ir.madjeed.healthcare.logic.entity.Supervision;
import ir.madjeed.healthcare.logic.entity.User;

import java.util.Calendar;
import java.util.Date;


public class ConsultantMessagePersistent extends ConsultantMessage {

    @DatabaseField(generatedId = true, columnName = "pk_column")
    private int id;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true,
            foreignAutoCreate = true)
    private ConsultantCasePersistent consultantCase;

    @DatabaseField
    private String detail;

    @DatabaseField
    private String sender;

    @DatabaseField(dataType = DataType.DATE_LONG)
    private Date date;


    public ConsultantMessagePersistent() {
        // needed by ormlite
    }

    public ConsultantMessagePersistent(ConsultantCase consultantCase, String sender, String detail) {
        this.consultantCase = (ConsultantCasePersistent) consultantCase;
        this.sender = sender;
        this.detail = detail;
        Calendar cal = Calendar.getInstance();
        this.date = cal.getTime();
    }

    @Override
    public int getId(){
        return this.id;
    }

    @Override
    public ConsultantCase getConsultantCase() {
        return consultantCase;
    }

    @Override
    public void setConsultantCase(ConsultantCase c) {
        consultantCase = (ConsultantCasePersistent) c;
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
    public String getSender() {
        return sender;
    }

    @Override
    public void setSender(String sender) {
        this.sender = sender;
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
