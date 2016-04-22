package com.elementaryengineers.fwc.model;

import java.util.ArrayList;

/**
 * Created by sarahakk on 4/22/16.
 */
public class Classroom {

    private int classID;
    private String className;
    private ArrayList<Student> students;

    public Classroom(int id, String name) {
        this.classID = id;
        this.className = name;
        this.students = new ArrayList<>();
    }

    public int getClassID() {
        return classID;
    }

    public String getClassName() {
        return className;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean addStudent(Student newStudent) {
        // TODO: talk to DB
        return true;
    }

    public boolean removeStudent(Student student) {
        // TODO: talk to DB
        return true;
    }

    public ArrayList<Student> search(String keyword) {
        ArrayList<Student> results = null;
        return results;
    }

    public boolean createClass() {
        // TODO: talk to DB
        return true;
    }

    public boolean deleteCLass() {
        // TODO: talk to DB
        return true;
    }
}