package OOP_CW_gui;


import OOP_CW_commandLine.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class RaceWithRandomPlace extends JFrame implements ActionListener {

    private DefaultTableModel dtm;
    private final JButton jb1;
    private final JButton jb2;
    int randomRaceCount = 1;

    public RaceWithRandomPlace() {
        /*
        Initializing JObjects
        */
        setIconImage(new ImageIcon("car_icon.jpg").getImage());
        jb1 = new JButton("<- Back");
        JLabel jl1 = new JLabel("Add a race with RANDOM positions.");
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 50));
        dtm = new DefaultTableModel();
        JTable jt = new JTable(dtm);
        JScrollPane jsp = new JScrollPane(jt);
        jb2 = new JButton("Add race");

        /*
        setting the column names of the JTable
        */
        dtm.addColumn("Random Race no");
        for (Driver driver : Formula1ChampionshipManager.driverArray) {
            dtm.addColumn(driver.getName() + "'s place");
        }

        /*
        disabling the JButton if the driver array is empty
        */
        if (Formula1ChampionshipManager.driverArray.size() == 0) {
            jb2.setEnabled(false);
        }

        /*
        setting the appearance of the JObjects
        */
        jb1.setFocusPainted(false);
        jb1.setBackground(Color.WHITE);
        jb1.setFont(new Font("Arial", Font.BOLD, 18));
        jl1.setOpaque(true);
        jl1.setBackground(Color.GREEN);
        jl1.setHorizontalAlignment(SwingConstants.CENTER);
        jl1.setFont(new Font("Arial", Font.PLAIN, 30));
        jp.setBackground(Color.BLACK);
        jt.setRowHeight(30);
        jt.setFont(new Font("Arial", Font.PLAIN, 18));
        jt.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        jt.setGridColor(Color.GREEN);
        jsp.setPreferredSize(new Dimension(1150, 500));
        jb2.setFocusPainted(false);
        jb2.setBackground(Color.WHITE);
        jb2.setFont(new Font("Arial", Font.BOLD, 18));

         /*
        placing the JObjects in suitable places
        */
        jb1.setBounds(20, 38, 100, 30);
        jl1.setBounds(350, 30, 500, 50);
        jp.setBounds(0, 100, 1200, 675);

        /*
        adding the  JObjects into both JFrame and JPanel
        */
        jp.add(jsp);
        jp.add(jb2);
        add(jb1);
        add(jl1);
        add(jp);

        /*
        connecting the relevant JObjects with the  ActionListeners to trigger the user events
        */
        jb1.addActionListener(this);
        jb2.addActionListener(this);

        /*
        changing the relevant properties of JFrame
        */
        setTitle("Add a race with random ending places");
        setSize(1200, 900);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

    }

    /*
     handling the button press event according to user selected option
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            new MenuGUI();
            dispose();
        } else if (e.getSource() == jb2) {
            updateDriverArrayStatistics(addRandomEndingPosition());
        }
    }

    /*
     generating a race with random ending positions and creating a new race object
     */
    private ArrayList<Integer> addRandomEndingPosition() {
        Random random = new Random();
        String[] achievedPosition = new String[Formula1ChampionshipManager.driverArray.size() + 1];
        ArrayList<String> driverNameArray = new ArrayList<>();
        ArrayList<Integer> positionArray = new ArrayList<>();
        achievedPosition[0] = "Race " + randomRaceCount;
        for (int i = 0; i < Formula1ChampionshipManager.driverArray.size(); i++) {
            while (true) {
                int place = random.nextInt(Formula1ChampionshipManager.driverArray.size()) + 1;
                if (!positionArray.contains(place)) {
                    achievedPosition[i + 1] = String.valueOf(place);
                    driverNameArray.add(Formula1ChampionshipManager.driverArray.get(i).getName());
                    positionArray.add(place);
                    break;
                }
            }
        }
        randomRaceCount++;
        dtm.addRow(achievedPosition);
        Formula1ChampionshipManager.raceArray.add(new Race(driverNameArray, positionArray, new Date(generateRandomDate())));
        return positionArray;
    }

    /*
     generating a random date that race had hold
     */
    private String[] generateRandomDate() {
        Random random = new Random();
        LocalDate todayDate = LocalDate.now();
        String[] dates = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

        while (true) {
            int day = random.nextInt(31);
            int month = random.nextInt(12);
            int year = random.nextInt(5) + 2017;

            if (year <= todayDate.getYear()) {
                if (month < todayDate.getMonthValue() && day <= 31) {
                    return new String[]{dates[day], dates[month], year + ""};
                } else if (month == todayDate.getMonthValue() && day <= todayDate.getDayOfMonth()) {
                    return new String[]{dates[day], dates[month], year + ""};
                }
            }
        }
    }

    /*
     updating the driver details
     */
    private void updateDriverArrayStatistics(ArrayList<Integer> positionArray) {
        for (int i = 0; i < positionArray.size(); i++) {
            if (positionArray.get(i) <= 10) {
                if (positionArray.get(i) == 1) {
                    ((Formula1Driver) Formula1ChampionshipManager.driverArray.get(i)).setNumOfFirstPosition((((Formula1Driver) Formula1ChampionshipManager.driverArray.get(i)).getNumOfFirstPosition()) + 1);

                } else if (positionArray.get(i) == 2) {
                    ((Formula1Driver) Formula1ChampionshipManager.driverArray.get(i)).setNumOfSecondPosition((((Formula1Driver) Formula1ChampionshipManager.driverArray.get(i)).getNumOfSecondPosition()) + 1);
                } else if (positionArray.get(i) == 3) {
                    ((Formula1Driver) Formula1ChampionshipManager.driverArray.get(i)).setNumOfThirdPosition((((Formula1Driver) Formula1ChampionshipManager.driverArray.get(i)).getNumOfThirdPosition()) + 1);
                }
                ((Formula1Driver) Formula1ChampionshipManager.driverArray.get(i)).setNumOfPoints((((Formula1Driver) Formula1ChampionshipManager.driverArray.get(i)).getNumOfPoints()) + Formula1ChampionshipManager.pointArray[positionArray.get(i) - 1]);
            }
            ((Formula1Driver) Formula1ChampionshipManager.driverArray.get(i)).setNumOfRaces((((Formula1Driver) Formula1ChampionshipManager.driverArray.get(i)).getNumOfRaces()) + 1);
        }
    }

}
