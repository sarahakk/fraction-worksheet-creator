package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.db.FWCConfigurator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by sarahakk on 4/10/16.
 */
public class TeacherMenu extends UserMenu {

    private JButton btnHome, btnTutorials, btnHistory, btnClasses, btnPasswords;

    public TeacherMenu() {
        btnHome = new ImageButton("Home", FWCConfigurator.HOME_IMG, 150, 50);
        btnTutorials = new ImageButton("Tutorials", FWCConfigurator.TUTORIAL_IMG, 150, 50);
        btnHistory = new ImageButton("History", FWCConfigurator.HISTORY_IMG, 150, 50);
        btnClasses = new ImageButton("Classes", FWCConfigurator.CLASSES_IMG, 150, 50);
        btnPasswords = new ImageButton("Passwords", FWCConfigurator.PASSWORDS_IMG, 150, 50);

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
