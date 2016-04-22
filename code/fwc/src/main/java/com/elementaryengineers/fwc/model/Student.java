package com.elementaryengineers.fwc.model;

import java.util.ArrayList;

/**
 * Created by sarahakk on 4/10/16.
 */
public class Student extends User {

    private int studentID;
    private Classroom classroom;
    private Difficulty difficulty;
    private ArrayList<Worksheet> history;

    public Student(int studentID, String user, String first, String last, String salt, String hash,
                   Classroom classroom, Difficulty difficulty) {
        super(user, first, last, salt, hash);
        this.studentID = studentID;
        this.classroom = classroom;
        this.difficulty = difficulty;
    }

    public int getStudentID() {
        return studentID;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public ArrayList<Worksheet> getHistory() {
        return history;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
