package com.elementaryengineers.fwc.model;

/**
 * Created by sarahakk on 4/10/16.
 */
public class Student extends User {

    private int studentID, classID, difficultyID;

    public Student(int studentID, String user, String first, String last, String salt, String hash,
                   int classID, int difficultyID) {
        super(user, first, last, salt, hash);
        this.studentID = studentID;
        this.classID = classID;
        this.difficultyID = difficultyID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public void setDifficultyID(int difficultyID) {
        this.difficultyID = difficultyID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getClassID() {
        return classID;
    }

    public int getDifficultyID() {
        return difficultyID;
    }
}
