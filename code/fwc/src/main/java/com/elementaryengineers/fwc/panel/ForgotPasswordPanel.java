package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.custom.TitleLabel;
import com.elementaryengineers.fwc.db.FWCConfigurator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by sarahakk on 4/24/16.
 */
public class ForgotPasswordPanel extends JPanel {

    private JPanel pnNorth, pnFields, pnButtons;
    private ImageButton btnSubmit, btnBack;
    private JLabel lblTitle, lblUser;
    private JTextField txtUser;

    public ForgotPasswordPanel() {
        super(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("Request Password Reset", FWCConfigurator.REQ_PASSW_RESET_IMG);
        pnNorth.add(lblTitle);

        // Build form and center panel
        lblUser = new JLabel("Username:", SwingConstants.RIGHT);
        lblUser.setFont(new Font("Calibri", Font.BOLD, 18));
        txtUser = new JTextField(24);

        pnFields = new JPanel(new GridBagLayout());
        pnFields.setBackground(Color.WHITE);
        pnFields.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));
        GridBagConstraints c = new GridBagConstraints();
        c.ipady = 5;
        c.anchor = GridBagConstraints.EAST;
        pnFields.add(lblUser, c);

        c.gridx = 1;
        c.insets = new Insets(0, 10, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnFields.add(txtUser, c);

        // Build buttons (south)
        pnButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnButtons.setBackground(Color.WHITE);
        btnSubmit = new ImageButton("Submit", FWCConfigurator.SUBMIT_IMG, 150, 50);
        btnBack = new ImageButton("Back", FWCConfigurator.BACK_IMG, 150, 50);
        pnButtons.add(btnBack);
        pnButtons.add(btnSubmit);

        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnFields, BorderLayout.CENTER);
        this.add(pnButtons, BorderLayout.SOUTH);
    }

    public String getUsernameText() {
        return txtUser.getText();
    }

    public void setBackListener(ActionListener backListener) {
        btnBack.addActionListener(backListener);
    }

    public void setSubmitListener(ActionListener submitListener) {
        btnSubmit.addActionListener(submitListener);
    }

    public void clearFields() {
        txtUser.setText("");
    }
}