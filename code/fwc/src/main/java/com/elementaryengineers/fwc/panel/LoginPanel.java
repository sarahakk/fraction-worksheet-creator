package com.elementaryengineers.fwc.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * TODO
 **/
public class LoginPanel extends JPanel {

    private JPanel pnNorth, pnFields, pnSouth, pnLogin, pnForgot;
    private JLabel lblTitle;
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin, btnForgotPass;

    public LoginPanel() {
        super(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        pnNorth = new JPanel((new FlowLayout(FlowLayout.CENTER)));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new JLabel("Login");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 32));
        lblTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        pnNorth.add(lblTitle);

        txtUser = new JTextField(24);
        txtPass = new JPasswordField(24);

        pnFields = new JPanel(new GridLayout(2, 2, 5, 10));
        pnFields.setBackground(Color.WHITE);
        pnFields.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
        pnFields.add(new JLabel("Username:", SwingConstants.CENTER));
        pnFields.add(txtUser);
        pnFields.add(new JLabel("Password:", SwingConstants.CENTER));
        pnFields.add(txtPass);

        pnSouth = new JPanel(new BorderLayout());
        pnSouth.setBackground(Color.WHITE);

        pnLogin = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnLogin.setBackground(Color.WHITE);
        btnLogin = new JButton("Login");
        pnLogin.add(btnLogin);

        pnForgot = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnForgot.setBackground(Color.WHITE);
        btnForgotPass = new JButton("Forgot Password?");
        pnForgot.add(btnForgotPass);

        pnSouth.add(pnLogin, BorderLayout.NORTH);
        pnSouth.add(pnForgot, BorderLayout.SOUTH);

        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnFields, BorderLayout.CENTER);
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