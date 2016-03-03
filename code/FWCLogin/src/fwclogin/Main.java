/*GUI login page in which the login is authenticated through 
the database for which database connectivity is done.
In the AdminHome.java file everythingis defined for the authentication.

The Main.java file runs the entire concept with the main class FWLogin.

The CONN.java file is used to connect the files with a database 
to do the authentication process for a proper login.

 * http://www.c-sharpcorner.com/UploadFile/9a9e6f/
graphical-user-interface-login-page-in-java/
 */
package fwclogin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import mysqlCONN.CONN;
import java.sql.*;
/**
 *
 * @author olgasheehan
 */

class FWCLogin extends JFrame implements ActionListener
{
    JLabel lblCompany,lblUser_Type,lblUser,lblPass;
    JComboBox CmbUser_Type;
    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin,btnCancel;
    String arr[]={"Admin","Teacher","Student"};
 
    public FWCLogin()
    {
        super("Fraction Worksheets Creator Login Authentification");
        setSize (500,400);
        setLocation (500, 280);
        //Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
 
        //this.setSize(dim.width, dim.height);
        this.setLayout(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        lblCompany=new JLabel("Login Authentification");
        lblCompany.setFont(new Font("Times New Roman",Font.BOLD,20));
        lblCompany.setForeground(Color.blue);
        lblUser_Type=new JLabel("User Type");
        lblUser=new JLabel("Username");
        
        lblPass=new JLabel("Password");
        txtUser=new JTextField();
        txtPass=new JPasswordField();
        CmbUser_Type=new JComboBox(arr);
        btnLogin=new JButton("Login");
        btnCancel=new JButton("Cancel");
 
        lblCompany.setBounds(150,30,400,30);
        
        lblUser_Type.setBounds(80, 70, 200, 25);
        CmbUser_Type.setBounds(200,70,210,25);
        
        lblUser.setBounds(80,110,200,25);
        txtUser.setBounds(200,110,210,25);
        
        lblPass.setBounds(80,160,240,25);
        txtPass.setBounds(200,160,210,25);
        
        btnLogin.setBounds(200,200,100,25);
        btnCancel.setBounds(300,200,100,25);
        
 
        this.add(btnCancel);
        this.add(btnLogin);
        this.add(CmbUser_Type);
        this.add(lblCompany);
        this.add(lblPass);
        this.add(lblUser);
        this.add(txtUser);
        this.add(lblUser_Type);
        this.add(txtPass);
 
        btnCancel.addActionListener(this);
        btnLogin.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
 
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
            CONN ob=new CONN();
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
}
 
public class Main {
 
    public static void main(String[] args)
    {
       FWCLogin ob=new FWCLogin();
       ob.setVisible(true);
    }
}
    

