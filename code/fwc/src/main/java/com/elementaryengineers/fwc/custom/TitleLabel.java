package com.elementaryengineers.fwc.custom;

import com.elementaryengineers.fwc.panel.CommonHeaderPanel;

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
            URL imgURL = CommonHeaderPanel.class.getClassLoader().getResource("images/" + filename);
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