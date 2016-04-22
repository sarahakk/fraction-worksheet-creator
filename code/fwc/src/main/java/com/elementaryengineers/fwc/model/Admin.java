package com.elementaryengineers.fwc.model;

import java.util.ArrayList;

/**
 * Created by sarahakk on 4/10/16.
 */
public class Admin extends User {

    private int adminID;
    private ArrayList<Teacher> teachers;
    private ArrayList<Admin> admins;
    private EncryptedPassword last4SSN, birthdate, firstJob;
    // TODO: handle storage of security questions

    public Admin(int adminID, String user, String first, String last, String salt, String hash) {
        super(user, first, last, salt, hash);
        this.adminID = adminID;
    }

    public int getAdminID() {
        return adminID;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public boolean verifySecurityQuestions(String last4SSN, String birthdate, String firstJob) {
        // TODO: write
        return true;
    }

    public boolean createAdmin() {
        // TODO: talk to database
        return true;
    }

    public boolean deleteAdmin() {
        // TODO: talk to database
        return true;
    }
}
