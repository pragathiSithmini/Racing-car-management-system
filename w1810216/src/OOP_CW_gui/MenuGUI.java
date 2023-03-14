package OOP_CW_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuGUI extends JFrame implements ActionListener, MouseListener {

    private final JButton btn1;
    private final JButton btn2;
    private final JButton btn3;
    private final JButton btn4;
    private final JButton btn5;
    private final JLabel jl5;

    public MenuGUI() {
        /*
        Initializing JObjects
        */
        JLabel jl1 = new JLabel("Welcome to Formula1 Championship manager GUI.", SwingConstants.CENTER);
        JPanel jp = new JPanel();
        JLabel jl2 = new JLabel(new ImageIcon(new ImageIcon("f1_car_race_image.jpg").getImage().getScaledInstance(370, 480, Image.SCALE_DEFAULT)));
        JLabel jl3 = new JLabel(new ImageIcon(new ImageIcon("Trophy_image.jpg").getImage().getScaledInstance(370, 480, Image.SCALE_DEFAULT)));
        JLabel jl4 = new JLabel(new ImageIcon(new ImageIcon("helmet_image.jpg").getImage().getScaledInstance(370, 480, Image.SCALE_DEFAULT)));
        btn1 = new JButton("Display drivers and their statistics");
        btn2 = new JButton("Add a race");
        btn3 = new JButton("Random race");
        btn4 = new JButton("Display all the completed races");
        btn5 = new JButton("Search drivers");
        jl5 = new JLabel();

        /*
        setting the appearance of the JObjects
        */
        jl1.setOpaque(true);
        jl1.setBackground(Color.GREEN);
        jl1.setFont(new Font("Arial", Font.PLAIN, 30));
        jp.setLayout(null);
        jp.setBackground(Color.BLACK);
        jl2.setOpaque(true);
        jl2.setBackground(Color.WHITE);
        jl3.setOpaque(true);
        jl3.setBackground(Color.WHITE);
        jl4.setOpaque(true);
        jl4.setBackground(Color.WHITE);
        btn1.setBackground(Color.WHITE);
        btn1.setFocusPainted(false);
        btn1.setFont(new Font("Arial", Font.PLAIN, 18));
        btn2.setBackground(Color.WHITE);
        btn2.setFocusPainted(false);
        btn2.setFont(new Font("Arial", Font.PLAIN, 18));
        btn3.setBackground(Color.WHITE);
        btn3.setFocusPainted(false);
        btn3.setFont(new Font("Arial", Font.PLAIN, 18));
        btn4.setBackground(Color.WHITE);
        btn4.setFocusPainted(false);
        btn4.setFont(new Font("Arial", Font.PLAIN, 18));
        btn5.setBackground(Color.WHITE);
        btn5.setFocusPainted(false);
        btn5.setFont(new Font("Arial", Font.PLAIN, 18));
        jl5.setVisible(false);
        jl5.setForeground(new Color(120, 120, 120));
        jl5.setFont(new Font("Arial", Font.PLAIN, 18));
        jl5.setHorizontalAlignment(SwingConstants.CENTER);

        /*
        placing the JObjects in suitable places
        */
        jl1.setBounds(250, 30, 700, 50);
        jp.setBounds(0, 100, 1200, 675);
        jl2.setBounds(13, 20, 380, 495);
        jl3.setBounds(400, 20, 380, 495);
        jl4.setBounds(788, 20, 380, 495);
        btn1.setBounds(40, 535, 350, 50);
        btn2.setBounds(413, 535, 350, 50);
        btn3.setBounds(786, 535, 350, 50);
        btn4.setBounds(238, 605, 350, 50);
        btn5.setBounds(610, 605, 350, 50);
        jl5.setBounds(40, 782, 1100, 50);

        /*
        adding the  JObjects into both JFrame and JPanel
        */
        jp.add(jl2);
        jp.add(jl3);
        jp.add(jl4);
        jp.add(btn1);
        jp.add(btn2);
        jp.add(btn3);
        jp.add(btn4);
        jp.add(btn5);
        add(jl1);
        add(jp);
        add(jl5);

        /*
        connecting the relevant JObjects with the  ActionListeners to trigger the user events
        */
        btn1.addMouseListener(this);
        btn2.addMouseListener(this);
        btn3.addMouseListener(this);
        btn4.addMouseListener(this);
        btn5.addMouseListener(this);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);

        /*
        changing the relevant properties of JFrame
        */
        setTitle("Formula1 Championship Manager main Menu");
        setIconImage(new ImageIcon("car_icon.jpg").getImage());
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
        if (e.getSource() == btn1) {
            new DriverStatistics();
        } else if (e.getSource() == btn2) {
            new RaceWithRandomPlace();
        } else if (e.getSource() == btn3) {
            new RandomRace();
        } else if (e.getSource() == btn4) {
            new RaceDetail();
        } else if (e.getSource() == btn5) {
            new SearchDriver();
        }
        dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /*
    hover the button and setting the text of the JLabel, which mouse entered
    */
    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setBackground(Color.GREEN);
        e.getComponent().setForeground(Color.BLACK);
        e.getComponent().setFont(e.getComponent().getFont().deriveFont(Font.BOLD));
        if (e.getSource() == btn1) {
            jl5.setText("~ Display drivers and their statistics ~");
        } else if (e.getSource() == btn2) {
            jl5.setText("~ Add a race with RANDOMLY generated positions ~");
        } else if (e.getSource() == btn4) {
            jl5.setText("~ Display all the completed races sorted in ascending order of date played ~");
        } else if (e.getSource() == btn5) {
            jl5.setText("~ Display the races that driver had participated through out the series ~");
        } else if (e.getSource() == btn3) {
            jl5.setText("~ Add a race with both random starting positions and ending positions ~");
        }
        jl5.setVisible(true);
    }

    /*
    removing the hover effect of the button and removing the text of the JLabel, after mouse exit that relevant button
    */
    @Override
    public void mouseExited(MouseEvent e) {
        e.getComponent().setBackground(Color.WHITE);
        e.getComponent().setFont(e.getComponent().getFont().deriveFont(Font.PLAIN));
        jl5.setVisible(false);
    }
}
