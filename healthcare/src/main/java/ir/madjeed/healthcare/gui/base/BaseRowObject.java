package ir.madjeed.healthcare.gui.base;


public class BaseRowObject {

    private final String[] data;

    private final String pk;

    public BaseRowObject(String pk, String... params){
        this.pk = pk;
        this.data = params;
    }

    public String[] getRow() {
        return data;
    }

    public String getColumn(int id) {
        return data[id];
    }

    public String getPk() {
        return pk;
    }

    public String[] getData() {
        return data;
    }
}
