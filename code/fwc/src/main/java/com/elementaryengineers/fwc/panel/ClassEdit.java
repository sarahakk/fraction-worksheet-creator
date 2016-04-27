
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
    
    private JPanel pnNorth, pnSouth, pnFields, pnButtons;
    private TitleLabel lblTitle;
    private JLabel lblClassName;
    private JTextField txtClassName;
    private ImageButton btnBack, btnSubmit,btnDeleteClass;
      /******
       * Should we use button Back?
       * *****************
       */     

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
        
        
        lblClassName = new JLabel("New Class:", SwingConstants.RIGHT);
        lblClassName.setFont(new Font("Calibri", Font.PLAIN, 18));
        txtClassName = new JTextField(24);
        
        
        // Use GridBagLayout
        pnFields = new JPanel(new GridBagLayout());
        pnFields.setBackground(Color.WHITE);
        pnFields.setBorder(BorderFactory.createEmptyBorder(10, 150, 20, 150));
       
        
        // Class Name
        c.gridy = 1;
        c.gridx = 0;
        c.insets.left = 0;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        pnFields.add(lblClassName, c);

        c.gridx = 1;
        c.insets.left = 10;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnFields.add(txtClassName, c);
        
    
        // Build south panel and submit button
        pnSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnSouth.setBackground(Color.WHITE);
        btnSubmit = new ImageButton("Submit", 
                FWCConfigurator.SUBMIT_IMG, 150, 50);
        
        btnDeleteClass = new ImageButton("Delete Class", 
                FWCConfigurator.DEL_CLASS_IMG, 150, 50); 
        pnSouth.add(btnSubmit);
        pnSouth.add (btnDeleteClass);

        add(pnNorth, BorderLayout.NORTH);
        add(pnFields,BorderLayout.CENTER);
        add(pnSouth, BorderLayout.SOUTH);
   
    }
    
        public void setSubmitListener(ActionListener submitListener) {
            btnSubmit.addActionListener(submitListener);
        }

        public void setDeleteClassListener(ActionListener deleteClassListener){
            btnSubmit.addActionListener(deleteClassListener);
        }
     /****** How the edited class updated in DB?Do we need this method?
        public void getClassName() {
        return ClassName (txtClassName.getText());
        }
    ********************************************/
        
        public void clearFields() {
        txtClassName.setText("");
        
    }
        
}