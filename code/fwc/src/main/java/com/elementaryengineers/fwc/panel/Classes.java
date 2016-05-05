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

import com.elementaryengineers.fwc.custom.DisabledTableModel;
import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.custom.TitleLabel;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.model.Classroom;
import com.elementaryengineers.fwc.model.Teacher;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The Classes displays the page which allows teachers to add a class,
 * add a student to an existing class, search classes, modify classes, 
 * and view a class’ roster
 * 
 * @author olgasheehan
 */
public class Classes extends JPanel {

    private JPanel pnNorth, pnCenter, pnSearch, pnButtons;
    private TitleLabel lblTitle;
    private JLabel lblSearch;
    private JTextField txtSearch;
    private ImageButton btnEdit, btnRoster, btnNewClass, btnNewStudent;
    private DisabledTableModel tableModel;
    private JTable classesTable;
    private JScrollPane tableScroll;
    private ArrayList<Classroom> currentList;

    public Classes() {
        super(new BorderLayout());
        setBackground(FWCConfigurator.bgColor);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(FWCConfigurator.bgColor);
        lblTitle = new TitleLabel("All Classes",
                FWCConfigurator.CLASSES_TITLE_IMG);
        pnNorth.add(lblTitle);

        // Build center panel
        pnCenter = new JPanel(new BorderLayout());
        pnCenter.setBackground(FWCConfigurator.bgColor);

        // Build buttons and search
        pnSearch = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnSearch.setBackground(FWCConfigurator.bgColor);
        pnSearch.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));

        lblSearch = new JLabel("Search: ");
        lblSearch.setFont(new Font("Calibri", Font.PLAIN, 18));
        txtSearch = new JTextField(24);
        txtSearch.setColumns(10);

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

                // Populate with search results, or all classes if empty
                currentList = !keyword.equals("") ?
                        FWCConfigurator.getTeacher().searchClasses(keyword) :
                        FWCConfigurator.getTeacher().getClasses();
                populateTable();
            }
        });

        pnSearch.add(lblSearch);
        pnSearch.add(txtSearch);

        pnButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnButtons.setBackground(FWCConfigurator.bgColor);
        pnButtons.setBorder(BorderFactory.createEmptyBorder(0, 100, 20, 100));

        // Create buttons
        btnEdit = new ImageButton("Edit Class",
                FWCConfigurator.EDIT_IMG, 150, 50);
        btnRoster = new ImageButton("View Roster",
                FWCConfigurator.VIEW_ROSTER_IMG, 150, 50);
        btnNewClass = new ImageButton("Add Class",
                FWCConfigurator.ADDCLASS_IMG, 150, 50);
        btnNewStudent = new ImageButton("Add Student",
                FWCConfigurator.ADDSTUDENT_IMG, 150, 50);

        pnButtons.add(btnEdit);
        pnButtons.add(btnRoster);
        pnButtons.add(btnNewClass);
        pnButtons.add(btnNewStudent);

        // Build table of classes
        tableModel = new DisabledTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Class Name",
                "Number of Students"});

        classesTable = new JTable(tableModel);
        classesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        classesTable.setFillsViewportHeight(true);
        classesTable.getTableHeader().setFont(new Font("Calibri",
                Font.PLAIN, 18));
        classesTable.setFont(new Font("Calibri", Font.PLAIN, 18));
        classesTable.setRowHeight(classesTable.getRowHeight() + 12);

        refresh();

        tableScroll = new JScrollPane(classesTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScroll.setBackground(FWCConfigurator.bgColor);
        tableScroll.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(10, 100, 10, 100),
                new LineBorder(Color.black, 1)));

        pnCenter.add(pnSearch, BorderLayout.NORTH);
        pnCenter.add(tableScroll, BorderLayout.CENTER);
        pnCenter.add(pnButtons, BorderLayout.SOUTH);

        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
    }

    private void populateTable() {
        // Remove all rows first
        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        for (Classroom classroom : currentList) {
            tableModel.addRow(new String[]{classroom.getClassName(),
                    Integer.toString(classroom.getStudents().size())});
        }
    }

    public int getSelectedClass() {
        return classesTable.getSelectedRow();
    }

    public void refresh() {
        txtSearch.setText("");
        currentList = FWCConfigurator.getTeacher().getClasses();
        populateTable();
    }

    public void setEditListener(ActionListener editListener) {
        btnEdit.addActionListener(editListener);
    }

    public void setRosterListener(ActionListener rosterListener) {
        btnRoster.addActionListener(rosterListener);
    }

    public void setNewClassListener(ActionListener newClassListener) {
        btnNewClass.addActionListener(newClassListener);
    }

    public void setNewStudentListener(ActionListener newStudentListener) {
        btnNewStudent.addActionListener(newStudentListener);
    }

    public ArrayList<Classroom> getCurrentList() {
        return currentList;
    }
}
