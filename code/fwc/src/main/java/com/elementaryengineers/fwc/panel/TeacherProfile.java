package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.custom.TitleLabel;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.model.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sarahakk on 4/25/16.
 */
public class TeacherProfile extends JPanel {

    private JPanel pnNorth, pnFields;
    private TitleLabel lblTitle;
    private JLabel lblFirst, lblLast, lblUser;
    private JTextField txtFirst, txtLast, txtUser;
    private ImageButton btnSubmit, btnDelete, btnReset;
    private int teacherIndex;

    public TeacherProfile() {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("Teacher Profile", FWCConfigurator.TEACHER_PROFILE_IMG);
        pnNorth.add(lblTitle);

        // Build fields and center panel
        lblFirst = new JLabel("Fist name:", SwingConstants.RIGHT);
        lblFirst.setFont(new Font("Calibri", Font.BOLD, 18));
        lblLast = new JLabel("Last name:", SwingConstants.RIGHT);
        lblLast.setFont(new Font("Calibri", Font.BOLD, 18));
        lblUser = new JLabel("Username:", SwingConstants.RIGHT);
        lblUser.setFont(new Font("Calibri", Font.BOLD, 18));
        txtFirst = new JTextField(24);
        txtLast = new JTextField(24);
        txtUser = new JTextField(24);
        txtUser.setEnabled(false);

        // Use GridBagLayout
        pnFields = new JPanel(new GridBagLayout());
        pnFields.setBackground(Color.WHITE);
        pnFields.setBorder(BorderFactory.createEmptyBorder(10, 150, 20, 150));
        GridBagConstraints c = new GridBagConstraints();
        c.ipady = 5;

        // First name
        c.anchor = GridBagConstraints.EAST;
        pnFields.add(lblFirst, c);

        c.gridx = 1;
        c.insets = new Insets(0, 10, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnFields.add(txtFirst, c);

        // Last name
        c.gridy = 1;
        c.gridx = 0;
        c.insets.left = 0;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        pnFields.add(lblLast, c);

        c.gridx = 1;
        c.insets.left = 10;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnFields.add(txtLast, c);

        // Username
        c.gridy = 2;
        c.gridx = 0;
        c.insets.left = 0;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        pnFields.add(lblUser, c);

        c.gridx = 1;
        c.insets.left = 10;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnFields.add(txtUser, c);

        // Build buttons
        btnSubmit = new ImageButton("Submit Changes", FWCConfigurator
                .SUBMIT_IMG, 150, 50);
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newFirst = txtFirst.getText(),
                        newLast = txtLast.getText();

                if (newFirst.equals("") || newLast.equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter all required information.",
                            "Teacher Update Failed",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Update teacher with information from profile
                Teacher modifiedTeacher = FWCConfigurator.getAdmin().
                        getTeachers().get(teacherIndex);
                modifiedTeacher.setFirstName(newFirst);
                modifiedTeacher.setLastName(newLast);

                // Check database update status
                if (FWCConfigurator.getDbConn().updateTeacher(modifiedTeacher)) {
                    JOptionPane.showMessageDialog(null,
                            "Teacher was successfully updated.",
                            "Teacher Update Successful",
                            JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "Teacher could not be updated in the database.",
                            "Teacher Update Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnReset = new ImageButton("Reset Password", FWCConfigurator
                .RESET_PASSW_IMG, 150, 50);
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Teacher teacher = FWCConfigurator.getAdmin().
                        getTeachers().get(teacherIndex);
                String newPassword = teacher.setRandomPassword();

                // Check database update status
                if (FWCConfigurator.getDbConn().updateTeacher(teacher)) {
                    JOptionPane.showMessageDialog(null,
                            "Teacher was successfully updated.",
                            "Teacher Update Successful",
                            JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "Teacher could not be updated in the database.",
                            "Teacher Update Failed",
                            JOptionPane.ERROR_MESSAGE);
                }

                // Popup with new password
                JOptionPane.showMessageDialog(null, teacher.getUsername() +
                                "'s password has been successfully " +
                                "reset to:\n" + newPassword,
                        "Password Reset Successful",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnDelete = new ImageButton("Delete Teacher", FWCConfigurator
                .DEL_TEACHER_IMG,
                150, 50);

        c.gridwidth = 2;
        c.weightx = 0.5;
        c.gridy = 3;
        c.gridx = 0;
        c.insets.left = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnFields.add(btnSubmit, c);

        c.gridy = 4;
        pnFields.add(btnReset, c);

        c.gridy = 5;
        pnFields.add(btnDelete, c);

        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnFields, BorderLayout.CENTER);
    }

    public void populateFields(Teacher teacher) {
        txtFirst.setText(teacher.getFirstName());
        txtLast.setText(teacher.getLastName());
        txtUser.setText(teacher.getUsername());
    }

    public void setTeacherIndex(int teacherIndex) {
        this.teacherIndex = teacherIndex;
    }

    /**
     * To be used when submitting changes to get the teacher object from the admin's list to update the DB with.
     * @return
     */
    public int getTeacherIndex() {
        return teacherIndex;
    }

    public void setDeleteListener(ActionListener deleteListener) {
        btnDelete.addActionListener(deleteListener);
    }
}