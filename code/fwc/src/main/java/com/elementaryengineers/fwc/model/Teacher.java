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

package com.elementaryengineers.fwc.model;

import com.elementaryengineers.fwc.db.FWCConfigurator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sarahakk on 4/10/16.
 */
public class Teacher extends User {

    private int teacherID;
    private boolean resetPassRequested;
    private ArrayList<Classroom> classes;
    private ArrayList<Worksheet> history;

    /**
     * Constructor for creating a brand new Teacher.
     */
    public Teacher(String user, String first, String last, String password) {
        super(user, first, last, password);
        setType(UserType.TEACHER);
        this.teacherID = -1; // To be set by database (autoincrement)
        this.classes = new ArrayList<>();
        this.history = new ArrayList<>();
        this.resetPassRequested = false;
    }

    /**
     * Constructor for creating an existing Teacher from the database.
     */
    public Teacher(int teacherID, String user, String first, String last, 
                   String salt, String hash, boolean resetPassRequested) {
        super(user, first, last, salt, hash);
        setType(UserType.TEACHER);
        this.teacherID = teacherID;
        this.resetPassRequested = resetPassRequested;
        getClasses();
        getHistory();
    }

    public int getTeacherID() {
        return teacherID;
    }

    public boolean isResetPassRequested() {
        return resetPassRequested;
    }

    public ArrayList<Classroom> getClasses() {
        classes = FWCConfigurator.getDbConn().getTeacherClasses(teacherID);
        return classes;
    }

    public ArrayList<Worksheet> getHistory() {
        history = FWCConfigurator.getDbConn().getUserWorksheets(getUsername());
        return history;
    }

    public ArrayList<Student> getStudentsRequestedReset() {
        ArrayList<Student> results = new ArrayList<>();

        classes.stream().forEach(classroom -> classroom.getStudents()
                .stream().filter(s -> s.isResetPassRequested())
                .forEach(student -> results.add(student)));

        return results;
    }

    public void setResetPassRequested(boolean option) {
        this.resetPassRequested = option;
    }

    public void addWorksheet(Worksheet worksheet) {
        this.history.add(worksheet);
        FWCConfigurator.getDbConn().createWorksheet(worksheet);
    }

    public ArrayList<Classroom> searchClasses(String keyword) {
        String lowerKey = keyword.toLowerCase();

        List<Classroom> results = classes.stream().filter(c ->
                c.getClassName().toLowerCase().contains(lowerKey)
        ).collect(Collectors.toList());

        return new ArrayList<>(results);
    }
}