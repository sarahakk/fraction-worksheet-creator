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

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.custom.TitleLabel;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.model.Student;
import com.elementaryengineers.fwc.model.Worksheet;
import com.elementaryengineers.fwc.random.*;
import org.apache.pdfbox.exceptions.COSVisitorException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**The student homepage allows him/her to generate worksheets of his/her 
 * assigned difficulty level.
 * Created by sarahakk on 4/26/16.
 */
public class StudentHome extends JPanel {

    private CardLayout cardLayout;
    private JPanel pnNorth, pnDiff, pnBeg, pnInt, pnAdv, pnCard;
    private JLabel lblTitle;
    private ImageButton btnBeg, btnInt, btnAdv, // Difficulty buttons
            btnBeg1, btnBeg2, btnBeg3, // Worksheet buttons
            btnInt1, btnInt2, btnInt3,
            btnAdv1, btnAdv2, btnAdv3;

    public StudentHome() {
        super(new BorderLayout());
        setBackground(FWCConfigurator.bgColor);
        Student stu = FWCConfigurator.getStudent();

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(FWCConfigurator.bgColor);
        lblTitle = new TitleLabel("Worksheets", FWCConfigurator.WS_IMG);
        pnNorth.add(lblTitle);

        // Build difficulty buttons and center panel
        pnDiff = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnDiff.setBackground(FWCConfigurator.bgColor);

        // Make difficulty buttons
        btnBeg = new ImageButton("Beginner", FWCConfigurator.STUDENT_BEG_IMG, 
                250, 125);
        btnBeg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnBeg.swapImage(FWCConfigurator.STUDENT_BEG_IMG); 
// Ensure button is colored
                cardLayout.show(pnCard, "Beginner"); 
// Switch to beginner worksheet buttons

                // Gray out other buttons, if any
                if (stu.getDifficultyID() > 0) {
                    btnInt.swapImage(FWCConfigurator.STUDENT_INT_G_IMG);

                    if (stu.getDifficultyID() > 1) {
                        btnAdv.swapImage(FWCConfigurator.STUDENT_ADV_G_IMG);
                    }
                }
            }
        });

        pnDiff.add(btnBeg); // Add to difficulties panel

        // Make card panel for different difficulty worksheet buttons
        cardLayout = new CardLayout();
        pnCard = new JPanel(cardLayout);
        pnCard.setBackground(FWCConfigurator.bgColor);

        // Make beginner buttons and panel
        pnBeg = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnBeg.setBackground(FWCConfigurator.bgColor);
        btnBeg1 = new ImageButton("Pie Charts", 
                FWCConfigurator.STUDENT_BEG_PIE_IMG, 250, 125);
        btnBeg1.addActionListener(new StudentHome.BeginnerActionListener());
        btnBeg2 = new ImageButton("Addition", 
                FWCConfigurator.STUDENT_BEG_ADD_IMG, 250, 125);
        btnBeg2.addActionListener(new StudentHome.BeginnerActionListener());
        btnBeg3 = new ImageButton("Least to Greatest",
                FWCConfigurator.STUDENT_BEG_LG_IMG, 250, 125);
        btnBeg3.addActionListener(new StudentHome.BeginnerActionListener());
        pnBeg.add(btnBeg1);
        pnBeg.add(btnBeg2);
        pnBeg.add(btnBeg3);
        pnCard.add(pnBeg, "Beginner"); // Add worksheet buttons to card panel

        if (stu.getDifficultyID() > 0) {
            btnInt = new ImageButton("Intermediate", 
                    FWCConfigurator.STUDENT_INT_IMG, 250, 125);
            btnInt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnInt.swapImage(FWCConfigurator.STUDENT_INT_IMG); 
// Ensure button is colored
                    cardLayout.show(pnCard, "Intermediate"); 
// Switch to intermediate worksheet buttons

                    // Gray out other buttons
                    btnBeg.swapImage(FWCConfigurator.STUDENT_BEG_G_IMG);

                    if (stu.getDifficultyID() > 1) { // If advanced
                        btnAdv.swapImage(FWCConfigurator.STUDENT_ADV_G_IMG);
                    }
                }
            });
            pnDiff.add(btnInt); // Add to difficulties panel

            // Make intermediate buttons and panel
            pnInt = new JPanel(new FlowLayout(FlowLayout.CENTER));
            pnInt.setBackground(FWCConfigurator.bgColor);
            btnInt1 = new ImageButton("Addition", 
                    FWCConfigurator.STUDENT_INT_ADD_IMG, 250, 125);
        btnInt1.addActionListener(new StudentHome.IntermediateActionListener());
        btnInt2 = new ImageButton("Subtraction", 
                    FWCConfigurator.STUDENT_INT_SUB_IMG, 250, 125);
        btnInt2.addActionListener(new StudentHome.IntermediateActionListener());
            btnInt3 = new ImageButton("Multiplication and Division",
                    FWCConfigurator.STUDENT_INT_MD_IMG, 250, 125);
       btnInt3.addActionListener(new StudentHome.IntermediateActionListener());
            pnInt.add(btnInt1);
            pnInt.add(btnInt2);
            pnInt.add(btnInt3);
            pnCard.add(pnInt, "Intermediate"); 
