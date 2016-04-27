package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.custom.TitleLabel;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.model.Teacher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by sarahakk on 4/25/16.
 */
public class AdminManagePasswords extends JPanel {

    private JPanel pnNorth, pnCenter, pnButtons;
    private TitleLabel lblTitle;
    private ImageButton btnReset, btnResetAll;
    private DefaultTableModel tableModel;
    private JTable teachersTable;
    private JScrollPane tableScroll;
    private ArrayList<Teacher> teachers;

    public AdminManagePasswords() {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("Reset Passwords", FWCConfigurator.RESET_PASSW_TITLE_IMG);
        pnNorth.add(lblTitle);

        // Build center panel
        pnCenter = new JPanel(new BorderLayout());
        pnCenter.setBackground(Color.WHITE);

        // Build table of teachers
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Username"});

        teachersTable = new JTable(tableModel);
        teachersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        teachersTable.setFillsViewportHeight(true);
        teachersTable.getTableHeader().setFont(new Font("Calibri", Font.PLAIN, 18));

        // Populate the table only with teachers that need a password reset
        teachers = FWCConfigurator.getAdmin().getTeachersRequestedReset();
        populateTable();

        tableScroll = new JScrollPane(teachersTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        pnCenter.add(tableScroll, BorderLayout.CENTER);

        // Build south panel
        pnButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnButtons.setBackground(Color.WHITE);
        btnReset = new ImageButton("Reset Selected", FWCConfigurator.RESET_SELECTED_IMG, 150, 50);
        btnReset.addActionListener(new ResetListener());
        btnResetAll = new ImageButton("Reset All", FWCConfigurator.RESET_ALL_IMG, 150, 50);
        btnResetAll.addActionListener(new ResetListener());
        pnButtons.add(btnReset);
        pnButtons.add(btnResetAll);

        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnButtons, BorderLayout.SOUTH);
    }

    private void populateTable() {
        // Remove all rows first
        for (int i = 0, len = tableModel.getRowCount(); i < len; i++) {
            tableModel.removeRow(i);
        }

        for (Teacher teacher : teachers) {
            tableModel.addRow(new String[]{teacher.getUsername()});
        }
    }

    private class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnReset) { // If reset selected teacher's password
                int index = teachersTable.getSelectedRow();

                // Check if selected a teacher
                if (index > 0) {
                    Teacher teacher = teachers.get(index);
                    String newPassword = teacher.setRandomPassword();

                    // Check database update status
                    if (FWCConfigurator.getDbConn().updateTeacher(teacher)) {
                        // Popup with new password
                        JOptionPane.showMessageDialog(null, teacher.getUsername() + "'s password has been successfully " +
                                "reset to:\n" + newPassword, "Password Reset Successful", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Teacher could not be updated in the database.",
                                "Teacher Update Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                else { // No teacher is selected from the table
                    JOptionPane.showMessageDialog(null, "Please select a teacher from the table first.",
                            "Reset Password Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if (e.getSource() == btnResetAll) { // If reset all teachers' passwords
                StringBuilder message = new StringBuilder("The following teachers' passwords have been reset.\n");
                message.append("Please keep the new passwords in a safe place.\n\nUsername: Password");
                String newPassword;
                boolean error = false;
                int count = 0;

                for (Teacher teacher : teachers) {
                    newPassword = teacher.setRandomPassword();

                    // Check database update status
                    if (!FWCConfigurator.getDbConn().updateTeacher(teacher)) {
                        JOptionPane.showMessageDialog(null, "Teacher could not be updated in the database.",
                                "Teacher Update Failed",
                                JOptionPane.ERROR_MESSAGE);
                        error = true;
                        break;
                    }
                    else {
                        message.append("\n")
                                .append(teacher.getUsername())
                                .append(": ")
                                .append(newPassword);
                        count++;
                    }
                }

                if (!error || count > 0) {
                    // Popup with new passwords
                    JOptionPane.showMessageDialog(null, message.toString(), "Password Reset Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

            refresh();
        }
    }

    public void refresh() {
        teachers = FWCConfigurator.getAdmin().getTeachersRequestedReset();
        populateTable();
    }
}