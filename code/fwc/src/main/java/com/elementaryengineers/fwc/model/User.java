package com.elementaryengineers.fwc.model;

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

    public boolean resetPassword(String newPassword) {
        return true;
    }
}
