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

    /**
     * Constructor for creating a brand new Student.
     */
    public Admin(String user, String first, String last, String password, String last4SSN,
                 String birthdate, String firstJob) {
        super(user, first, last, password);
        setType(UserType.ADMIN);
        this.adminID = -1; // To be set by database (autoincrement)

        // Encrypt answers to security questions
        this.last4SSN = new EncryptedPassword(last4SSN);
        this.birthdate = new EncryptedPassword(birthdate);
        this.firstJob = new EncryptedPassword(firstJob);
    }

    /**
     * Constructor for creating an existing Student from the database.
     */
    public Admin(int adminID, String user, String first, String last, String passSalt, String passHash,
                 String last4SSNSalt, String last4SSNHash, String birthdateSalt, String birthdateHash,
                 String firstJobSalt, String firstJobHash) {
        super(user, first, last, passSalt, passHash);
        setType(UserType.ADMIN);
        this.adminID = adminID;
        this.last4SSN = new EncryptedPassword(last4SSNHash, last4SSNSalt);
        this.birthdate = new EncryptedPassword(birthdateHash, birthdateSalt);
        this.firstJob = new EncryptedPassword(firstJobHash, firstJobSalt);
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
        return this.last4SSN.checkPassword(last4SSN) && this.birthdate.checkPassword(birthdate) &&
                this.firstJob.checkPassword(firstJob);
    }
}
