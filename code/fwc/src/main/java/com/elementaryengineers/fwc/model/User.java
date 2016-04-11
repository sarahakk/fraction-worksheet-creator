package com.elementaryengineers.fwc.model;

/**
 * Created by sarahakk on 4/10/16.
 */
public class User {

    private String username;
    private String firstName;
    private String lastName;
    private EncryptedPassword password;

    public User(String user, String first, String last, String salt, String hash) {
        this.username = user;
        this.firstName = first;
        this.lastName = last;
        this.password = new EncryptedPassword(hash, salt);
    }

    public boolean verifyLogin(String pass) {
        return password.checkPassword(pass);
    }
}
