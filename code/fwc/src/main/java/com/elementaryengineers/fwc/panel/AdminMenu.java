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
 * Created by sarahakk on 4/25/16.
 */
public class AdminMenu extends UserMenu {

    private JButton btnHome, btnPassReset, btnAddTeacher;

    public AdminMenu() {
        btnHome = new ImageButton("Home", FWCConfigurator.HOME_IMG, 150, 50);
        btnPassReset = new ImageButton("Password Reset", FWCConfigurator.PASSWORDS_IMG, 150, 50);
        btnAddTeacher = new ImageButton("Add Teacher", FWCConfigurator.ADDTEACHER_IMG, 150, 50);

        this.add(btnHome);
        this.add(btnPassReset);
        this.add(btnAddTeacher);
    }

    public void setHomeListener(ActionListener listener) {
        btnHome.addActionListener(listener);
    }

    public void setPasswordResetListener(ActionListener listener) {
        btnPassReset.addActionListener(listener);
    }

    public void setAddTeacherListener(ActionListener listener) {
        btnAddTeacher.addActionListener(listener);
    }
}