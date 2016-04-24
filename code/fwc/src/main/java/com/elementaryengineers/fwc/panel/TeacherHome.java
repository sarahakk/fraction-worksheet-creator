package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.custom.TitleLabel;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.model.Teacher;
import com.elementaryengineers.fwc.random.WS_Beginner_Pie;
import com.elementaryengineers.fwc.random.WS_Intermediate;
import org.apache.pdfbox.exceptions.COSVisitorException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by sarahakk on 4/10/16.
 */
public class TeacherHome extends JPanel {

    private JPanel pnNorth, pnButtons;
    private JLabel lblTitle;
    private JButton btnBeg1, btnBeg2, btnBeg3,
            btnInt1, btnInt2, btnInt3,
            btnAdv1, btnAdv2, btnAdv3;

    public TeacherHome() {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("Teacher Home", FWCConfigurator.TEACHER_HOME_IMG);
        pnNorth.add(lblTitle);

        // Build buttons and center panel
        pnButtons = new JPanel(new GridBagLayout());
        pnButtons.setBackground(Color.WHITE);
        pnButtons.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        GridBagConstraints c = new GridBagConstraints();

        // Make beginner buttons
        btnBeg1 = new ImageButton("Beginner\nPie Charts", FWCConfigurator.BEG1_IMG, 200, 100);
        btnBeg1.addActionListener(new BeginnerActionListener());
        btnBeg2 = new ImageButton("Beginner Addition", FWCConfigurator.BEG2_IMG, 200, 100);
        btnBeg2.addActionListener(new BeginnerActionListener());
        btnBeg3 = new ImageButton("Beginner\nLeast to Greatest", FWCConfigurator.BEG3_IMG, 200, 100);
        btnBeg3.addActionListener(new BeginnerActionListener());

        // Make intermediate buttons
        btnInt1 = new ImageButton("Intermediate Addition", FWCConfigurator.INT1_IMG, 200, 100);
        btnInt1.addActionListener(new IntermediateActionListener());
        btnInt2 = new ImageButton("Intermediate Subtraction", FWCConfigurator.INT2_IMG, 200, 100);
        btnInt2.addActionListener(new IntermediateActionListener());
        btnInt3 = new ImageButton("Intermediate Multiplication\nand Division", FWCConfigurator.INT3_IMG, 200, 100);
        btnInt3.addActionListener(new IntermediateActionListener());

        // Make advanced buttons
        btnAdv1 = new ImageButton("Advanced Addition", FWCConfigurator.ADV1_IMG, 200, 100);
        btnAdv1.addActionListener(new AdvancedActionListener());
        btnAdv2 = new ImageButton("Advanced Subtraction", FWCConfigurator.ADV2_IMG, 200, 100);
        btnAdv2.addActionListener(new AdvancedActionListener());
        btnAdv3 = new ImageButton("Advanced Multiplication\nand Division", FWCConfigurator.ADV3_IMG, 200, 100);
        btnAdv3.addActionListener(new AdvancedActionListener());

        // Add buttons to center panel
        pnButtons.add(btnBeg1, c);
        c.gridy = 1;
        pnButtons.add(btnBeg2, c);
        c.gridy = 2;
        pnButtons.add(btnBeg3, c);
        c.gridy = 0;
        c.gridx = 1;
        pnButtons.add(btnInt1, c);
        c.gridy = 1;
        pnButtons.add(btnInt2, c);
        c.gridy = 2;
        pnButtons.add(btnInt3, c);
        c.gridy = 0;
        c.gridx = 2;
        pnButtons.add(btnAdv1, c);
        c.gridy = 1;
        pnButtons.add(btnAdv2, c);
        c.gridy = 2;
        pnButtons.add(btnAdv3, c);

        // Add north and center panel to TeacherHome
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnButtons, BorderLayout.CENTER);
    }

    private class BeginnerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: need a way to generalize worksheet, initialize here before checking source of event to reduce repetition
            if (e.getSource() == btnBeg1)
            {
                Teacher teacher = FWCConfigurator.getTeacher();
                WS_Beginner_Pie worksheet = new WS_Beginner_Pie(0, 10,
                        teacher.getMinNumerator(), teacher.getMaxNumerator(),
                        teacher.getMinDenominator(), teacher.getMaxDenominator(),
                        FWCConfigurator.GEN_WHOLENUM_NO);

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
    }

    private class IntermediateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Teacher teacher = FWCConfigurator.getTeacher();
            WS_Intermediate worksheet = new WS_Intermediate(0, 40,
                    teacher.getMinNumerator(), teacher.getMaxNumerator(),
                    teacher.getMinDenominator(), teacher.getMaxDenominator(),
                    FWCConfigurator.GEN_WHOLENUM_NO + FWCConfigurator.GEN_DENOM_MATCHED,
                    (e.getSource() == btnInt1) ? '+' :
                            (e.getSource() == btnInt2) ? '-' : '*');

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
