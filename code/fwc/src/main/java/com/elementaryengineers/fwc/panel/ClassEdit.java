
package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.custom.TitleLabel;
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
public class ClassEdit extends JPanel{
    
    private JPanel pnNorth, pnCenter,pnButtons;
    private JLabel lblTitle;
    private JButton btnClassName, btnBack, btnSubmit;
           

    public ClassEdit () {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("Edit Class", FWCConfigurator.EDIT_CLASS_IMG);
        pnNorth.add(lblTitle);

        // Build buttons and center panel
        pnButtons = new JPanel(new GridBagLayout());
        pnButtons.setBackground(Color.WHITE);
        pnButtons.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        GridBagConstraints c = new GridBagConstraints();

        // Make Back button
        btnBack = new ImageButton("Back", FWCConfigurator.BACK_IMG, 200, 100);
        //btnBack.addActionListener(new BackListener());
        
        // Add buttons to center panel
        pnButtons.add(btnBack, c);
        c.gridy = 1;
        

        // Add north and center panel to TeacherHome
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnButtons, BorderLayout.CENTER);
    }

}