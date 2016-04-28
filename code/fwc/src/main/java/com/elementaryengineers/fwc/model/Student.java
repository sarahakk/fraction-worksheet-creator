package com.elementaryengineers.fwc.model;

import com.elementaryengineers.fwc.db.FWCConfigurator;

import java.util.ArrayList;

/**
 * Created by sarahakk on 4/10/16.
 */
public class Student extends User {

    private int studentID, difficultyID, classID;
    private boolean resetPassRequested;
    private String className;
    private ArrayList<Worksheet> history;

    /**
     * Constructor for creating a brand new Student.
     */
    public Student(String user, String first, String last, String password,
                   int difficultyID, int classID, String className) {
        super(user, first, last, password);
        setType(UserType.STUDENT);
        this.studentID = -1; // To be set by database (autoincrement)
        this.difficultyID = difficultyID;
        this.classID = classID;
        this.className = className;
        this.history = new ArrayList<>();
        this.resetPassRequested = false;
    }

    /**
     * Constructor for creating an existing Student from the database.
     */
    public Student(int studentID, int difficultyID, String user, String first, String last, String salt, String hash,
                   int classID, String className, boolean resetPassRequested) {
        super(user, first, last, salt, hash);
        setType(UserType.STUDENT);
        this.studentID = studentID;
        this.difficultyID = difficultyID;
        this.classID = classID;
        this.className = className;
        this.history = FWCConfigurator.getDbConn().getUserWorksheets(user);
        this.resetPassRequested = resetPassRequested;
    }

    public int getStudentID() {
        return studentID;
    }

    public boolean isResetPassRequested() {
        return resetPassRequested;
    }

    public int getClassID() {
        return classID;
    }

    public String getClassName() {
        return className;
    }

    public int getDifficultyID() {
        return difficultyID;
    }

    public ArrayList<Worksheet> getHistory() {
        history = FWCConfigurator.getDbConn().getUserWorksheets(getUsername());
        return history;
    }

    public void setClassroom(Classroom classroom) {
        this.classID = classroom.getClassID();
        this.className = classroom.getClassName();
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public void setDifficultyID(int difficultyID) {
        this.difficultyID = difficultyID;
    }

    public void setResetPassRequested(boolean option) {
        this.resetPassRequested = option;
    }

    public void addWorksheet(Worksheet worksheet) {
        this.history.add(worksheet);
        FWCConfigurator.getDbConn().createWorksheet(worksheet);
    }
}