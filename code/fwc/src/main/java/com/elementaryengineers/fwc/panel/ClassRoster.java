
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
/**
 *
 * @author olgasheehan
 */
public class ClassRoster extends JPanel{
    

    private JPanel pnNorth, pnCenter, pnButtons;
    private TitleLabel lblTitle;
    private ImageButton btnPrint, btnDelete;
    private DefaultTableModel tableModel;
    private JTable sheetsTable;
    private JScrollPane tableScroll;
    private ArrayList<Worksheet> sheets;

    public ClassRoster() {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("Class Roster", FWCConfigurator.CLASS_ROSTER_IMG);
        pnNorth.add(lblTitle);

        // Build center panel
        pnCenter = new JPanel(new BorderLayout());
        pnCenter.setBackground(Color.WHITE);

        // Build table of worksheets
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Student Name", "Username",
            "Difficulty Level"});
        
        
        

    }