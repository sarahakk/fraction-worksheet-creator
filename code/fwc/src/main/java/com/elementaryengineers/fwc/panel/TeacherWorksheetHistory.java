package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.custom.TitleLabel;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.model.Worksheet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author olgasheehan
 */
public class TeacherWorksheetHistory extends JPanel{

    private JPanel pnNorth,pnCenter, pnButtons;
    private TitleLabel lblTitle;
    private JButton btnPrint, btnAnswerKey, btnDelete;
  
    private DefaultTableModel tableModel;
    private JTable sheetsTable;
    private JScrollPane tableScroll;
    private ArrayList<Worksheet> sheets;

    public TeacherWorksheetHistory() {
        super(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("Worksheet History", FWCConfigurator.WS_HISTORY_IMG);
        pnNorth.add(lblTitle);

        // Build center panel
        pnCenter = new JPanel(new BorderLayout());
        pnCenter.setBackground(Color.WHITE);
        
        // Build table of worksheets
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Date", "Difficulty", 
            "Exercise"});

       sheetsTable = new JTable(tableModel);
       sheetsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
       sheetsTable.setFillsViewportHeight(true);
       sheetsTable.getTableHeader().setFont(new Font("Calibri",Font.PLAIN, 18));

        // Populate the table with the teachers's worksheets
        sheets = FWCConfigurator.getTeacher().getHistory();
        populateTable();

        tableScroll = new JScrollPane(sheetsTable, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        pnCenter.add(tableScroll, BorderLayout.CENTER);

        // Build south panel
        pnButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnButtons.setBackground(Color.WHITE);
        btnPrint = new ImageButton("Print Selected", 
                FWCConfigurator.PRINT_SELECTED_IMG, 150, 50);
        btnPrint.addActionListener(new PrintListener());
        
        btnDelete = new ImageButton("Delete Selected", 
                FWCConfigurator.DEL_SELECT_IMG, 150, 50);
        btnDelete.addActionListener(new DeleteListener());
        
        
        btnAnswerKey = new ImageButton("Answer Key", 
                FWCConfigurator.DEL_SELECT_IMG, 150, 50);
       btnAnswerKey.addActionListener(new DeleteListener());
       
        pnButtons.add(btnPrint);
        pnButtons.add(btnDelete);

        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnButtons, BorderLayout.SOUTH);
    }

    public void populateTable() {
        // Remove all rows first
        for (int i = 0, len = tableModel.getRowCount(); i < len; i++) {
            tableModel.removeRow(i);
        }

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
            if (index > 0) {
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

    private class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = sheetsTable.getSelectedRow();

            // Check if selected a worksheet
            if (index > 0) {
                // Check database update status
                if (FWCConfigurator.getDbConn().deleteWorksheet
                (sheets.get(index).getWorksheetID())) {
                    sheets.remove(index); // Remove from student history
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
            else { // No worksheet is selected from the table
                JOptionPane.showMessageDialog(null, 
                        "Please select a worksheet from the table first.",
                        "Delete Worksheet Error", 
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void refresh() { // Reload worksheets table for new student login
        sheets = FWCConfigurator.getStudent().getHistory();
        populateTable();
    }
}
        
        /*
        //Button Print
        btnPrint = new ImageButton("Print", FWCConfigurator.PRINT_IMG, 200, 100);
        btnPrint.addActionListener(new PrintActionListener());
        

        // Make intermediate buttons //Button Answer key
        btnAnswerKey = new ImageButton("Answer Key", FWCConfigurator.ANSWER_IMG, 200, 100);
        btnAnswerKey.addActionListener(new AnswerKeyActionListener());
        
        // Make advanced buttons // Delete
        btnDelete = new ImageButton("Delete", FWCConfigurator.DEL_SELECT_IMG, 200, 100);
        btnDelete.addActionListener(new DeleteActionListener());
        

        // Add buttons to center panel
        pnButtons.add(btnPrint, c);
        c.gridy = 1;
       
        c.gridx = 1;
        pnButtons.add(btnAnswerKey, c);
        c.gridy = 1;
        
        c.gridx = 2;
        pnButtons.add(btnDelete, c);
        c.gridy = 1;

        // Add north and center panel to Teacher Worksheet History Home
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnButtons, BorderLayout.CENTER);
        
        // Create a new table instance
        // ****** Add the table****** Still working
  
        Object rowData[][] = { { "Date1", "Dif1", "Exersice1"},
                       { "Date2", "Diff2", "Exercise2"} };
        Object columnNames[] = { "Date", "Difficulty", "Exercise"};
        
        JTable TeacherWSHistoryTable = new JTable(rowData, columnNames);
        TeacherWSHistoryTable.setPreferredScrollableViewportSize(new Dimension(100, 50));
        this.add (TeacherWSHistoryTable, BorderLayout.CENTER);
        //TeacherWSHistoryTable.setSize(300, 200);
        TeacherWSHistoryTable.setVisible(true);
               
  // Add the table to a scrolling pane
       /* 
       scrollPane = new JScrollPane( TeacherWSHistoryTable );
       this.add( scrollPane, BorderLayout.CENTER );  
            //or?
       //scrollPane.setViewportView(TeacherWSHistoryTable); 
        
           */
           //************************* ???
        //Table selection Mode
        //http://www.java2s.com/Code/Java/Swing-JFC/TableSelectionmode.htm
