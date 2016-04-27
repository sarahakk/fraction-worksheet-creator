
package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.custom.TitleLabel;
import com.elementaryengineers.fwc.db.FWCConfigurator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author olgasheehan
 */
public class ClassNew extends JPanel{
    
    private JPanel pnNorth,pnSouth,pnButtons, pnFields;
    private TitleLabel lblTitle;
    private JLabel lblClassName;
    private JTextField txtClassName;
    private ImageButton btnBack, btnSubmit;
          
     public ClassNew () {
        super(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("New Class", FWCConfigurator.NEW_CLASS_IMG);
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
    
        // Build south panel and submit button
        pnSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnSouth.setBackground(Color.WHITE);
        btnSubmit = new ImageButton("Submit", FWCConfigurator.SUBMIT_IMG, 150, 50);
        pnSouth.add(btnSubmit);

        add(pnNorth, BorderLayout.NORTH);
        add(pnFields, BorderLayout.CENTER);
        add(pnSouth, BorderLayout.SOUTH);
        
     }
     
      public void setSubmitListener(ActionListener submitListener) {
        btnSubmit.addActionListener(submitListener);
    }
    
        
       

}
