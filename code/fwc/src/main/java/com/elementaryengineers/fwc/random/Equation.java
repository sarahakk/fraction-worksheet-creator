//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class        :  Equation
//  Author       :  Eric Holm
//  Version      :  1.0.0
//  Description  :  Class to build equations used for the worksheets
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//  Package Declaration
//------------------------------------------------------------------------------
package com.elementaryengineers.fwc.random;
//------------------------------------------------------------------------------

import java.util.HashSet;
import java.util.Set;


//------------------------------------------------------------------------------
public class Equation
{
    //  Class Variables  //
    //==========================================================================
    private final Fraction fraction1;           //  First Fraction
    private final Fraction fraction2;           //  Second Fraction
    char operator;                              //  Operand
    private final Fraction answer;              //  Answer Fraction
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
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private Fraction genAnswer()
    {
        switch (operator)
        {
            //  Answers for Beginning Pie Addition Problems
            case 'B':
            {
                return genAnswerPieAddition();
            }
            
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
        
        return null;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  genAnswerPieAddition  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private Fraction genAnswerPieAddition()
    {
        //  Setup Variables Needed
        Fraction tempFrac;
        
        //  Test numerators
        //  If they add up to more then 1.  Reduce them.
        while ((fraction1.getNumerator() + fraction2.getNumerator()) 
                                                 > fraction2.getDenominator())
        {
            if (fraction1.getNumerator() > fraction2.getNumerator())
            {
                fraction1.setFraction(fraction1.getNumerator() - 1, fraction1.getDenominator());
            }
            else
            {
                fraction2.setFraction(fraction2.getNumerator() - 1, fraction2.getDenominator());
            }
        }
        
        int tempNum = fraction1.getNumerator() + fraction2.getNumerator();
        int tempDen = fraction1.getDenominator();
        tempFrac = new Fraction(tempNum, tempDen);
        
        //  Return the answer
        return tempFrac;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  genAnswerAddition  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private Fraction genAnswerAddition()
    {
        //  Setup Variables Needed
        Fraction tempFrac;
        
        //  Test denominators
        //  If equal it's a simple addition of the numerators
        if (fraction1.getDenominator() == fraction2.getDenominator())
        {
            int tempNum = fraction1.getNumerator() + fraction2.getNumerator();
            int tempDen = fraction1.getDenominator();
            tempFrac = new Fraction(tempNum, tempDen);
        }
        else
        {
            //  Error Statement
            tempFrac = new Fraction(0,0);
        }
        
        //  Return the answer
        return tempFrac;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  genAnswerSubtraction  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private Fraction genAnswerSubtraction()
    {
        //  Setup Variables Needed
        Fraction tempFrac;
        
        //  Test denominators
        //  If equal then it's an easier form of subtraction
        if (fraction1.getDenominator() == fraction2.getDenominator())
        {
            //  Test numerators
            //  To avoid negative answers we will swap them if the 2nd numerator
            //  is greater than the 1st.
            if (fraction1.getNumerator() < fraction2.getNumerator())
            {
                //  Hold Fraction1s numerator
                int holdNum = fraction1.getNumerator();
                //  Move Fraction2s numerator to Fraction1
                fraction1.setFraction(fraction2.getNumerator(), 
                                      fraction1.getDenominator());
                //  Move Fraction1s numerator to Fraction2
                fraction2.setFraction(holdNum, fraction1.getDenominator());
            }
            
            int tempNum = fraction1.getNumerator() - fraction2.getNumerator();
            int tempDen = fraction1.getDenominator();
            tempFrac = new Fraction(tempNum, tempDen);
        }
        else
        {
            //  Error Statement
            tempFrac = new Fraction(0,0);
        }
        
        //  Return the answer
        return tempFrac;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  genAnswerMultiplication  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private Fraction genAnswerMultiplication()
    {
        //  Setup Variables Needed
        Fraction tempFrac;
        
        int tempNum = fraction1.getNumerator() * fraction2.getNumerator();
        int tempDen = fraction1.getDenominator() * fraction2.getDenominator();
        
        tempFrac = new Fraction(tempNum, tempDen);
        tempFrac.lowestTerms();
        
        //  Return the answer
        return tempFrac;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  genAnswerDivision  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private Fraction genAnswerDivision()
    {
        //  Setup Variables Needed
        Fraction tempFrac;
        
        int tempNum = fraction1.getNumerator() * fraction2.getDenominator();
        int tempDen = fraction1.getDenominator() * fraction2.getNumerator();
        
        tempFrac = new Fraction(tempNum, tempDen);
        tempFrac.lowestTerms();
        
        //  Return the answer
        return tempFrac;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  toString  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public String toString()
    {
        char tempOperator = operator;
        
        //  Converts the temp operator for Beginner Pie Addition to the '+' sign
        if (tempOperator == 'B')
        {
            tempOperator = '+';
        }
        
        return String.format("%s %c %s = %s", 
                              fraction1.toString(), tempOperator, 
                              fraction2.toString(), answer.toString());
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
//------------------------------------------------------------------------------
//  End class Equation