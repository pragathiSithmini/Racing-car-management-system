package OOP_CW_commandLine;

import java.util.Comparator;

public class SortArray implements Comparator<Driver> {

    @Override
    public int compare(Driver driver1, Driver driver2) {
        if (((Formula1Driver)driver1).getNumOfPoints()>((Formula1Driver)driver2).getNumOfPoints()){
            return 1;
        }
        else if (((Formula1Driver)driver1).getNumOfPoints()==((Formula1Driver)driver2).getNumOfPoints()){
            if (((Formula1Driver)driver1).getNumOfFirstPosition()>((Formula1Driver)driver2).getNumOfFirstPosition()){
                return 1;
            }
            else{
                return -1;
            }
        }
        else{
            return -1;
        }
    }
}
