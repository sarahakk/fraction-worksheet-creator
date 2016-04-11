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

    //  Flags for fraction generation
    //  These signal if a pair of fractions should have matching denominators
    public final static int GEN_DENOM_UNMATCHED = 0;
    public final static int GEN_DENOM_MATCHED = 1;

    //  These signal if fractions are allowed to evaluate to 1 or greater
    public final static int GEN_WHOLENUM_YES = 0;
    public final static int GEN_WHOLENUM_NO = 1;

    //  Flags for the answersheet generation
    public static final int WORKSHEET_ONLY = 1;
    public static final int ANSWER_SHEET = 2;
    public static final int ANSWER_ONLY = 3;

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