package com.elementaryengineers.fwc.model;

import java.util.ArrayList;

/**
 * Created by sarahakk on 4/10/16.
 */
public class Teacher extends User {

    private int teacherID, minNumerator, maxNumerator, minDenominator, maxDenominator;
    private ArrayList<Classroom> classes;
    private ArrayList<Worksheet> history;

    public Teacher(int teacherID, String user, String first, String last, String salt, String hash,
                   int minNumerator, int maxNumerator, int minDenominator, int maxDenominator,
                   ArrayList<Classroom> classes, ArrayList<Worksheet> history) {
        super(user, first, last, salt, hash);
        this.teacherID = teacherID;
        this.minNumerator = minNumerator;
        this.maxNumerator = maxNumerator;
        this.minDenominator = minDenominator;
        this.maxDenominator = maxDenominator;
        this.classes = classes;
        this.history = history;
    }

    public void setMinNumerator(int minNumerator) {
        this.minNumerator = minNumerator;
    }

    public void setMaxNumerator(int maxNumerator) {
        this.maxNumerator = maxNumerator;
    }

    public void setMinDenominator(int minDenominator) {
        this.minDenominator = minDenominator;
    }

    public void setMaxDenominator(int maxDenominator) {
        this.maxDenominator = maxDenominator;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public int getMinNumerator() {
        return minNumerator;
    }

    public int getMaxNumerator() {
        return maxNumerator;
    }

    public int getMinDenominator() {
        return minDenominator;
    }

    public int getMaxDenominator() {
        return maxDenominator;
    }

    public ArrayList<Classroom> getClasses() {
        return classes;
    }

    public ArrayList<Worksheet> getHistory() {
        return history;
    }

    public boolean createTeacher() {
        // TODO: talk to database
        return true;
    }

    public boolean deleteTeacher() {
        // TODO: talk to database
        return true;
    }
}
