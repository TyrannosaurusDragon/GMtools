package com.zach.gmtools;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class timeweatherscreen {

    private JPanel mainpanel;
    private JPanel timepanel;
    private JTextField timebox;
    private JButton addhour;
    private JButton add10min;
    private JButton add1min;
    private JButton setbutton;
    private JButton turnbutton;
    private JPanel weatherpanel;
    private JTextField weathertext;
    private JButton randomweather;
    private JTextField daybox;

    public timeweatherscreen(){
        randomweather.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomweatherdialog rwd = new randomweatherdialog();
                rwd.setVisible(true);
            }
        });
        addhour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTime(1,0,0,0);
            }
        });
        add10min.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTime(0,10,0,0);
            }
        });
        add1min.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTime(0,1,0,0);
            }
        });
        setbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!timebox.isEditable()&&!daybox.isEditable()){
                    timebox.setEditable(true);
                    daybox.setEditable(true);
                } else {
                    timebox.setEditable(false);
                    daybox.setEditable(false);
                }
            }
        });
    }

    public void addTime(int hour, int minute, int second, int day){
        try {
            String[] currentTime = timebox.getText().split(":");
            int currentHour = Integer.parseInt(currentTime[0]);
            int currentMinute = Integer.parseInt(currentTime[1]);
            int currentSecond = Integer.parseInt(currentTime[2]);
            int currentDay = Integer.parseInt(daybox.getText());
            String newHour;
            String newMinute;
            String newSecond;
            currentSecond = currentSecond+second;
            while(currentSecond>=60) {
                currentSecond = currentSecond-60;
                currentMinute = currentMinute+1;
            }
            currentMinute = currentMinute+minute;
            while(currentMinute>=60){
                currentMinute = currentMinute-60;
                currentHour = currentHour+1;
            }
            currentHour = currentHour+hour;
            while(currentHour>=24){
                currentHour=currentHour-24;
                currentDay=currentDay+1;
            }
            if(currentHour<10){
                newHour = "0"+currentHour;
            } else {
                newHour = currentHour+"";
            }
            if(currentMinute<10){
                newMinute = "0"+currentMinute;
            } else {
                newMinute = currentMinute+"";
            }
            if(currentSecond<10){
                newSecond = "0"+currentSecond;
            } else {
                newSecond = currentSecond+"";
            }
            timebox.setText(newHour+":"+newMinute+":"+newSecond);
            daybox.setText(currentDay+"");
        } catch (Exception e){
            timebox.setText("06:00:00");
            daybox.setText("0");
        }

    }

    public void open(){
        JFrame frame = new JFrame("Time & Weather");
        frame.setContentPane(MainScreen.timeweather.mainpanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screensize.width/2)-(frame.getSize().width/2), (screensize.height/2)-(frame.getSize().height/2));
        frame.setVisible(true);
    }
}
