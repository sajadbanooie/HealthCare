package ir.madjeed.healthcare.gui.base;

import ir.madjeed.healthcare.gui.ItemDetailActivity;
import org.parceler.Parcel;


/**
 * Created by admin on 4/4/2015.
 */
@Parcel
public final class ListOptions {

    Class listItemTargetClass;
    String type; // one of entities type
    String purpose; // contain view , select
    String category; // contain all | mine

    public ListOptions(){
    }

    public ListOptions(Class listItemTargetClass, String type, String purpose, String category) {
        this.listItemTargetClass = listItemTargetClass;
        this.type = type;
        this.purpose = purpose;
        this.category = category;
    }

    public ListOptions(String type, String purpose, String category) {
        this.listItemTargetClass = ItemDetailActivity.class;
        this.type = type;
        this.purpose = purpose;
        this.category = category;
    }

    public Class getListItemTargetClass() {
        return listItemTargetClass;
    }

    public void setListItemTargetClass(Class listItemTargetClass) {
        this.listItemTargetClass = listItemTargetClass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
