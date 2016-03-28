package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;

/**
 * TODO
 **/
public class LoginPanel extends JPanel {

    JLabel lblCompany, lblUser, lblPass;
    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin,btnCancel;

    public LoginPanel() {
        setSize (500,400);
        setLocation (500, 280);

        lblCompany = new JLabel("Login Authentication");
        lblCompany.setFont(new Font("Times New Roman",Font.BOLD,20));
        lblCompany.setForeground(Color.blue);

        lblUser = new JLabel("Username");
        lblPass = new JLabel("Password");
        txtUser = new JTextField();
        txtPass = new JPasswordField();
        btnLogin = new JButton("Login");
        btnCancel = new JButton("Cancel");

        lblCompany.setBounds(150,30,400,30);
        lblUser.setBounds(80,110,200,25);
        lblPass.setBounds(80,160,240,25);
        txtUser.setBounds(200,110,210,25);
        txtPass.setBounds(200,160,210,25);
        btnLogin.setBounds(200,200,100,25);
        btnCancel.setBounds(300,200,100,25);

        this.add(lblCompany);
        this.add(lblUser);
        this.add(txtUser);
        this.add(lblPass);
        this.add(txtPass);
        this.add(btnLogin);
        this.add(btnCancel);

        // TODO
        //btnCancel.addActionListener(this);
        //btnLogin.addActionListener(this);
    }

    /*
    public void actionPerformed(ActionEvent e) {

        String u=txtUser.getText();
        String p=txtPass.getText();
        String ut=(String)CmbUser_Type.getSelectedItem();

        if(e.getSource()==btnLogin)
        {
            if(u.equals("")||u==null)
            {
                JOptionPane.showMessageDialog(rootPane, "Fill the username");
            }
            else
            {
                if(p.equals("")||p==null)
                {
                    JOptionPane.showMessageDialog(rootPane, "Fill the password");
                }
            }
        }

        if(e.getSource()==btnCancel)
        {
            System.exit(0);
        }
        try
        {
            DatabaseConnection ob=new DatabaseConnection();
            Connection con=ob.c();
            Statement stm=con.createStatement();
            ResultSet rst = null;

            if(ut.equals("Admin"))
            {
                rst=stm.executeQuery("select * from admin where username='"+
                        u+"' and password='"+p+"'");
                if(rst.next())
                {
                    AdminHome ob1=new AdminHome();
                    ob1.setVisible(true);
                    this.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane,
                            "Incorrect username or password");
                }

                if(ut.equals("Teacher"))
                {
                    rst=stm.executeQuery("select * from customer where "
                            + "username='"+u+"' and password='"+p+"'");
                }
                if(ut.equals("Student"));
                {
                    rst=stm.executeQuery("select * from user where "
                            + "username='"+u+"' and password='"+p+"'");
                }
            }
        }
        catch (Exception e1)
              {
            JOptionPane.showMessageDialog(rootPane, e1);
        }
    }
    */
}