package com.elementaryengineers.fwc.model;

/**
 * Created by sarahakk on 4/22/16.
 */
public class Difficulty {

    private int difficultyID;
    private String description;

    public Difficulty(int id, String description) {
        this.difficultyID = id;
        this.description = description;
    }

    public int getDifficultyID() {
        return difficultyID;
    }

    public String getDescription() {
        return description;
    }
}