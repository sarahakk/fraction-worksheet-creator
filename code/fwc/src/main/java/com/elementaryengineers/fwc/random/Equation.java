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
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Generate answers based on the equation operator
    private Fraction genAnswer()
    {
        switch (operator)
        {
            //  Answers for Beginning Pie Addition Problems
            case 'B':
            {
                return genAnswerAdditionPie();
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
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genAnswerPieAddition  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Answer generation for Beginner Pie Addition.
    //  Reduces fractions to ensure they add up to no more than 1.
    private Fraction genAnswerAdditionPie()
    {
        //  Method Variables  //
        //======================================================================
        Fraction tempFrac;
        //======================================================================
        
        //  Test numerators
        //  If they add up to more then 1.  Reduce them.
        while ((fraction1.getNumerator() + fraction2.getNumerator()) 
                                                 > fraction2.getDenominator())
        {
            if (fraction1.getNumerator() > fraction2.getNumerator())
            {
                fraction1.setFraction(fraction2.getNumerator() - 1, fraction1.getDenominator());
            }
            else
            {
                fraction2.setFraction(fraction1.getNumerator() - 1, fraction2.getDenominator());
            }
        }
        
        //  After ensuring the fractions don't add up to more than one...
        //  Create an answer fraction object to hold the data.
        int tempNum = fraction1.getNumerator() + fraction2.getNumerator();
        int tempDen = fraction1.getDenominator();
        tempFrac = new Fraction(tempNum, tempDen);
        
        //  Return the answer
        return tempFrac;
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genAnswerAddition  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Addition of two fractions
    private Fraction genAnswerAddition()
    {
        //  Method Variables  //
        //======================================================================
        Fraction tempFrac;
        //======================================================================
        
        //  Test denominators
        //  If equal it's a simple addition of the numerators
        if (fraction1.getDenominator() == fraction2.getDenominator())
        {
            //  Add numerators and pass the denominator
            int tempNum = fraction1.getNumerator() + fraction2.getNumerator();
            int tempDen = fraction1.getDenominator();
            tempFrac = new Fraction(tempNum, tempDen);
        }
        else
        {
            //  Error Statement
            //  Since right now the only fractions being produced have a
            //  common denominator
            tempFrac = new Fraction(0,0);
        }
        
        //  Return the answer
        return tempFrac;
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genAnswerSubtraction  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Subtraction of two fractions
    private Fraction genAnswerSubtraction()
    {
        //  Method Variables  //
        //======================================================================
        Fraction tempFrac;
        //======================================================================
        
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
            
            //  Subtract numerators and pass the denominator
            int tempNum = fraction1.getNumerator() - fraction2.getNumerator();
            int tempDen = fraction1.getDenominator();
            tempFrac = new Fraction(tempNum, tempDen);
        }
        else
        {
            //  Error Statement
            //  Since right now the only fractions being produced have a
            //  common denominator
            tempFrac = new Fraction(0,0);
        }
        
        //  Return the answer
        return tempFrac;
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genAnswerMultiplication  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Multiplication of two fractions
    private Fraction genAnswerMultiplication()
    {
        //  Method Variables  //
        //======================================================================
        Fraction tempFrac;
        //======================================================================
        
        //  Multiply numerators and denominators
        int tempNum = fraction1.getNumerator() * fraction2.getNumerator();
        int tempDen = fraction1.getDenominator() * fraction2.getDenominator();
        tempFrac = new Fraction(tempNum, tempDen);
        
        //  Reduce to lowest terms.
        tempFrac.lowestTerms();
        
        //  Return the answer
        return tempFrac;
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genAnswerDivision  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Division of two fractions
    private Fraction genAnswerDivision()
    {
        //  Method Variables  //
        //======================================================================
        Fraction tempFrac;
        //======================================================================

        //  Cross multiply numerators and denominators
        int tempNum = fraction1.getNumerator() * fraction2.getDenominator();
        int tempDen = fraction1.getDenominator() * fraction2.getNumerator();
        tempFrac = new Fraction(tempNum, tempDen);
        
        //  Reduce to lowest terms.
        tempFrac.lowestTerms();
        
        //  Return the answer
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
//  End class Equation
//------------------------------------------------------------------------------
