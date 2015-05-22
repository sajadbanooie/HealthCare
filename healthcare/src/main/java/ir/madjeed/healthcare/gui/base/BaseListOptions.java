package ir.madjeed.healthcare.gui.base;

import ir.madjeed.healthcare.gui.ItemDetailActivity;
import org.parceler.Parcel;


/**
 * Created by admin on 4/4/2015.
 */
@Parcel
public final class BaseListOptions {

    Class listItemTargetClass;
    String relatedID; // primary key of list owner (default = null)
    String purpose; // contain view , select
    String category; // contain all | mine

    public BaseListOptions(){
    }

    public BaseListOptions(Class listItemTargetClass, String relatedID, String purpose, String category) {
        this.listItemTargetClass = listItemTargetClass;
        this.relatedID = relatedID;
        this.purpose = purpose;
        this.category = category;
    }

    public BaseListOptions(String relatedID, String purpose, String category) {
        this.listItemTargetClass = ItemDetailActivity.class;
        this.relatedID = relatedID;
        this.purpose = purpose;
        this.category = category;
    }

    public Class getListItemTargetClass() {
        return listItemTargetClass;
    }

    public void setListItemTargetClass(Class listItemTargetClass) {
        this.listItemTargetClass = listItemTargetClass;
    }

    public String getRelatedID() {
        return relatedID;
    }

    public void setRelatedID(String relatedID) {
        this.relatedID = relatedID;
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
