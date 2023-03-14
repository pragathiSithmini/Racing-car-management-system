package OOP_CW_gui;

import OOP_CW_commandLine.Race;

import java.util.Comparator;

public class SortDate implements Comparator<Race> {
    @Override
    public int compare(Race race1, Race race2) {
        if(race1.getDate().toString().compareTo(race2.getDate().toString())>0){
            return 1;
        }
        else if(race1.getDate().toString().compareTo(race2.getDate().toString())<0){
            return -1;
        }
        else {
            return 0;
        }
    }
}
