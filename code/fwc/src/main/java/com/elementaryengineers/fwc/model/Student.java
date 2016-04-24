package com.elementaryengineers.fwc.model;

import java.util.ArrayList;

/**
 * Created by sarahakk on 4/10/16.
 */
public class Student extends User {

    private int studentID, difficultyID;
    private Classroom classroom;
    private ArrayList<Worksheet> history;

    public Student(int studentID, String user, String first, String last, String salt, String hash,
                   Classroom classroom, int difficultyID) {
        super(user, first, last, salt, hash);
        this.studentID = studentID;
        this.classroom = classroom;
        this.difficultyID = difficultyID;
    }

    public int getStudentID() {
        return studentID;
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
}
