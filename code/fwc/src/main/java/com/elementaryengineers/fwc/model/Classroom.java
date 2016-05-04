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
 * 
 * 
 * Created by sarahakk on 4/22/16.
 */
public class Classroom {

    private int classID;
    private String className;
    private ArrayList<Student> students;

    /**
     * Constructor for new class, unknown ID.
     * @param name
     */
    public Classroom(String name) {
        this.classID = -1;
        this.className = name;
        this.students = new ArrayList<>();
    }

    public Classroom(int id, String name) {
        this.classID = id;
        this.className = name;
        this.students = FWCConfigurator.getDbConn().getClassroomStudents(id);
    }

    public int getClassID() {
        return classID;
    }

    public String getClassName() {
        return className;
    }

    public ArrayList<Student> getStudents() {
        students = FWCConfigurator.getDbConn().getClassroomStudents(classID);
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

    public ArrayList<Student> searchStudents(String keyword) {
        keyword.toLowerCase();

        List<Student> results = students.stream().filter(stu ->
                stu.getFirstName().toLowerCase().contains(keyword) ||
                stu.getLastName().toLowerCase().contains(keyword) ||
                FWCConfigurator.getDifficulties().get(stu.getDifficultyID())
                        .getDescription().toLowerCase().contains(keyword) ||
                stu.getUsername().toLowerCase().contains(keyword)
                ).collect(Collectors.toList());

        return new ArrayList<>(results);
    }
}