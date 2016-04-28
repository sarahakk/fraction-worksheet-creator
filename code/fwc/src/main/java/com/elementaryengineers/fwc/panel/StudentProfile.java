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

/**
 *
 * @author olgasheehan
 */
public class StudentProfile extends JPanel {

    private JPanel pnNorth, pnFieldsLeft, pnFieldsRight, pnSouth;
    private TitleLabel lblTitle;
    private JLabel lblFirst, lblLast, lblUser, lblClass, lblDifficulty;
    private JTextField txtFirst, txtLast, txtUser;
    private JComboBox cbClassName, cbDifficulty;
    private ImageButton btnSubmit, btnDelete, btnReset, btnHistory;
    private int classIndex, studentIndex;

    public StudentProfile() {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("Student Profile",
                FWCConfigurator.STUDENT_PROFILE_IMG);
        pnNorth.add(lblTitle);

        // Build west panel and form
        lblFirst = new JLabel("First name:", SwingConstants.RIGHT);
        lblFirst.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblLast = new JLabel("Last name:", SwingConstants.RIGHT);
        lblLast.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblUser = new JLabel("Username:", SwingConstants.RIGHT);
        lblUser.setFont(new Font("Calibri", Font.PLAIN, 18));
        txtFirst = new JTextField(24);
        txtLast = new JTextField(24);
        txtUser = new JTextField(24);

        // Use GridBagLayout
        pnFieldsLeft = new JPanel(new GridBagLayout());
        pnFieldsLeft.setBackground(Color.WHITE);
        pnFieldsLeft.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 0));
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
        pnFieldsRight.setBorder(BorderFactory.createEmptyBorder(0, 60, 10, 30));
        GridBagConstraints cRight = new GridBagConstraints();
        cRight.ipady = 5;

        // Class
        cRight.anchor = GridBagConstraints.EAST;
        pnFieldsLeft.add(lblClass, cRight);

        cRight.gridx = 1;
        cRight.insets = new Insets(0, 10, 0, 0);
        cRight.anchor = GridBagConstraints.CENTER;
        cRight.weightx = 1;
        cRight.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsLeft.add(cbClassName, cRight);

        // Difficulty
        cRight.gridy = 1;
        cRight.gridx = 0;
        cRight.insets.left = 0;
        cRight.anchor = GridBagConstraints.EAST;
        cRight.weightx = 0;
        cRight.fill = GridBagConstraints.NONE;
        pnFieldsLeft.add(lblDifficulty, cRight);

        cRight.gridx = 1;
        cRight.insets.left = 10;
        cRight.anchor = GridBagConstraints.CENTER;
        cRight.weightx = 1;
        cRight.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsLeft.add(cbDifficulty, cRight);

        // Build buttons and south panel
        pnSouth = new JPanel(new GridBagLayout());
        pnSouth.setBackground(Color.WHITE);

        btnSubmit = new ImageButton("Submit Changes", FWCConfigurator
                .SUBMIT_IMG,
                150, 50);
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newFirst = txtFirst.getText(),
                        newLast = txtLast.getText(),
                        newUser = txtUser.getText();

                if (newFirst.equals("") || newLast.equals("") || newUser.equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter all required information.",
                            "Student Update Failed",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if username is available
                if (!FWCConfigurator.getDbConn().isUsernameAvailable(newUser)) {
                    JOptionPane.showMessageDialog(null, "Username is not available.",
                            "Student Update Failed",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Update student with information from profile
                Classroom classroom = FWCConfigurator.getTeacher().
                        getClasses().get(classIndex);
                Student modifiedStudent = classroom.getStudents().
                        get(studentIndex);
                modifiedStudent.setFirstName(newFirst);
                modifiedStudent.setLastName(newLast);
                modifiedStudent.setUsername(newUser);
                modifiedStudent.setClassroom(classroom);
                modifiedStudent.setDifficultyID(
                        cbDifficulty.getSelectedIndex());

                // Check database update status
                if (FWCConfigurator.getDbConn().
                        updateStudent(modifiedStudent)) {
                    JOptionPane.showMessageDialog(null,
                            "Student was successfully updated.",
                            "Student Update Successful",
                            JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "Student could not be updated in the database.",
                            "Student Update Failed",
                            JOptionPane.ERROR_MESSAGE);
                }

                // Should update list of classes of teacher
                FWCConfigurator.getTeacher().getClasses();
            }
        });

        btnReset = new ImageButton("Reset Password", FWCConfigurator
                .RESET_PASSW_IMG, 150, 50);
        btnReset.addActionListener(new ActionListener() { // TODO
            @Override
            public void actionPerformed(ActionEvent e) {
                Teacher teacher = FWCConfigurator.getAdmin().
                        getTeachers().get(studentIndex);
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

        btnHistory = new ImageButton("View Worksheet History", FWCConfigurator
                .WS_HISTORY_IMG, 150,
                50);
        btnDelete = new ImageButton("Delete Student", FWCConfigurator
                .DEL_STUDENT_IMG, 150, 50);

        GridBagConstraints c = new GridBagConstraints();
        c.ipady = 5;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnSouth.add(btnSubmit, c);

        c.gridy = 1;
        pnSouth.add(btnHistory, c);

        c.gridy = 2;
        pnSouth.add(btnReset, c);

        c.gridy = 3;
        pnSouth.add(btnDelete, c);

        add(pnNorth, BorderLayout.NORTH);
        add(pnFieldsLeft, BorderLayout.WEST);
        add(pnFieldsRight, BorderLayout.EAST);
        add(pnSouth, BorderLayout.SOUTH);
    }

    public void populateFields(Student student) {
        txtFirst.setText(student.getFirstName());
        txtLast.setText(student.getLastName());
        txtUser.setText(student.getUsername());
        cbClassName.setSelectedIndex(classIndex);
        cbDifficulty.setSelectedIndex(student.getDifficultyID());
    }

    /**
     * Class index used in teacher's classes arraylist, and student index
     * used in that class' students arraylist.
     * @param classIndex
     * @param studentIndex
     */
    public void setStudentIndexes(int classIndex, int studentIndex) {
        this.classIndex = classIndex;
        this.studentIndex = studentIndex;
    }

    /**
     * To be used when submitting changes to get the student object from the
     * class' list to update the DB with.
     * @return
     */
    public int getClassIndex() {
        return classIndex;
    }

    /**
     * To be used when submitting changes to get the student object from the
     * class' list to update the DB with.
     * @return
     */
    public int getStudentIndex() {
        return studentIndex;
    }

    public void setHistoryListener(ActionListener historyListener) {
        btnHistory.addActionListener(historyListener);
    }

    public void setDeleteListener(ActionListener deleteListener) {
        btnDelete.addActionListener(deleteListener);
    }
}