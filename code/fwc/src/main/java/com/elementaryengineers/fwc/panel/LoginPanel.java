package com.elementaryengineers.fwc.panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * TODO
 **/
public class LoginPanel extends JPanel {

    private JPanel pnFields, pnSouth, pnLogin, pnForgot, pnExit, pnWrapper;
    private JLabel lblUser, lblPass;
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin, btnForgotPass, btnExit;

    public LoginPanel() {
        super(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        lblUser = new JLabel("Username:", SwingConstants.RIGHT);
        lblUser.setFont(new Font("Calibri", Font.BOLD, 18));
        lblPass = new JLabel("Password:", SwingConstants.RIGHT);
        lblPass.setFont(new Font("Calibri", Font.BOLD, 18));
        txtUser = new JTextField(24);
        txtPass = new JPasswordField(24);

        pnFields = new JPanel(new GridBagLayout());
        pnFields.setBackground(Color.WHITE);
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
        pnWrapper.setBackground(Color.WHITE);
        pnWrapper.add(pnFields);

        pnSouth = new JPanel(new BorderLayout());
        pnSouth.setBackground(Color.WHITE);

        pnLogin = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnLogin.setBackground(Color.WHITE);
        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Calibri", Font.BOLD, 16));
        btnLogin.setPreferredSize(new Dimension(100, 40));
        pnLogin.add(btnLogin);

        pnForgot = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnForgot.setBackground(Color.WHITE);
        btnForgotPass = new JButton("Forgot Password?");
        btnForgotPass.setFont(new Font("Calibri", Font.BOLD, 16));
        pnForgot.add(btnForgotPass);

        pnExit = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnExit.setBackground(Color.WHITE);
        btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Calibri", Font.BOLD, 16));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        pnExit.add(btnExit);

        pnSouth.add(pnLogin, BorderLayout.NORTH);
        pnSouth.add(pnForgot, BorderLayout.CENTER);
        pnSouth.add(pnExit, BorderLayout.SOUTH);

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