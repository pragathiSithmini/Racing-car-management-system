package OOP_CW_commandLine;

public class Formula1Driver extends Driver{
    private int numOfFirstPosition;
    private int numOfSecondPosition;
    private int numOfThirdPosition;
    private int numOfPoints;
    private int numOfRaces;


    public Formula1Driver(String name, String team, String location, int numOfFirstPosition, int numOfSecondPosition, int numOfThirdPosition, int numOfPoints, int numOfRaces) {
        super(name, location, team);
        this.numOfFirstPosition = numOfFirstPosition;
        this.numOfSecondPosition = numOfSecondPosition;
        this.numOfThirdPosition = numOfThirdPosition;
        this.numOfPoints = numOfPoints;
        this.numOfRaces = numOfRaces;
    }

    public int getNumOfFirstPosition() {
        return numOfFirstPosition;
    }

    public void setNumOfFirstPosition(int numOfFirstPosition) {
        this.numOfFirstPosition = numOfFirstPosition;
    }

    public int getNumOfSecondPosition() {
        return numOfSecondPosition;
    }

    public void setNumOfSecondPosition(int numOfSecondPosition) {
        this.numOfSecondPosition = numOfSecondPosition;
    }

    public int getNumOfThirdPosition() {
        return numOfThirdPosition;
    }

    public void setNumOfThirdPosition(int numOfThirdPosition) {
        this.numOfThirdPosition = numOfThirdPosition;
    }

    public int getNumOfPoints() {
        return numOfPoints;
    }

    public void setNumOfPoints(int numOfPoints) {
        this.numOfPoints = numOfPoints;
    }

    public int getNumOfRaces() {
        return numOfRaces;
    }

    public void setNumOfRaces(int numOfRaces) {
        this.numOfRaces = numOfRaces;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\n* Number of first places      : " + this.numOfFirstPosition
                + "\n* Number of second places     : " + this.numOfSecondPosition
                + "\n* Number of third places      : " + this.numOfThirdPosition
                + "\n* Number of points            : " + this.numOfPoints
                + "\n* Number of racers            : " + this.numOfRaces
                + "\n";
    }
}
