/*
 * The CONN.java file is used to connect the files with a database 
to do the authentication process for a proper login.

 */
package fwclogin;

/**
 *
 * @author olgasheehan
 */
import java.sql.*;
 
public class CONN {
 
    public Connection c() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb", "root", "root");
        return con;
    }
}
