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


package com.elementaryengineers.fwc.custom;

import javax.swing.table.DefaultTableModel;

/**
 * Created by sarahakk on 4/28/16.
 */
public class DisabledTableModel extends DefaultTableModel {

    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
}