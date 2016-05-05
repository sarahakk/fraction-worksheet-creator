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
import com.elementaryengineers.fwc.db.FWCConfigurator;
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
* A list of registered teachers is displayed on the Admin homepage. A profile 
* button next to each teacher allows the Admin to view a teacher’s profile
* to make changes.
*/
public class AdminHome extends JPanel {

    private JPanel pnNorth, pnCenter, pnSearch, pnProfile;
    private JLabel lblTitle, lblSearch;
    private JTextField txtSearch;
    private ImageButton btnProfile;
    private DisabledTableModel tableModel;
    private JTable teachersTable;
    private JScrollPane tableScroll;
    private ArrayList<Teacher> currentList;

    public AdminHome() {
        super(new BorderLayout());
        setBackground(FWCConfigurator.bgColor);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(FWCConfigurator.bgColor);
        lblTitle = new JLabel("All Teachers", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 32));
        pnNorth.add(lblTitle);

        // Build center panel
        pnCenter = new JPanel(new BorderLayout());
        pnCenter.setBackground(FWCConfigurator.bgColor);

        // Build search
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

            private void search() {
                String keyword = txtSearch.getText();

                // Populate with search results, or all teachers if search is empty
                currentList = !keyword.equals("") ?
                        FWCConfigurator.getAdmin().searchTeachers(keyword) :
                        FWCConfigurator.getAdmin().getTeachers();
                populateTable();
            }
        });

        pnSearch.add(lblSearch);
        pnSearch.add(txtSearch);

        // Build table of teachers
        tableModel = new DisabledTableModel();
        tableModel.setColumnIdentifiers(new String[]{"First Name", "Last Name",
            "Username"});

        teachersTable = new JTable(tableModel);
        teachersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        teachersTable.setFillsViewportHeight(true);
        teachersTable.getTableHeader().setFont(new Font("Calibri", Font
                .PLAIN, 20));
        teachersTable.setFont(new Font("Calibri", Font.PLAIN, 18));
        teachersTable.setRowHeight(teachersTable.getRowHeight() + 12);

        refresh(); // Populates table

        tableScroll = new JScrollPane(teachersTable, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScroll.setBackground(FWCConfigurator.bgColor);
        tableScroll.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(10, 100, 10, 100),
                new LineBorder(Color.black, 1)));

        pnCenter.add(pnSearch, BorderLayout.NORTH);
        pnCenter.add(tableScroll, BorderLayout.CENTER);

        // Build south panel
        pnProfile = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnProfile.setBackground(FWCConfigurator.bgColor);
        pnProfile.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        btnProfile = new ImageButton("View Profile", 
                FWCConfigurator.PROFILE_IMG, 150, 50);
        pnProfile.add(btnProfile);

        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnProfile, BorderLayout.SOUTH);
    }

    private void populateTable() {
        // Remove all rows first
        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        for (Teacher teacher : currentList) {
                tableModel.addRow(new String[]{teacher.getFirstName(),
                        teacher.getLastName(), teacher.getUsername()});
        }
    }

    public void refresh() {
        txtSearch.setText("");
        currentList = FWCConfigurator.getAdmin().getTeachers();
        populateTable();
    }

    public void setProfileListener(ActionListener profileListener) {
        btnProfile.addActionListener(profileListener);
    }

    public int getSelectedTeacher() {
        return teachersTable.getSelectedRow();
    }

    public ArrayList<Teacher> getCurrentList() {
        return currentList;
    }
}