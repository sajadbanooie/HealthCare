package ir.madjeed.healthcare.logic.entity.impl.persistent;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import ir.madjeed.healthcare.logic.entity.Drug;
import java.util.Calendar;
import java.util.Date;


public class DrugPersistent extends Drug {

    @DatabaseField(generatedId = true, columnName = "pk_column")
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private Integer price;

    @DatabaseField(dataType = DataType.DATE_LONG)
    private Date date;


    public DrugPersistent() {
        // needed by ormlite
    }

    public DrugPersistent(String name, Integer price) {
        this.name = name;
        this.price = price;
        Calendar cal = Calendar.getInstance();
        this.date = cal.getTime();
    }

    @Override
    public int getId(){
        return this.id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
    public void setPrice(Integer price) {
        this.price = price;
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
