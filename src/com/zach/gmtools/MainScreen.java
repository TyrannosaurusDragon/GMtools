package com.zach.gmtools;

import com.zach.gmtools.com.zach.gmtools.objects.Beasts;
import com.zach.gmtools.com.zach.gmtools.objects.Items;
import com.zach.gmtools.com.zach.gmtools.objects.Skills;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen {


    private JLabel gmtoolslabel;
    private JPanel mainpanel;
    private JPanel buttonpanel;
    private JButton timeweatherbutton;
    private JButton beastiarybutton;
    private JButton itemsbutton;
    private JButton partybutton;
    private JButton combatbutton;
    private JButton exitbutton;
    public static timeweatherscreen timeweather;
    public static beastiaryscreen beastscreen;
    public static Beasts beasts;
    public static Items items;
    public static Skills skills;


    public MainScreen(){
        timeweatherbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(timeweather==null){
                    timeweather = new timeweatherscreen();
                }
                timeweather.open();
            }
        });
        beastiarybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(beastscreen==null){
                    beastscreen = new beastiaryscreen();
                }
                beastscreen.open();
            }
        });
        exitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String args[]){
        beasts = new Beasts();
        items = new Items();
        skills = new Skills();
        JFrame frame = new JFrame("GM Tools");
        frame.setContentPane(new MainScreen().mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500,-1));
        frame.setResizable(false);
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.pack();
        frame.setLocation((screensize.width/2)-(frame.getSize().width/2), (screensize.height/2)-(frame.getSize().height/2));
        frame.setVisible(true);
    }
}
