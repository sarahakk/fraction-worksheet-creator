package com.elementaryengineers.fwc.model;

/**
 * Created by sarahakk on 4/10/16.
 */
public class Admin extends User {

    private int adminID;
    // TODO: handle storage of security questions

    public Admin(int adminID, String user, String first, String last, String salt, String hash) {
        super(user, first, last, salt, hash);
        this.adminID = adminID;
    }

    public int getAdminID() {
        return adminID;
    }
}
