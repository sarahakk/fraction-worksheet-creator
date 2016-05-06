/*
 * The CONN.java file is used to connect the files with a database 
to do the authentication process for a proper login.
 */

package com.elementaryengineers.fwc.db;

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

import com.elementaryengineers.fwc.model.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class FWCDatabaseConnection implements DatabaseConnection {

	protected Connection conn;
	protected Statement stmt;
	final String USER = "root";
	final String PASS = "boston2013"; //"ETHAN";
	final String DB_URL = "jdbc:mysql://localhost/";

	public FWCDatabaseConnection()  {
		boolean initialize = false;

        try {
			initialize = connect();
		}
        catch(SQLException se) {
            displayErrorPopup("Could not connect to the database at " + DB_URL);
        }
        catch(ClassNotFoundException cnfe) {
            displayErrorPopup("Could not register the JDBC driver.");
        }

        /*
        System.out.println(initialize ? "New database initialized." : "Using" +
				" preexisting database.");
        */
	}

    /**
     * Connect to the local MySQL server and execute "USE our_db_name"
     * so that queries we run later run on our database.
     *
     * @return
     */
    @Override
    public boolean connect() throws ClassNotFoundException, SQLException {
        boolean dintiliaze;

        Class.forName("com.mysql.jdbc.Driver");
        //System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();

        try {
            createDatabase();
            dintiliaze = true;
        }
        catch (SQLException e) {
            //e.printStackTrace();
            dintiliaze = false;
            String sql = "USE FWC_Creator";
            stmt.execute(sql);
        }

        return dintiliaze;
    }

	/**
	 * Create the database from scratch. Do this before connecting,
	 * and if the database already exists, the "CREATE DATABASE" SQL
	 * statement, when executed, should throw an SQLException. If it
	 * does, then you know you can connect to the database.
	 * If it doesn't, the method should create all the tables we need
	 * in the database.
	 */
	@Override
	public void createDatabase() throws SQLException {
        String sql = "CREATE DATABASE FWC_Creator";
        stmt.execute(sql);

        sql = "USE FWC_Creator";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS `FWC_Creator`.`User` (" +
                "`Username` VARCHAR(50) NOT NULL," +
                "`FirstName` VARCHAR(50) NOT NULL," +
                "`LastName` VARCHAR(50) NOT NULL," +
                "`PasswordSalt` VARCHAR(50) NOT NULL," +
                "`PasswordHash` VARCHAR(50) NOT NULL," +
                "PRIMARY KEY (`Username`));";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS `FWC_Creator`.`Teacher` (" +
                "`TeacherID` INT NOT NULL AUTO_INCREMENT," +
                "`Username` VARCHAR(50) NOT NULL," +
                "`MinNumerator` INT NULL," +
                "`MaxNumerator` INT NULL," +
                "`MinDenominator` INT NULL," +
                "`MaxDenominator` INT NULL," +
                "`ResetPassword` TINYINT(1) NOT NULL," +
                "PRIMARY KEY (`TeacherID`)," +
                "INDEX `TeacherUsername_idx` (`Username` ASC)," +
                "CONSTRAINT `TeacherUsername` " +
                "FOREIGN KEY (`Username`) " +
                "REFERENCES `FWC_Creator`.`User` (`Username`) " +
                "ON DELETE CASCADE " +
                "ON UPDATE NO ACTION);";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS `FWC_Creator`.`Class` (" +
                "`ClassID` INT NOT NULL AUTO_INCREMENT," +
                "`TeacherID` INT NOT NULL," +
                "`ClassName` VARCHAR(50) NOT NULL," +
                "PRIMARY KEY (`ClassID`)," +
                "INDEX `Teacher_idx` (`TeacherID` ASC)," +
                "CONSTRAINT `Teacher` " +
                "FOREIGN KEY (`TeacherID`) " +
                "REFERENCES `FWC_Creator`.`Teacher` (`TeacherID`) " +
                "ON DELETE CASCADE " +
                "ON UPDATE NO ACTION);";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS `FWC_Creator`.`Difficulty` (" +
                "`DifficultyID` INT NOT NULL," +
                "`Description` VARCHAR(50) NOT NULL," +
                "PRIMARY KEY (`DifficultyID`));";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS `FWC_Creator`.`Student` (" +
                "`StudentID` INT NOT NULL AUTO_INCREMENT," +
                "`Class` INT NOT NULL," +
                "`Username` VARCHAR(50) NOT NULL," +
                "`DifficultyLevel` INT NULL," +
                "`ResetPassword` TINYINT(1) NOT NULL," +
                "PRIMARY KEY (`StudentID`)," +
                "INDEX `StudentUsername_idx` (`Username` ASC)," +
                "INDEX `DifficultyID_idx` (`DifficultyLevel` ASC)," +
                "INDEX `ClassID_idx` (`Class` ASC)," +
                "CONSTRAINT `StudentUsername` " +
                "FOREIGN KEY (`Username`) " +
                "REFERENCES `FWC_Creator`.`User` (`Username`) " +
                "ON DELETE CASCADE " +
                "ON UPDATE NO ACTION," +
                "CONSTRAINT `DifficultyID` " +
                "FOREIGN KEY (`DifficultyLevel`) " +
                "REFERENCES `FWC_Creator`.`Difficulty` (`DifficultyID`) " +
                "ON DELETE SET NULL " +
                "ON UPDATE CASCADE," +
                "CONSTRAINT `ClassID` " +
                "FOREIGN KEY (`Class`) " +
                "REFERENCES `FWC_Creator`.`Class` (`ClassID`) " +
                "ON DELETE CASCADE " +
                "ON UPDATE NO ACTION);";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS `FWC_Creator`.`Administrator` (" +
                "`AdminID` INT NOT NULL AUTO_INCREMENT," +
                "`Username` VARCHAR(50) NOT NULL," +
                "`SQ_SSNSalt` VARCHAR(50) NOT NULL," +
                "`SQ_BirthdateSalt` VARCHAR(50) NOT NULL," +
                "`SQ_FirstJobSalt` VARCHAR(50) NOT NULL," +
                "`SQ_SSNHash` VARCHAR(50) NOT NULL," +
                "`SQ_BirthdateHash` VARCHAR(50) NOT NULL," +
                "`SQ_FirstJobHash` VARCHAR(50) NOT NULL," +
                "PRIMARY KEY (`AdminID`)," +
                "INDEX `Username_idx` (`Username` ASC)," +
                "CONSTRAINT `Username` " +
                "FOREIGN KEY (`Username`) " +
                "REFERENCES `FWC_Creator`.`User` (`Username`) " +
                "ON DELETE CASCADE " +
                "ON UPDATE CASCADE);";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS `FWC_Creator`.`Worksheet` (" +
                "`WorksheetID` INT NOT NULL AUTO_INCREMENT," +
                "`Username` VARCHAR(50) NOT NULL," +
                "`Seed` INT NOT NULL," +
                "`DateCreated` VARCHAR(50) NOT NULL," +
                "`DifficultyID` INT NULL," +
                "`Exercise` VARCHAR(50) NOT NULL," +
                "PRIMARY KEY (`WorksheetID`)," +
                "INDEX `Creator_idx` (`Username` ASC)," +
                "INDEX `DifficultyID_idx` (`DifficultyID` ASC)," +
                "CONSTRAINT `Creator` " +
                "FOREIGN KEY (`Username`) " +
                "REFERENCES `FWC_Creator`.`User` (`Username`) " +
                "ON DELETE CASCADE " +
                "ON UPDATE NO ACTION," +
                "CONSTRAINT `WSDifficultyID` " +
                "FOREIGN KEY (`DifficultyID`) " +
                "REFERENCES `FWC_Creator`.`Difficulty` (`DifficultyID`) " +
                "ON DELETE SET NULL " +
                "ON UPDATE CASCADE);";
        stmt.execute(sql);

        sql = "INSERT INTO Difficulty VALUES(0, \"Beginner\");";
        stmt.execute(sql);

        sql = "INSERT INTO Difficulty VALUES(1, \"Intermediate\");";
        stmt.execute(sql);

        sql = "INSERT INTO Difficulty VALUES(2, \"Advanced\");";
        stmt.execute(sql);
	}

	/**
	 * This should also figure out if the user is a teacher/student/admin,
	 * and then return the Teacher/Student/Admin object, NOT a User object,
	 * because that only has info that's common for all users.
	 *
	 * @param username
	 * @return
	 */
	@Override
	public User getUser(String username) {
        String sql = "SELECT * FROM User WHERE Username=?;";
        PreparedStatement selectStmt = null;
        ResultSet rs = null;
        String user, first, last, passSalt, passHash;
        User result = null;

		try
		{
            selectStmt = conn.prepareStatement(sql);
            selectStmt.setString(1, username);
            rs = selectStmt.executeQuery();

			if (rs.next()) {
				user = rs.getString("Username");
                first = rs.getString("FirstName");
                last = rs.getString("LastName");
                passSalt = rs.getString("PasswordSalt");
                passHash = rs.getString("PasswordHash");

                // Is user a teacher?
                sql = "SELECT * FROM Teacher WHERE Username=?;";

                selectStmt = conn.prepareStatement(sql);
                selectStmt.setString(1, username);
                rs = selectStmt.executeQuery();

                if (rs.next()) {
                    result = new Teacher(rs.getInt("TeacherID"), user, first,
                            last, passSalt, passHash, rs.getBoolean
                            ("ResetPassword"));
                }
                else {
                    // Is user a student?
                    sql = "SELECT * FROM Student WHERE Username=?;";

                    selectStmt = conn.prepareStatement(sql);
                    selectStmt.setString(1, username);
                    rs = selectStmt.executeQuery();

                    if (rs.next()) {
                        sql = "SELECT * FROM Class WHERE ClassID=?;";
                        selectStmt = conn.prepareStatement(sql);
                        selectStmt.setInt(1, rs.getInt("Class"));
                        ResultSet rs2 = selectStmt.executeQuery();

                        if (rs2.next()) {
                            result = new Student(rs.getInt("StudentID"), rs
                                    .getInt("DifficultyLevel"), user, first,
                                    last, passSalt, passHash, rs2.getInt
                                    ("ClassID"), rs2.getString("ClassName"),
                                    rs.getBoolean("ResetPassword"));
                        }

                        try {
                            rs2.close();
                        } catch(SQLException se2) {
                            se2.printStackTrace();
                        }
                    }
                    else {
                        // Is user an admin?
                        sql = "SELECT * FROM Administrator WHERE Username=?;";

                        selectStmt = conn.prepareStatement(sql);
                        selectStmt.setString(1, username);
                        rs = selectStmt.executeQuery();

                        if (rs.next()) {
                            result = new Admin(rs.getInt("AdminID"), user,
                                    first, last, passSalt, passHash, rs
                                    .getString("SQ_SSNSalt"), rs.getString
                                    ("SQ_SSNHash"), rs.getString
                                    ("SQ_BirthdateSalt"), rs.getString
                                    ("SQ_BirthdateHash"), rs.getString
                                    ("SQ_FirstJobSalt"), rs.getString
                                    ("SQ_FirstJobHash"));
                        }
                    }
                }
			}
		}
        catch (SQLException se)
		{
			se.printStackTrace();
		}
        finally {
            try {
                if (selectStmt != null) {
                    selectStmt.close();
                }

                if (rs != null) {
                    rs.close();
                }
            } catch(SQLException se2) {
                se2.printStackTrace();
            }
        }

		return result;
	}

	/**
	 * Get all teachers, creating a Teacher object for each,
	 * which also includes their information from the User table.
	 *
	 * @return
	 */
	@Override
	public ArrayList<Teacher> getAllTeachers() {
        String sql = "SELECT * FROM Teacher;";
        String sql2 = "SELECT * FROM User WHERE Username=?;";
        PreparedStatement selectStmt = null;
        ResultSet rs = null, rs2 = null;
        String user, first, last, passSalt, passHash;
        ArrayList<Teacher> results = new ArrayList<>();

		try
		{
            selectStmt = conn.prepareStatement(sql);
            rs = selectStmt.executeQuery();

            while (rs.next()) {
                // Get information from user table
                user = rs.getString("Username");
                selectStmt = conn.prepareStatement(sql2);
                selectStmt.setString(1, user);
                rs2 = selectStmt.executeQuery();

                if (rs2.next()) {
                    first = rs2.getString("FirstName");
                    last = rs2.getString("LastName");
                    passSalt = rs2.getString("PasswordSalt");
                    passHash = rs2.getString("PasswordHash");

                    results.add(new Teacher(rs.getInt("TeacherID"), user, first,
                            last, passSalt, passHash, rs.getBoolean
                            ("ResetPassword")));
                }
            }
		} catch (SQLException se)
        {
            se.printStackTrace();
        }
        finally {
            try {
                if (selectStmt != null) {
                    selectStmt.close();
                }

                if (rs != null) {
                    rs.close();
                }

                if (rs2 != null) {
                    rs2.close();
                }
            } catch(SQLException se2) {
                se2.printStackTrace();
            }
        }

		return results;
	}

	/**
	 * Get all admins, creating an Admin object for each,
	 * which also includes their information from the User table.
	 *
	 * @return
	 */
	@Override
	public ArrayList<Admin> getAllAdmins() {
        String sql = "SELECT * FROM Administrator;";
        String sql2 = "SELECT * FROM User WHERE Username=?;";
        PreparedStatement selectStmt = null;
        ResultSet rs = null, rs2 = null;
        String user, first, last, passSalt, passHash;
        ArrayList<Admin> results = new ArrayList<>();

        try
        {
            selectStmt = conn.prepareStatement(sql);
            rs = selectStmt.executeQuery();

            while (rs.next()) {
                // Get information from user table
                user = rs.getString("Username");
                selectStmt = conn.prepareStatement(sql2);
                selectStmt.setString(1, user);
                rs2 = selectStmt.executeQuery();

                if (rs2.next()) {
                    first = rs2.getString("FirstName");
                    last = rs2.getString("LastName");
                    passSalt = rs2.getString("PasswordSalt");
                    passHash = rs2.getString("PasswordHash");

                    results.add(new Admin(rs.getInt("AdminID"), user,
                            first, last, passSalt, passHash, rs
                            .getString("SQ_SSNSalt"), rs.getString
                            ("SQ_SSNHash"), rs.getString
                            ("SQ_BirthdateSalt"), rs.getString
                            ("SQ_BirthdateHash"), rs.getString
                            ("SQ_FirstJobSalt"), rs.getString
                            ("SQ_FirstJobHash")));
                }
            }
        } catch (SQLException se)
        {
            se.printStackTrace();
        }
        finally {
            try {
                if (selectStmt != null) {
                    selectStmt.close();
                }

                if (rs != null) {
                    rs.close();
                }

                if (rs2 != null) {
                    rs2.close();
                }
            } catch(SQLException se2) {
                se2.printStackTrace();
            }
        }

        return results;
	}

	/**
	 * Add this admin to the database, updating the
	 * Administrator and User tables!
	 *
	 * @param admin
	 * @return
	 */
	@Override
	public boolean createAdmin(Admin admin) {
        String sql1 = "INSERT INTO User VALUE(?, ?, ?, ?, ?);";
		String sql2 = "INSERT INTO Administrator VALUES(NULL, ?, ?, ?, ?, ?, " +
                "?, ?);";
		PreparedStatement regStmt = null;
        boolean result = true;

		try {
			regStmt = conn.prepareStatement(sql1);
			regStmt.setString(1, admin.getUsername());
            regStmt.setString(2, admin.getFirstName());
            regStmt.setString(3, admin.getLastName());
            regStmt.setString(4, admin.getPassword().getSalt());
            regStmt.setString(5, admin.getPassword().getHash());
            regStmt.execute();

            regStmt = conn.prepareStatement(sql2);
            regStmt.setString(1, admin.getUsername());
            regStmt.setString(2, admin.getLast4SSN().getSalt());
            regStmt.setString(3, admin.getBirthdate().getSalt());
            regStmt.setString(4, admin.getFirstJob().getSalt());
            regStmt.setString(5, admin.getLast4SSN().getHash());
            regStmt.setString(6, admin.getBirthdate().getHash());
            regStmt.setString(7, admin.getFirstJob().getHash());
            regStmt.execute();
		} catch(SQLException se) {
            se.printStackTrace();
            result = false;
		} finally {
			try {
				if (regStmt != null) {
					regStmt.close();
				}
			} catch (SQLException se2) {
                se2.printStackTrace();
                result = false;
			}
		}

		return result;
	}

    /**
     * Update this admin by getting all their attributes
     * and updating them in the Admin and User tables. No need
     * to check if their attributes are actually different from
     * what's in the database, just update them anyway.
     * No need to check the AdminID, that shouldn't change.
     *
     * @param admin
     * @return
     */
    @Override
    public boolean updateAdmin(Admin admin) {
	    String sql = "UPDATE User SET FirstName=?, LastName=?, " +
                "PasswordSalt=?, PasswordHash=? WHERE Username=?;";
        PreparedStatement regStmt = null;
        boolean result = true;

        try {
            regStmt = conn.prepareStatement(sql);
            regStmt.setString(1, admin.getFirstName());
            regStmt.setString(2, admin.getLastName());
            regStmt.setString(3, admin.getPassword().getSalt());
            regStmt.setString(4, admin.getPassword().getHash());
            regStmt.setString(5, admin.getUsername());
            regStmt.execute();
        }
        catch(SQLException se) {
            se.printStackTrace();
            result = false;
        }
        finally {
            try {
                if (regStmt != null) {
                    regStmt.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
                result = false;
            }
        }

        return result;
    }

    /**
     * Delete this admin by removing them from the Administrator
     * and User tables.
     *
     * @param admin
     * @return
     */
    @Override
    public boolean deleteAdmin(Admin admin) {
        String sql = "DELETE FROM User WHERE Username=?;";
        PreparedStatement regStmt = null;
        boolean result = true;

        try {
            regStmt = conn.prepareStatement(sql);
            regStmt.setString(1, admin.getUsername());
            regStmt.execute();
        }
        catch(SQLException se) {
            se.printStackTrace();
            result = false;
        }
        finally {
            try {
                if (regStmt != null) {
                    regStmt.close();
                }
            }
            catch (SQLException se2) {
                se2.printStackTrace();
                result = false;
            }
        }

        return result;
	}

    /**
     * Add this teacher to the database, updating the
     * Teacher and User tables! You don't need to worry about
     * a new teacher's worksheets or classes, there wouldn't be any.
     *
     * @param teacher
     * @return
     */
    @Override
    public boolean createTeacher(Teacher teacher) {
        String sql1 = "INSERT INTO User VALUE(?, ?, ?, ?, ?);";
        String sql2 = "INSERT INTO Teacher VALUES(NULL, ?, 0, 0, 0, 0, " +
                "0);";
        PreparedStatement regStmt = null;
        boolean result = true;

        try {
            regStmt = conn.prepareStatement(sql1);
            regStmt.setString(1, teacher.getUsername());
            regStmt.setString(2, teacher.getFirstName());
            regStmt.setString(3, teacher.getLastName());
            regStmt.setString(4, teacher.getPassword().getSalt());
            regStmt.setString(5, teacher.getPassword().getHash());
            regStmt.execute();

            regStmt = conn.prepareStatement(sql2);
            regStmt.setString(1, teacher.getUsername());
            regStmt.execute();
        }
        catch(SQLException se) {
            se.printStackTrace();
            result = false;
        }
        finally {
            try {
                if (regStmt != null) {
                    regStmt.close();
                }
            }
            catch (SQLException se2) {
                se2.printStackTrace();
                result = false;
            }
        }

        return result;
    }

    /**
     * Update this teacher by getting all their attributes
     * and updating them in the Teacher and User tables. No need
     * to check if their attributes are actually different from
     * what's in the database, just update them anyway.
     * No need to check the TeacherID, that shouldn't change.
     *
     * @param teacher
     * @return
     */
    @Override
    public boolean updateTeacher(Teacher teacher) {
        String sql = "UPDATE User SET FirstName=?, LastName=?, " +
                "PasswordSalt=?, PasswordHash=? WHERE Username=?;";
        String sql2 = "UPDATE Teacher SET ResetPassword=? WHERE TeacherID=?;";
        PreparedStatement regStmt = null;
        boolean result = true;

        try {
            regStmt = conn.prepareStatement(sql);
            regStmt.setString(1, teacher.getFirstName());
            regStmt.setString(2, teacher.getLastName());
            regStmt.setString(3, teacher.getPassword().getSalt());
            regStmt.setString(4, teacher.getPassword().getHash());
            regStmt.setString(5, teacher.getUsername());
            regStmt.execute();

            regStmt = conn.prepareStatement(sql2);
            regStmt.setBoolean(1, teacher.isResetPassRequested());
            regStmt.setInt(2, teacher.getTeacherID());
            regStmt.execute();
        }
        catch(SQLException se) {
            se.printStackTrace();
            result = false;
        }
        finally {
            try {
                if (regStmt != null) {
                    regStmt.close();
                }
            }
            catch (SQLException se2) {
                se2.printStackTrace();
                result = false;
            }
        }

        return result;
    }

	/**
     * Delete this teacher from the Teacher and User tables, as
     * well as all of this teacher's Worksheets, Classes, and Students,
     * in the Worksheet, Class, and Student tables, respectively.
     * If cascade on delete is enabled for those tables, then when the
     * teacher is deleted, they would be deleted automatically and you
     * don't need to do it manually. Please try that and test to make
     * sure it works!
     *
     * @param teacher
     * @return
     */
    @Override
    public boolean deleteTeacher(Teacher teacher) {
        String sql = "DELETE FROM User WHERE Username=?;";
        PreparedStatement regStmt = null;
        boolean result = true;

        try {
            // Loop over classes and delete each
            teacher.getClasses().stream().forEach(classroom ->
                    deleteClassroom(classroom));

            regStmt = conn.prepareStatement(sql);
            regStmt.setString(1, teacher.getUsername());
            regStmt.execute();
        }
        catch(SQLException se) {
            se.printStackTrace();
            result = false;
        }
        finally {
            try {
                if(regStmt != null) {
                    regStmt.close();
                }
            }
            catch(SQLException se2) {
                se2.printStackTrace();
                result = false;
            }
        }

        return result;
    }

    /**
     * Add this student to the database, updating the
     * Student and User tables! You don't need to worry about
     * a new student's worksheets, there wouldn't be any.
     *
     * @param student
     * @return
     */
    @Override
    public boolean createStudent(Student student) {
        String sql1 = "INSERT INTO User VALUE(?, ?, ?, ?, ?);";
        String sql2 = "INSERT INTO Student VALUES(NULL, ?, ?, ?, 0);";
        PreparedStatement regStmt = null;
        boolean result = true;

        try {
            regStmt = conn.prepareStatement(sql1);
            regStmt.setString(1, student.getUsername());
            regStmt.setString(2, student.getFirstName());
            regStmt.setString(3, student.getLastName());
            regStmt.setString(4, student.getPassword().getSalt());
            regStmt.setString(5, student.getPassword().getHash());
            regStmt.execute();

            regStmt = conn.prepareStatement(sql2);
            regStmt.setInt(1, student.getClassID());
            regStmt.setString(2, student.getUsername());
            regStmt.setInt(3, student.getDifficultyID());
            regStmt.execute();
        }
        catch(SQLException se) {
            se.printStackTrace();
            result = false;
        }
        finally {
            try {
                if (regStmt != null) {
                    regStmt.close();
                }
            }
            catch (SQLException se2) {
                se2.printStackTrace();
                result = false;
            }
        }

        return result;
    }

    /**
     * Update this student by getting all their attributes
     * and updating them in the Student and User tables. No need
     * to check if their attributes are actually different from
     * what's in the database, just update them anyway.
     * No need to check the StudentID, that shouldn't change.
     *
     * @param student
     * @return
     */
    @Override
    public boolean updateStudent(Student student) {
        String sql = "UPDATE User SET FirstName=?, LastName=?, " +
                "PasswordSalt=?, PasswordHash=? WHERE Username=?;";
        String sql2 = "UPDATE Student SET Class=?, DifficultyLevel=?, " +
                "ResetPassword=? WHERE StudentID=?;";
        PreparedStatement regStmt = null;
        boolean result = true;

        try {
            regStmt = conn.prepareStatement(sql);
            regStmt.setString(1, student.getFirstName());
            regStmt.setString(2, student.getLastName());
            regStmt.setString(3, student.getPassword().getSalt());
            regStmt.setString(4, student.getPassword().getHash());
            regStmt.setString(5, student.getUsername());
            regStmt.execute();

            regStmt = conn.prepareStatement(sql2);
            regStmt.setInt(1, student.getClassID());
            regStmt.setInt(2, student.getDifficultyID());
            regStmt.setBoolean(3, student.isResetPassRequested());
            regStmt.setInt(4, student.getStudentID());
            regStmt.execute();
        }
        catch(SQLException se) {
            se.printStackTrace();
            result = false;
        }
        finally {
            try {
                if (regStmt != null) {
                    regStmt.close();
                }
            }
            catch (SQLException se2) {
                se2.printStackTrace();
                result = false;
            }
        }

        return result;
    }

    /**
     * Delete this student from the Student and User tables, as
     * well as all of this student's Worksheets
     * in the Worksheet table.
     * If cascade on delete is enabled, then when the
     * student is deleted, the worksheets would be deleted automatically and you
     * don't need to do it manually. Please try that and test to make
     * sure it works!
     *
     * @param student
     * @return
     */
    @Override
    public boolean deleteStudent(Student student) {
        String sql = "DELETE FROM User WHERE Username=?;";
        PreparedStatement regStmt = null;
        boolean result = true;

        try {
            regStmt = conn.prepareStatement(sql);
            regStmt.setString(1, student.getUsername());
            regStmt.execute();
        }
        catch(SQLException se) {
            se.printStackTrace();
            result = false;
        }
        finally {
            try {
                if(regStmt != null) {
                    regStmt.close();
                }
            }
            catch(SQLException se2) {
                se2.printStackTrace();
                result = false;
            }
        }

        return result;
    }

    /**
     * Add this class to the database, updating the Class table.
     *
     * @param classroom
     * @return
     */
    @Override
    public boolean createClassroom(Classroom classroom) {
	    String sql = "INSERT INTO Class VALUES(NULL, ?, ?);";
        PreparedStatement regStmt = null;
        boolean result = true;

        try {
            regStmt = conn.prepareStatement(sql);
            regStmt.setInt(1, FWCConfigurator.getTeacher().getTeacherID());
            regStmt.setString(2, classroom.getClassName());
            regStmt.execute();
        }
        catch(SQLException se) {
            se.printStackTrace();
            result = false;
        }
        finally {
            try {
                if (regStmt != null) {
                    regStmt.close();
                }
            }
            catch (SQLException se2) {
                se2.printStackTrace();
                result = false;
            }
        }

        return result;
    }

	/**
     * Update this class by getting its name
     * and updating it in the Class table. No need
     * to check if it's actually different from
     * what's in the database, just update it anyway.
     * No need to check the ClassID, that shouldn't change.
     *
     * @param classroom
     * @return
     */
    @Override
    public boolean updateClassroom(Classroom classroom) {
	    String sql = "UPDATE Class SET ClassName=? "+
			    "WHERE ClassID=?;";
	    PreparedStatement regStmt = null;
        boolean result = true;

        try {
            regStmt = conn.prepareStatement(sql);
            regStmt.setString(1, classroom.getClassName());
            regStmt.setInt(2, classroom.getClassID());
            regStmt.execute();
        }
        catch(SQLException se) {
            se.printStackTrace();
            result = false;
        }
        finally {
            try {
                if (regStmt != null) {
                    regStmt.close();
                }
            }
            catch (SQLException se2) {
                se2.printStackTrace();
                result = false;
            }
        }

        return result;
    }

    /**
     * Delete all students that are in the class, too!
     * If database is set up to cascade the deletion of a class
     * to students in the class, you won't need to do it manually.
     *
     * @param classroom
     * @return
     */
    @Override
    public boolean deleteClassroom(Classroom classroom) {
        String sql = "DELETE FROM Class WHERE ClassID=?;";
        PreparedStatement regStmt = null;
        boolean result = true;

        try {
            // Loop over students and delete each
            classroom.getStudents().stream().forEach(student -> deleteStudent
                    (student));

            regStmt = conn.prepareStatement(sql);
            regStmt.setInt(1, classroom.getClassID());
            regStmt.execute();
        }
        catch (SQLException se) {
            se.printStackTrace();
            result = false;
        }
        finally {
            try {
                if(regStmt != null) {
                    regStmt.close();
                }
            }
            catch (SQLException se2) {
                se2.printStackTrace();
                result = false;
            }
        }

        return result;
    }

    /**
     * Add this worksheet to the database, updating the Worksheet table.
     *
     * @param ws
     * @return
     */
    public boolean createWorksheet(Worksheet ws) {
        String sql = "INSERT INTO Worksheet VALUES(NULL, ?, ?, ?, ?, ?);";
        PreparedStatement regStmt = null;
        boolean result = true;

        // Get user that is creating this worksheet
        String user = (FWCConfigurator.getUserType() == UserType.TEACHER) ?
                FWCConfigurator.getTeacher().getUsername() :
                FWCConfigurator.getStudent().getUsername();

        try {
            regStmt = conn.prepareStatement(sql);
            regStmt.setString(1, user);
            regStmt.setInt(2, ws.getSeed());
            regStmt.setString(3, ws.getDateCreated());
            regStmt.setInt(4, ws.getDifficultyID());
            regStmt.setString(5, ws.getExercise());
            regStmt.execute();
        }
        catch(SQLException se) {
            se.printStackTrace();
            result = false;
        }
        finally {
            try {
                if (regStmt != null) {
                    regStmt.close();
                }
            }
            catch (SQLException se2) {
                se2.printStackTrace();
                result = false;
            }
        }

        return result;
    }

    /**
     * Delete the worksheet record identified by the given
     * worksheet ID.
     *
     * @param worksheetID
     * @return
     */
    @Override
    public boolean deleteWorksheet(int worksheetID) {
        String sql = "DELETE FROM Worksheet WHERE WorksheetID=?;";
        PreparedStatement regStmt = null;
        boolean result = true;

        try {
            regStmt = conn.prepareStatement(sql);
            regStmt.setInt(1, worksheetID);
            regStmt.execute();
        }
        catch (SQLException se) {
            se.printStackTrace();
            result = false;
        }
        finally {
            try {
                if(regStmt != null) {
                    regStmt.close();
                }
            }
            catch (SQLException se2) {
                se2.printStackTrace();
                result = false;
            }
        }

        return result;
    }

    /**
     * Return a list of Classroom objects, each representing a class
     * from the Class table that is taught by a given teacher.
     *
     * @param teacherID
     * @return
     */
    @Override
    public ArrayList<Classroom> getTeacherClasses(int teacherID) {
		String sql = "SELECT * FROM Class WHERE TeacherID=?;";
		PreparedStatement selectStmt = null;
		ResultSet rs = null;
		ArrayList<Classroom> matches = new ArrayList<>();

		try {
			selectStmt = conn.prepareStatement(sql);
			selectStmt.setInt(1, teacherID);
		 	rs = selectStmt.executeQuery();

			while(rs.next()) {
				Classroom p = new Classroom(rs.getInt("ClassID"), rs.getString
                        ("ClassName"));
				matches.add(p);
			}
		} catch(SQLException se) {
            se.printStackTrace();
		} finally {
			try {
				if(selectStmt != null) {
					selectStmt.close();
				}

				if(rs != null) {
					rs.close();
				}
			}
			catch(SQLException se2) {
                se2.printStackTrace();
			}
		}

		return matches;
	}

	/**
	 * Return a list of Student objects, each representing a student
	 * from the Student table that is in a given class.
	 *
	 * @param classID
	 * @return
	 */
    @Override
    public ArrayList<Student> getClassroomStudents(int classID) {
        String sql = "SELECT * FROM Student WHERE Class=?;";
        String sql2 = "SELECT * FROM User WHERE Username=?;";
        String sql3 = "SELECT * FROM Class WHERE ClassID=?;";
        PreparedStatement selectStmt = null;
        ResultSet rs = null, rs2 = null, rs3 = null;
        String user, first, last, passSalt, passHash;
        ArrayList<Student> results = new ArrayList<>();

        try
        {
            selectStmt = conn.prepareStatement(sql);
            selectStmt.setInt(1, classID);
            rs = selectStmt.executeQuery();

            while (rs.next()) {
                // Get information from user table
                user = rs.getString("Username");
                selectStmt = conn.prepareStatement(sql2);
                selectStmt.setString(1, user);
                rs2 = selectStmt.executeQuery();

                if (rs2.next()) {
                    first = rs2.getString("FirstName");
                    last = rs2.getString("LastName");
                    passSalt = rs2.getString("PasswordSalt");
                    passHash = rs2.getString("PasswordHash");

                    selectStmt = conn.prepareStatement(sql3);
                    selectStmt.setInt(1, classID);
                    rs3 = selectStmt.executeQuery();

                    if (rs3.next()) {
                        results.add(new Student(rs.getInt("StudentID"), rs
                                .getInt("DifficultyLevel"), user, first,
                                last, passSalt, passHash, rs3.getInt
                                ("ClassID"), rs3.getString("ClassName"),
                                rs.getBoolean("ResetPassword")));
                    }
                }
            }
        } catch (SQLException se)
        {
            se.printStackTrace();
        }
        finally {
            try {
                if (selectStmt != null) {
                    selectStmt.close();
                }

                if (rs != null) {
                    rs.close();
                }

                if (rs2 != null) {
                    rs2.close();
                }

                if (rs3 != null) {
                    rs3.close();
                }
            } catch(SQLException se2) {
                se2.printStackTrace();
            }
        }

        return results;
    }

    /**
     * Return a list of Worksheet objects, each representing a worksheet
     * from the Worksheet table that was created by a given user.
     *
     * @param username
     * @return
     */
    @Override
    public ArrayList<Worksheet> getUserWorksheets(String username) {
	    String sql = "SELECT * FROM Worksheet WHERE Username=?;";
	    PreparedStatement selectStmt = null;
	    ResultSet rs = null;
	    ArrayList<Worksheet> matches = new ArrayList<>();

	    try {
		    selectStmt = conn.prepareStatement(sql);
		    selectStmt.setString(1, username);
		    rs = selectStmt.executeQuery();

		    while (rs.next()) {
				Worksheet p = new Worksheet(rs.getInt("WorksheetID"), rs.getInt
                        ("Seed"), rs.getString
                        ("Exercise"), rs.getInt("DifficultyID"), rs.getString
                        ("DateCreated"));
			    matches.add(p);
		    }
	    }
		catch(SQLException se) {
            se.printStackTrace();
	    }
        finally {
		    try {
			    if (selectStmt != null) {
				    selectStmt.close();
			    }
			    if (rs != null) {
				    rs.close();
			    }
		    } catch (SQLException se2) {
                se2.printStackTrace();
		    }
	    }

	    return matches;
    }

	/**
     * Return a list of Difficulty objects, each representing a difficulty
     * level in the Difficulty table. The 3 standard difficulties should
     * already exist in that table. We just need them in an ArrayList so
     * they can be used by the GUI.
     *
     * @return
     */
    @Override
    public ArrayList<Difficulty> getDifficulties() {
		String sql = "SELECT * FROM Difficulty;";
		PreparedStatement selectStmt = null;
		ResultSet rs = null;
		ArrayList<Difficulty> matches = new ArrayList<>();

		try {
			selectStmt = conn.prepareStatement(sql);
			rs = selectStmt.executeQuery();

			while (rs.next()) {
				Difficulty p = new Difficulty(rs.getInt("DifficultyID"), rs
                        .getString("Description"));
				matches.add(p);
			}
		}
		catch(SQLException se) {
            se.printStackTrace();
		} finally {
			try {
				if (selectStmt != null) {
					selectStmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException se2) {
                se2.printStackTrace();
            }
		}

		return matches;
	}

    /**
     * Check if the Administrator has ANY users. If not, the GUI needs to
     * show the admin registration panel.
     *
     * @return
     */
    @Override
    public boolean doesAdminExist() {
        String sql = "SELECT * FROM Administrator;";
        PreparedStatement selectStmt = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            selectStmt = conn.prepareStatement(sql);
            rs = selectStmt.executeQuery();

            if (rs.next()) { // Found at least 1 admin
                result = true;
            }
        }
        catch(SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (selectStmt != null) {
                    selectStmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Check if a given username is available by getting the list of
     * existing usernames from the User table and checking if it exists.
     *
     * @param username
     * @return
     */
    @Override
    public boolean isUsernameAvailable(String username) {
        String sql = "SELECT * FROM User WHERE Username=?;";
        PreparedStatement selectStmt = null;
        ResultSet rs = null;
        boolean result = true;

        try {
            selectStmt = conn.prepareStatement(sql);
            selectStmt.setString(1, username);
            rs = selectStmt.executeQuery();

            if (rs.next()) { // Username is used
                result = false;
            }
        }
        catch(SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (selectStmt != null) {
                    selectStmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }

        return result;
    }

    /**
     * This method should display a GUI pop-up when a database-specific error
     * occurs, because the rest of the GUI won't know about it.
     * Use javax.swing.JOptionPane! That's Java's GUI pop-up class.
     * @param message
     */
    @Override
    public void displayErrorPopup(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    /**
     * This closes the database connection.
     */
    @Override
    public void closeConnection() {
		try {
            if(stmt != null)
                stmt.close();
            if(conn != null)
                conn.close();
	    } catch (SQLException e) {
		    e.printStackTrace();
	    }
    }
}