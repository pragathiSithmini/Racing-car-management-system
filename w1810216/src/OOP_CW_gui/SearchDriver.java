package OOP_CW_gui;

import OOP_CW_commandLine.Formula1ChampionshipManager;
import OOP_CW_commandLine.Race;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class SearchDriver extends JFrame implements ActionListener, FocusListener {

    JButton jb1;
    JTextField jtf1;
    JButton jb2;
    DefaultTableModel dtm;
    JLabel jl2;

    public SearchDriver() {
        /*
        Initializing JObjects
        */
        setIconImage(new ImageIcon("car_icon.jpg").getImage());
        jb1 = new JButton("<- Back");
        JLabel jl1 = new JLabel("Search driver by his name");
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 50));
        jb2 = new JButton("Search");
        jtf1 = new JTextField("Enter driver name ex: amal");
        dtm = new DefaultTableModel();
        JTable jt = new JTable(dtm);
        JScrollPane jsp = new JScrollPane(jt);
        jl2 = new JLabel("*No such driver from that name*");

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
        jb2.setFocusPainted(false);
        jb2.setBackground(Color.WHITE);
        jb2.setFont(new Font("Arial", Font.BOLD, 18));
        jtf1.setFont(new Font("Arial", Font.PLAIN, 16));
        jtf1.setHorizontalAlignment(SwingConstants.CENTER);
        jp.setBackground(Color.BLACK);
        jt.setRowHeight(30);
        jt.setFont(new Font("Arial", Font.PLAIN, 18));
        jt.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        jt.setGridColor(Color.GREEN);
        jsp.setPreferredSize(new Dimension(1150, 480));
        jtf1.setPreferredSize(new Dimension(230, 30));
        jb1.setOpaque(true);
        jl2.setHorizontalAlignment(SwingConstants.CENTER);
        jl2.setFont(new Font("Arial", Font.PLAIN, 18));
        jb1.setBounds(20, 38, 100, 30);
        jl1.setBounds(390, 30, 420, 50);
        jp.setBounds(0, 100, 1200, 675);
        jl2.setBounds(0,800,1200,30);
        jl2.setVisible(false);

        /*
        adding the  JObjects into both JFrame and JPanel
        */
        jp.add(jtf1);
        jp.add(jb2);
        jp.add(jsp);
        add(jb1);
        add(jl1);
        add(jp);
        add(jl2);

        /*
        connecting the relevant JObjects with the  ActionListeners to trigger the user events
        */
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jtf1.addFocusListener(this);

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
        } else if (e.getSource() == jb2) {
            findingDriverDetails();
        }
    }

    /*
    checking if there is a driver from the name user entered and displaying the driver details if driver exists
    or displaying a relevant message if there is no driver from that name
     */
    private void findingDriverDetails() {
        jl2.setVisible(false);
        dtm.setColumnCount(0);
        dtm.setRowCount(0);
        int raceCount = 1;
        boolean isColumnsSet = false;
        boolean isDriverFound = false;
        for (Race race : Formula1ChampionshipManager.raceArray) {
            for (int i=0; i<race.getName().size(); i++) {
                if (race.getName().get(i).equalsIgnoreCase(jtf1.getText())) {
                    if (!isColumnsSet) {
                        dtm.addColumn("Race no");
                        dtm.addColumn("Held date");
                        dtm.addColumn("Driver name");
                        dtm.addColumn("Achieved position");
                        isColumnsSet = true;
                        isDriverFound = true;
                    }
                    dtm.addRow(new String[]{"Race "+raceCount++,race.getDate().toString(),jtf1.getText(),race.getPositions().get(i)+""});
                }
            }
        }
        if(!isDriverFound){
            jl2.setVisible(true);
        }
    }

    /*
     removing the placeholder of JTextField when the focus is gained
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (jtf1.getText().equals("Enter driver name ex: amal")) {
            jtf1.setText("");
            jtf1.setForeground(Color.BLACK);
        }
    }

    /*
     adding the placeholder of JTextField when the focus is lost
     */
    @Override
    public void focusLost(FocusEvent e) {
        if (jtf1.getText().isEmpty()) {
            jtf1.setText("Enter driver name ex: amal");
            jtf1.setForeground(Color.DARK_GRAY);
        }
    }
}