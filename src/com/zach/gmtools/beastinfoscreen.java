package com.zach.gmtools;

import com.zach.gmtools.com.zach.gmtools.objects.Beast;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class beastinfoscreen {
    private JPanel mainpanel;
    private JTextField nameBox;
    private JFrame frame;
    private JPanel defencePanel;
    private JLabel acLabel;
    private JTextField acBox;
    private JLabel touchLabel;
    private JTextField touchBox;
    private JLabel flatfootLabel;
    private JTextField flatfootBox;
    private JLabel fortLabel;
    private JTextField fortBox;
    private JLabel reflexLabel;
    private JTextField reflexBox;
    private JLabel willLabel;
    private JTextField willBox;
    private JPanel picturePanel;
    private JLabel immuneLabel;
    private JTextField immuneBox;
    private JLabel defensiveAbLabel;
    private JTextField defensiveAbBox;
    private JLabel specialDeLabel;
    private JTextField specialDeBox;
    private JLabel damageReLabel;
    private JTextField damageReBox;
    private JLabel weakLabel;
    private JTextField weakBox;
    private JLabel nameLabel;
    private JTextField initiativeBox;
    private JLabel initiativeLabel;
    private JTextField speedBox;
    private JLabel speedLabel;
    private JTextField crBox;
    private JTextField xpBox;
    private JTextField hpBox;
    private JLabel crLabel;
    private JLabel xpLabel;
    private JLabel hpLabel;
    private JPanel offencePanel;
    private JScrollPane offenceScroll;
    private JTextArea offenceBox;
    private JPanel statsPanel;
    private JLabel strLabel;
    private JTextField strBox;
    private JLabel dexLabel;
    private JTextField dexBox;
    private JLabel conLabel;
    private JTextField conBox;
    private JLabel intLabel;
    private JTextField intBox;
    private JLabel wisLabel;
    private JTextField wisBox;
    private JLabel chaLabel;
    private JTextField chaBox;
    private JLabel skillsLabel;
    private JTextField skillsBox;
    private JLabel senseLabel;
    private JTextField senseBox;
    private JPanel itemsPanel;
    private JScrollPane itemScroll;
    private JTextArea itemsBox;
    private JPanel specialPanel;
    private JScrollPane specialScroll;
    private JTextArea specialBox;
    private JPanel namePanel;
    private JPanel initPanel;
    private JButton saveButton;
    private JPanel savePanel;
    private int beast;
    private final Object[][] boxes = {
            {"HP", hpBox},
            {"Name", nameBox},
            {"CR", crBox},
            {"Init", initiativeBox},
            {"Speed", speedBox},
            {"XP", xpBox},
            {"AC", acBox},
            {"Touch", touchBox},
            {"FF", flatfootBox},
            {"Fort", fortBox},
            {"Will", willBox},
            {"Reflex", reflexBox},
            {"Immunity", immuneBox},
            {"DefAb", defensiveAbBox},
            {"SD", specialDeBox},
            {"DR", damageReBox},
            {"Weak", weakBox},
            {"Offence", offenceBox},
            {"STR", strBox},
            {"DEX", dexBox},
            {"CON", conBox},
            {"INT", intBox},
            {"WIS", wisBox},
            {"CHA", chaBox},
            {"Skills", skillsBox},
            {"Items", itemsBox},
            {"Special", specialBox}
    };

    public beastinfoscreen(int b){
        this.beast = b;
        loadInfo();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveInfo();
            }
        });
    }

    public void saveInfo(){
        Beast tempBeast = new Beast(beast);
        MainScreen.beasts.add(tempBeast);
        for(int i=0;i<boxes.length;i++){
            if(boxes[i][1] instanceof JTextComponent){
                if(boxes[i][0].equals("Skills") || boxes[i][0].equals("Items")){
                    continue;//TODO
                }
                MainScreen.beasts.getByID(beast).saveValue(boxes[i][0].toString(),((JTextComponent)boxes[i][1]).getText());
            }
        }
    }

    public void loadInfo() {
        if(MainScreen.beasts.getByID(beast)==null){
            return;
        }
        for (int i = 0; i < MainScreen.beasts.getByID(beast).getValues().size(); i++) {
            for (int j = 0; j < boxes.length; j++) {
                if(MainScreen.beasts.getByID(beast).getValues().get(i)[0].equals(boxes[j][0])){
                    boxes[j][1] = MainScreen.beasts.getByID(beast).getValues().get(i)[1];
                }
            }
        }
    }

    public void open(){
        frame = new JFrame("Beast Info: "+beast);
        frame.setContentPane(mainpanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screensize.width/2)-(frame.getSize().width/2), (screensize.height/2)-(frame.getSize().height/2));
        frame.setVisible(true);
    }
}
