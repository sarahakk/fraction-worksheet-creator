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

/**Admin page is used to register the Administrator as the first user of 
 * the software right after installation and asks for security questions
 * to check for in the event that the Administrator forgets the master 
 * password and needs to reset it.
 * Created by sarahakk on 4/10/16.
 */
public class Admin extends User {

    private int adminID;
    private ArrayList<Teacher> teachers;
    private EncryptedPassword last4SSN, birthdate, firstJob;

    /**
     * Constructor for creating a brand new Student.
     */
    public Admin(String user, String first, String last, String password, 
            String last4SSN, String birthdate, String firstJob) {
        super(user, first, last, password);
        setType(UserType.ADMIN);
        this.adminID = -1; // To be set by database (autoincrement)

        // Encrypt answers to security questions
        this.last4SSN = new EncryptedPassword(last4SSN);
        this.birthdate = new EncryptedPassword(birthdate);
        this.firstJob = new EncryptedPassword(firstJob);
        this.teachers = new ArrayList<>();
    }

    /**
     * Constructor for creating an existing Student from the database.
     */
    public Admin(int adminID, String user, String first, String last, 
                 String passSalt, String passHash,
                 String last4SSNSalt, String last4SSNHash, String birthdateSalt,
                 String birthdateHash,
                 String firstJobSalt, String firstJobHash) {
        super(user, first, last, passSalt, passHash);
        setType(UserType.ADMIN);
        this.adminID = adminID;
        this.last4SSN = new EncryptedPassword(last4SSNHash, last4SSNSalt);
        this.birthdate = new EncryptedPassword(birthdateHash, birthdateSalt);
        this.firstJob = new EncryptedPassword(firstJobHash, firstJobSalt);
        this.teachers = new ArrayList<>();
        this.teachers = FWCConfigurator.getDbConn().getAllTeachers();
    }

    public int getAdminID() {
        return adminID;
    }

    public ArrayList<Teacher> getTeachers() {
        this.teachers = FWCConfigurator.getDbConn().getAllTeachers();
        return teachers;
    }

    public ArrayList<Teacher> getTeachersRequestedReset() {
        List<Teacher> results = teachers.stream().filter(t ->
                t.isResetPassRequested()).collect(Collectors.toList());

        return new ArrayList<>(results);
    }

    public boolean verifySecurityQuestions(String last4SSN, String birthdate, 
            String firstJob) {
        return this.last4SSN.checkPassword(last4SSN) && 
                this.birthdate.checkPassword(birthdate) &&
                this.firstJob.checkPassword(firstJob);
    }

    public ArrayList<Teacher> searchTeachers(String keyword) {
        String lowerKey = keyword.toLowerCase();

        List<Teacher> results = teachers.stream().filter(t ->
                t.getFirstName().toLowerCase().contains(lowerKey) ||
                        t.getLastName().toLowerCase().contains(lowerKey) ||
                        t.getUsername().toLowerCase().contains(lowerKey)
        ).collect(Collectors.toList());

        return new ArrayList<>(results);
    }

    public EncryptedPassword getLast4SSN() {
        return last4SSN;
    }

    public EncryptedPassword getBirthdate() {
        return birthdate;
    }

    public EncryptedPassword getFirstJob() {
        return firstJob;
    }
}
