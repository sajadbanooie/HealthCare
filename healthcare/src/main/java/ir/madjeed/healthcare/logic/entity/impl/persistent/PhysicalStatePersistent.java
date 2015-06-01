package ir.madjeed.healthcare.logic.entity.impl.persistent;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import ir.madjeed.healthcare.logic.entity.PhysicalState;
import ir.madjeed.healthcare.logic.entity.User;

import java.util.Calendar;
import java.util.Date;


public class PhysicalStatePersistent extends PhysicalState {

    @DatabaseField(generatedId = true, columnName = "pk_column")
    private int id;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private UserPersistent patient;

    @DatabaseField
    private Integer ghand;

    @DatabaseField
    private Integer vazn;

    @DatabaseField
    private Integer feshar;

    @DatabaseField
    private Integer ghandeKhun;

    @DatabaseField(dataType = DataType.DATE_LONG)
    private Date date;


    public PhysicalStatePersistent() {
        // needed by ormlite
    }

    public PhysicalStatePersistent(User patient, Integer ghand, Integer vazn, Integer feshar, Integer ghandeKhun) {
        this.patient = (UserPersistent) patient;
        this.ghand = ghand;
        this.vazn = vazn;
        this.feshar = feshar;
        this.ghandeKhun = ghandeKhun;
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
    public Integer getGhand() {
        return ghand;
    }

    @Override
    public void setGhand(Integer ghand) {
        this.ghand = ghand;
    }

    @Override
    public Integer getVazn() {
        return vazn;
    }

    @Override
    public void setVazn(Integer vazn) {
        this.vazn = vazn;
    }

    @Override
    public Integer getFeshar() {
        return feshar;
    }

    @Override
    public void setFeshar(Integer feshar) {
        this.feshar = feshar;
    }

    @Override
    public Integer getGhandeKhun() {
        return ghandeKhun;
    }

    @Override
    public void setGhandeKhun(Integer ghandeKhun) {
        this.ghandeKhun = ghandeKhun;
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
