package com.zach.gmtools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class YesNoDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel messageLabel;
    private int returned = -1;

    public YesNoDialog(String title, String message) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle(title);
        messageLabel.setText(message);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pack();
        setMinimumSize(new Dimension(250,150));
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screensize.width/2)-(getSize().width/2), (screensize.height/2)-(getSize().height/2));
        setVisible(true);
    }

    public int getReturned(){
        return returned;
    }

    private void onOK() {
        returned = 1;
        dispose();
    }

    private void onCancel() {
        returned = 0;
        dispose();
    }
}
