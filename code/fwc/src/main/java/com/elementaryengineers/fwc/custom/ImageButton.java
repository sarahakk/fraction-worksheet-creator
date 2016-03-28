package com.elementaryengineers.fwc.custom;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * This class allows the easy creation of a JButton
 * that uses an image as a button, rather than
 * the default Java style button with text.
 **/
public class ImageButton extends JButton {

    public ImageButton(String filename, int height, int width) {
        this.setText(null);

        // Get icon of image pointed to by filename
        this.setIcon(new ImageIcon(ImageButton.class.getClassLoader().getResource(filename)));
        //this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setFocusPainted(false);
        this.setRolloverEnabled(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE.darker(), Color.BLACK),
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));


        /*
        this.setText(null);

        this.setFocusPainted(false); // Disable highlighting of button when clicked
        this.setBorderPainted(false); // Remove default button border
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorder(null);
        */
    }
}