package OOP_CW_gui;

import OOP_CW_commandLine.Formula1ChampionshipManager;
import OOP_CW_commandLine.Race;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class RaceDetail extends JFrame implements ActionListener {

    int raceCount = 0;
    String[] columns = {"RACE NUMBER", "NUMBER OF DRIVERS", "WINNER", "DATE"};
    JButton jb1;

    public RaceDetail() {
        /*
        Initializing JObjects
        */
        setIconImage(new ImageIcon("car_icon.jpg").getImage());
        jb1 = new JButton("<- Back");
        JLabel jl1 = new JLabel("Display races in ascending order.");
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 90));
        DefaultTableModel dtm = new DefaultTableModel();
        JTable jt = new JTable(dtm);
        JScrollPane jsp = new JScrollPane(jt);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(jt.getModel());
        jt.setRowSorter(sorter);

        /*
        setting the column names of the JTable
        */
        for (String column : columns) {
            dtm.addColumn(column);
        }

        /*
        sort date in ascending order
        */
        Collections.sort(Formula1ChampionshipManager.raceArray,new SortDate());

        /*
        adding the data of the JTable
        */
        for (Race race : Formula1ChampionshipManager.raceArray) {
            dtm.addRow(new String[]{"Race " + ++raceCount, (race.getName()).size() + "", race.getName().get(race.getPositions().indexOf(1)), (race.getDate()).toString()});
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

        /*
        placing the JObjects in suitable places
        */
        jb1.setBounds(20, 38, 100, 30);
        jl1.setBounds(350, 30, 450, 50);
        jp.setBounds(0, 100, 1200, 675);

        /*
        adding the  JObjects into both JFrame and JPanel
        */
        jp.add(jsp);
        add(jb1);
        add(jl1);
        add(jp);

        jb1.addActionListener(this); //connecting the relevant JObjects with the  ActionListeners to trigger the user events

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
    }
}
