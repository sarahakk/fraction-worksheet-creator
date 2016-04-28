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
import com.elementaryengineers.fwc.model.Student;
import com.elementaryengineers.fwc.model.Worksheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by sarahakk on 4/27/16.
 */
public class TeacherStudentHistory extends JPanel {

    private JPanel pnNorth,pnCenter, pnButtons;
    private TitleLabel lblTitle;
    private JLabel lblName;
    private JButton btnPrint, btnAnswerKey;
    private DisabledTableModel tableModel;
    private JTable sheetsTable;
    private JScrollPane tableScroll;
    private ArrayList<Worksheet> sheets;
    private Student student;

    public TeacherStudentHistory() {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("Student Worksheet History",
                FWCConfigurator.STUDENT_WS_HISTORY_IMG);
        pnNorth.add(lblTitle);

        // Build center panel
        pnCenter = new JPanel(new BorderLayout());
        pnCenter.setBackground(Color.WHITE);
        pnCenter.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));

        lblName = new JLabel("", SwingConstants.RIGHT);
        lblName.setFont(new Font("Calibri", Font.BOLD, 20));
        lblName.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Build table of worksheets
        tableModel = new DisabledTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Date", "Difficulty",
                "Exercise"});

        sheetsTable = new JTable(tableModel);
        sheetsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        sheetsTable.setFillsViewportHeight(true);
        sheetsTable.getTableHeader().setFont(new Font("Calibri",Font.PLAIN,
                18));
        sheetsTable.setFont(new Font("Calibri", Font.PLAIN, 18));
        sheetsTable.setRowHeight(sheetsTable.getRowHeight() + 12);

        tableScroll = new JScrollPane(sheetsTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        pnCenter.add(lblName, BorderLayout.NORTH);
        pnCenter.add(tableScroll, BorderLayout.CENTER);

        // Build south panel
        pnButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnButtons.setBackground(Color.WHITE);
        pnButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        btnPrint = new ImageButton("Print Selected",
                FWCConfigurator.PRINT_SELECTED_IMG, 150, 50);
        btnPrint.addActionListener(new PrintListener());

        btnAnswerKey = new ImageButton("Answer Key",
                FWCConfigurator.ANSWER_IMG, 150, 50);
        btnAnswerKey.addActionListener(new AnswerKeyListener());

        pnButtons.add(btnPrint);
        pnButtons.add(btnAnswerKey);

        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnButtons, BorderLayout.SOUTH);
    }

    private void populateTable() {
        // Remove all rows first
        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        Collections.reverse(sheets);

        for (Worksheet sheet : sheets) {
            tableModel.addRow(new String[]{sheet.getDateCreated(),
                    FWCConfigurator.getDifficulties().get(sheet.getDifficultyID()).
                            getDescription(),
                    sheet.getExercise()
            });
        }
    }

    public void setStudent(Student student) {
        this.student = student;
        this.lblName.setText("Student: " + student.getFirstName() + " " +
                student
                .getLastName());
        this.sheets = student.getHistory();
        populateTable();
    }

    private class PrintListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = sheetsTable.getSelectedRow();

            // Check if selected a worksheet
            if (index >= 0) {
                sheets.get(index).print(false);
            }
            else { // No worksheet is selected from the table
                JOptionPane.showMessageDialog(null,
                        "Please select a worksheet from the table first.",
                        "Print Worksheet Failed",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class AnswerKeyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = sheetsTable.getSelectedRow();

            // Check if selected a worksheet
            if (index >= 0) {
                sheets.get(index).print(true);
            }
            else { // No worksheet is selected from the table
                JOptionPane.showMessageDialog(null,
                        "Please select a worksheet from the table first.",
                        "Print Answer Key Failed",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}