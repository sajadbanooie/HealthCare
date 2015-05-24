package ir.madjeed.healthcare.logic.entity.impl.persistent;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import ir.madjeed.healthcare.logic.entity.Message;
import ir.madjeed.healthcare.logic.entity.User;

import java.util.Date;


public class MessagePersistent implements Message {

    @DatabaseField(generatedId = true, columnName = "pk_column")
    private int id;

    @DatabaseField
    private String detail;

    @DatabaseField(dataType = DataType.DATE_LONG)
    private Date date;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private UserPersistent owner;


    public MessagePersistent() {
        // needed by ormlite
    }

    public MessagePersistent(String detail, Date date) {
        this.detail = detail;
        this.date = date;
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

    @Override
    public User getOwner() {
        return owner;
    }

    @Override
    public void setOwner(User owner) {
        this.owner = (UserPersistent) owner;
    }

}
