package OOP_CW_commandLine;

import OOP_CW_gui.MenuGUI;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Formula1ChampionshipManager implements ChampionshipManager {
    private final int NUMOFCARS = 20;
    public static ArrayList<Driver> driverArray = new ArrayList<>();
    public static ArrayList<Race> raceArray = new ArrayList<>();
    private final Scanner input = new Scanner(System.in);
    public static int[] pointArray = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};

    public void startGUI(){
        new MenuGUI();
    }

    /*
    reading the information
    */
    public void readInfo() {
        try {
            ObjectInputStream driverReader = new ObjectInputStream(new FileInputStream("driver.txt"));
            ObjectInputStream raceReader = new ObjectInputStream(new FileInputStream("race.txt"));
            driverArray = (ArrayList<Driver>) driverReader.readObject();
            raceArray = (ArrayList<Race>) raceReader.readObject();
            System.out.println("Program data is successfully retrieved");
        } catch (Exception ex) {
            System.out.println("No any program data to retrieve");
        }
    }


    /*
    saving the information to a file
    */
    public void saveInfo() {
        try {
            ObjectOutputStream driverWriter = new ObjectOutputStream(new FileOutputStream("driver.txt"));
            ObjectOutputStream raceWriter = new ObjectOutputStream(new FileOutputStream("race.txt"));
            driverWriter.writeObject(driverArray);
            raceWriter.writeObject(raceArray);
            System.out.println("\nProgram data is successfully stored into \"manager.txt\"");
        } catch (Exception ex) {
            System.out.println("\nProgram data is not successfully stored into \"manager.txt\"");
        }
    }


    /*
    adding a race
    */
    public void addRace() {
        System.out.println("\n--- ADD A RACE ---------------------------------------------\n");
        System.out.println("Number of racers in the race: "+driverArray.size());
        if (!driverArray.isEmpty()) {
            ArrayList<String> name = new ArrayList<>();
            ArrayList<Integer> position = new ArrayList<>();
            String[] dateArray = getDate();
            int place;
            for (Driver driver : driverArray) {
                while (true) {
                    place = validateIntegerInput("* Enter the place of driver \"" + driver.getName() + "\" : ");
                    if (place <= driverArray.size() && !position.contains(place)) {
                        break;
                    } else if (position.contains(place)) {
                        System.out.println("Place " + place + " is already awarded");
                    } else {
                        System.out.println("Place is invalid");
                    }
                }
                name.add(driver.getName());
                position.add(place);
            }
            raceArray.add(new Race(name, position, new Date(dateArray[2], dateArray[1], dateArray[0])));

            for (int i = 0; i < position.size(); i++) {
                if (position.get(i) <= 10) {
                    if (position.get(i) == 1) {
                        ((Formula1Driver) driverArray.get(i)).setNumOfFirstPosition(((Formula1Driver) driverArray.get(i)).getNumOfFirstPosition() + 1);
                    } else if (position.get(i) == 2) {
                        ((Formula1Driver) driverArray.get(i)).setNumOfSecondPosition(((Formula1Driver) driverArray.get(i)).getNumOfSecondPosition() + 1);
                    } else if (position.get(i) == 3) {
                        ((Formula1Driver) driverArray.get(i)).setNumOfThirdPosition(((Formula1Driver) driverArray.get(i)).getNumOfThirdPosition() + 1);
                    }
                    ((Formula1Driver) driverArray.get(i)).setNumOfPoints(((Formula1Driver) driverArray.get(i)).getNumOfPoints() + pointArray[position.get(i) - 1]);
                }
                ((Formula1Driver) driverArray.get(i)).setNumOfRaces(((Formula1Driver) driverArray.get(i)).getNumOfRaces() + 1);
            }
        } else {
            System.out.println("\nNo drivers registered yet to add race details");
        }
        System.out.println("\n------------------------------------------------------------");
    }


    /*
    validating the date
    */
    private String[] getDate() {
        LocalDate todayDate = LocalDate.now();
        while (true) {
            System.out.print("\n* Enter the date race hold (YYYY-MM-DD) : ");
            String date = input.next();
            if (date.indexOf("-") != -1 && date.lastIndexOf("-") != -1 && date.indexOf("-") != date.lastIndexOf("-") && date.length() == 10) {
                String[] dateArray = date.split("-");
                try {
                    if (todayDate.getYear() >= Integer.parseInt(dateArray[0]) && dateArray[0].length() == 4) {
                        if (Integer.parseInt(dateArray[1]) == todayDate.getMonthValue() && Integer.parseInt(dateArray[2]) < todayDate.getDayOfMonth()) {
                            return dateArray;
                        } else if (Integer.parseInt(dateArray[1]) < todayDate.getMonthValue() && Integer.parseInt(dateArray[2]) <= 31) {
                            return dateArray;
                        } else {
                            System.out.print("Not a valid date. Please try again!");
                        }
                    } else {
                        System.out.print("Not a valid date. Please try again!");
                    }
                } catch (Exception ex) {
                    System.out.print("Not a valid date. Please try again!");
                }
            } else {
                System.out.print("Not a valid date. Please try again!");
            }
        }
    }


    /*
    displaying the formula 1 driver table
    */
    public void displayTable() {
        System.out.println("\n--- VIEW FORMULA 1 TABLE -----------------------------------------------------------------------------------------------------------------------------------\n");
        if (driverArray.size() > 0) {
            Collections.sort(driverArray, new SortArray());
            Collections.reverse(driverArray);
            String rowSeparator = "+----------------------+----------------------+----------------------+-------------------+-------------------+-------------------+------------+------------+";
            System.out.println(rowSeparator);
            System.out.printf("| %-20s | %-20s | %-20s | %-17s | %-17s | %-17s | %-10s | %-10s |\n", "DRIVER NAME", "DRIVER TEAM", "DRIVER LOCATION", "FIRST POSITIONS", "SECOND POSITIONS", "THIRD POSITIONS", "POINTS", "RACES");
            System.out.println(rowSeparator);
            for (Driver driver : driverArray) {
                System.out.printf("| %-20s | %-20s | %-20s | %-17s | %-17s | %-17s | %-10s | %-10s |\n", driver.getName(), driver.getTeam(), driver.getLocation(), ((Formula1Driver) driver).getNumOfFirstPosition(), ((Formula1Driver) driver).getNumOfSecondPosition(), ((Formula1Driver) driver).getNumOfThirdPosition(), ((Formula1Driver) driver).getNumOfPoints(), ((Formula1Driver) driver).getNumOfRaces());
            }
            System.out.println(rowSeparator);
        } else {
            System.out.println("\nNo teams registered yet to display in the table.");
        }
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }


    /*
    viewing the statistics of the driver
    */
    public void viewStatistics() {
        System.out.println("\n--- View The Statistics Of The Driver --------------------------");
        boolean driverPresented = false;
        if (driverArray.size() != 0) {
            System.out.print("\n* Enter the name of the driver you want to view statistics : ");
            String name = input.next();
            for (Driver driver : driverArray) {
                if (driver.getName().equalsIgnoreCase(name)) {
                    System.out.println(driver.toString());
                    driverPresented = true;
                    break;
                }
            }
            if (!driverPresented) {
                System.out.println("\nNo such driver called \"" + name + "\" to view the statistics.");
            }
        } else {
            System.out.println("\nNo any driver registered currently to display statistics.");
        }
        System.out.println("\n----------------------------------------------------------------");
    }


    /*
    changing a driver for an existing team
    */
    public void changeDriver() {
        System.out.println("\n--- CHANGE THE DRIVER FOR AN EXISTING TEAM ----------------------");
        boolean driverChanged = false;
        if (driverArray.size() != 0) {
            System.out.print("\n* Enter the team name you want to change the driver : ");
            String team = input.next();
            for (Driver driver : driverArray) {
                if (driver.getTeam().equalsIgnoreCase(team)) {
                    System.out.print("* Enter the name of the driver you want to replace  : ");
                    String name = input.next();
                    if (!driverAlreadyRegistered(name)) {
                        System.out.print("* Enter the location                                : ");
                        String location = input.next();
                        int firstPosition = validateIntegerInput("* Enter number of first position                    : ");
                        int secondPosition = validateIntegerInput("* Enter number of second position                   : ");
                        int thirdPosition = validateIntegerInput("* Enter number of third position                    : ");
                        int points = validateIntegerInput("* Enter number of points                            : ");
                        int races = validateIntegerInput("* Enter number of races                             : ");
                        driver.setName(name);
                        driver.setLocation(location);
                        ((Formula1Driver) driver).setNumOfFirstPosition(firstPosition);
                        ((Formula1Driver) driver).setNumOfSecondPosition(secondPosition);
                        ((Formula1Driver) driver).setNumOfThirdPosition(thirdPosition);
                        ((Formula1Driver) driver).setNumOfPoints(points);
                        ((Formula1Driver) driver).setNumOfRaces(races);
                        driverChanged = true;
                        System.out.println("\nThe driver of the team \"" + team + "\" is changed to new driver called \"" + name + "\".");
                    }
                    break;   ///remove else part
                }
            }
            if (!driverChanged) {
                System.out.println("\nNo such team called \"" + team + "\".");
            }
        } else {
            System.out.println("\nNo any team registered yet to change.");
        }
        System.out.println("\n----------------------------------------------------------------");
    }


    /*
    deleting a driver
    */
    public void deleteDriver() {
        System.out.println("\n--- DELETE A DRIVER ----------------------");
        boolean driverDeleted = false;
        if (driverArray.size() != 0) {
            System.out.print("\n* Enter the driver name you want to delete : ");
            String name = input.next();
            for (int i = 0; i < driverArray.size(); i++) {
                if (driverArray.get(i).getName().equalsIgnoreCase(name)) {
                    driverArray.remove(i);
                    System.out.println("\nDriver \"" + name + "\" deleted successfully.");
                    driverDeleted = true;
                    break;
                }
            }
            if (!driverDeleted) {
                System.out.println("\nNo such driver called \"" + name + "\".");
            }
        } else {
            System.out.println("\nNo any drivers registered yet to delete.");
        }
        System.out.println("\n------------------------------------------");
    }


    /*
    creating a new driver
    */
    public void createDriver() {
        System.out.println("\n--- CREATE A NEW DRIVER ------------------");
        if (driverArray.size() < NUMOFCARS) {
            System.out.print("\n* Enter driver name               : ");
            String name = input.next();
            if (!driverAlreadyRegistered(name)) {
                System.out.print("* Enter team name                 : ");
                String team = input.next();
                if (!teamAlreadyExist(team)) {
                    System.out.print("* Enter driver location           : ");
                    String location = input.next();
                    int firstPosition = validateIntegerInput("* Enter number of first position  : ");
                    int secondPosition = validateIntegerInput("* Enter number of second position : ");
                    int thirdPosition = validateIntegerInput("* Enter number of third position  : ");
                    int points = validateIntegerInput("* Enter number of points          : ");
                    int races = validateIntegerInput("* Enter number of races           : ");
                    driverArray.add(new Formula1Driver(name, team, location, firstPosition, secondPosition, thirdPosition, points, races));
                    System.out.println("\nDriver " + "\"" + name + "\"" + " is successfully added to the team " + "\"" + team + "\"" + ".");
                }
            }
        } else {
            System.out.println("No more cars to add drivers.");
        }
        System.out.println("\n------------------------------------------");
    }


    /*
    checking if the driver is already registered
    */
    private boolean driverAlreadyRegistered(String name) {

        for (Driver driver : driverArray) {
            if (driver.getName().equalsIgnoreCase(name)) {
                System.out.println("\nDriver " + "\"" + name + "\"" + " is already having a team.");
                return true;
            }
        }
        return false;

    }


    /*
    checking if the team is already registered
    */
    private boolean teamAlreadyExist(String team) {
        for (Driver driver : driverArray) {
            if (driver.getTeam().equalsIgnoreCase(team)) {
                System.out.println("\nThe team " + "\"" + team + "\"" + " is already registered.");
                return true;
            }
        }
        return false;
    }


    /*
    validating integer input
    */
    private int validateIntegerInput(String description) {
        while (true) {
            try {
                System.out.print(description);
                return input.nextInt();
            } catch (Exception ex) {
                System.out.println("Invalid input. You have to enter an integer type value. Please try again!");
                input.next();
            }
        }
    }


    /*
    showing the menu
    */
    public void displayMenu() {
        System.out.println("\n|||*--- MENU ---*|||\n\n"
                + "->  \"N\" - Create a new driver\n"
                + "->  \"D\" - Delete a driver\n"
                + "->  \"C\" - Change the driver for an existing team\n"
                + "->  \"V\" - View the statistics of the driver\n"
                + "->  \"T\" - Display the Formula 1 Driver Table\n"
                + "->  \"A\" - Add a race\n"
                + "->  \"S\" - Save data to a file\n"
                + "->  \"G\" - Start GUI\n"
                + "->  \"E\" - Exit programme\n"
        );
    }
}
