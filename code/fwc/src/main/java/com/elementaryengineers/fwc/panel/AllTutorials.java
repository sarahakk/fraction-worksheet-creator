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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/** Shows all available tutorial videos
 * Created by sarahakk on 4/26/16.
 */
public class AllTutorials extends JPanel {

    private JPanel pnNorth, pnButtons;
    private JLabel lblTitle;
    private JButton btnBeg1, btnBeg2, btnBeg3,
            btnInt1, btnInt2, btnInt3,
            btnAdv1, btnAdv2, btnAdv3;

    public AllTutorials() {
        super(new BorderLayout());
        setBackground(FWCConfigurator.bgColor);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(FWCConfigurator.bgColor);
        lblTitle = new TitleLabel("Tutorials", FWCConfigurator.TUTORIALS_IMG);
        pnNorth.add(lblTitle);

        // Build buttons and center panel
        pnButtons = new JPanel(new GridBagLayout());
        pnButtons.setBackground(FWCConfigurator.bgColor);
        pnButtons.setBorder(BorderFactory.createEmptyBorder(10, 0, 25, 0));
        GridBagConstraints c = new GridBagConstraints();

        // Make beginner buttons
        btnBeg1 = new ImageButton("Beginner\nPie Charts", FWCConfigurator.BEG1_IMG, 200, 100);
        btnBeg1.addActionListener(new ButtonListener());
        btnBeg2 = new ImageButton("Beginner Addition", FWCConfigurator.BEG2_IMG, 200, 100);
        btnBeg2.addActionListener(new ButtonListener());
        btnBeg3 = new ImageButton("Beginner\nLeast to Greatest", FWCConfigurator.BEG3_IMG, 200, 100);
        btnBeg3.addActionListener(new ButtonListener());

        // Make intermediate buttons
        btnInt1 = new ImageButton("Intermediate Addition", FWCConfigurator.INT1_IMG, 200, 100);
        btnInt1.addActionListener(new ButtonListener());
        btnInt2 = new ImageButton("Intermediate Subtraction", FWCConfigurator.INT2_IMG, 200, 100);
        btnInt2.addActionListener(new ButtonListener());
        btnInt3 = new ImageButton("Intermediate Multiplication\nand Division", FWCConfigurator.INT3_IMG, 200, 100);
        btnInt3.addActionListener(new ButtonListener());

        // Make advanced buttons
        btnAdv1 = new ImageButton("Advanced Addition", FWCConfigurator.ADV1_IMG, 200, 100);
        btnAdv1.addActionListener(new ButtonListener());
        btnAdv2 = new ImageButton("Advanced Subtraction", FWCConfigurator.ADV2_IMG, 200, 100);
        btnAdv2.addActionListener(new ButtonListener());
        btnAdv3 = new ImageButton("Advanced Multiplication", FWCConfigurator.ADV3_IMG, 200, 100);
        btnAdv3.addActionListener(new ButtonListener());

        // Add buttons to panel
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

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String filename = "videos/";

            // Check button that was selected, set the video filename
            if (e.getSource() == btnBeg1) {
                filename += FWCConfigurator.BEG1_VID;
            }
            else if (e.getSource() == btnBeg2) {
                filename += FWCConfigurator.BEG2_VID;
            }
            else if (e.getSource() == btnBeg3) {
                filename += FWCConfigurator.BEG3_VID;
            }
            else if (e.getSource() == btnInt1) {
                filename += FWCConfigurator.INT1_VID;
            }
            else if (e.getSource() == btnInt2) {
                filename += FWCConfigurator.INT2_VID;
            }
            else if (e.getSource() == btnInt3) {
                filename += FWCConfigurator.INT3_VID;
            }
            else if (e.getSource() == btnAdv1) {
                filename += FWCConfigurator.ADV1_VID;
            }
            else if (e.getSource() == btnAdv2) {
                filename += FWCConfigurator.ADV2_VID;
            }
            else if (e.getSource() == btnAdv3) {
                filename += FWCConfigurator.ADV3_VID;
            }

            // Show tutorial video
            try { // This is done because files packaged in the jar cannot be accessed directly by path
                InputStream input = AllTutorials.class.getClassLoader()
                        .getResourceAsStream(filename);
                File file = File.createTempFile("FWCtempVideo", ".m4v");
                OutputStream out = new FileOutputStream(file);

                int read;
                byte[] bytes = new byte[1024];

                // Write the video file to a temporary file on disk
                while ((read = input.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }

                file.deleteOnExit(); // Delete the temporary file when the program exits
                Desktop.getDesktop().open(file); // Open the video using the default video player
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Oh no! Something went wrong. This tutorial video is unavailable.",
                        "Tutorial Video Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}