// Add worksheet buttons to card panel
            cardLayout.show(pnCard, "Intermediate");

            if (stu.getDifficultyID() > 1) {
                btnAdv = new ImageButton("Advanced", 
                        FWCConfigurator.STUDENT_ADV_IMG, 250, 125);
                btnAdv.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        btnAdv.swapImage(FWCConfigurator.STUDENT_ADV_IMG); 
// Ensure button is colored
                        cardLayout.show(pnCard, "Advanced"); 
// Switch to advanced worksheet buttons

                        // Gray out other buttons
                        btnBeg.swapImage(FWCConfigurator.STUDENT_BEG_G_IMG);
                        btnInt.swapImage(FWCConfigurator.STUDENT_INT_G_IMG);
                    }
                });
                pnDiff.add(btnAdv); // Add to difficulties panel

                // Make advanced buttons and panel
                pnAdv = new JPanel(new FlowLayout(FlowLayout.CENTER));
                pnAdv.setBackground(FWCConfigurator.bgColor);
                btnAdv1 = new ImageButton("Addition",
                        FWCConfigurator.STUDENT_ADV_ADD_IMG, 250, 125);
            btnAdv1.addActionListener(new StudentHome.AdvancedActionListener());
                btnAdv2 = new ImageButton("Subtraction", 
                        FWCConfigurator.STUDENT_ADV_SUB_IMG, 250, 125);
            btnAdv2.addActionListener(new StudentHome.AdvancedActionListener());
                btnAdv3 = new ImageButton("Multiplication", 
                        FWCConfigurator.STUDENT_ADV_MD_IMG, 250, 125);
            btnAdv3.addActionListener(new StudentHome.AdvancedActionListener());
                pnAdv.add(btnAdv1);
                pnAdv.add(btnAdv2);
                pnAdv.add(btnAdv3);
                pnCard.add(pnAdv, "Advanced"); 
