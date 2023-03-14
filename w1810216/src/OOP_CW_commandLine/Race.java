package OOP_CW_commandLine;

import java.io.Serializable;
import java.util.ArrayList;

public class Race implements Serializable {

    private ArrayList<String> name;
    private ArrayList<Integer> positions;
    Date date;

    public Race(ArrayList<String> name, ArrayList<Integer> positions, Date date) {
        this.name = name;
        this.positions = positions;
        this.date = date;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }

    public ArrayList<Integer> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Integer> positions) {
        this.positions = positions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

