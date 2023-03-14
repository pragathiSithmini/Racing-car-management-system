package OOP_CW_gui;

import OOP_CW_commandLine.Driver;
import OOP_CW_commandLine.Formula1ChampionshipManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class RandomRace extends JFrame implements ActionListener {

    JButton jb1, jb2;
    DefaultTableModel dtm1, dtm2;
    int race = 0;

    public RandomRace() {
        /*
        Initializing JObjects
        */
        setIconImage(new ImageIcon("car_icon.jpg").getImage());
        jb1 = new JButton("<- Back");
        JLabel jl1 = new JLabel("Race with random positions");
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 30));
        dtm1 = new DefaultTableModel();
        dtm2 = new DefaultTableModel();
        JTable jt1 = new JTable(dtm1);
        JTable jt2 = new JTable(dtm2);
        JScrollPane jsp1 = new JScrollPane(jt1);
        JScrollPane jsp2 = new JScrollPane(jt2);
        jb2 = new JButton("Add a race");

         /*
        setting the column names of the JTable
        */
        dtm1.addColumn("Line up");
        for (Driver driver : Formula1ChampionshipManager.driverArray) {
            dtm1.addColumn(driver.getName() + "'s place");
        }

        /*
        setting the appearance of the JObjects
        */
        jb1.setFocusPainted(false);
        jb1.setBackground(Color.WHITE);
        jb1.setFont(new Font("Arial", Font.BOLD, 18));
        jp.setBackground(Color.BLACK);
        jt1.setRowHeight(30);
        jt1.setFont(new Font("Arial", Font.PLAIN, 18));
        jt1.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        jt1.setGridColor(Color.GREEN);
        jt2.setRowHeight(30);
        jt2.setFont(new Font("Arial", Font.PLAIN, 18));
        jt2.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        jt2.setGridColor(Color.GREEN);
        jl1.setOpaque(true);
        jl1.setBackground(Color.GREEN);
        jl1.setHorizontalAlignment(SwingConstants.CENTER);
        jl1.setFont(new Font("Arial", Font.PLAIN, 30));
        jsp1.setPreferredSize(new Dimension(1150, 290));
        jsp2.setPreferredSize(new Dimension(1150, 290));
        jb2.setFocusPainted(false);
        jb2.setBackground(Color.WHITE);
        jb2.setFont(new Font("Arial", Font.BOLD, 18));

        /*
        placing the JObjects in suitable places
        */
        jb1.setBounds(20, 38, 100, 30);
        jl1.setBounds(350, 30, 450, 50);
        jp.setBounds(0, 100, 1200, 675);
        jb2.setBounds(500,800,150,30);

        /*
        adding the  JObjects into both JFrame and JPanel
        */
        jp.add(jsp1);
        jp.add(jsp2);
        add(jb1);
        add(jl1);
        add(jp);
        add(jb2);

        /*
        connecting the relevant JObjects with the  ActionListeners to trigger the user events
        */
        jb1.addActionListener(this);
        jb2.addActionListener(this);

        /*
        changing the relevant properties of JFrame
        */
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
        }
        if (e.getSource() == jb2) {
            randomStartingPositions();
        }
    }
    /*
     generating the lineup of racers
     */
    private void randomStartingPositions() {
        int place;
        Random random = new Random();
        ArrayList<Integer> startingPositions = new ArrayList<>();
        String[] position = new String[Formula1ChampionshipManager.driverArray.size()+1];

        position[0] = "Race" + ++race;
        for (int i = 0; i < Formula1ChampionshipManager.driverArray.size(); i++) {
            while (true){
                place = random.nextInt(Formula1ChampionshipManager.driverArray.size())+1;
                if (!startingPositions.contains(place)){
                    startingPositions.add(place);
                    break;
                }
            }
            position[i+1]=String.valueOf(place);
        }
        dtm1.addRow(position);
    }

}
