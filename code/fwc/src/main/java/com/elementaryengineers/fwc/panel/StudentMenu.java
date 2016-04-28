/****************************************************************************
 * Name: Fraction Worksheet Creator
 * Team: Elementary Engineers 
 * Date produced: 04/28/2016
 * ________________________________
 * Purpose of program:
 * The Fraction Worksheet Creator (FWC) is a new stand-alone product 
 * that allows teachers and students to create random exercise worksheets 
 * to practice operations with fractions.The generated worksheets can contain 
 * fraction problems of various difficulty levels, from basic addition and 
 * subtraction problems with visuals and images suitable for small children, 
 * to quite advanced fraction equations. 
 * ****************************************************************************
 */

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