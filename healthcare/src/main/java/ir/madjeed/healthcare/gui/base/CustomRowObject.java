package ir.madjeed.healthcare.gui.base;


public class CustomRowObject  {

    private final String[] data;

    public CustomRowObject(String ... params){
        this.data = params;
    }

    public String[] getRow() {
        return data;
    }

    public String getColumn(int id) {
        return data[id];
    }
}
