package OOP_CW_gui;

import OOP_CW_commandLine.Driver;
import OOP_CW_commandLine.Formula1Driver;

import java.util.Comparator;

public class SortFirstPosition implements Comparator<Driver> {
    public int compare(Driver driver1, Driver driver2) {
        if(((Formula1Driver)driver1).getNumOfFirstPosition()>((Formula1Driver)driver2).getNumOfFirstPosition()){
            return 1;
        }
        else if(((Formula1Driver)driver1).getNumOfFirstPosition()<((Formula1Driver)driver2).getNumOfFirstPosition()){
            return -1;
        }
        else {
            return 0;
        }
    }
}
