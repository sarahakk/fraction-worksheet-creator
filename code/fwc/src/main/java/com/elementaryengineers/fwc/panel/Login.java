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


package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.db.FWCConfigurator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * TODO
 **/
public class Login extends JPanel {

    private JPanel pnFields, pnSouth, pnWrapper;
    private JLabel lblUser, lblPass;
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin, btnForgotPass;

    public Login() {
        super(new BorderLayout());
        this.setBackground(FWCConfigurator.bgColor);
        this.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        lblUser = new JLabel("Username:", SwingConstants.RIGHT);
        lblUser.setFont(new Font("Calibri", Font.BOLD, 18));
        lblPass = new JLabel("Password:", SwingConstants.RIGHT);
        lblPass.setFont(new Font("Calibri", Font.BOLD, 18));
        txtUser = new JTextField(24);
        txtPass = new JPasswordField(24);

        pnFields = new JPanel(new GridBagLayout());
        pnFields.setBackground(FWCConfigurator.bgColor);
        pnFields.setBorder(BorderFactory.createCompoundBorder(
                new TitledBorder("Welcome to the Fraction Worksheet Creator!"),
                new EmptyBorder(10, 10, 10, 10)));
        pnFields.setPreferredSize(new Dimension(400, 100));
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

        c.gridy = 1;
        c.gridx = 0;
        c.insets.left = 0;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        pnFields.add(lblPass, c);

        c.gridx = 1;
        c.insets.left = 10;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnFields.add(txtPass, c);

        pnWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnWrapper.setBackground(FWCConfigurator.bgColor);
        pnWrapper.add(pnFields);

        pnSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnSouth.setBackground(FWCConfigurator.bgColor);
        btnLogin = new ImageButton("Login", FWCConfigurator.LOGIN_IMG, 150, 50);
        btnForgotPass = new ImageButton("Forgot password?", FWCConfigurator.FORGOT_PASSW_IMG, 150, 50);
        pnSouth.add(btnLogin);
        pnSouth.add(btnForgotPass);

        this.add(pnWrapper, BorderLayout.CENTER);
        this.add(pnSouth, BorderLayout.SOUTH);
    }

    public void setLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }

    public void setForgotPassListener(ActionListener listener) {
        btnForgotPass.addActionListener(listener);
    }

    public String getUsernameText() {
        return txtUser.getText();
    }

    public String getPasswordText() {
        return String.valueOf(txtPass.getPassword());
    }

    public void clearFields() {
        txtUser.setText("");
        txtPass.setText("");
    }
}