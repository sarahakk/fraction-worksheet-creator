
package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.custom.TitleLabel;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.model.Student;
import com.elementaryengineers.fwc.model.Worksheet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author olgasheehan
 */
public class ClassRoster extends JPanel {

    private JPanel pnNorth, pnCenter, pnButtons;
    private TitleLabel lblTitle;
    private ImageButton btnProfile, btnHistory;
    private DefaultTableModel tableModel;
    private JTable rosterTable;
    private JScrollPane tableScroll;
    private ArrayList<Student> students;

    public ClassRoster() {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("Class Roster",FWCConfigurator.CLASS_ROSTER_IMG);
        pnNorth.add(lblTitle);

        // Build center panel
        pnCenter = new JPanel(new BorderLayout());
        pnCenter.setBackground(Color.WHITE);

        // Build table of worksheets
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Student Name", "Username",
            "Difficulty Level"});
        
        tableScroll = new JScrollPane(rosterTable, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        pnCenter.add(tableScroll, BorderLayout.CENTER);
        
        
        // Build south panel
        pnButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnButtons.setBackground(Color.WHITE);
        btnProfile = new ImageButton("Profile", FWCConfigurator.PROFILE_IMG, 150, 50);
        btnProfile.addActionListener(new ProfileListener());
        
        btnHistory = new ImageButton("History", FWCConfigurator.HISTORY_IMG, 150, 50);
        btnHistory.addActionListener(new HistoryListener());
        pnButtons.add(btnProfile);
        pnButtons.add(btnHistory);

        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnButtons, BorderLayout.SOUTH);
        
        
    }
        
        public void populateTable() {
        // Remove all rows first
        for (int i = 0, len = tableModel.getRowCount(); i < len; i++) {
            tableModel.removeRow(i);
        }

        for (Student student : students) {
            tableModel.addRow (new String[]{student.getStudent(),
                    FWCConfigurator.getStudent().get(student.getDifficultyID())
            });
        }
    }

    private class ProfileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = rosterTable.getSelectedRow();

            // Check if selected a student
            if (index > 0) {
                student.get(index).print(false);
            }
            else { // No profile is choosen
                JOptionPane.showMessageDialog(null, 
                    "Please select student's name from the table first.",
                     "Student Profile Generation Error",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class HistoryListener implements ActionListener {
        @Override
      public void actionPerformed(ActionEvent e) {
        int index = rosterTable.getSelectedRow();

            // Check if selected a student
        if (index > 0) {
                // Check database update status
        if (FWCConfigurator.getDbConn().getHistory(students.getStudentID())) {; // Remove from student history
            populateTable(); // Refresh table of students
            JOptionPane.showMessageDialog(null, 
                    "History has been created successfully!",
                    "History Worksheets is created successfully", 
                    JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, 
                    "History could not be generated! "
                    + "Please try again later.",
                   "Creating History Worksheet Failed",
                   JOptionPane.INFORMATION_MESSAGE);
                }
            
        }
    }

    public void refresh() { // Reload table for new student login
        students = FWCConfigurator.getStudent().getHistory();
        populateTable();
    }
}