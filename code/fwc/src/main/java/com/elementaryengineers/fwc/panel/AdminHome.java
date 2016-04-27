/*
 * In the AdminHome.java file everythingis defined for the authentication.
 */
package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.model.Teacher;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminHome extends JPanel {

    private JPanel pnNorth, pnCenter, pnSearch, pnProfile;
    private JLabel lblTitle, lblSearch;
    private JTextField txtSearch;
    private ImageButton btnProfile;
    private DefaultTableModel tableModel;
    private JTable teachersTable;
    private JScrollPane tableScroll;

    public AdminHome() {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new JLabel("All Teachers", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 32));
        pnNorth.add(lblTitle);

        // Build center panel
        pnCenter = new JPanel(new BorderLayout());
        pnCenter.setBackground(Color.WHITE);

        // Build search
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

                // Populate with search results, or all teachers if search is empty
                populateTable(keyword.equals("") ?
                        FWCConfigurator.getAdmin().searchTeachers(keyword) :
                        FWCConfigurator.getAdmin().getTeachers()
                );
            }
        });

        pnSearch.add(lblSearch);
        pnSearch.add(txtSearch);

        // Build table of teachers
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"First Name", "Last Name",
            "Username"});

        teachersTable = new JTable(tableModel);
        teachersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        teachersTable.setFillsViewportHeight(true);
        teachersTable.getTableHeader().setFont(new Font("Calibri", Font.PLAIN, 18));
        populateTable(FWCConfigurator.getAdmin().getTeachers());

        tableScroll = new JScrollPane(teachersTable, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        pnCenter.add(pnSearch, BorderLayout.NORTH);
        pnCenter.add(tableScroll, BorderLayout.CENTER);

        // Build south panel
        pnProfile = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnProfile.setBackground(Color.WHITE);
        btnProfile = new ImageButton("View Profile", 
                FWCConfigurator.PROFILE_IMG, 150, 50);
        pnProfile.add(btnProfile);

        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnProfile, BorderLayout.SOUTH);
    }

    private void populateTable(ArrayList<Teacher> teachers) {
        // Remove all rows first
        for (int i = 0, len = tableModel.getRowCount(); i < len; i++) {
            tableModel.removeRow(i);
        }

        for (Teacher teacher : teachers) {
                tableModel.addRow(new String[]{teacher.getFirstName(),
                        teacher.getLastName(), teacher.getUsername()});
        }
    }

    public void refresh() {
        populateTable(FWCConfigurator.getAdmin().getTeachers());
    }

    public void setProfileListener(ActionListener profileListener) {
        btnProfile.addActionListener(profileListener);
    }

    public int getSelectedTeacher() {
        return teachersTable.getSelectedRow();
    }
}