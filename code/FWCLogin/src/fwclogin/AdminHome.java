/*
 * In the AdminHome.java file everythingis defined for the authentication.
 */
package fwclogin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import mysqlCONN.CONN;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.Vector;
 
public class AdminHome extends JFrame {
 
    public AdminHome()throws Exception{
 
        super("Admin Home");
        Vector row;
        Vector data=new Vector();
        CONN ob=new CONN();
        Connection con=ob.c();
        Statement stm=con.createStatement();
        ResultSet rst=stm.executeQuery("select * from user");
 
        while(rst.next())
        {
            row=new Vector();
            row.add(rst.getString("UserID"));
            row.add(rst.getString("Username"));
            row.add(rst.getString("Password"));
            row.add(rst.getString("Name"));
            //row.add(rst.getString("Address"));
            //row.add(rst.getString("Contact"));
            data.add(row);
        }
 
        Vector cols=new Vector();
        cols.add("User ID");
        cols.add("Username");
        cols.add("Password");
        cols.add("Name");
        //cols.add("Address");
        //cols.add("Contact");
        
 
        TableModel model=new DefaultTableModel(data,cols);
        JTable table=new JTable(model);
        final TableRowSorter<TableModel> sorter;
        sorter=new TableRowSorter<TableModel>(model);
        table.setRowSorter(sorter);
        getContentPane().add(new JScrollPane(table));
        JPanel pnl=new JPanel();
        pnl.add(new JLabel("Search expression"));
        final JTextField txtFE=new JTextField(25);
        pnl.add(txtFE);
        final JButton btnSetFE=new JButton("Search");
        final JButton btnAdd=new JButton("Add User"); 
 
        ActionListener a1;
        a1=new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==btnSetFE)
                {
                    String expr=txtFE.getText();
                    sorter.setRowFilter(RowFilter.regexFilter(expr));
                    sorter.setSortKeys(null);
                }
                if(e.getSource()==btnAdd)
                {
 
                }
            } 
        };
 
        btnSetFE.addActionListener(a1);
        btnAdd.addActionListener(a1);
        pnl.add(btnSetFE);
        pnl.add(btnAdd);
        getContentPane().add(pnl,BorderLayout.SOUTH);
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
 
        this.setSize(dim.width,dim.height-40);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}