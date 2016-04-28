package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.custom.TitleLabel;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.model.Classroom;
import com.elementaryengineers.fwc.model.Student;
import com.elementaryengineers.fwc.model.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author olgasheehan
 */
public class StudentRegistration extends JPanel{

    private JPanel pnNorth, pnFieldsLeft, pnFieldsRight, pnSouth;
    private TitleLabel lblTitle;
    private JLabel lblFirst, lblLast, lblUser, lblPass, lblConfirm, lblClass, lblDifficulty;
    private JComboBox cbClassName, cbDifficulty;
    private JTextField txtFirst, txtLast, txtUser;
    private JPasswordField txtPass, txtConfirm;
    private ImageButton btnSubmit;

    public StudentRegistration() {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("Student Registration",
        FWCConfigurator.STUDENT_REG_IMG);
        pnNorth.add(lblTitle);

        // Build west panel and form
        lblFirst = new JLabel("First name:", SwingConstants.RIGHT);
        lblFirst.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblLast = new JLabel("Last name:", SwingConstants.RIGHT);
        lblLast.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblUser = new JLabel("Username:", SwingConstants.RIGHT);
        lblUser.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblPass = new JLabel("Password:", SwingConstants.RIGHT);
        lblPass.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblConfirm = new JLabel("Confirm password:", SwingConstants.RIGHT);
        lblConfirm.setFont(new Font("Calibri", Font.PLAIN, 18));
        txtFirst = new JTextField(24);
        txtLast = new JTextField(24);
        txtUser = new JTextField(24);
        txtPass = new JPasswordField(24);
        txtConfirm = new JPasswordField(24);

        // Use GridBagLayout
        pnFieldsLeft = new JPanel(new GridBagLayout());
        pnFieldsLeft.setBackground(Color.WHITE);
        pnFieldsLeft.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 0));
        GridBagConstraints cLeft = new GridBagConstraints();
        cLeft.ipady = 5;

        // First name
        cLeft.anchor = GridBagConstraints.EAST;
        pnFieldsLeft.add(lblFirst, cLeft);

        cLeft.gridx = 1;
        cLeft.insets = new Insets(0, 10, 0, 0);
        cLeft.anchor = GridBagConstraints.CENTER;
        cLeft.weightx = 1;
        cLeft.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsLeft.add(txtFirst, cLeft);

        // Last name
        cLeft.gridy = 1;
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

        // Username
        cLeft.gridy = 2;
        cLeft.gridx = 0;
        cLeft.insets.left = 0;
        cLeft.anchor = GridBagConstraints.EAST;
        cLeft.weightx = 0;
        cLeft.fill = GridBagConstraints.NONE;
        pnFieldsLeft.add(lblUser, cLeft);

        cLeft.gridx = 1;
        cLeft.insets.left = 10;
        cLeft.anchor = GridBagConstraints.CENTER;
        cLeft.weightx = 1;
        cLeft.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsLeft.add(txtUser, cLeft);

        // Password
        cLeft.gridy = 3;
        cLeft.gridx = 0;
        cLeft.insets.left = 0;
        cLeft.anchor = GridBagConstraints.EAST;
        cLeft.weightx = 0;
        cLeft.fill = GridBagConstraints.NONE;
        pnFieldsLeft.add(lblPass, cLeft);

        cLeft.gridx = 1;
        cLeft.insets.left = 10;
        cLeft.anchor = GridBagConstraints.CENTER;
        cLeft.weightx = 1;
        cLeft.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsLeft.add(txtPass, cLeft);

        // Confirm password
        cLeft.gridy = 4;
        cLeft.gridx = 0;
        cLeft.insets.left = 0;
        cLeft.anchor = GridBagConstraints.EAST;
        cLeft.weightx = 0;
        cLeft.fill = GridBagConstraints.NONE;
        pnFieldsLeft.add(lblConfirm, cLeft);

        cLeft.gridx = 1;
        cLeft.insets.left = 10;
        cLeft.anchor = GridBagConstraints.CENTER;
        cLeft.weightx = 1;
        cLeft.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsLeft.add(txtConfirm, cLeft);

        // Build east panel and form: Class Name and Difficulty
        lblClass = new JLabel("Class:", SwingConstants.RIGHT);
        lblClass.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblDifficulty = new JLabel("Difficulty:", SwingConstants.RIGHT);
        lblDifficulty.setFont(new Font("Calibri", Font.PLAIN, 18));
        ArrayList<String> classNames = new ArrayList<>(),
        difficulties = new ArrayList<>();

        FWCConfigurator.getTeacher().getClasses().stream()
                .forEach(classroom -> classNames.add(classroom.getClassName()));

        FWCConfigurator.getDifficulties().stream()
                .forEach(difficulty -> difficulties.add(difficulty.getDescription()));

        cbClassName = new JComboBox(classNames.toArray());
        cbClassName.setFont(new Font("Calibri", Font.PLAIN, 18));

        cbDifficulty = new JComboBox(difficulties.toArray());
        cbDifficulty.setFont(new Font("Calibri", Font.PLAIN, 18));

        // Use GridBagLayout
        pnFieldsRight = new JPanel(new GridBagLayout());
        pnFieldsRight.setBackground(Color.WHITE);
        pnFieldsRight.setBorder(BorderFactory.createEmptyBorder(0, 60, 10,
                150));
        GridBagConstraints cRight = new GridBagConstraints();
        cRight.ipady = 5;

        // Class
        cRight.anchor = GridBagConstraints.EAST;
        pnFieldsRight.add(lblClass, cRight);

        cRight.gridx = 1;
        cRight.insets = new Insets(0, 10, 0, 0);
        cRight.anchor = GridBagConstraints.CENTER;
        cRight.weightx = 1;
        cRight.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsRight.add(cbClassName, cRight);

        // Difficulty
        cRight.gridy = 1;
        cRight.gridx = 0;
        cRight.insets.left = 0;
        cRight.anchor = GridBagConstraints.EAST;
        cRight.weightx = 0;
        cRight.fill = GridBagConstraints.NONE;
        pnFieldsRight.add(lblDifficulty, cRight);

        cRight.gridx = 1;
        cRight.insets.left = 10;
        cRight.anchor = GridBagConstraints.CENTER;
        cRight.weightx = 1;
        cRight.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsRight.add(cbDifficulty, cRight);

        // Build south panel and submit button
        pnSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnSouth.setBackground(Color.WHITE);
        pnSouth.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        btnSubmit = new ImageButton("Submit", FWCConfigurator.SUBMIT_IMG, 150, 50);
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verifyRegistration()) {
                    Student newStudent = getNewStudent();

                    // If adding new student to database works
                    if (FWCConfigurator.getDbConn().createStudent(newStudent)) {
                        // Add student to teacher's list of students
                        FWCConfigurator.getTeacher().getClasses();

                        // Clear fields in student registration form
                        clearFields();

                        JOptionPane.showMessageDialog(null,
                                "Student was successfully registered.",
                                "Student Registration Successful",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,
                                "Student could not be registered "
                                        + "in the database. Please try again.",
                                "Student Registration Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        pnSouth.add(btnSubmit);

        add(pnNorth, BorderLayout.NORTH);
        add(pnFieldsLeft, BorderLayout.WEST);
        add(pnFieldsRight, BorderLayout.EAST);
        add(pnSouth, BorderLayout.SOUTH);
    }

    private boolean verifyRegistration() {
        if (txtFirst.getText().equals("") || txtLast.getText().equals("") ||
                txtUser.getText().equals("") || String.valueOf(txtPass.getPassword()).equals("") ||
                String.valueOf(txtConfirm.getPassword()).equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter all required information.", "Registration Failed",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Check if username is available
        if (!FWCConfigurator.getDbConn().isUsernameAvailable(txtUser.getText())) {
            JOptionPane.showMessageDialog(null, "Username is not available.", "Registration Failed",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Check if passwords match
        if (!String.valueOf(txtPass.getPassword()).equals(
                String.valueOf(txtConfirm.getPassword()))) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.",
                    "Registration Failed",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private Student getNewStudent() {
        Classroom classroom = FWCConfigurator.getTeacher().getClasses().get(cbClassName
                .getSelectedIndex());

        return new Student(txtUser.getText(), txtFirst.getText(), txtLast.getText(),
                String.valueOf(txtPass.getPassword()), cbDifficulty.getSelectedIndex(),
                classroom.getClassID(), classroom.getClassName());
    }

    public void clearFields() {
        txtFirst.setText("");
        txtLast.setText("");
        txtUser.setText("");
        txtPass.setText("");
        txtConfirm.setText("");
        cbClassName.setSelectedIndex(0);
        cbDifficulty.setSelectedIndex(0);
    }
}