package com.elementaryengineers.fwc.db;

import com.elementaryengineers.fwc.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * This interface contains all the methods that will be needed
 * by the FWC in the database connection implementation.
 * They are mainly needed for updating and getting data to and
 * from the database.
 * All the methods should return a boolean indicating whether
 * the database query passed or failed.
 * If an object is supposed to be returned, return null if an
 * error occurred.
 * Also note that the implementing class should be responsible
 * for displaying an error pop-up, because the class calling these
 * methods has no idea what exactly may have went wrong!
 * Created by sarahakk on 4/21/16.
 */
public interface DatabaseConnection {

    /**
     * Create the database from scratch. Do this before connecting,
     * and if the database already exists, the "CREATE DATABASE" SQL
     * statement, when executed, should throw an SQLException. If it
     * does, then you know you can connect to the database.
     * If it doesn't, the method should create all the tables we need
     * in the database.
     */
    public void createDatabase();

    /**
     * Connect to the local MySQL server and execute "USE our_db_name"
     * so that queries we run later run on our database.
     * @return
     */
    public boolean connect() throws ClassNotFoundException, SQLException;

    /**
     * This should also figure out if the user is a teacher/student/admin,
     * and then return the Teacher/Student/Admin object, NOT a User object,
     * because that only has info that's common for all users.
     * @param username
     * @return
     */
    public User getUser(String username);

    /**
     * Get all teachers, creating a Teacher object for each,
     * which also includes their information from the User table.
     * @return
     */
    public List<String> getAllTeachers();

    /**
     * Get all admins, creating an Admin object for each,
     * which also includes their information from the User table.
     * @return
     */
    public ArrayList<String> getAllAdmins();

    /**
     * Add this admin to the database, updating the
     * Administrator and User tables!
     * @param admin
     * @return
     */
    public boolean createAdmin(Admin admin);

    /**
     * Update this admin by getting all their attributes
     * and updating them in the Admin and User tables. No need
     * to check if their attributes are actually different from
     * what's in the database, just update them anyway.
     * No need to check the AdminID, that shouldn't change.
     * @param admin
     * @return
     */
    public boolean updateAdmin(Admin admin);

    /**
     * Delete this admin by removing them from the Administrator
     * and User tables.
     * @param admin
     * @return
     */
    public boolean deleteAdmin(Admin admin);

    /**
     * Add this teacher to the database, updating the
     * Teacher and User tables! You don't need to worry about
     * a new teacher's worksheets or classes, there wouldn't be any.
     * @param teacher
     * @return
     */
    public boolean createTeacher(Teacher teacher);

    /**
     * Update this teacher by getting all their attributes
     * and updating them in the Teacher and User tables. No need
     * to check if their attributes are actually different from
     * what's in the database, just update them anyway.
     * No need to check the TeacherID, that shouldn't change.
     * @param teacher
     * @return
     */
    public boolean updateTeacher(Teacher teacher);

    /**
     * Delete this teacher from the Teacher and User tables, as
     * well as all of this teacher's Worksheets, Classes, and Students,
     * in the Worksheet, Class, and Student tables, respectively.
     * If cascade on delete is enabled for those tables, then when the
     * teacher is deleted, they would be deleted automatically and you
     * don't need to do it manually. Please try that and test to make
     * sure it works!
     * @param teacher
     * @return
     */
    public boolean deleteTeacher(Teacher teacher);

    /**
     * Add this student to the database, updating the
     * Student and User tables! You don't need to worry about
     * a new student's worksheets, there wouldn't be any.
     * @param student
     * @return
     */
    public boolean createStudent(Student student);

    /**
     * Update this student by getting all their attributes
     * and updating them in the Student and User tables. No need
     * to check if their attributes are actually different from
     * what's in the database, just update them anyway.
     * No need to check the StudentID, that shouldn't change.
     * @param student
     * @return
     */
    public boolean updateStudent(Student student);

    /**
     * Delete this student from the Student and User tables, as
     * well as all of this student's Worksheets
     * in the Worksheet table.
     * If cascade on delete is enabled, then when the
     * student is deleted, the worksheets would be deleted automatically and you
     * don't need to do it manually. Please try that and test to make
     * sure it works!
     * @param student
     * @return
     */
    public boolean deleteStudent(Student student);

    /**
     * Add this class to the database, updating the Class table.
     * @param classroom
     * @return
     */
    public boolean createClassroom(Classroom classroom);

    /**
     * Update this class by getting its name
     * and updating it in the Class table. No need
     * to check if it's actually different from
     * what's in the database, just update it anyway.
     * No need to check the ClassID, that shouldn't change.
     * @param classroom
     * @return
     */
    public boolean updateClassroom(Classroom classroom);

    /**
     * Delete all students that are in the class, too!
     * If database is set up to cascade the deletion of a class
     * to students in the class, you won't need to do it manually.
     * @param classID
     * @return
     */
    public boolean deleteClassroom(int classID);

    /**
     * Delete the worksheet record identified by the given
     * worksheet ID.
     * @param worksheetID
     * @return
     */
    public boolean deleteWorksheet(int worksheetID);

    /**
     * Return a list of Classroom objects, each representing a class
     * from the Class table that is taught by a given teacher.
     * @param teacherID
     * @return
     */
    public ArrayList<Classroom> getTeacherClasses(int teacherID);

    /**
     * Return a list of Student objects, each representing a student
     * from the Student table that is in a given class.
     * @param classID
     * @return
     */
    public ArrayList<Student> getClassroomStudents(int classID);

    /**
     * Return a list of Worksheet objects, each representing a worksheet
     * from the Worksheet table that was created by a given user.
     * @param username
     * @return
     */
    public ArrayList<Worksheet> getUserWorksheets(String username);

    /**
     * Return a list of Difficulty objects, each representing a difficulty
     * level in the Difficulty table. The 3 standard difficulties should
     * already exist in that table. We just need them in an ArrayList so
     * they can be used by the GUI.
     * @return
     */
    public ArrayList<Difficulty> getDifficulties();

    /**
     * Check if the Administrator has ANY users. If not, the GUI needs to
     * show the admin registration panel.
     * @return
     */
    public boolean doesAdminExist();

    /**
     * Check if a given username is available by getting the list of
     * existing usernames from the User table and checking if it exists.
     * @param username
     * @return
     */
    public boolean isUsernameAvailable(String username);

    /**
     * This method should display a GUI pop-up when a database-specific error
     * occurs, because the rest of the GUI won't know about it.
     * Use javax.swing.JOptionPane! That's Java's GUI pop-up class.
     * @param message
     */
    public void displayErrorPopup(String message);

    /**
     * This method should close the database connection.
     */
    public void closeConnection();
}
