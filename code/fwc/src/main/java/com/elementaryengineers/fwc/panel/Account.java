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
import com.elementaryengineers.fwc.model.Admin;
import com.elementaryengineers.fwc.model.Student;
import com.elementaryengineers.fwc.model.Teacher;
import com.elementaryengineers.fwc.model.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sarahakk on 4/26/16.
 */
public class Account extends JPanel {

    private JPanel pnNorth, pnFieldsLeft, pnFieldsRight, pnSouth;
    private TitleLabel lblTitle;
    private JLabel lblFirst, lblLast, lblUser, lblCurrent, lblPass, lblConfirm,
            lblDifficulty, lblClass;
    private JTextField txtFirst, txtLast, txtUser, txtDifficulty, txtClass;
    private JPasswordField txtCurrent, txtPass, txtConfirm;
    private ImageButton btnSubmit;
    private UserType userType;

    public Account() {
        super(new BorderLayout());
        setBackground(FWCConfigurator.bgColor);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        userType = FWCConfigurator.getUserType();

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(FWCConfigurator.bgColor);
        lblTitle = new TitleLabel("My Account", FWCConfigurator.MY_ACCOUNT_TITLE_IMG);
        pnNorth.add(lblTitle);

        // Build west panel and form
        lblUser = new JLabel("Username:", SwingConstants.RIGHT);
        lblUser.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblFirst = new JLabel("First name:", SwingConstants.RIGHT);
        lblFirst.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblLast = new JLabel("Last name:", SwingConstants.RIGHT);
        lblLast.setFont(new Font("Calibri", Font.PLAIN, 18));
        txtUser = new JTextField(24);
        txtUser.setEnabled(false);
        txtFirst = new JTextField(24);
        txtLast = new JTextField(24);

        // Student-specific account info
        if (userType == UserType.STUDENT) {
            lblDifficulty = new JLabel("Difficulty:", SwingConstants.RIGHT);
            lblDifficulty.setFont(new Font("Calibri", Font.PLAIN, 18));
            lblClass = new JLabel("Class:", SwingConstants.RIGHT);
            lblClass.setFont(new Font("Calibri", Font.PLAIN, 18));
            txtDifficulty = new JTextField(24);
            txtDifficulty.setEnabled(false);
            txtClass = new JTextField(24);
            txtClass.setEnabled(false);
        }

        // Use GridBagLayout
        pnFieldsLeft = new JPanel(new GridBagLayout());
        pnFieldsLeft.setBackground(FWCConfigurator.bgColor);
        pnFieldsLeft.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 0));
        pnFieldsLeft.setPreferredSize(new Dimension(400, 200));
        GridBagConstraints cLeft = new GridBagConstraints();
        cLeft.ipady = 5;

        // Username
        cLeft.anchor = GridBagConstraints.EAST;
        pnFieldsLeft.add(lblUser, cLeft);

        cLeft.gridx = 1;
        cLeft.insets = new Insets(0, 10, 0, 0);
        cLeft.anchor = GridBagConstraints.CENTER;
        cLeft.weightx = 1;
        cLeft.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsLeft.add(txtUser, cLeft);

        // First name
        cLeft.gridy = 1;
        cLeft.gridx = 0;
        cLeft.insets.left = 0;
        cLeft.anchor = GridBagConstraints.EAST;
        cLeft.weightx = 0;
        cLeft.fill = GridBagConstraints.NONE;
        pnFieldsLeft.add(lblFirst, cLeft);

        cLeft.gridx = 1;
        cLeft.insets.left = 10;
        cLeft.anchor = GridBagConstraints.CENTER;
        cLeft.weightx = 1;
        cLeft.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsLeft.add(txtFirst, cLeft);

        // Last name
        cLeft.gridy = 2;
        cLeft.gridx = 0;
        cLeft.insets.left = 0;
        cLeft.anchor = GridBagConstraints.EAST;
        cLeft.weightx = 0;
        cLeft.fill = GridBagConstraints.NONE;
        pnFieldsLeft.add(lblLast, cLeft);

        cLeft.gridx = 1;
        cLeft.insets.left = 10;
        cLeft.anchor = GridBagConstraints.CENTER;
        cLeft.weightx = 1;
        cLeft.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsLeft.add(txtLast, cLeft);

        // Student-specific account info
        if (userType == UserType.STUDENT) {
            // First name
            cLeft.gridy = 3;
            cLeft.gridx = 0;
            cLeft.insets.left = 0;
            cLeft.anchor = GridBagConstraints.EAST;
            cLeft.weightx = 0;
            cLeft.fill = GridBagConstraints.NONE;
            pnFieldsLeft.add(lblDifficulty, cLeft);

            cLeft.gridx = 1;
            cLeft.insets.left = 10;
            cLeft.anchor = GridBagConstraints.CENTER;
            cLeft.weightx = 1;
            cLeft.fill = GridBagConstraints.HORIZONTAL;
            pnFieldsLeft.add(txtDifficulty, cLeft);

            // Last name
            cLeft.gridy = 4;
            cLeft.gridx = 0;
            cLeft.insets.left = 0;
            cLeft.anchor = GridBagConstraints.EAST;
            cLeft.weightx = 0;
            cLeft.fill = GridBagConstraints.NONE;
            pnFieldsLeft.add(lblClass, cLeft);

            cLeft.gridx = 1;
            cLeft.insets.left = 10;
            cLeft.anchor = GridBagConstraints.CENTER;
            cLeft.weightx = 1;
            cLeft.fill = GridBagConstraints.HORIZONTAL;
            pnFieldsLeft.add(txtClass, cLeft);
        }

        // Build east panel and form
        lblCurrent = new JLabel("Current password:", SwingConstants.RIGHT);
        lblCurrent.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblPass = new JLabel("Password:", SwingConstants.RIGHT);
        lblPass.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblConfirm = new JLabel("Confirm password:", SwingConstants.RIGHT);
        lblConfirm.setFont(new Font("Calibri", Font.PLAIN, 18));
        txtCurrent = new JPasswordField(24);
        txtPass = new JPasswordField(24);
        txtConfirm = new JPasswordField(24);

        // Use GridBagLayout
        pnFieldsRight = new JPanel(new GridBagLayout());
        pnFieldsRight.setBackground(FWCConfigurator.bgColor);
        pnFieldsRight.setBorder(BorderFactory.createEmptyBorder(0, 60, 10, 30));
        pnFieldsRight.setPreferredSize(new Dimension(500, 200));
        GridBagConstraints cRight = new GridBagConstraints();
        cRight.ipady = 5;

        // Current password
        cRight.gridwidth = 1;
        cRight.anchor = GridBagConstraints.EAST;
        cRight.weightx = 0;
        pnFieldsRight.add(lblCurrent, cRight);

        cRight.gridx = 1;
        cRight.insets = new Insets(0, 10, 0, 0);
        cRight.anchor = GridBagConstraints.CENTER;
        cRight.weightx = 1;
        cRight.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsRight.add(txtCurrent, cRight);

        // New password
        cRight.gridy = 1;
        cRight.gridx = 0;
        cRight.insets.left = 0;
        cRight.anchor = GridBagConstraints.EAST;
        cRight.weightx = 0;
        cRight.fill = GridBagConstraints.NONE;
        pnFieldsRight.add(lblPass, cRight);

        cRight.gridx = 1;
        cRight.insets.left = 10;
        cRight.anchor = GridBagConstraints.CENTER;
        cRight.weightx = 1;
        cRight.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsRight.add(txtPass, cRight);

        // Confirm password
        cRight.gridy = 2;
        cRight.gridx = 0;
        cRight.insets.left = 0;
        cRight.anchor = GridBagConstraints.EAST;
        cRight.weightx = 0;
        cRight.fill = GridBagConstraints.NONE;
        pnFieldsRight.add(lblConfirm, cRight);

        cRight.gridx = 1;
        cRight.insets.left = 10;
        cRight.anchor = GridBagConstraints.CENTER;
        cRight.weightx = 1;
        cRight.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsRight.add(txtConfirm, cRight);

        // Build south panel and submit button
        pnSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnSouth.setBackground(FWCConfigurator.bgColor);
        btnSubmit = new ImageButton("Submit", FWCConfigurator.SUBMIT_IMG, 150, 50);
        btnSubmit.addActionListener(new SubmitListener());
        pnSouth.add(btnSubmit);

        add(pnNorth, BorderLayout.NORTH);
        add(pnFieldsLeft, BorderLayout.WEST);
        add(pnFieldsRight, BorderLayout.EAST);
        add(pnSouth, BorderLayout.SOUTH);

        populateFields();
    }

    private void populateFields() {
        switch (userType) {
            case TEACHER: {
                Teacher teacher = FWCConfigurator.getTeacher();
                txtUser.setText(teacher.getUsername());
                txtFirst.setText(teacher.getFirstName());
                txtLast.setText(teacher.getLastName());
                break;
            }

            case STUDENT: {
                Student student = FWCConfigurator.getStudent();
                txtUser.setText(student.getUsername());
                txtFirst.setText(student.getFirstName());
                txtLast.setText(student.getLastName());
                txtDifficulty.setText(FWCConfigurator.getDifficulties()
                        .get(student.getDifficultyID()).getDescription());
                txtClass.setText(student.getClassName());
                break;
            }

            case ADMIN: {
                Admin admin = FWCConfigurator.getAdmin();
                txtUser.setText(admin.getUsername());
                txtFirst.setText(admin.getFirstName());
                txtLast.setText(admin.getLastName());
                break;
            }
        }
    }

    private class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String current = String.valueOf(txtCurrent.getPassword()),
                    password = String.valueOf(txtPass.getPassword()),
                    confirm = String.valueOf(txtConfirm.getPassword()),
                    first = txtFirst.getText(),
                    last = txtLast.getText();

            // Check if first or last name is empty
            if (first.equals("") || last.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter all required information.", "Update Failed",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            switch (userType) {
                case TEACHER: {
                    Teacher teacher = FWCConfigurator.getTeacher();

                    // Reset password if not empty
                    if (!password.equals("")) {
                        // Check if current password is correct
                        if (!teacher.verifyLogin(current)) {
                            JOptionPane.showMessageDialog(null, "Current password is invalid.", "Update Failed",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Check if passwords match
                        if (!password.equals(confirm)) {
                            JOptionPane.showMessageDialog(null, "New passwords do not match.", "Update Failed",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Update password
                        teacher.resetPassword(password);
                    }

                    teacher.setFirstName(first);
                    teacher.setLastName(last);

                    if (!FWCConfigurator.getDbConn().updateTeacher(teacher)) {
                        JOptionPane.showMessageDialog(null, "Could not update your account in the database.",
                                "Update Failed", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Your account information has been updated.",
                                "Update Successful", JOptionPane.INFORMATION_MESSAGE);
                    }

                    break;
                }

                case STUDENT: {
                    Student student = FWCConfigurator.getStudent();

                    // Reset password if not empty
                    if (!password.equals("")) {
                        // Check if current password is correct
                        if (!student.verifyLogin(current)) {
                            JOptionPane.showMessageDialog(null, "Current password is invalid.", "Update Failed",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Check if passwords match
                        if (!password.equals(confirm)) {
                            JOptionPane.showMessageDialog(null, "New passwords do not match.", "Update Failed",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Update password
                        student.resetPassword(password);
                    }

                    student.setFirstName(first);
                    student.setLastName(last);

                    if (!FWCConfigurator.getDbConn().updateStudent(student)) {
                        JOptionPane.showMessageDialog(null, "Could not update your account in the database.",
                                "Update Failed", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Your account information has been updated.",
                                "Update Successful", JOptionPane.INFORMATION_MESSAGE);
                    }

                    break;
                }

                case ADMIN: {
                    Admin admin = FWCConfigurator.getAdmin();

                    // Reset password if not empty
                    if (!password.equals("")) {
                        // Check if current password is correct
                        if (!admin.verifyLogin(current)) {
                            JOptionPane.showMessageDialog(null, "Current password is invalid.", "Update Failed",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Check if passwords match
                        if (!password.equals(confirm)) {
                            JOptionPane.showMessageDialog(null, "New passwords do not match.", "Update Failed",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Update password
                        admin.resetPassword(password);
                    }

                    admin.setFirstName(first);
                    admin.setLastName(last);

                    if (!FWCConfigurator.getDbConn().updateAdmin(admin)) {
                        JOptionPane.showMessageDialog(null, "Could not update your account in the database.",
                                "Update Failed", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Your account information has been updated.",
                                "Update Successful", JOptionPane.INFORMATION_MESSAGE);
                    }

                    break;
                }
            }

            // Empty password fields
            txtCurrent.setText("");
            txtPass.setText("");
            txtConfirm.setText("");
        }
    }
}