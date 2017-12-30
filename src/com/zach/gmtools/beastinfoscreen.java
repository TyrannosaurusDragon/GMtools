package com.zach.gmtools;

import com.zach.gmtools.com.zach.gmtools.objects.Beast;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.*;

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
    private int bID;
    private boolean newThing=false;
    private ArrayList<Type> itemsInList;
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
            {"Items", itemsInList},
            {"Special", specialBox},
            {"Sense", senseBox}
    };

    public beastinfoscreen(int b, boolean newClick){
        itemsInList=new ArrayList<>();
        bID = b;
        if(!newClick) loadInfo();
        newThing=newClick;
        saveButton.addActionListener(e -> saveInfo());
    }

    public void saveInfo(){
        Beast me;
        if(newThing){
            me = new Beast(bID);
        } else {
            me = (Beast)MainScreen.beasts.getByID(bID);
        }
        for (Object[] boxe : boxes) {
            if (boxe[1] instanceof JTextComponent) {
                me.saveValue(boxe[0].toString(), ((JTextComponent) boxe[1]).getText());
            } else if (boxe[1] instanceof ArrayList){
                me.saveValue(boxe[0].toString(),boxe[1]);
            }
        }
        if(newThing){
            MainScreen.beasts.add(me);
            newThing = false;
        }
        me.writeToFile();
        MainScreen.beastscreen.refreshTable(MainScreen.beasts.getList());
    }

    public void loadInfo() {
        Set<Map.Entry<String,Object>> tempSet = MainScreen.beasts.getByID(bID).getValues().entrySet();
        tempSet.forEach(i->{
            for (int j = 0; j < boxes.length; j++) {
                if(i.getKey().equals(boxes[j][0])){
                    if(!(boxes[j][1] instanceof JTextComponent)) continue;
                    JTextComponent jtc = (JTextComponent)boxes[j][1];
                    jtc.setText(i.getValue().toString());
                    boxes[j][1] = jtc;
                }
            }
        });
    }

    public void open(){
        frame = new JFrame("Beast Info: "+bID);
        frame.setContentPane(mainpanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screensize.width/2)-(frame.getSize().width/2), (screensize.height/2)-(frame.getSize().height/2));
        frame.setVisible(true);
    }
}
