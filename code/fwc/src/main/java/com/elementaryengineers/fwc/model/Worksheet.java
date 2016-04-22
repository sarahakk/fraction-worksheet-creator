package com.elementaryengineers.fwc.model;

import java.time.LocalDate;

/**
 * Created by sarahakk on 4/21/16.
 */
public class Worksheet {

    private int worksheetID, seed;
    private String exercise;
    private Difficulty difficulty;
    private LocalDate dateCreated;

    public Worksheet(int seed, String exercise, Difficulty difficulty) {
        this.seed = seed;
        this.exercise = exercise;
        this.difficulty = difficulty;
        // TODO: auto-set worksheet id in DB
        // TODO: auto-set date created
    }

    public int getSeed() {
        return seed;
    }

    public String getExercise() {
        return exercise;
    }

    public int getWorksheetID() {
        return worksheetID;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void print() {
        // TODO: generate worksheet based on worksheet info
    }

    public boolean create() {
        // TODO: save to DB
        return true;
    }

    public boolean delete() {
        // TODO: talk to DB
        return true;
    }
}