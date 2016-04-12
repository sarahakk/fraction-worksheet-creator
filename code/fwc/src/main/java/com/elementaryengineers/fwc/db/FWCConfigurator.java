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

    // Images in resources folder
    public static final String TITLE_IMG = "title.png";
    public static final String HELP_IMG = "help.png";
    public static final String ACCOUNT_IMG = "myaccount.png";
    public static final String LOGOUT_IMG = "logout.png";
    public static final String HOME_IMG = "home.png";
    public static final String TUTORIAL_IMG = "tutorial.png";
    public static final String HISTORY_IMG = "history.png";
    public static final String CLASSES_IMG = "classes.png";
    public static final String PASSWORDS_IMG = "passwords.png";
    public static final String TEACHER_HOME_IMG = "teacherhome.png";
    public static final String BEG1_IMG = "beginnerPC.png";
    public static final String BEG2_IMG = "beginnerAdd.png";
    public static final String BEG3_IMG = "beginnerLG.png";
    public static final String INT1_IMG = "intermediateAdd.png";
    public static final String INT2_IMG = "intermediateSub.png";
    public static final String INT3_IMG = "intermediateMD.png";
    public static final String ADV1_IMG = "advancedAdd.png";
    public static final String ADV2_IMG = "advancedSub.png";
    public static final String ADV3_IMG = "advancedMD.png";


    public static void setTeacher(Teacher teacher) {
        FWCConfigurator.teacher = teacher;
    }

    public static void setStudent(Student student) {
        FWCConfigurator.student = student;
    }

    public static void setAdmin(Admin admin) {
        FWCConfigurator.admin = admin;
    }

    public static Teacher getTeacher() {
        return teacher;
    }

    public static Student getStudent() {
        return student;
    }

    public static Admin getAdmin() {
        return admin;
    }
}