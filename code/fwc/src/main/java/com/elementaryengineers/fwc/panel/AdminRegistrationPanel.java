package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.custom.TitleLabel;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sarahakk on 4/24/16.
 */
public class AdminRegistrationPanel extends JPanel {

    private JPanel pnNorth, pnFieldsLeft, pnFieldsRight, pnSouth;
    private TitleLabel lblTitle;
    private JLabel lblFirst, lblLast, lblUser, lblPass, lblConfirm,
            lblSecurity, lblSSN, lblBirthdate, lblJob;
    private JTextField txtFirst, txtLast, txtUser, txtSSN, txtBirthdate, txtJob;
    private JPasswordField txtPass, txtConfirm;
    private ImageButton btnSubmit;

    public AdminRegistrationPanel() {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("Administrator Registration", FWCConfigurator.ADMIN_REG_IMG);
        pnNorth.add(lblTitle);

        // Build west panel and form
        lblFirst = new JLabel("First name:", SwingConstants.RIGHT);
        lblFirst.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblLast = new JLabel("Last name:", SwingConstants.RIGHT);
        lblLast.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblUser = new JLabel("Username:", SwingConstants.RIGHT);
        lblUser.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblPass = new JLabel("Password:", SwingConstants.RIGHT);
        lblPass.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblConfirm = new JLabel("Confirm password:", SwingConstants.RIGHT);
        lblConfirm.setFont(new Font("Calibri", Font.PLAIN, 18));
        txtFirst = new JTextField(24);
        txtLast = new JTextField(24);
        txtUser = new JTextField(24);
        txtPass = new JPasswordField(24);
        txtConfirm = new JPasswordField(24);

        // Use GridBagLayout
        pnFieldsLeft = new JPanel(new GridBagLayout());
        pnFieldsLeft.setBackground(Color.WHITE);
        pnFieldsLeft.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 0));
        pnFieldsLeft.setPreferredSize(new Dimension(400, 200));
        GridBagConstraints cLeft = new GridBagConstraints();
        cLeft.ipady = 5;

        // First name
        cLeft.anchor = GridBagConstraints.EAST;
        pnFieldsLeft.add(lblFirst, cLeft);

        cLeft.gridx = 1;
        cLeft.insets = new Insets(0, 10, 0, 0);
        cLeft.anchor = GridBagConstraints.CENTER;
        cLeft.weightx = 1;
        cLeft.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsLeft.add(txtFirst, cLeft);

        // Last name
        cLeft.gridy = 1;
        cLeft.gridx = 0;
        cLeft.insets.left = 0;
        cLeft.anchor = GridBagConstraints.EAST;
        cLeft.weightx = 0;
        cLeft.fill = GridBagConstraints.NONE;
        pnFieldsLeft.add(lblLast, cLeft);

        cLeft.gridx = 1;
        cLeft.insets.left = 10;
        cLeft.anchor = GridBagConstraints.CENTER;
        cLeft.weightx = 1;
        cLeft.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsLeft.add(txtLast, cLeft);

        // Username
        cLeft.gridy = 2;
        cLeft.gridx = 0;
        cLeft.insets.left = 0;
        cLeft.anchor = GridBagConstraints.EAST;
        cLeft.weightx = 0;
        cLeft.fill = GridBagConstraints.NONE;
        pnFieldsLeft.add(lblUser, cLeft);

        cLeft.gridx = 1;
        cLeft.insets.left = 10;
        cLeft.anchor = GridBagConstraints.CENTER;
        cLeft.weightx = 1;
        cLeft.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsLeft.add(txtUser, cLeft);

        // Password
        cLeft.gridy = 3;
        cLeft.gridx = 0;
        cLeft.insets.left = 0;
        cLeft.anchor = GridBagConstraints.EAST;
        cLeft.weightx = 0;
        cLeft.fill = GridBagConstraints.NONE;
        pnFieldsLeft.add(lblPass, cLeft);

        cLeft.gridx = 1;
        cLeft.insets.left = 10;
        cLeft.anchor = GridBagConstraints.CENTER;
        cLeft.weightx = 1;
        cLeft.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsLeft.add(txtPass, cLeft);

        // Confirm password
        cLeft.gridy = 4;
        cLeft.gridx = 0;
        cLeft.insets.left = 0;
        cLeft.anchor = GridBagConstraints.EAST;
        cLeft.weightx = 0;
        cLeft.fill = GridBagConstraints.NONE;
        pnFieldsLeft.add(lblConfirm, cLeft);

        cLeft.gridx = 1;
        cLeft.insets.left = 10;
        cLeft.anchor = GridBagConstraints.CENTER;
        cLeft.weightx = 1;
        cLeft.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsLeft.add(txtConfirm, cLeft);

        // Build east panel and form
        lblSecurity = new JLabel("Security Questions", SwingConstants.CENTER);
        lblSecurity.setFont(new Font("Calibri", Font.BOLD, 24));
        lblSSN = new JLabel("Last 4 digits of SSN:", SwingConstants.RIGHT);
        lblSSN.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblBirthdate = new JLabel("Date of birth (yyyy-mm-dd):", SwingConstants.RIGHT);
        lblBirthdate.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblJob = new JLabel("First Job:", SwingConstants.RIGHT);
        lblJob.setFont(new Font("Calibri", Font.PLAIN, 18));
        txtSSN = new JTextField(24);
        txtBirthdate = new JTextField(24);
        txtJob = new JTextField(24);

        // Use GridBagLayout
        pnFieldsRight = new JPanel(new GridBagLayout());
        pnFieldsRight.setBackground(Color.WHITE);
        pnFieldsRight.setBorder(BorderFactory.createEmptyBorder(0, 60, 10, 30));
        pnFieldsRight.setPreferredSize(new Dimension(500, 200));
        GridBagConstraints cRight = new GridBagConstraints();
        cRight.ipady = 5;

        // Security questions
        cRight.gridwidth = 2;
        cRight.weightx = 0.5;
        pnFieldsRight.add(lblSecurity, cRight);

        // Last 4 digits of SSN
        cRight.gridwidth = 1;
        cRight.gridy = 1;
        cRight.anchor = GridBagConstraints.EAST;
        cRight.weightx = 0;
        pnFieldsRight.add(lblSSN, cRight);

        cRight.gridx = 1;
        cRight.insets = new Insets(0, 10, 0, 0);
        cRight.anchor = GridBagConstraints.CENTER;
        cRight.weightx = 1;
        cRight.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsRight.add(txtSSN, cRight);

        // Date of birth
        cRight.gridy = 2;
        cRight.gridx = 0;
        cRight.insets.left = 0;
        cRight.anchor = GridBagConstraints.EAST;
        cRight.weightx = 0;
        cRight.fill = GridBagConstraints.NONE;
        pnFieldsRight.add(lblBirthdate, cRight);

        cRight.gridx = 1;
        cRight.insets.left = 10;
        cRight.anchor = GridBagConstraints.CENTER;
        cRight.weightx = 1;
        cRight.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsRight.add(txtBirthdate, cRight);

        // First job
        cRight.gridy = 3;
        cRight.gridx = 0;
        cRight.insets.left = 0;
        cRight.anchor = GridBagConstraints.EAST;
        cRight.weightx = 0;
        cRight.fill = GridBagConstraints.NONE;
        pnFieldsRight.add(lblJob, cRight);

        cRight.gridx = 1;
        cRight.insets.left = 10;
        cRight.anchor = GridBagConstraints.CENTER;
        cRight.weightx = 1;
        cRight.fill = GridBagConstraints.HORIZONTAL;
        pnFieldsRight.add(txtJob, cRight);

        // Build south panel and submit button
        pnSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnSouth.setBackground(Color.WHITE);
        btnSubmit = new ImageButton("Submit", FWCConfigurator.SUBMIT_IMG, 150, 50);
        pnSouth.add(btnSubmit);

        add(pnNorth, BorderLayout.NORTH);
        add(pnFieldsLeft, BorderLayout.WEST);
        add(pnFieldsRight, BorderLayout.EAST);
        add(pnSouth, BorderLayout.SOUTH);
    }

    public boolean verifyRegistration() {
        if (txtFirst.getText().equals("") || txtLast.getText().equals("") ||
                txtUser.getText().equals("") || String.valueOf(txtPass.getPassword()).equals("") ||
                String.valueOf(txtConfirm.getPassword()).equals("") || txtSSN.getText().equals("") ||
                txtBirthdate.getText().equals("") || txtJob.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter all required information.", "Registration Failed",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Check if passwords match
        if (!String.valueOf(txtPass.getPassword()).equals(
                String.valueOf(txtConfirm.getPassword()))) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.", "Registration Failed",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Check if SSN is only 4 numbers
        if (txtSSN.getText().length() != 4) {
            JOptionPane.showMessageDialog(null, "Last 4 digits of SSN are invalid.", "Registration Failed",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!StringUtils.isNumeric(txtSSN.getText())) {
            JOptionPane.showMessageDialog(null, "Last 4 digits of SSN are invalid.", "Registration Failed",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Check birthdate format
        try {
            String birth = txtBirthdate.getText(), fixedBirth;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            Date result = sdf.parse(birth);
            fixedBirth = sdf.format(result);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Please fix birthday format.", "Registration Failed",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public void setSubmitListener(ActionListener submitListener) {
        btnSubmit.addActionListener(submitListener);
    }
}