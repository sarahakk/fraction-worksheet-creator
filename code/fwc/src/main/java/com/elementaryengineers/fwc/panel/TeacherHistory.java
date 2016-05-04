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
import com.elementaryengineers.fwc.model.Worksheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Page TeacherHistory displays teacher's worksheet history, allowing the Teacher 
 * to print old worksheets, view their answer sheets, and delete them 
 * from the history.
 * 
 * @author olgasheehan
 */
public class TeacherHistory extends JPanel {

    private JPanel pnNorth,pnCenter, pnButtons;
    private TitleLabel lblTitle;
    private JButton btnPrint, btnAnswerKey, btnDelete;
    private DisabledTableModel tableModel;
    private JTable sheetsTable;
    private JScrollPane tableScroll;
    private ArrayList<Worksheet> sheets;

    public TeacherHistory() {
        super(new BorderLayout());
        setBackground(FWCConfigurator.bgColor);
        
        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(FWCConfigurator.bgColor);
        lblTitle = new TitleLabel("Worksheet History", 
                       FWCConfigurator.WS_HISTORY_TITLE_IMG);
        pnNorth.add(lblTitle);

        // Build center panel
        pnCenter = new JPanel(new BorderLayout());
        pnCenter.setBackground(FWCConfigurator.bgColor);
        
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

        // Populate the table with the teachers's worksheets
        sheets = FWCConfigurator.getTeacher().getHistory();
        populateTable();

        tableScroll = new JScrollPane(sheetsTable, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScroll.setBackground(FWCConfigurator.bgColor);
        tableScroll.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(10, 100, 10, 100),
                new LineBorder(Color.black, 1)));

        pnCenter.add(tableScroll, BorderLayout.CENTER);

        // Build south panel
        pnButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnButtons.setBackground(FWCConfigurator.bgColor);
        pnButtons.setBorder(BorderFactory.createEmptyBorder(0, 100, 20, 100));

        btnPrint = new ImageButton("Print Selected", 
                FWCConfigurator.PRINT_SELECTED_IMG, 150, 50);
        btnPrint.addActionListener(new PrintListener());
        
        btnAnswerKey = new ImageButton("Answer Key",
                FWCConfigurator.ANSWER_IMG, 150, 50);
        btnAnswerKey.addActionListener(new AnswerKeyListener());

        btnDelete = new ImageButton("Delete Selected",
                FWCConfigurator.DEL_SELECT_IMG, 150, 50);
        btnDelete.addActionListener(new DeleteListener());
       
        pnButtons.add(btnPrint);
        pnButtons.add(btnAnswerKey);
        pnButtons.add(btnDelete);

        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnButtons, BorderLayout.SOUTH);
    }

    public void populateTable() {
        sheets = FWCConfigurator.getTeacher().getHistory();

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
                        "Print Worksheet Error", 
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
                 "Please select an Answer Key worksheet from the table first.",
                 "Print Answer Key Worksheet Failed", 
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    private class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = sheetsTable.getSelectedRow();

            // Check if selected a worksheet
            if (index >= 0) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this worksheet?",
                        "Delete Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Check database update status
                    if (FWCConfigurator.getDbConn().deleteWorksheet
                            (sheets.get(index)
                                    .getWorksheetID())) {
                        populateTable(); // Refresh table of worksheets
                        JOptionPane.showMessageDialog(null,
                                "Worksheet has been deleted successfully!",
                                "Delete Worksheet Successful",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,
                     "Worksheet could not be deleted! Please try again later.",
                                "Delete Worksheet Failed",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            else { // No worksheet is selected from the table
                JOptionPane.showMessageDialog(null,
                        "Please select a worksheet from the table first.",
                        "Delete Worksheet Error",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}