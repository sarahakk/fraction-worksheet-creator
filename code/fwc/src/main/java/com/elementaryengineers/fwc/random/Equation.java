//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class   :  Equation
//  Author  :  Eric Holm
//  Version :  1.0.0
//  Description  :  Class to build equations used for the worksheets
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//  Package Declaration
//------------------------------------------------------------------------------
package com.elementaryengineers.fwc.random;
//------------------------------------------------------------------------------

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
            case '+':
            {
                return genAnswerAddition();
            }
        }
        
        return null;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  genAnswerAddition  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private Fraction genAnswerAddition()
    {
        //  Setup Variables Needed
        Fraction tempFrac;
        int tempDen = 0;
        int tempNum = 0;
        
        //  Test denominators
        //  If equal it's a simple addition of the numerators
        if (fraction1.getDenominator() == fraction2.getDenominator())
        {
            tempNum = fraction1.getNumerator() + fraction2.getNumerator();
            tempDen = fraction1.getDenominator();
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
    
    //  toString  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public String toString()
    {
        return String.format("%s %c %s = %s", 
                              fraction1.toString(), operator, fraction2.toString(),
                              answer.toString());
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
//------------------------------------------------------------------------------
//  End class Equation