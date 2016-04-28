package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.custom.TitleLabel;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.model.Classroom;
import com.elementaryengineers.fwc.model.Student;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author olgasheehan
 */
public class ClassRoster extends JPanel {

    private JPanel pnNorth, pnCenter, pnTools, pnSearch, pnButtons;
    private TitleLabel lblTitle;
    private JLabel lblSearch, lblName;
    private JTextField txtSearch;
    private ImageButton btnBack, btnProfile, btnHistory;
    private DefaultTableModel tableModel;
    private JTable studentsTable;
    private JScrollPane tableScroll;
    private Classroom classroom;
    private int classIndex;

    public ClassRoster() {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("Class Roster",
                FWCConfigurator.CLASS_ROSTER_IMG);
        pnNorth.add(lblTitle);

        // Build center panel
        pnCenter = new JPanel(new BorderLayout());
        pnCenter.setBackground(Color.WHITE);

        // Build buttons and search
        pnTools = new JPanel(new BorderLayout());
        pnTools.setBackground(Color.WHITE);

        pnSearch = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnSearch.setBackground(Color.WHITE);

        lblSearch = new JLabel("Search: ");
        lblSearch.setFont(new Font("Calibri", Font.PLAIN, 18));
        txtSearch = new JTextField(24);

        // Add listener to show search results as soon as user starts typing
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                search();
            }
            public void removeUpdate(DocumentEvent e) {
                search();
            }
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            public void search() {
                String keyword = txtSearch.getText();

                // Populate with search results, or all students if empty
                populateTable(keyword.equals("") ?
                        classroom.searchStudents(keyword):
                        classroom.getStudents());
            }
        });

        pnSearch.add(lblSearch);
        pnSearch.add(txtSearch);

        pnButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnButtons.setBackground(Color.WHITE);

        // Create buttons
        btnBack = new ImageButton("Back",
                FWCConfigurator.BACK_IMG, 150, 50);
        btnProfile = new ImageButton("Profile",
                FWCConfigurator.PROFILE_IMG, 150, 50);
        btnHistory = new ImageButton("Worksheet History",
                FWCConfigurator.ROSTER_IMG, 150, 50);

        pnButtons.add(btnBack);
        pnButtons.add(btnProfile);
        pnButtons.add(btnHistory);

        // Create class name label
        lblName = new JLabel("", SwingConstants.CENTER);

        pnTools.add(pnButtons, BorderLayout.WEST);
        pnTools.add(lblName, BorderLayout.CENTER);
        pnTools.add(pnSearch, BorderLayout.EAST);

        // Build table of classes
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"First Name",
                "Last Name", "Difficulty Level"});

        studentsTable = new JTable(tableModel);
        studentsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        studentsTable.setFillsViewportHeight(true);
        studentsTable.getTableHeader().setFont(new Font("Calibri",
                Font.PLAIN, 18));
        populateTable(classroom.getStudents());

        tableScroll = new JScrollPane(studentsTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        pnCenter.add(pnTools, BorderLayout.NORTH);
        pnCenter.add(tableScroll, BorderLayout.CENTER);

        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
    }

    private void populateTable(ArrayList<Student> students) {
        // Remove all rows first
        for (int i = 0, len = tableModel.getRowCount(); i < len; i++) {
            tableModel.removeRow(i);
        }

        for (Student student : students) {
            tableModel.addRow(new String[]{student.getFirstName(),
                    student.getLastName(),
                    FWCConfigurator.getDifficulties().
                            get(student.getDifficultyID()).getDescription()});
        }
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
        this.classroom = FWCConfigurator.getTeacher().getClasses()
                .get(classIndex);
        lblName.setText("Class: " + classroom.getClassName());
        populateTable(classroom.getStudents());
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getSelectedStudent() {
        return studentsTable.getSelectedRow();
    }

    public void setBackListener(ActionListener backListener) {
        btnBack.addActionListener(backListener);
    }

    public void setProfileListener(ActionListener profileListener) {
        btnProfile.addActionListener(profileListener);
    }

    public void setHistoryListener(ActionListener historyListener) {
        btnHistory.addActionListener(historyListener);
    }
}