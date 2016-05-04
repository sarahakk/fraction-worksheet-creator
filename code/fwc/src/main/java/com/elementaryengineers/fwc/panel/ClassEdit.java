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
 *ClassEdit - this page which allows teachers to rename a class or delete 
 * the class, which also deletes all the students in that class.
 * A warning message will be displayed.
 * 
 * @author olgasheehan
 */
public class ClassEdit extends JPanel{
    
    private JPanel pnNorth, pnFields, pnTxt;
    private TitleLabel lblTitle;
    private JLabel lblClassName;
    private JTextField txtClassName;
    private ImageButton btnSubmit, btnDeleteClass;
    private int classIndex;
    // No need for a back button, can simply click Classes in menu to go back

    public ClassEdit() {
        super(new BorderLayout());
        setBackground(FWCConfigurator.bgColor);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(FWCConfigurator.bgColor);
        lblTitle = new TitleLabel("Edit Class", FWCConfigurator.EDIT_CLASS_IMG);
        pnNorth.add(lblTitle);

        // Build center panel
        lblClassName = new JLabel("Class name:", SwingConstants.RIGHT);
        lblClassName.setFont(new Font("Calibri", Font.PLAIN, 18));
        txtClassName = new JTextField(24);
        txtClassName.setColumns(10);

        // Use GridBagLayout
        pnFields = new JPanel(new GridBagLayout());
        pnFields.setBackground(FWCConfigurator.bgColor);
        pnFields.setBorder(BorderFactory.createEmptyBorder(10, 150, 20, 150));

        pnTxt = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnTxt.setBackground(FWCConfigurator.bgColor);
        pnTxt.add(lblClassName);
        pnTxt.add(txtClassName);

        GridBagConstraints c = new GridBagConstraints();
        c.ipady = 5;

        // Class Name
        c.weightx = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnFields.add(pnTxt, c);

        // Build buttons
        btnSubmit = new ImageButton("Submit", 
                FWCConfigurator.SUBMIT_IMG, 150, 50);
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtClassName.getText();

                if (name.equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter all required information.",
                            "Class Update Failed",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Update class name
                Classroom modifiedClass = FWCConfigurator.getTeacher().
                        getClasses().get(classIndex);
                modifiedClass.setClassName(name);

                // Check database update status
                if (FWCConfigurator.getDbConn().updateClassroom(modifiedClass)) {
                    JOptionPane.showMessageDialog(null,
                            "Class was successfully updated.",
                            "Class Update Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "Class could not be updated in the database.",
                            "Class Update Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDeleteClass = new ImageButton("Delete Class", 
                FWCConfigurator.DEL_CLASS_IMG, 150, 50);

        c.gridwidth = 2;
        c.weightx = 0.5;
        c.gridy = 1;
        c.gridx = 0;
        c.insets.left = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnFields.add(btnSubmit, c);

        c.gridy = 2;
        pnFields.add(btnDeleteClass, c);

        add(pnNorth, BorderLayout.NORTH);
        add(pnFields,BorderLayout.CENTER);
    }

    public void populateFields(Classroom classroom) {
        txtClassName.setText(classroom.getClassName());
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }

    /**
     * To be used when submitting changes to get the class object 
     * from the teacher's list to update the DB with.
     * @return
     */
    public int getClassIndex() {
        return classIndex;
    }

    public void setDeleteClassListener(ActionListener deleteClassListener){
        btnDeleteClass.addActionListener(deleteClassListener);
    }
}