package OOP_CW_commandLine;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
            Scanner input=new Scanner(System.in);
            Formula1ChampionshipManager formula=new Formula1ChampionshipManager();
            formula.readInfo();
            System.out.println("-----------------------------------------------------------------------\n\n|||~~~~~~~~~~~~~~~ FORMULA 1 RACING CAR CHAMPIONSHIP ~~~~~~~~~~~~~~~|||");
            while (true) {
                formula.displayMenu();

            /*
            selecting and calling the relevant option
            */
                System.out.print("Enter your option choice : ");
                String choice = input.next().toUpperCase();
                switch (choice) {
                    case "N":
                        formula.createDriver();
                        break;

                    case "D":
                        formula.deleteDriver();
                        break;

                    case "C":
                        formula.changeDriver();
                        break;

                    case "V":
                        formula.viewStatistics();
                        break;

                    case "T":
                        formula.displayTable();
                        break;

                    case "A":
                        formula.addRace();
                        break;

                    case "S":
                        formula.saveInfo();
                        break;

                    case "G":
                        formula.startGUI();
                        break;

                    case "E":
                        break;

                    default:
                        System.out.println("\n**** Invalid Input. Please choose a valid option to continue.");
                        break;
                }

                if (choice.equals("E")) {
                    formula.saveInfo();
                    System.out.println("Have a good day!");
                    break;
                }
            }
        }
    }

