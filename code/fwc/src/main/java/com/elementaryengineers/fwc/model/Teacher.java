package com.elementaryengineers.fwc.model;

/**
 * Created by sarahakk on 4/10/16.
 */
public class Teacher extends User {

    private int teacherID;

    public Teacher(int teacherID, String user, String first, String last, String salt, String hash) {
        super(user, first, last, salt, hash);
        this.teacherID = teacherID;
    }
}
