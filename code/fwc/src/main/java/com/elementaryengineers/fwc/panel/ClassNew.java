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
import com.elementaryengineers.fwc.custom.TitleLabel;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.model.Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author olgasheehan
 */
public class ClassNew extends JPanel {
    
    private JPanel pnNorth, pnSouth, pnFields;
    private TitleLabel lblTitle;
    private JLabel lblClassName;
    private JTextField txtClassName;
    private ImageButton btnSubmit;
    // No need for a back button, can simply click Classes in menu to go back
          
    public ClassNew() {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("New Class", FWCConfigurator.NEW_CLASS_IMG);
        pnNorth.add(lblTitle);

        // Build center panel
        lblClassName = new JLabel("New class name:");
        lblClassName.setFont(new Font("Calibri", Font.PLAIN, 18));
        txtClassName = new JTextField(24);
        txtClassName.setColumns(10);

        pnFields = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnFields.setBackground(Color.WHITE);
        pnFields.add(lblClassName);
        pnFields.add(txtClassName);

        // Build south panel and submit button
        pnSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnSouth.setBackground(Color.WHITE);
        btnSubmit = new ImageButton("Submit", FWCConfigurator.SUBMIT_IMG, 150, 50);
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if class name is empty
                if (txtClassName.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter a class name.",
                            "Create Class Failed",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Classroom newClass = new Classroom(txtClassName.getText());

                // If adding new class to database works
                if (FWCConfigurator.getDbConn().createClassroom(newClass)) {

                    // Add class to teacher's list of classes
                    FWCConfigurator.getTeacher().getClasses().add(newClass);
                    clearFields();

                    JOptionPane.showMessageDialog(null,
                            "Class was successfully created.",
                            "Class Created",
                            JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "Class could not be created "
                            + "in the database. Please try again.",
                            "Create Class Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pnFields.add(btnSubmit);

        add(pnNorth, BorderLayout.NORTH);
        add(pnFields, BorderLayout.CENTER);
    }

    public void clearFields() {
        txtClassName.setText("");
    }
}