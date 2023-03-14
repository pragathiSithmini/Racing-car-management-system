package OOP_CW_gui;


import OOP_CW_commandLine.Driver;
import OOP_CW_commandLine.Formula1ChampionshipManager;
import OOP_CW_commandLine.Formula1Driver;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class DriverStatistics extends JFrame implements ActionListener {

    private String[] columnNameArray = {"DRIVER NAME", "DRIVER TEAM", "DRIVER LOCATION", "FIRST POSITIONS", "SECOND POSITIONS", "THIRD POSITIONS", "NUMBER OF POINTS", "NUMBER OF RACES"};
    private DefaultTableModel dtm;
    private final JButton jb1;
    private final JButton jb2;
    private final JButton jb3;
    private final JButton jb4;
    private TableRowSorter<TableModel> sorter;

    public DriverStatistics() {
        /*
        Initializing JObjects
        */
        setIconImage(new ImageIcon("car_icon.jpg").getImage());
        jb1 = new JButton("<- Back");
        JLabel jl1 = new JLabel("Display driver statistics.");
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 90));
        dtm = new DefaultTableModel();
        JTable jt = new JTable(dtm);
        JScrollPane jsp = new JScrollPane(jt);
        jb2 = new JButton("Points (Descending order)");
        jb3 = new JButton("Points (Ascending order)");
        jb4 = new JButton("Number of first positions (Descending order)");

        /*
        setting the column names of the JTable
        */
        for (String columnName : columnNameArray) {
            dtm.addColumn(columnName);
        }

        /*
        adding the data of the JTable
        */
        for (Driver driver : Formula1ChampionshipManager.driverArray) {
            dtm.addRow(new Object[]{driver.getName(),driver.getTeam(),driver.getLocation(),((Formula1Driver)driver).getNumOfFirstPosition(),((Formula1Driver)driver).getNumOfSecondPosition(),((Formula1Driver)driver).getNumOfThirdPosition(),((Formula1Driver)driver).getNumOfPoints(),((Formula1Driver)driver).getNumOfRaces()});
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
        jb2.setFont(new Font("Arial", Font.PLAIN, 18));
        jb3.setFocusPainted(false);
        jb3.setBackground(Color.WHITE);
        jb3.setFont(new Font("Arial", Font.PLAIN, 18));
        jb4.setFocusPainted(false);
        jb4.setBackground(Color.WHITE);
        jb4.setFont(new Font("Arial", Font.PLAIN, 18));

        /*
        placing the JObjects in suitable places
        */
        jb1.setBounds(20, 38, 100, 30);
        jl1.setBounds(410, 30, 380, 50);
        jp.setBounds(0, 100, 1200, 675);
        jb2.setBounds(147, 800, 245, 30);
        jb3.setBounds(407, 800, 240, 30);
        jb4.setBounds(662, 800, 390, 30);

        /*
        adding the  JObjects into both JFrame and JPanel
        */
        jp.add(jsp);
        add(jb1);
        add(jl1);
        add(jp);
        add(jb2);
        add(jb3);
        add(jb4);

        /*
        connecting the relevant JObjects with the  ActionListeners to trigger the user events
        */
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        jb4.addActionListener(this);

        /*
        changing the relevant properties of JFrame
        */
        setTitle("Driver statistics");
        setSize(1200, 900);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

    }

    /*
    handling the button press event according to user selected option and sorting the table
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            new MenuGUI();
            dispose();
        } else if (e.getSource() == jb2) {
            jb2.setEnabled(false);
            jb3.setEnabled(true);
            jb4.setEnabled(true);
            removingRows();
            Collections.sort(Formula1ChampionshipManager.driverArray,new SortPoint());
            Collections.reverse(Formula1ChampionshipManager.driverArray);
            addingRowsInSortedOrder();
        } else if (e.getSource() == jb3) {
            jb3.setEnabled(false);
            jb2.setEnabled(true);
            jb4.setEnabled(true);
            removingRows();
            Collections.sort(Formula1ChampionshipManager.driverArray,new SortPoint());
            addingRowsInSortedOrder();
        } else {
            jb4.setEnabled(false);
            jb2.setEnabled(true);
            jb3.setEnabled(true);
            removingRows();
            Collections.sort(Formula1ChampionshipManager.driverArray,new SortFirstPosition());
            Collections.reverse(Formula1ChampionshipManager.driverArray);
            addingRowsInSortedOrder();
        }
    }

    /*
    recreating the rows
    */
    private void addingRowsInSortedOrder() {
        for (Driver driver : Formula1ChampionshipManager.driverArray) {
            dtm.addRow(new Object[]{driver.getName(),driver.getTeam(),driver.getLocation(),((Formula1Driver)driver).getNumOfFirstPosition(),((Formula1Driver)driver).getNumOfSecondPosition(),((Formula1Driver)driver).getNumOfThirdPosition(),((Formula1Driver)driver).getNumOfPoints(),((Formula1Driver)driver).getNumOfRaces()});
        }
    }

    /*
    removing the JTable data
    */
    private void removingRows() {
        while (dtm.getRowCount()>0){
            dtm.removeRow(0);
        }
    }
}
