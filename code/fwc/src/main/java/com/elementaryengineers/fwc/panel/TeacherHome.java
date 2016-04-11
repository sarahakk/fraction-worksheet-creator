package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.random.WS_Intermediate;
import org.apache.pdfbox.exceptions.COSVisitorException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by sarahakk on 4/10/16.
 */
public class TeacherHome extends JPanel {

    private JPanel pnNorth, pnButtons;
    private ImageButton btnTeacherHome;
    private JButton btnBeg1, btnBeg2, btnBeg3,
            btnInt1, btnInt2, btnInt3,
            btnAdv1, btnAdv2, btnAdv3;
    
    private static final String HOME_IMG = "teacherhome.png";
    private static final String BEG1_IMG = "beginninerPC.png";

    public TeacherHome() {
        super(new BorderLayout());
        this.setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        btnTeacherHome = new ImageButton("images/" + HOME_IMG, 250, 75);
        pnNorth.add(btnTeacherHome);

        // Build buttons and center panel
        pnButtons = new JPanel(new GridLayout(3, 3, 5, 10));
        pnButtons.setBackground(Color.WHITE);
        pnButtons.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        btnBeg1 = new JButton("Beginner Pie Chart");
        btnBeg1.setFont(new Font("Calibri", Font.BOLD, 16));
                
        btnBeg2 = new JButton("Beginner Addition");
        btnBeg2.setFont(new Font("Calibri", Font.BOLD, 16));

        btnBeg3 = new JButton("Beginner Least to Greatest");
        btnBeg3.setFont(new Font("Calibri", Font.BOLD, 16));

        btnInt1 = new JButton("Intermediate Addition");
        btnInt1.setFont(new Font("Calibri", Font.BOLD, 16));
        btnInt1.addActionListener(new IntermediateActionListener());

        btnInt2 = new JButton("Intermediate Subtraction");
        btnInt2.setFont(new Font("Calibri", Font.BOLD, 16));
        btnInt2.addActionListener(new IntermediateActionListener());

        btnInt3 = new JButton("Intermediate Multiplication & Division");
        btnInt3.setFont(new Font("Calibri", Font.BOLD, 16));
        btnInt3.addActionListener(new IntermediateActionListener());

        btnAdv1 = new JButton("Advanced Addition");
        btnAdv1.setFont(new Font("Calibri", Font.BOLD, 16));

        btnAdv2 = new JButton("Advanced Subtraction");
        btnAdv2.setFont(new Font("Calibri", Font.BOLD, 16));

        btnAdv3 = new JButton("Advanced Multiplication & Division");
        btnAdv3.setFont(new Font("Calibri", Font.BOLD, 16));

        // Add buttons to center panel
        pnButtons.add(btnBeg1);
        pnButtons.add(btnInt1);
        pnButtons.add(btnAdv1);
        pnButtons.add(btnBeg2);
        pnButtons.add(btnInt2);
        pnButtons.add(btnAdv2);
        pnButtons.add(btnBeg3);
        pnButtons.add(btnInt3);
        pnButtons.add(btnAdv3);

        // Add north and center panel to TeacherHome
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnButtons, BorderLayout.CENTER);
    }

    private class BeginnerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class IntermediateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            WS_Intermediate worksheet = null;

            if (e.getSource() == btnInt1)
                 worksheet = new WS_Intermediate(0, 40,
                        1, 16,
                        2, 16,
                        FWCConfigurator.GEN_DENOM_MATCHED,
                        FWCConfigurator.GEN_WHOLENUM_NO,
                        '+');
            else if (e.getSource() == btnInt2)
                worksheet = new WS_Intermediate(0, 40,
                        1, 16,
                        2, 16,
                        FWCConfigurator.GEN_DENOM_MATCHED,
                        FWCConfigurator.GEN_WHOLENUM_NO,
                        '-');
            else if (e.getSource() == btnInt3)
                worksheet = new WS_Intermediate(0, 40,
                        1, 16,
                        2, 16,
                        FWCConfigurator.GEN_DENOM_MATCHED,
                        FWCConfigurator.GEN_WHOLENUM_NO,
                        '*');

            worksheet.getSeed();

            try {
                worksheet.CreateWorksheet(FWCConfigurator.ANSWER_SHEET);
            }
            catch (IOException|COSVisitorException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred while creating your worksheet!\n" +
                                "If the problem persists, please restart the Fraction Worksheet Creator and try again.",
                        "Worksheet Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class AdvancedActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
