package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.custom.TitleLabel;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.model.Classroom;
import com.elementaryengineers.fwc.model.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author olgasheehan
 */
public class ClassEdit extends JPanel{
    
    private JPanel pnNorth, pnFields;
    private TitleLabel lblTitle;
    private JLabel lblClassName;
    private JTextField txtClassName;
    private ImageButton btnSubmit, btnDeleteClass;
    private int classIndex;
    // No need for a back button, can simply click Classes in menu to go back

    public ClassEdit() {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("Edit Class", FWCConfigurator.EDIT_CLASS_IMG);
        pnNorth.add(lblTitle);

        // Build center panel
        lblClassName = new JLabel("Class name:", SwingConstants.RIGHT);
        lblClassName.setFont(new Font("Calibri", Font.PLAIN, 18));
        txtClassName = new JTextField(24);

        // Use GridBagLayout
        pnFields = new JPanel(new GridBagLayout());
        pnFields.setBackground(Color.WHITE);
        pnFields.setBorder(BorderFactory.createEmptyBorder(10, 150, 20, 150));
        GridBagConstraints c = new GridBagConstraints();
        c.ipady = 5;

        // Class Name
        c.anchor = GridBagConstraints.EAST;
        pnFields.add(lblClassName, c);

        c.gridx = 1;
        c.insets = new Insets(0, 10, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnFields.add(txtClassName, c);
    
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
                            JOptionPane.PLAIN_MESSAGE);
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
     * To be used when submitting changes to get the class object from the teacher's list to update the DB with.
     * @return
     */
    public int getClassIndex() {
        return classIndex;
    }

    public void setDeleteClassListener(ActionListener deleteClassListener){
        btnSubmit.addActionListener(deleteClassListener);
    }
}