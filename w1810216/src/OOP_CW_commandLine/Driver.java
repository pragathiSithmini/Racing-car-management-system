package OOP_CW_commandLine;

import java.io.Serializable;

public abstract class Driver implements Serializable {
    private final static long serializeVersion = 1L;
    private String name;
    private String location;
    private String team;


    public Driver() {
    }

    public Driver(String name, String location, String team) {
        this.name = name;
        this.location = location;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "\n* Driver name                 : " + this.name
                + "\n* Driver location             : " + this.location
                + "\n* Team that driver belongs to : " + this.team;
    }
}
