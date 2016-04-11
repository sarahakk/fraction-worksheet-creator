package com.elementaryengineers.fwc.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by sarahakk on 4/10/16.
 */
public class TeacherMenu extends UserMenu {

    private JButton btnHome, btnTutorials, btnHistory, btnClasses, btnPasswords;

    public TeacherMenu() {
        btnHome = new JButton("Home");
        btnHome.setFont(new Font("Calibri", Font.BOLD, 16));
        btnTutorials = new JButton("Tutorials");
        btnTutorials.setFont(new Font("Calibri", Font.BOLD, 16));
        btnHistory = new JButton("History");
        btnHistory.setFont(new Font("Calibri", Font.BOLD, 16));
        btnClasses = new JButton("Classes");
        btnClasses.setFont(new Font("Calibri", Font.BOLD, 16));
        btnPasswords = new JButton("Passwords");
        btnPasswords.setFont(new Font("Calibri", Font.BOLD, 16));

        this.add(btnHome);
        this.add(btnTutorials);
        this.add(btnHistory);
        this.add(btnClasses);
        this.add(btnPasswords);
    }

    public void setHomeListener(ActionListener listener) {
        btnHome.addActionListener(listener);
    }

    public void setTutorialsListener(ActionListener listener) {
        btnTutorials.addActionListener(listener);
    }

    public void setHistoryListener(ActionListener listener) {
        btnHistory.addActionListener(listener);
    }

    public void setClassesListener(ActionListener listener) {
        btnClasses.addActionListener(listener);
    }

    public void setPasswordsListener(ActionListener listener) {
        btnPasswords.addActionListener(listener);
    }
}
