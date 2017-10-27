package com.zach.gmtools;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class randomweatherdialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel randomweatherlabel;
    private String[] temperature = new String[]{"Hot ", "Warm ","Moderate ", "Cold ","Brisk ","Crisp "};
    private String[] descriptor = new String[]{"Heavy ","Light ","Medium ",""};
    private String[] effect = new String[]{"Rain", "Overcast", "Clear", "Snow"};
    private Random rand;

    public randomweatherdialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Random Weather Generation");
        rand = new Random();
        String weathereffect = getRandomEffect();
        randomweatherlabel.setText(weathereffect);
        pack();
        setMinimumSize(new Dimension(200,100));
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screensize.width/2)-(getSize().width/2), (screensize.height/2)-(getSize().height/2));
        buttonOK.addActionListener(e -> onOK());
    }

    private String getRandomEffect(){
        int rtem = rand.nextInt(temperature.length);
        int rdes = rand.nextInt(descriptor.length);
        int reff = rand.nextInt(effect.length);
        return temperature[rtem] + descriptor[rdes] + effect[reff];
    }

    private void onOK() {
        dispose();
    }
}
