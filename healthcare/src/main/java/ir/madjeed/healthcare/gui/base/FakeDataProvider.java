package ir.madjeed.healthcare.gui.base;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by admin on 4/4/2015.
 */
public final class FakeDataProvider {

    public static ArrayList<CustomRowObject> get_data_list(String type, String category){
        Random rnd = new Random();

        int x=0;
        if (category.equals("mine")){
            x = 3;
        }else{
            x = 20;
        }

        ArrayList<CustomRowObject> data= new ArrayList<CustomRowObject>();
        for (int i = 0; i < rnd.nextInt(3)+x; i++) {
            data.add(new CustomRowObject(type+" "+String.valueOf(rnd.nextInt(100)+ 100)));
        }
        return data;
    }

}
