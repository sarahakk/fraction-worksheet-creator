package com.elementaryengineers.fwc.model;

import com.elementaryengineers.fwc.db.FWCConfigurator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        this.students.add(newStudent);
        return FWCConfigurator.getDbConn().updateClassroom(this);
    }

    public boolean removeStudent(Student student) {
        this.students.remove(student);
        return FWCConfigurator.getDbConn().deleteStudent(student);
    }

    public ArrayList<Student> search(String keyword) {
        List<Student> results = students.stream().filter(stu ->
                stu.getFirstName().contains(keyword) ||
                stu.getLastName().contains(keyword) ||
                FWCConfigurator.getDifficulties().get(stu.getDifficultyID()).getDescription().contains(keyword) ||
                stu.getUsername().contains(keyword)
                ).collect(Collectors.toList());

        return new ArrayList<>(results);
    }
}