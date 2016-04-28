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

package com.elementaryengineers.fwc.custom;

import com.elementaryengineers.fwc.panel.CommonHeader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by sarahakk on 4/24/16.
 */
public class TitleLabel extends JLabel {

    public TitleLabel(String text, String filename) {
        try {
            URL imgURL = CommonHeader.class.getClassLoader().getResource("images/" + filename);
            BufferedImage imgBuff = ImageIO.read(imgURL);

            if (imgURL != null) {
                this.setIcon(new ImageIcon(imgBuff.getScaledInstance(250, 75, Image.SCALE_SMOOTH)));
            }
            else {
                // Use text instead
                this.setText(text);
                this.setFont(new Font("Calibri", Font.BOLD, 32));
                this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 5, true));
            }
        }
        catch (IOException e) {
            e.printStackTrace();

            // Use text instead
            this.setText(text);
            this.setFont(new Font("Calibri", Font.BOLD, 32));
            this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 5, true));
        }
    }
}