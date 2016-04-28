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
import com.elementaryengineers.fwc.random.*;
import org.apache.pdfbox.exceptions.COSVisitorException;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by sarahakk on 4/21/16.
 */
public class Worksheet {

    private int worksheetID, seed, difficultyID;
    private String exercise, dateCreated;

    /**
     * For new worksheets
     * @param seed
     * @param exercise
     * @param difficultyID
     */
    public Worksheet(int seed, String exercise, int difficultyID) {
        this.seed = seed;
        this.exercise = exercise;
        this.difficultyID = difficultyID;
        this.worksheetID = -1; // Should be auto-incremented in database

        // Construct date
        LocalDateTime date = LocalDateTime.now();
        String temp = Integer.toString(date.getMonthValue());
        dateCreated = ((temp.length() < 2) ? "0" + temp : temp) + "/";
        temp = Integer.toString(date.getDayOfMonth());
        dateCreated += ((temp.length() < 2) ? "0" + temp : temp) + "/";
        dateCreated += Integer.toString(date.getYear());
    }

    /**
     * For existing worksheets
     * @param seed
     * @param exercise
     * @param difficultyID
     */
    public Worksheet(int id, int seed, String exercise, int difficultyID,
                     String dateCreated) {
        this.seed = seed;
        this.exercise = exercise;
        this.difficultyID = difficultyID;
        this.worksheetID = id;
        this.dateCreated = dateCreated;
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

    public String getDateCreated() {
        return dateCreated;
    }

    public int getDifficultyID() {
        return difficultyID;
    }

    public void print(boolean answerSheet) {
        // Check worksheet difficulty
        switch (difficultyID) {
            case 0: // Beginner
                // Check type of exercise
                switch (exercise) {
                    case FWCConfigurator.WS_Beginner_Pie: {
                        WS_Beginner_Pie worksheet = new WS_Beginner_Pie (this.seed, 10,
                                1, 10, // Use default numerator and denominator limits
                                2, 10,
                                FWCConfigurator.GEN_WHOLENUM_NO);

                        try {
                            worksheet.CreateWorksheet((answerSheet) ? FWCConfigurator.ANSWER_ONLY :
                                    FWCConfigurator.WORKSHEET_ONLY);
                        }
                        catch (IOException |COSVisitorException ex) {
                            JOptionPane.showMessageDialog(null, "An error occurred while creating your worksheet!\n" +
                                    "If the problem persists, please restart the Fraction Worksheet Creator and try again.",
                                    "Worksheet Error", JOptionPane.ERROR_MESSAGE);
                        }

                        break;
                    }

                    case FWCConfigurator.WS_Beginner_PieAdd: {
                        WS_Beginner_PieAdd worksheet = new WS_Beginner_PieAdd (this.seed, 20,
                                1, 10, // Use default numerator and denominator limits
                                2, 10,
                                FWCConfigurator.GEN_DENOM_MATCHED + FWCConfigurator.GEN_WHOLENUM_NO);

                        try {
                            worksheet.CreateWorksheet((answerSheet) ? FWCConfigurator.ANSWER_ONLY :
                                    FWCConfigurator.WORKSHEET_ONLY);
                        }
                        catch (IOException |COSVisitorException ex) {
                            JOptionPane.showMessageDialog(null, "An error occurred while creating your worksheet!\n" +
                                    "If the problem persists, please restart the Fraction Worksheet Creator and try again.",
                                    "Worksheet Error", JOptionPane.ERROR_MESSAGE);
                        }

                        break;
                    }

                    case FWCConfigurator.WS_Beginner_LG: {
                        WS_Beginner_LG worksheet = new WS_Beginner_LG(this.seed, 50,
                                1, 10, // Use default numerator and denominator limits
                                2, 10,
                                FWCConfigurator.GEN_UNIQUE5);

                        try {
                            worksheet.CreateWorksheet((answerSheet) ? FWCConfigurator.ANSWER_ONLY :
                                    FWCConfigurator.WORKSHEET_ONLY);
                        }
                        catch (IOException |COSVisitorException ex) {
                            JOptionPane.showMessageDialog(null, "An error occurred while creating your worksheet!\n" +
                                    "If the problem persists, please restart the Fraction Worksheet Creator and try again.",
                                    "Worksheet Error", JOptionPane.ERROR_MESSAGE);
                        }

                        break;
                    }
                }

                break;

            case 1: { // Intermediate
                WS_Intermediate worksheet = new WS_Intermediate(this.seed, 40,
                        1, 12, // Use default numerator and denominator limits
                        2, 12,
                        FWCConfigurator.GEN_WHOLENUM_NO,
                        (exercise.equals(FWCConfigurator.WS_Intermediate_Add)) ? '+' :
                                (exercise.equals(FWCConfigurator.WS_Intermediate_Sub)) ? '-' : '*');

                try {
                    worksheet.CreateWorksheet((answerSheet) ? FWCConfigurator.ANSWER_ONLY :
                            FWCConfigurator.WORKSHEET_ONLY);
                }
                catch (IOException | COSVisitorException ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred while creating your worksheet!\n" +
                            "If the problem persists, please restart the Fraction Worksheet Creator and try again.",
                            "Worksheet Error", JOptionPane.ERROR_MESSAGE);
                }

                break;
            }

            case 2: { // Advanced
                try {
                    WS_Advanced worksheet = new WS_Advanced(this.seed, 20,
                            1, 8, // Use default numerator and denominator limits
                            2, 8,
                            FWCConfigurator.GEN_WHOLENUM_NO,
                            (exercise.equals(FWCConfigurator.WS_Advanced_Add)) ? '+' :
                                    (exercise.equals(FWCConfigurator.WS_Advanced_Sub)) ? '-' : '*');

                    worksheet.CreateWorksheet((answerSheet) ? FWCConfigurator.ANSWER_ONLY :
                            FWCConfigurator.WORKSHEET_ONLY);
                }
                catch (IOException|COSVisitorException ex) {
                    JOptionPane.showMessageDialog(null,
                            "An error occurred while creating your worksheet!\n" +
                            "If the problem persists, please restart "
                            + "the Fraction Worksheet Creator and try again.",
                            "Worksheet Error", JOptionPane.ERROR_MESSAGE);
                }

                break;
            }
        }
    }
}