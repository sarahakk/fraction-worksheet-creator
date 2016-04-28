package com.elementaryengineers.fwc.db;

import com.elementaryengineers.fwc.model.*;

import java.util.ArrayList;

/**
 * Created by sarahakk on 4/10/16.
 */
public class FWCConfigurator {

    private static FWCDatabaseConnection dbConn;
    private static ArrayList<Difficulty> difficulties;      // List of the standard difficulties
    private static UserType userType;                       // Determines what kind of user is currently logged in
    private static Teacher teacher;
    private static Student student;
    private static Admin admin;
    private static Page currentPage;                        // Keeps track of the current page of the GUI (used by Help)

    //  Flags for fraction generation
    public static final int GEN_UNIQUE5       = 1;          // Used for L->G worksheet
    public static final int GEN_WHOLENUM_NO   = 2;          // Eliminate whole numbers
    public static final int GEN_DENOM_MATCHED = 4;          // Match denominator pairs

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
    
    //added another images by Olga

    public static final String ANSWER_IMG = "answerkey.png";
    public static final String ADDCLASS_IMG = "addclass.png";
    public static final String ADDSTUDENT_IMG = "addteacher.png";
    public static final String ADDTEACHER_IMG = "addteacher.png";
    
    public static final String BACK_IMG = "back.png";
    public static final String CLOSE_IMG = "close.png";
    public static final String EDIT_IMG = "edit.png";
    public static final String EXIT_IMG = "exit.png";
    
    public static final String DEL_CLASS_IMG = "deleteclass.png";
    public static final String DEL_SELECT_IMG = "deleteselected.png";
    public static final String DEL_STUDENT_IMG = "deletestudent.png";
    public static final String DEL_TEACHER_IMG = "deleteteacher.png";
    
    public static final String FORGOT_PASSW_IMG = "forgotpassword.png";
    public static final String LOGIN_IMG = "login.png";
    public static final String PRINT_SELECTED_IMG = "printselected.png";
    public static final String PROFILE_IMG = "profile.png";
    public static final String RESET_ALL_IMG = "resetall.png";
    public static final String RESET_PASSW_IMG = "resetpassword.png";
    public static final String RESET_SELECTED_IMG = "resetselected.png";
    public static final String ROSTER_IMG = "roster.png";
    public static final String SUBMIT_IMG = "submit.png";
    public static final String VIEW_ROSTER_IMG = "viewroster.png";
    public static final String WS_HISTORY_IMG = "worksheethistory.png";
    
    public static final String ADMIN_PASSW_RESET_IMG = "adminpasswordreset.png";
    public static final String ADMIN_REG_IMG = "adminregistration.png";
    public static final String CLASSES_TITLE_IMG = "classesTitle.png";
    public static final String CLASS_ROSTER_IMG = "classroster.png";
    public static final String CREATE_NEW_PASSW_IMG = "createnewpassword.png";
    public static final String EDIT_CLASS_IMG = "editclass.png";
    public static final String NEW_CLASS_IMG = "newclass.png";
    public static final String MY_ACCOUNT_TITLE_IMG = "myaccountTitle.png";
    public static final String REQ_PASSW_RESET_IMG = "requestpasswordreset.png";
    public static final String RESET_PASSW_TITLE_IMG = "resetpasswordTitle.png";
    
    public static final String STUDENT_ADV_ADD_IMG = "studentAdvADD.png";
    public static final String STUDENT_ADV_MD_IMG = "studentAdvMD.png";
    public static final String STUDENT_ADV_SUB_IMG = "studentAdvSub.png";
    public static final String STUDENT_ADV_IMG = "studentAdvanced.png";
    public static final String STUDENT_ADV_G_IMG = "studentAdvancedGray.png";
    
    public static final String STUDENT_BEG_ADD_IMG = "studentBegAddition.png";
    public static final String STUDENT_BEG_LG_IMG = "studentBegLG.png";
    public static final String STUDENT_BEG_PIE_IMG = "studentBegPieCharts.png";
    public static final String STUDENT_BEG_IMG = "studentBeginner.png";
    public static final String STUDENT_BEG_G_IMG = "studentBeginnerGray.png";
    
    public static final String STUDENT_INT_ADD_IMG = "studentIntAdd.png";
    public static final String STUDENT_INT_MD_IMG = "studentIntMD.png";
    public static final String STUDENT_INT_SUB_IMG = "studentIntSub.png";
    public static final String STUDENT_INT_IMG = "studentIntermediate.png";
    public static final String STUDENT_INT_G_IMG = "studentIntermediateGray.png";
    
    public static final String STUDENT_PROFILE_IMG = "studentprofile.png";
    public static final String STUDENT_REG_IMG = "studentregistration.png";
    public static final String STUDENT_WS_HISTORY_IMG = "studentworksheethistory.png";
    public static final String TEACHER_PROFILE_IMG = "teacherprofile.png";
    
    public static final String TEACHER_REG_IMG = "teacherregistration.png";
    public static final String TUTORIAL_TITLE_IMG = "tutorialTitle.png";
    public static final String TUTORIALS_IMG = "tutorialsTitle.png";
    
    public static final String WS_HISTORY_TITLE_IMG = "worksheethistoryTitle" +
            ".png";
    public static final String WS_IMG = "worksheets.png";

    // Tutorial videos in resource folder
    public static final String BEG1_VID = "";
    public static final String BEG2_VID = "";
    public static final String BEG3_VID = "";
    public static final String INT1_VID = "";
    public static final String INT2_VID = "";
    public static final String INT3_VID = "";
    public static final String ADV1_VID = "";
    public static final String ADV2_VID = "";
    public static final String ADV3_VID = "";

    // Constants for different worksheet exercises to be compared to the Worksheet objects
    // These should be used when creating a new Worksheet object to store in the database
    // And also to figure out what kind of worksheet/answer sheet to generate for an existing
    // Worksheet from a user's history.
    public static final String WS_Beginner_Pie = "Pie Charts";
    public static final String WS_Beginner_PieAdd = "Addition";
    public static final String WS_Beginner_LG = "Least to Greatest";
    public static final String WS_Intermediate_Add = "Addition";
    public static final String WS_Intermediate_Sub = "Subtraction";
    public static final String WS_Intermediate_MD = "Multiplication and Division";
    public static final String WS_Advanced_Add = "Addition";
    public static final String WS_Advanced_Sub = "Subtraction";
    public static final String WS_Advanced_MD = "Multiplication and Division";

    public static FWCDatabaseConnection connectToDatabase() {
        dbConn = new FWCDatabaseConnection();
        //difficulties = dbConn.getDifficulties();
        return dbConn;
    }

    public static FWCDatabaseConnection getDbConn() {
        return dbConn;
    }

    public static ArrayList<Difficulty> getDifficulties() {
        return difficulties;
    }

    public static void setTeacher(Teacher teacher) {
        FWCConfigurator.teacher = teacher;
        FWCConfigurator.userType = UserType.TEACHER;
    }

    public static void setStudent(Student student) {
        FWCConfigurator.student = student;
        FWCConfigurator.userType = UserType.STUDENT;
    }

    public static void setAdmin(Admin admin) {
        FWCConfigurator.admin = admin;
        FWCConfigurator.userType = UserType.ADMIN;
    }

    public static void setCurrentPage(Page currentPage) {
        FWCConfigurator.currentPage = currentPage;
    }

    public static UserType getUserType() {
        return userType;
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

    public static Page getCurrentPage() {
        return currentPage;
    }

    public static void logout() {
        setTeacher(null);
        setStudent(null);
        setAdmin(null);
        FWCConfigurator.userType = UserType.NONE;
    }
}