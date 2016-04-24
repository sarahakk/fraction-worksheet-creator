package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
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
        btnPrint = new ImageButton(FWCConfigurator.PRINT_IMG, 200, 100);
        btnPrint.addActionListener(new PrintActionListener());
        

        // Make intermediate buttons //Button Answer key
        btnAnswerKey = new ImageButton(FWCConfigurator.ANSWER_IMG, 200, 100);
        btnAnswerKey.addActionListener(new AnswerKeyActionListener());
        
        // Make advanced buttons // Delete
        btnDelete = new ImageButton(FWCConfigurator.DELETE_IMG, 200, 100);
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
            URL imgURL = CommonHeaderPanel.class.getClassLoader().getResource
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
             
            //**********************************
            //????? How to print worksheet?????
            // Sara: Olga, I made the following change to line 130:
            // Old: worksheet.CreateWorksheet(FWCConfigurator.ANSWER_SHEET);
            // Fixed: worksheet.CreateWorksheet(FWCConfigurator.WORKSHEET_ONLY);
            // Now it should generate the worksheet.
            //************************************
            //checking source of event to reduce repetition
            if (e.getSource() == btnPrint)
            {
                Teacher teacher = FWCConfigurator.getTeacher();
                WS_Beginner_Pie worksheet = new WS_Beginner_Pie(0, 10,
                        teacher.getMinNumerator(), teacher.getMaxNumerator(),
                        teacher.getMinDenominator(), teacher.getMaxDenominator(),
                        FWCConfigurator.GEN_WHOLENUM_NO);

                worksheet.getSeed();

                try {
                    worksheet.CreateWorksheet(FWCConfigurator.WORKSHEET_ONLY);
                }
                catch (IOException|COSVisitorException ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred while"
                            + " creating your worksheet!\n" +
                                    "If the problem persists, please restart "
                            + "the Fraction Worksheet Creator and try again.",
                            "Worksheet Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class AnswerKeyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Teacher teacher = FWCConfigurator.getTeacher();

            // Sara: This needs to be modified to use the seed of the
            // selected worksheet in the JTable, and you should look at the
            // difficulty and exercise attributes of the selected Worksheet object
            // to determine what kind of worksheet to create. Here, we're creating
            // an intermediate worksheet. We need to look at what's selected, and
            // look at the exercise string to see if it's "Addition", "Subtraction",
            // etc to determine what operator goes into the worksheet initialization.
            // Again, below we are assuming addition. Thanks!
            WS_Intermediate worksheet = new WS_Intermediate(0, 40,
                    teacher.getMinNumerator(), teacher.getMaxNumerator(),
                    teacher.getMinDenominator(), teacher.getMaxDenominator(),
                    FWCConfigurator.GEN_WHOLENUM_NO + FWCConfigurator.GEN_DENOM_MATCHED,
                    '+');

            worksheet.getSeed();

            try {
                worksheet.CreateWorksheet(FWCConfigurator.ANSWER_SHEET);
            }
            catch (IOException|COSVisitorException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred while "
                        + "creating your worksheet!\n" +
                                "If the problem persists, please restart the Fraction Worksheet Creator and try again.",
                        "Worksheet Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
