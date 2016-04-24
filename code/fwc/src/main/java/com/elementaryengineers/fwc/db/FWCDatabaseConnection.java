/*
 * The CONN.java file is used to connect the files with a database 
to do the authentication process for a proper login.

 */

package com.elementaryengineers.fwc.db;

/**
 *
 * @author olgasheehan
 */
import com.elementaryengineers.fwc.model.*;

import java.sql.*;
import java.util.ArrayList;

public class FWCDatabaseConnection implements DatabaseConnection {

    public void c() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb", "root", "root");
        //return con;
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
    public void createDatabase() {

    }

    /**
     * Connect to the local MySQL server and execute "USE our_db_name"
     * so that queries we run later run on our database.
     *
     * @return
     */
    @Override
    public boolean connect() {
        return false;
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
        // DUMMY DATA --- this is just a placeholder to be able to login to the FWC!
        return new Teacher(0, "shakkoum", "Sara", "Hakkoum",
                "C004EE0C55A1E4548FB211DC142BCC8A5C68E94D7FF615AC", // password salt
                "3F9073CF53B93A066DE125757A647BE7F2DDABCA05B12CF4", // password hash
                1, 10, 3, 10, new ArrayList<Classroom>(), new ArrayList<Worksheet>(), false);
    }

    /**
     * Get all teachers, creating a Teacher object for each,
     * which also includes their information from the User table.
     *
     * @return
     */
    @Override
    public ArrayList<Teacher> getAllTeachers() {
        return null;
    }

    /**
     * Get all admins, creating an Admin object for each,
     * which also includes their information from the User table.
     *
     * @return
     */
    @Override
    public ArrayList<Admin> getAllAdmins() {
        return null;
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
        return false;
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
        return false;
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
        return false;
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
        return false;
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
        return false;
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
        return false;
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
        return false;
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
        return false;
    }

    /**
     * Add this class to the database, updating the Class table.
     *
     * @param classroom
     * @return
     */
    @Override
    public boolean createClassroom(Classroom classroom) {
        return false;
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
        return false;
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
        return false;
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
        return false;
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
        return null;
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
        return null;
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
        return null;
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
        return null;
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

    }
}
