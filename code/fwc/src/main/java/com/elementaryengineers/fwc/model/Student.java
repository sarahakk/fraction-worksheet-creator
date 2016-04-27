package com.elementaryengineers.fwc.model;

import com.elementaryengineers.fwc.db.FWCConfigurator;

import java.util.ArrayList;

/**
 * Created by sarahakk on 4/10/16.
 */
public class Student extends User {

    private int studentID, difficultyID;
    private boolean resetPassRequested;
    private Classroom classroom;
    private ArrayList<Worksheet> history;

    /**
     * Constructor for creating a brand new Student.
     */
    public Student(String user, String first, String last, String password, int difficultyID, Classroom classroom) {
        super(user, first, last, password);
        setType(UserType.STUDENT);
        this.studentID = -1; // To be set by database (autoincrement)
        this.difficultyID = difficultyID;
        this.classroom = classroom;
        this.history = new ArrayList<>();
        this.resetPassRequested = false;
    }

    /**
     * Constructor for creating an existing Student from the database.
     */
    public Student(int studentID, int difficultyID, String user, String first, String last, String salt, String hash,
                   Classroom classroom, boolean resetPassRequested) {
        super(user, first, last, salt, hash);
        setType(UserType.STUDENT);
        this.studentID = studentID;
        this.difficultyID = difficultyID;
        this.classroom = classroom;
        this.history = FWCConfigurator.getDbConn().getUserWorksheets(user);
        this.resetPassRequested = resetPassRequested;
    }

    public int getStudentID() {
        return studentID;
    }

    public boolean isResetPassRequested() {
        return resetPassRequested;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public int getDifficultyID() {
        return difficultyID;
    }

    public ArrayList<Worksheet> getHistory() {
        return history;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public void setDifficultyID(int difficultyID) {
        this.difficultyID = difficultyID;
    }

    public void setResetPassRequested(boolean option) {
        this.resetPassRequested = option;
    }

    public void addWorksheet(Worksheet worksheet) {
        this.history.add(worksheet);
        FWCConfigurator.getDbConn().updateStudent(this);
    }

   
    
}
