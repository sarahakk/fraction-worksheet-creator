package com.elementaryengineers.fwc.model;

import com.elementaryengineers.fwc.db.FWCConfigurator;

import java.util.ArrayList;

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
    public Teacher(int teacherID, String user, String first, String last, String salt, String hash,
                   ArrayList<Classroom> classes, ArrayList<Worksheet> history, boolean resetPassRequested) {
        super(user, first, last, salt, hash);
        setType(UserType.TEACHER);
        this.teacherID = teacherID;
        this.classes = classes;
        this.history = history;
        this.resetPassRequested = resetPassRequested;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public boolean isResetPassRequested() {
        return resetPassRequested;
    }

    public ArrayList<Classroom> getClasses() {
        return classes;
    }

    public ArrayList<Worksheet> getHistory() {
        return history;
    }

    public void setResetPassRequested() {
        this.resetPassRequested = true;
    }

    public void addWorksheet(Worksheet worksheet) {
        this.history.add(worksheet);
        FWCConfigurator.getDbConn().updateTeacher(this);
    }
}
