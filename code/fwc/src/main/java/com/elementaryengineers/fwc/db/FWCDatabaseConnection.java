/*
 * The CONN.java file is used to connect the files with a database 
to do the authentication process for a proper login.
 */

package com.elementaryengineers.fwc.db;

import com.elementaryengineers.fwc.model.*;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FWCDatabaseConnection implements DatabaseConnection {

	protected Connection conn;
	protected Statement stmt;
	final String USER = "root";
	final String PASS = "ETHAN";
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost/";

	public FWCDatabaseConnection()  {
		boolean Initialize = false;

        try {
			Initialize = connect();

			//stmt = conn.createStatement();
			//createDatabase();
			//String sql = "Use FWC_Creator";
			//stmt.execute(sql);
		}
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
		}

        System.out.println( Initialize ? "new Database Initialized." : "Using preexisting database");
	}

   	/* public User getUser(String username) {
        // DUMMY DATA --- this is just a placeholder to be able to login to the FWC!
        /*
        return new Teacher(0, "shakkoum", "Sara", "Hakkoum",
                "C004EE0C55A1E4548FB211DC142BCC8A5C68E94D7FF615AC", // password salt
                "3F9073CF53B93A066DE125757A647BE7F2DDABCA05B12CF4", // password hash
                new ArrayList<Classroom>(), new ArrayList<Worksheet>(), false);

        return new Admin(0, "admin", "Sara", "Hakkoum", "C004EE0C55A1E4548FB211DC142BCC8A5C68E94D7FF615AC",
                "3F9073CF53B93A066DE125757A647BE7F2DDABCA05B12CF4", "C004EE0C55A1E4548FB211DC142BCC8A5C68E94D7FF615AC",
                "3F9073CF53B93A066DE125757A647BE7F2DDABCA05B12CF4", "C004EE0C55A1E4548FB211DC142BCC8A5C68E94D7FF615AC",
                "3F9073CF53B93A066DE125757A647BE7F2DDABCA05B12CF4", "C004EE0C55A1E4548FB211DC142BCC8A5C68E94D7FF615AC",
                "3F9073CF53B93A066DE125757A647BE7F2DDABCA05B12CF4");
        return null;
    }
    */

	/**
	 * Create the database from scratch. Do this before connecting,
	 * and if the database already exists, the "CREATE DATABASE" SQL
	 * statement, when executed, should throw an SQLException. If it
	 * does, then you know you can connect to the database.
	 * If it doesn't, the method should create all the tables we need
	 * in the database.
	 */
	@Override
	public void createDatabase() {
		try {
			String NewInput = null;

			File filen = new File("/Users/Fiero/Documents/SoftwareEngineering/file");
			Scanner File = new Scanner("filen");
			while (File.hasNext()) {
				String Input = File.nextLine();
				if (Input.equals(" ")) {
					stmt.execute(Input);
				} else {
					NewInput += (Input);
				}
				if (NewInput.equals(" ")) {
					stmt.execute(NewInput);
				}

			}
		} catch (SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			//Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			//finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}//end finally try
		}//end try
	}//end main

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
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			try {
				createDatabase();
				dintiliaze = true;
			} catch (Exception e) {
				dintiliaze = false;
				//Handle errors for Class.forName
				e.printStackTrace();
			}
			//finally block used to close resources

		return dintiliaze;
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
		String un = null;
		try
		{
			ResultSet rs;

			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sqlTeacher= (" Select * from Teacher Where Username = '" + username + "'");

			String sqlStudent= (" Select * from Student Where Username = '" + username + "'");
			String sqladmin= (" Select * from Administrator Where Username = '" + username + "'");
			String sql = ("Select  * from  User Where username= '" + username + "'");
			rs = stmt.executeQuery(sql);
			stmt.execute(sqlTeacher);
			String Username = rs.getString("Username");
			String FirstName = rs.getString("FirstName");
			String LastName = rs.getString("LastName");
			String Salt = rs.getString("PasswordSalt");
			String Hash = rs.getString("PasswordHalt");

		 	rs = stmt.executeQuery(sqlTeacher);// rs.next

			if ( rs.next()){
				rs.beforeFirst();
				//Teacher teacher = new Teacher( 	rs.getInt("TeacherID"), Username, FirstName,
						//LastName, Salt,Hash,rs.getString("ResetPassword"));
			}
			else {
				ResultSet RS2 = stmt.executeQuery(sqlStudent);
				if (RS2.next()) {
					RS2.beforeFirst();
					//Student student = new Student(RS2.getInt("StudentID"), Username, FirstName, LastName,
							//Salt, Hash, rs.getString("Classroom"), RS2.getBoolean("resetPassRequest"));
				} else {
					ResultSet RS3 = stmt.executeQuery(sqladmin);
					if(RS3.next()){
						RS3.beforeFirst();
						Admin admin = new Admin(RS3.getInt("AdminID"),Username,FirstName,LastName,Salt,Hash,
								RS3.getString("last4SSNSalt"), RS3.getString("last4SSNHash"), RS3.getString("birthdateSalt"),
								RS3.getString("birthdateHash"),RS3.getString("firstJobSalt"), RS3.getString("firstJobHash"));
					}
				}
			}

			while (rs.next()) {
				//Retrieve by column name
				un = rs.getString("username");
			}

			String sqlstudent = ("Select "  + username + " from students");
		} catch (SQLException se)
		{
			//Handle errors for JDBC
			se.printStackTrace();
		} finally
		{
			//finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}//end finally try
		}//end try

		System.out.println("Goodbye!");
		return null;
	}

	/**
	 * Get all teachers, creating a Teacher object for each,
	 * which also includes their information from the User table.
	 *
	 * @return
	 */
	@Override
	public ArrayList<Teacher> getAllTeachers() {
		ArrayList<Teacher> results = null;

		try
		{
			String sql = ("Select * from Teacher");
			ResultSet rs = stmt.executeQuery(sql);
			results = new ArrayList<>();
			while (rs.next()) {
				//results.add(rs.getString(1));
			}
		} catch (SQLException se)
		{
			//Handle errors for JDBC
			se.printStackTrace();
		} finally
		{
			//finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}//end finally try
		}//end try

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
		ArrayList<Admin> results = null;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");

			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			String sql = ("Select * from Admin");
			ResultSet rs = stmt.executeQuery(sql);
			results = new ArrayList<>();
			while (rs.next()) {
				//results.add(rs.getString(1));
			}
		} catch (SQLException | ClassNotFoundException se)
		{
			//Handle errors for JDBC
			se.printStackTrace();
		} finally
		{
			//finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}//end finally try
		}//end try

		System.out.println("Goodbye!");
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
		String sql = "INSERT INTO Admin VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";
		PreparedStatement regStmt = null;

		try {
			ResultSet rs = stmt.executeQuery(sql);
			regStmt = conn.prepareStatement(sql);
			regStmt.setString(1, String.valueOf(rs.getInt("AdminID")));
			regStmt.setString(2, rs.getString("first"));
			regStmt.setString(3, rs.getString("last"));
			regStmt.setString(4, rs.getString("passSalt"));
			regStmt.setString(5, rs.getString("passHash"));
			regStmt.setString(6, rs.getString("last4SSNSalt"));
			regStmt.setString(7, rs.getString("last4SSNHash"));
			regStmt.setString(8, rs.getString("birthdateSalt"));
			regStmt.setString(9, rs.getString("birthdateHash"));
			regStmt.setString(10, rs.getString("firstJobSalt"));
			regStmt.setString(11, rs.getString("firstJobHash"));

			regStmt.execute();
		} catch(SQLException se) {

		} finally {
			try {
				if (regStmt != null) {
					regStmt.close();
				}
			} catch (SQLException se2) {

			}
		}

		return true;
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
	    String sql = "UPDATE Admin SET first=?, last=?, passSalt=?, passHash=?, last4SSNSalt=? , Last4SSNHash" +
			    " birthdateSalt, birthdateHash, firstJobSalt, firstJobHash" +
			    "WHERE username=?;";
	    PreparedStatement regStmt = null;

	    try {
		    ResultSet rs = stmt.executeQuery(sql);
		    regStmt = conn.prepareStatement(sql);
		    regStmt.setString(1, String.valueOf(rs.getInt("AdminID")));
		    regStmt.setString(2, rs.getString("first"));
		    regStmt.setString(3, rs.getString("last"));
		    regStmt.setString(4, rs.getString("passSalt"));
		    regStmt.setString(5, rs.getString("passHash"));
		    regStmt.setString(6, rs.getString("last4SSNSalt"));
		    regStmt.setString(7, rs.getString("last4SSNHash"));
		    regStmt.setString(8, rs.getString("birthdateSalt"));
		    regStmt.setString(9, rs.getString("birthdateHash"));
		    regStmt.setString(10, rs.getString("firstJobSalt"));
		    regStmt.setString(11, rs.getString("firstJobHash"));

		    regStmt.execute();
	    }
		catch(SQLException se) {

	    } finally {
		    try {
			    if(regStmt != null) {
				    regStmt.close();
			    }
		    } catch(SQLException se2) {

		    }
	    }

		return true;
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
		try
		{
			stmt = conn.createStatement();
			String sql = ("Delete * From Admin Where AdminID =" + admin.getAdminID());
			stmt.executeUpdate(sql);
		}
		catch (SQLException se)
		{
			//Handle errors for JDBC
			se.printStackTrace();
		} //end try

		return false;
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
	    String sql = "INSERT INTO Teacher VALUES(?, ?, ?, ?, ?, ?, ?);";
	    PreparedStatement regStmt = null;

	    try {
		    ResultSet rs = stmt.executeQuery(sql);
		    regStmt = conn.prepareStatement(sql);
		    regStmt.setInt(1, (rs.getInt("TeacherID")));
		    regStmt.setString(2, rs.getString("first"));
		    regStmt.setString(3, rs.getString("last"));
		    regStmt.setString(4, rs.getString("passSalt"));
		    regStmt.setString(5, rs.getString("passHash"));
		    regStmt.setBoolean(7, rs.getBoolean("ResetPassRequested"));

		    regStmt.execute();
	    } catch (SQLException se) {

	    } finally {
		    try {
			    if (regStmt != null) {
				    regStmt.close();
			    }
		    } catch (SQLException se2) {

		    }
	    }

	    return true;
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
	    String sql = "UPDATE Teacher SET first=?, last=?, passSalt=?, passHash=?" + "ResetPassRequested = "+
			    "WHERE TeacherID=?;";
	    PreparedStatement regStmt = null;

	    try {
		    ResultSet rs = stmt.executeQuery(sql);
		    regStmt = conn.prepareStatement(sql);
		    regStmt.setInt(1, rs.getInt("TeacherID"));
		    regStmt.setString(2, rs.getString("first"));
		    regStmt.setString(3, rs.getString("last"));
		    regStmt.setString(4, rs.getString("passSalt"));
		    regStmt.setString(5, rs.getString("passHash"));
		    regStmt.setBoolean(7, rs.getBoolean("ResetPassRequested"));

		    regStmt.execute();
	    }
	    catch(SQLException se) {

	    } finally {
		    try {
			    if(regStmt != null) {
				    regStmt.close();
			    }
		    } catch(SQLException se2) {

		    }
	    }

	    return true;
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
	    try
	    {
		    stmt = conn.createStatement();
		    String sql = ("Delete * From Teacher Where TeacherID =" + teacher.getTeacherID());
		    stmt.executeUpdate(sql);
	    } catch (SQLException se)
	    {
		    //Handle errors for JDBC
		    se.printStackTrace();
	    } //end try

	    return true;
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
	    String sql = "INSERT INTO Teacher VALUES(?, ?, ?, ?, ?, , ?, ?,?);";
	    PreparedStatement regStmt = null;

	    try {
		    ResultSet rs = stmt.executeQuery(sql);
		    regStmt = conn.prepareStatement(sql);
		    regStmt.setInt(1, rs.getInt("StudentID"));
		    regStmt.setInt(2, rs.getInt("difficultyID"));
		    regStmt.setString(3, rs.getString("user"));
		    regStmt.setString(4, rs.getString("first"));
		    regStmt.setString(5, rs.getString("last"));
		    regStmt.setString(6, rs.getString("salt"));
		    regStmt.setString(6, rs.getString("hash"));
		    regStmt.setString(7, rs.getString("classroom"));
		    regStmt.setBoolean(8, rs.getBoolean("ResetPassRequested"));

		    regStmt.execute();
	    } catch (SQLException se) {

	    } finally {
		    try {
			    if (regStmt != null) {
				    regStmt.close();
			    }
		    } catch (SQLException se2) {

		    }
	    }

	    return true;
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
	    String sql = "UPDATE Teacher SET DifficultyID=?, user=?, first=?, " +
			    "last=?, salt=?, hash =?, classroom=?" + "ResetPassRequested = "+
			    "WHERE TeacherID=?;";
	    PreparedStatement regStmt = null;

	    try {
		    ResultSet rs = stmt.executeQuery(sql);
		    regStmt = conn.prepareStatement(sql);
		    regStmt.setInt(1, rs.getInt("StudentID"));
		    regStmt.setInt(2, rs.getInt("difficultyID"));
		    regStmt.setString(3, rs.getString("user"));
		    regStmt.setString(4, rs.getString("first"));
		    regStmt.setString(5, rs.getString("last"));
		    regStmt.setString(6, rs.getString("salt"));
		    regStmt.setString(6, rs.getString("hash"));
		    regStmt.setString(7, rs.getString("classroom"));
		    regStmt.setBoolean(9, rs.getBoolean("ResetPassRequested"));

		    regStmt.execute();
	    }
	    catch(SQLException se) {

	    } finally {
		    try {
			    if(regStmt != null) {
				    regStmt.close();
			    }
		    } catch(SQLException se2) {

		    }
	    }

	    return true;
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
    public boolean deleteStudent(Student student)   {
	    try
	    {
			stmt = conn.createStatement();
		    String sql = ("Delete * From Student Where StudnetID =" + student.getStudentID());
		    stmt.executeUpdate(sql);
	    }
		catch (SQLException se)
	    {
		    //Handle errors for JDBC
		    se.printStackTrace();
	    } //end try

	    return true;
    }

    /**
     * Add this class to the database, updating the Class table.
     *
     * @param classroom
     * @return
     */
    @Override
    public boolean createClassroom(Classroom classroom) {
	    String sql = "INSERT INTO Classroom VALUES(?, ?);";
	    PreparedStatement regStmt = null;

	    try {
		    ResultSet rs = stmt.executeQuery(sql);
		    regStmt = conn.prepareStatement(sql);
		    regStmt.setInt(1, rs.getInt("ClassID"));
		    regStmt.setString(2, rs.getString("Name"));

		    regStmt.execute();
	    } catch (SQLException se) {

	    } finally {
		    try {
			    if (regStmt != null) {
				    regStmt.close();
			    }
		    } catch (SQLException se2) {

		    }
	    }

	    return true;
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
	    String sql = "UPDATE Classroom SET Name=? "+
			    "WHERE ID=?;";
	    PreparedStatement regStmt = null;
	    try {
		    ResultSet rs = stmt.executeQuery(sql);
		    regStmt = conn.prepareStatement(sql);
		    regStmt.setInt(1, rs.getInt("ClassID"));
		    regStmt.setString(2, rs.getString("Name"));

		    regStmt.execute();
	    }
	    catch(SQLException se) {

	    } finally {
		    try {
			    if(regStmt != null) {
				    regStmt.close();
			    }
		    } catch(SQLException se2) {

		    }
	    }

	    return true;
    }

    /**
     * Delete all students that are in the class, too!
     * If database is set up to cascade the deletion of a class
     * to students in the class, you won't need to do it manually.
     *
     * @param classID
     * @return
     */
    @Override
    public boolean deleteClassroom(int classID) {
	    try
	    {
			stmt = conn.createStatement();
		    String sql = ("Delete * From Classroom Where ID =" + classID);
		    stmt.executeUpdate(sql);
	    }
		catch (SQLException se)
	    {
		    //Handle errors for JDBC
		    se.printStackTrace();
	    } //end try

	    return true;
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
	    try
	    {
			stmt = conn.createStatement();
		    String sql = ("Delete * From Worksheet Where WorksheetID =" + worksheetID);
		    stmt.executeUpdate(sql);
	    }
		catch (SQLException se)
	    {
		    //Handle errors for JDBC
		    se.printStackTrace();
	    } //end try

	    return true;
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
		String sql = "SELECT * FROM Classroom WHERE TeacherID?;";
		PreparedStatement selectStmt = null;
		ResultSet rs = null;
		ArrayList<Classroom> matches = new ArrayList<>();

		try {
			selectStmt = conn.prepareStatement(sql);
			selectStmt.setInt(1, teacherID);
		 	rs = selectStmt.executeQuery();

			while(rs.next()) {
				Classroom p = new Classroom(rs.getInt(1), rs.getString(2));
				matches.add(p);
			}
		} catch(SQLException se) {

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
	    String sql = "SELECT * FROM Student WHERE classID?;";
	    PreparedStatement selectStmt = null;
	    ResultSet rs = null;
	    ArrayList<Student> matches = new ArrayList<>();

	    try {
		    selectStmt = conn.prepareStatement(sql);
		    selectStmt.setInt(1, classID);
		    rs = selectStmt.executeQuery();

		    while(rs.next()) {
			    //Student p = new Student(rs.getInt("StudentID"), rs.getString("User"), rs.getString("First"), rs.getString("Last"),
				//	    rs.getString("Salt"), rs.getString("Hash"),rs.getString("Classroom"), rs.getBoolean("resetPassRequest"));
			    //matches.add(p);
		    }
	    }
		catch(SQLException se) {
	    } finally {
		    try {
			    if (selectStmt != null) {
				    selectStmt.close();
			    }
			    if (rs != null) {
				    rs.close();
			    }
		    } catch (SQLException se2) {
		    }
	    }

	    return matches;
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
	    String sql = "SELECT * FROM Worksheet WHERE username?;";
	    PreparedStatement selectStmt = null;
	    ResultSet rs = null;
	    ArrayList<Worksheet> matches = new ArrayList<>();

	    try {
		    selectStmt = conn.prepareStatement(sql);
		    selectStmt.setString(1,username);
		    rs = selectStmt.executeQuery();

		    while(rs.next()) {

				Worksheet p = new Worksheet(rs.getInt("seed"), rs.getString("exercise"), rs.getInt("difficultyID"));
			    matches.add(p);
		    }
	    }
		catch(SQLException se) {
	    } finally {
		    try {
			    if (selectStmt != null) {
				    selectStmt.close();
			    }
			    if (rs != null) {
				    rs.close();
			    }
		    } catch (SQLException se2) {
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
		String sql = "SELECT * Difficulties WHERE *;";
		PreparedStatement selectStmt = null;
		ResultSet rs = null;
		ArrayList<Difficulty> matches = new ArrayList<>();

		try {
			selectStmt = conn.prepareStatement(sql);
			selectStmt.setString(1," ");
			rs = selectStmt.executeQuery();

			while(rs.next()) {
				Worksheet p = new Worksheet(rs.getInt("seed"), rs.getString("exercise") ,rs.getInt("difficultyID"));
				//matches.add(p);
			}
		}
		catch(SQLException se) {
		} finally {
			try {
				if (selectStmt != null) {
					selectStmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException se2) {
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
        return false;
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
        return false;
    }

    /**
     * This method should display a GUI pop-up when a database-specific error
     * occurs, because the rest of the GUI won't know about it.
     * Use javax.swing.JOptionPane! That's Java's GUI pop-up class.
     * Example: JOptionPane.showMessageDialog(null, "Please check your username and password.", "Login Error", JOptionPane.ERROR_MESSAGE);
     * @param message
     */
    @Override
    public void displayErrorPopup(String message) {

    }

    /**
     * This method should close the database connection.
     */
    @Override
    public void closeConnection() {
		try {
	    	conn.close();
		    stmt.close();
	    } catch (SQLException e) {
		    e.printStackTrace();
	    }
    }
}