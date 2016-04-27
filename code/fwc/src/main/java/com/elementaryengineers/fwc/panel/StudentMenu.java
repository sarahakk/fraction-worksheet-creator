package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.db.FWCConfigurator;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by sarahakk on 4/27/16.
 */
public class StudentMenu extends UserMenu {

    private JButton btnHome, btnTutorials, btnHistory;

    public StudentMenu() {
        btnHome = new ImageButton("Home", FWCConfigurator.HOME_IMG, 150, 50);
        btnTutorials = new ImageButton("Tutorials", FWCConfigurator.TUTORIAL_IMG, 150, 50);
        btnHistory = new ImageButton("History", FWCConfigurator.HISTORY_IMG, 150, 50);

        this.add(btnHome);
        this.add(btnTutorials);
        this.add(btnHistory);
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
}