package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.DisabledTableModel;
import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.custom.TitleLabel;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.model.Classroom;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
public class Classes extends JPanel {

    private JPanel pnNorth, pnCenter, pnTools, pnSearch, pnButtons;
    private TitleLabel lblTitle;
    private JLabel lblSearch;
    private JTextField txtSearch;
    private ImageButton btnEdit, btnRoster, btnNewClass, btnNewStudent;
    private DisabledTableModel tableModel;
    private JTable classesTable;
    private JScrollPane tableScroll;

    public Classes() {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("All Classes",
                FWCConfigurator.CLASSES_TITLE_IMG);
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
                populateTable(!keyword.equals("") ?
                        FWCConfigurator.getTeacher().searchClasses(keyword) :
                        FWCConfigurator.getTeacher().getClasses()
                );
            }
        });

        pnSearch.add(lblSearch);
        pnSearch.add(txtSearch);

        pnButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnButtons.setBackground(Color.WHITE);

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

        pnTools.add(pnButtons, BorderLayout.WEST);
        pnTools.add(pnSearch, BorderLayout.EAST);

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

        populateTable(FWCConfigurator.getTeacher().getClasses());

        tableScroll = new JScrollPane(classesTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScroll.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(10, 100, 10, 100),
                new LineBorder(Color.black, 1)));

        pnCenter.add(pnTools, BorderLayout.NORTH);
        pnCenter.add(tableScroll, BorderLayout.CENTER);

        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
    }

    private void populateTable(ArrayList<Classroom> classes) {
        // Remove all rows first
        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        for (Classroom classroom : classes) {
            tableModel.addRow(new String[]{classroom.getClassName(),
                    Integer.toString(classroom.getStudents().size())});
        }
    }

    public int getSelectedClass() {
        return classesTable.getSelectedRow();
    }

    public void refresh() {
        populateTable(FWCConfigurator.getTeacher().getClasses());
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
}