package com.elementaryengineers.fwc.db;

import com.elementaryengineers.fwc.model.Admin;
import com.elementaryengineers.fwc.model.Student;
import com.elementaryengineers.fwc.model.Teacher;

/**
 * Created by sarahakk on 4/10/16.
 */
public class FWCConfigurator {

    private static DatabaseConnection dbConn;

    private static Teacher teacher;
    private static Student student;
    private static Admin admin;

    public static void setTeacher(Teacher teacher) {
        FWCConfigurator.teacher = teacher;
    }

    public static void setStudent(Student student) {
        FWCConfigurator.student = student;
    }

    public static void setAdmin(Admin admin) {
        FWCConfigurator.admin = admin;
    }
}