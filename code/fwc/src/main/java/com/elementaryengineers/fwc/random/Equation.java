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

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class       :  Equation
//  Author      :  Eric Holm
//  Version     :  1.1.0 (FINAL)
//  Description :  Class to build equations used for the worksheets
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//  Package Declaration
//------------------------------------------------------------------------------
package com.elementaryengineers.fwc.random;
//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
public class Equation
{
    //  Class Constants  //
    //==========================================================================
    private static final int FRACTION1 = 1;
    private static final int FRACTION2 = 2;
    private static final int ANSWER = 3;
    //==========================================================================
    
    //  Class Variables  //
    //==========================================================================
    private final Fraction fraction1;           //  First Fraction
    private final Fraction fraction2;           //  Second Fraction
    char operator;                              //  Operand
    private Fraction answer;              //  Answer Fraction
    //==========================================================================
    
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //  Builds the equation based on the data passed to it
    public Equation(Fraction frac1, Fraction frac2, char operator)
    {
        fraction1 = frac1;
        fraction2 = frac2;
        this.operator = operator;
        answer = genAnswer();
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  genAnswer  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Generate answers based on the equation operator
    private Fraction genAnswer()
    {
        switch (operator)
        {
            //  Answers for Addition Problems
            case '+':
            {
                return genAnswerAddition();
            }
            
            //  Answers for Subtraction Problems
            case '-':
            {
                return genAnswerSubtraction();
            }
            
            //  Answers for Multiplication Problems
            case '*':
            {
                return genAnswerMultiplication();
            }
            
            //  Answers for Division Problems
            case '/':
            {
                return genAnswerDivision();
            }
        }

        //  Error State - Should never be reached
        return null;
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genAnswerAddition  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Addition of two fractions
    private Fraction genAnswerAddition()
    {
        //  Grab all the values needed.
        int num1 = fraction1.getNumerator();
        int num2 = fraction2.getNumerator();
        int den1 = fraction1.getDenominator();
        int den2 = fraction2.getDenominator();
        //  Containers for the final values
        int addNum;
        int addDen;
        
        //  Common denominators
        if (den1 == den2)
        {
            //  Add the numerators and pass along the denominator
            addNum = num1 + num2;
            addDen = den1;
        }
        //  Mixed denominators
        else
        {
            //  Multiply the denominators to make them common
            addDen = den1 * den2;
            //  Multiply by common denominator and divide by the original one
            int tempNum1 = (num1 * addDen) / den1;
            int tempNum2 = (num2 * addDen) / den2;
            //  Add the new numerators togeter to get result
            addNum = tempNum1 + tempNum2;
        }
        
        //  Return Fraction
        Fraction tempFrac = new Fraction(addNum, addDen);
        return tempFrac;
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genAnswerSubtraction  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Subtraction of two fractions
    private Fraction genAnswerSubtraction()
    {
        //  Grab all the values needed.
        int num1 = fraction1.getNumerator();
        int num2 = fraction2.getNumerator();
        int den1 = fraction1.getDenominator();
        int den2 = fraction2.getDenominator();
        //  Containers for the final values
        int subNum;
        int subDen;
        
        //  Common Denominators
        if (den1 == den2)
        {
            //  Test numerators
            //  Swap them if the result of subtraction would be a negative value
            if (num1 < num2)
            {
                fraction1.setFraction(num2, den1);
                fraction2.setFraction(num1, den2);

                int hold = num1;
                num1 = num2;
                num2 = hold;
            }
            
            //  Subtract the numerators and pass along the denominator
            subNum = num1 - num2;
            subDen = den1;
        }
        else
        {
            //  Multiply the denominators to make them common
            subDen = den1 * den2;
            //  Multiply by common denominator and divide by the original one
            int tempNum1 = (num1 * subDen) / den1;
            int tempNum2 = (num2 * subDen) / den2;
            
            //  Test numerators
            //  Swap them if the result of subtraction would be a negative value
            if (tempNum1 < tempNum2)
            {
                int tempHold = tempNum1;
                tempNum1 = tempNum2;
                tempNum2 = tempHold;
                
                //  Swap original fractions as well so they appear correctly
                fraction1.setFraction(num2, den2);
                fraction2.setFraction(num1, den1);
            }
            
            //  Subtract the new numerators togeter to get result
            subNum = tempNum1 - tempNum2;
        }
        
        //  Reduce fraction to lowest terms and return it
        Fraction tempFrac = new Fraction(subNum, subDen);
        tempFrac.convertLowestTerms();
        return tempFrac;
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genAnswerMultiplication  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Multiplication of two fractions
    private Fraction genAnswerMultiplication()
    {
        //  Grab all the values needed.
        int num1 = fraction1.getNumerator();
        int num2 = fraction2.getNumerator();
        int den1 = fraction1.getDenominator();
        int den2 = fraction2.getDenominator();
        //  Containers for the final values
        int multiNum;
        int multiDen;
        
        //  Multiply numerators and denominators
        multiNum = num1 * num2;
        multiDen = den1 * den2;
        
        //  Reduce fraction to lowest terms and return it
        Fraction tempFrac = new Fraction(multiNum, multiDen);
        tempFrac.convertLowestTerms();
        return tempFrac;
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genAnswerDivision  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Division of two fractions
    private Fraction genAnswerDivision()
    {
        //  Grab all the values needed.
        int num1 = fraction1.getNumerator();
        int num2 = fraction2.getNumerator();
        int den1 = fraction1.getDenominator();
        int den2 = fraction2.getDenominator();
        //  Containers for the final values
        int divNum;
        int divDen;
        
        //  Cross multiply numerators and denominators
        divNum = num1 * den2;
        divDen = num2 * den1;
        
        //  Reduce fraction to lowest terms and return it
        Fraction tempFrac = new Fraction(divNum, divDen);
        tempFrac.convertLowestTerms();
        return tempFrac;
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  getFraction  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Return the requested fraction
    public Fraction getFraction (int flag)
    {
        switch (flag)
        {
            case FRACTION1:
                return fraction1;
                
            case FRACTION2:
                return fraction2;
                
            case ANSWER:
                return answer;
        }
        
        return null;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  toString  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public String toString()
    {
        return String.format("%s %c %s = %s", 
                              fraction1.toString(), operator, 
                              fraction2.toString(), answer.toString());
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
//  End class Equation
//------------------------------------------------------------------------------
