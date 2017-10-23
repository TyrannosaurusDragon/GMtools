package com.zach.gmtools;

import com.zach.gmtools.com.zach.gmtools.objects.Beast;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class beastiaryscreen {
    private JPanel mainpanel;
    private JLabel searchlabel;
    private JTextField searchbox;
    private JButton filterbutton;
    private JButton newbutton;
    private JScrollPane beastsbox;
    private JTable beaststable;
    private JButton refreshButton;
    private JPanel beastspanel;
    private ArrayList<Beast> beastsInTable = new ArrayList<>();

    public beastiaryscreen(){
        refreshTable();

        newbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Beast beast = new Beast(MainScreen.beasts.getNextID());
                beastinfoscreen bin = new beastinfoscreen(beast);
                bin.open();
            }
        });
        beaststable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if(me.getClickCount()==2){
                    JTable table = (JTable)me.getSource();
                    int row = table.rowAtPoint(me.getPoint());
                    if(row<0){
                        return;
                    }
                    Beast b = beastsInTable.get(row);
                    beastinfoscreen beastinfo = new beastinfoscreen(b);
                    beastinfo.open();
                }
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
    }

    private void refreshTable(){
        TableModel tableModel = new DefaultTableModel(getData(), getColumns()){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        beaststable.setModel(tableModel);
        beaststable.setFillsViewportHeight(true);
        beastsbox.getViewport().add(beaststable);
        beastsbox.repaint();
    }

    private String[] getColumns(){
        return new String[]{"Name","HP","CR","XP"};
    }

    private Object[][] getData(){
        if(MainScreen.beasts.listSize()==0){
            return new Object[0][0];
        }
        beastsInTable.clear();
        Object[][] toReturn = new Object[MainScreen.beasts.listSize()][4];
        for(int i = 0; i<MainScreen.beasts.listSize(); i++){
            Object tempObj = MainScreen.beasts.getByIndex(i);
            if(!(tempObj instanceof Beast)){
                continue;
            }
            Beast tempBeast = (Beast)tempObj;
            toReturn[i][0] = tempBeast.getValue("Name");
            toReturn[i][1] = tempBeast.getValue("HP");
            toReturn[i][2] = tempBeast.getValue("CR");
            toReturn[i][3] = tempBeast.getValue("XP");
            beastsInTable.add(tempBeast);
        }
        return toReturn;
    }

    public void open(){
        JFrame frame = new JFrame("Beastiary");
        frame.setContentPane(MainScreen.beastscreen.mainpanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screensize.width/2)-(frame.getSize().width/2), (screensize.height/2)-(frame.getSize().height/2));
        frame.setVisible(true);
    }
}
