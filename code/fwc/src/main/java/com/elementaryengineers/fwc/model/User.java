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

import org.apache.commons.lang.RandomStringUtils;

/**
 * Created by sarahakk on 4/10/16.
 */
public class User {

    private String username;
    private String firstName;
    private String lastName;
    private EncryptedPassword password;
    private UserType type;

    /**
     * Constructor for creating a brand new User.
     * @param user
     * @param first
     * @param last
     * @param password
     */
    public User(String user, String first, String last, String password) {
        this.username = user;
        this.firstName = first;
        this.lastName = last;
        this.password = new EncryptedPassword(password);
    }

    /**
     * Constructor for creating an existing User from the database.
     * @param user
     * @param first
     * @param last
     * @param salt
     * @param hash
     */
    public User(String user, String first, String last, String salt, String hash) {
        this.username = user;
        this.firstName = first;
        this.lastName = last;
        this.password = new EncryptedPassword(hash, salt);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public UserType getType() {
        return type;
    }

    public EncryptedPassword getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public boolean verifyLogin(String pass) {
        return password.checkPassword(pass);
    }

    /**
     * User should be updated in the database by the caller.
     * @param newPassword
     */
    public void resetPassword(String newPassword) {
        password = new EncryptedPassword(newPassword);
    }

    /**
     * User should be updated in the database by the caller.
     * @return
     */
    public String setRandomPassword() {
        String random = RandomStringUtils.randomAlphanumeric(8);
        password = new EncryptedPassword(random);
        return random;
    }
}