// Add worksheet buttons to card panel
                cardLayout.show(pnCard, "Advanced");
            }
        }

        pnDiff.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        pnCard.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        // Add north and center panel to TeacherHome
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnDiff, BorderLayout.CENTER);
        this.add(pnCard, BorderLayout.SOUTH);
    }

    private class BeginnerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Student stu = FWCConfigurator.getStudent();

            // Check type of exercise selection
            if (e.getSource() == btnBeg1)
            {
                WS_Beginner_Pie worksheet =
                        new WS_Beginner_Pie (0, 10,
                         1, 10, // Use default numerator and denominator limits
                         2, 10, FWCConfigurator.GEN_WHOLENUM_NO);

                try {
                    worksheet.CreateWorksheet(FWCConfigurator.WORKSHEET_ONLY);

                    // Create new worksheet object
                    Worksheet newWorksheet = new Worksheet(worksheet.getSeed(),
                            FWCConfigurator.WS_Beginner_Pie, 0);
                    stu.addWorksheet(newWorksheet); // Add to Student's history
                }
                catch (IOException |COSVisitorException ex) {
                    JOptionPane.showMessageDialog(null, "Oh no! Something went"
                            + " wrong. Please try again later.",
                            "Worksheet Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getSource() == btnBeg2) {
                WS_Beginner_PieAdd worksheet =
                        new WS_Beginner_PieAdd (0, 20,
                        1, 10, // Use default numerator and denominator limits
                                2, 10,
                                FWCConfigurator.GEN_DENOM_MATCHED +
                                        FWCConfigurator.GEN_WHOLENUM_NO);

                try {
                    worksheet.CreateWorksheet(FWCConfigurator.WORKSHEET_ONLY);

                    // Create new worksheet object
                    Worksheet newWorksheet = new Worksheet(worksheet.getSeed(),
                            FWCConfigurator.WS_Beginner_PieAdd, 0);
                    stu.addWorksheet(newWorksheet); // Add to Student's history
                }
                catch (IOException|COSVisitorException ex) {
                    JOptionPane.showMessageDialog(null, "Oh no! Something went"
                            + " wrong. Please try again later.",
                            "Worksheet Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getSource() == btnBeg3) {
                WS_Beginner_LG worksheet =
                        new WS_Beginner_LG (0, 50,
                       1, 10, // Use default numerator and denominator limits
                                2, 10,
                                FWCConfigurator.GEN_UNIQUE5);

                try {
                    worksheet.CreateWorksheet(FWCConfigurator.WORKSHEET_ONLY);

                    // Create new worksheet object
                    Worksheet newWorksheet = new Worksheet(worksheet.getSeed(),
                            FWCConfigurator.WS_Beginner_LG, 0);
                    stu.addWorksheet(newWorksheet); // Add to Student's history
                }
                catch (IOException|COSVisitorException ex) {
                    JOptionPane.showMessageDialog(null, "Oh no! Something went"
                            + " wrong. Please try again later.",
                            "Worksheet Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class IntermediateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Student stu = FWCConfigurator.getStudent();

            WS_Intermediate worksheet = new WS_Intermediate(0, 40,
                    1, 12, // Use default numerator and denominator limits
                    2, 12,
                    FWCConfigurator.GEN_WHOLENUM_NO,
                    (e.getSource() == btnInt1) ? '+' :
                            (e.getSource() == btnInt2) ? '-' : '*');

            try {
                worksheet.CreateWorksheet(FWCConfigurator.WORKSHEET_ONLY);

                // Create new worksheet object
                Worksheet newWorksheet = new Worksheet(worksheet.getSeed(),
                        (e.getSource() == btnInt1) ? 
                                FWCConfigurator.WS_Intermediate_Add :
                                (e.getSource() == btnInt2) ? 
                                        FWCConfigurator.WS_Intermediate_Sub :
                                        FWCConfigurator.WS_Intermediate_MD, 1);
                stu.addWorksheet(newWorksheet); // Add to Student's history
            }
            catch (IOException|COSVisitorException ex) {
                JOptionPane.showMessageDialog(null, "Oh no! Something went "
                        + "wrong. Please try again later.",
                        "Worksheet Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class AdvancedActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Student stu = FWCConfigurator.getStudent();

            try {
                WS_Advanced worksheet = new WS_Advanced(0, 20,
                        1, 8, // Use default numerator and denominator limits
                        2, 8,
                        FWCConfigurator.GEN_WHOLENUM_NO,
                        (e.getSource() == btnInt1) ? '+' :
                                (e.getSource() == btnInt2) ? '-' : '*');

                worksheet.CreateWorksheet(FWCConfigurator.WORKSHEET_ONLY);

                // Create new worksheet object
                Worksheet newWorksheet = new Worksheet(worksheet.getSeed(),
                        (e.getSource() == btnInt1) ? 
                                FWCConfigurator.WS_Advanced_Add :
                                (e.getSource() == btnInt2) ? 
                                        FWCConfigurator.WS_Advanced_Sub :
                                        FWCConfigurator.WS_Advanced_MD, 2);
                stu.addWorksheet(newWorksheet); // Add to Student's history
            }
            catch (IOException|COSVisitorException ex) {
                JOptionPane.showMessageDialog(null, "Oh no! Something went"
                        + " wrong. Please try again later.",
                        "Worksheet Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}