package com.elementaryengineers.fwc.custom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * This class allows the easy creation of a JButton
 * that uses an image as a button, rather than
 * the default Java style button with text.
 **/
public class ImageButton extends JButton {

    private String text;
    private int width, height;

    public ImageButton(String text, String filename, int width, int height) {
        this.text = text;
        this.width = width;
        this.height = height;

        // Get icon of image pointed to by filename
        try {
            URL imgURL = ImageButton.class.getClassLoader().getResource("images/" + filename);
            BufferedImage imgBuff = ImageIO.read(imgURL);

            if (imgURL != null) {
                this.setText(null);
                this.setBorderPainted(false); // Remove default JButton border
                this.setFocusPainted(false);
                this.setRolloverEnabled(false);
                this.setOpaque(false);
                this.setContentAreaFilled(false);
                this.setIcon(new ImageIcon(imgBuff.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
            }
            else { // Create a regular JButton
                this.setText(text);
                this.setFont(new Font("Calibri", Font.BOLD, 16));
                this.setPreferredSize(new Dimension(width, height));
            }
        }
        catch (IOException e) {
            e.printStackTrace();

            // Create a regular JButton
            this.setText(text);
            this.setFont(new Font("Calibri", Font.BOLD, 16));
            this.setPreferredSize(new Dimension(width, height));
        }
    }

    public void swapImage(String filename) {
        // Get icon of image pointed to by filename
        try {
            URL imgURL = ImageButton.class.getClassLoader().getResource("images/" + filename);
            BufferedImage imgBuff = ImageIO.read(imgURL);

            if (imgURL != null) {
                this.setText(null);
                this.setBorderPainted(false); // Remove default JButton border
                this.setFocusPainted(false);
                this.setRolloverEnabled(false);
                this.setOpaque(false);
                this.setContentAreaFilled(false);
                this.setIcon(new ImageIcon(imgBuff.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
            }
            else { // Create a regular JButton
                this.setText(text);
                this.setFont(new Font("Calibri", Font.BOLD, 16));
                this.setPreferredSize(new Dimension(width, height));
            }
        }
        catch (IOException e) {
            e.printStackTrace();

            // Create a regular JButton
            this.setText(text);
            this.setFont(new Font("Calibri", Font.BOLD, 16));
            this.setPreferredSize(new Dimension(width, height));
        }
    }
}