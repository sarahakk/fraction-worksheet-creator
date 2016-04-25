package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.model.Teacher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author olgasheehan
 */
public class TeacherWorksheetHistory extends JPanel{
    

private JPanel pnNorth, pnButtons;
    private JLabel lblTitle;
    private ImageButton btnTeacherWorksheetHistory;
    private JButton btnPrint, btnAnswerKey,btnDelete;

    public TeacherWorksheetHistory() {
        super(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        buildTitleLabel();
        pnNorth.add(lblTitle);

        // Build buttons and center panel
        pnButtons = new JPanel(new GridBagLayout());
        pnButtons.setBackground(Color.WHITE);
        pnButtons.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        GridBagConstraints c = new GridBagConstraints();

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
    }

    private void buildTitleLabel() {
        boolean imgRead = true;

        try {
            URL imgURL = CommonHeader.class.getClassLoader().getResource
        ("images/" + FWCConfigurator.HISTORY_IMG);
            BufferedImage imgBuff = ImageIO.read(imgURL);

            if (imgURL != null) {
                lblTitle = new JLabel(new ImageIcon(imgBuff.getScaledInstance
        (250, 75, Image.SCALE_SMOOTH)));
            }
            else {
                System.out.println("Could not load " + FWCConfigurator.
                        HISTORY_IMG + ".");
                imgRead = false;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            imgRead = false;
        }

       // If error occurs in fetching title image, use text instead
       if (!imgRead) {
        lblTitle = new JLabel("Teacher Worksheet History");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 32));
        lblTitle.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 5, true));
      }
    }

    private class PrintActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            //checking source of event to reduce repetition
            if (e.getSource() == btnPrint)
            {
                Teacher teacher = FWCConfigurator.getTeacher();
                // get selected worksheet in JTable
                // get corresponding worksheet object in teacher's history arraylist
                // print that worksheet using worksheet.print(false)
            }
        }
    }

    private class AnswerKeyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Teacher teacher = FWCConfigurator.getTeacher();
            // get selected worksheet in JTable
            // get corresponding worksheet object in teacher's history arraylist
            // print that worksheet using worksheet.print(true)
            // the true parameter means it will only print the answer key
        }
    }

    private class DeleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
