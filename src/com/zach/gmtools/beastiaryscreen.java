package com.zach.gmtools;

import com.zach.gmtools.com.zach.gmtools.objects.Beast;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
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

        newbutton.addActionListener(e -> {
            Beast b = new Beast(MainScreen.beasts.getNextID());
            int bID = b.getID();
            beastinfoscreen bin = new beastinfoscreen(bID, true);
            bin.open();
        });
        beaststable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                JTable table = (JTable)me.getSource();
                int column = table.columnAtPoint(me.getPoint());
                int row = table.rowAtPoint(me.getPoint());
                if(column==4){
                    Beast beast = beastsInTable.get(row);
                    deleteButtonPress(beast);
                }
                if(me.getClickCount()==2){
                    if(row<0){
                        return;
                    }
                    Beast b = beastsInTable.get(row);
                    int bID = b.getID();
                    beastinfoscreen beastinfo = new beastinfoscreen(bID, false);
                    beastinfo.open();
                }
            }
        });
        refreshButton.addActionListener(e -> refreshTable());
        ImageIcon iicon  = new ImageIcon(new ImageIcon(getClass().getResource("/resources/search_icon.png")).getImage().getScaledInstance(20,15, Image.SCALE_SMOOTH));
        filterbutton.setIcon(iicon);
    }

    private void deleteButtonPress(Beast beast){
        MainScreen.beasts.remove(beast);
        refreshTable();
        //TODO
    }

    public void refreshTable(){
        TableModel tableModel = new DefaultTableModel(getData(), getColumns()){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        beaststable.setModel(tableModel);
        beaststable.setFillsViewportHeight(true);
        com.zach.gmtools.ButtonColumn bc = new com.zach.gmtools.ButtonColumn(beaststable, null, 4);
        bc.setFocusBorder(new LineBorder(Color.red));
        beastsbox.getViewport().add(beaststable);
        beastsbox.repaint();
    }

    private String[] getColumns(){
        return new String[]{"Name","HP","CR","XP","Remove"};
    }

    private Object[][] getData(){
        if(MainScreen.beasts.listSize()==0){
            return new Object[0][0];
        }
        beastsInTable.clear();
        Object[][] toReturn = new Object[MainScreen.beasts.listSize()][5];
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
            toReturn[i][4] = new ImageIcon(new ImageIcon(getClass().getResource("/resources/delete_icon.png")).getImage().getScaledInstance(20,15,Image.SCALE_SMOOTH));
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
