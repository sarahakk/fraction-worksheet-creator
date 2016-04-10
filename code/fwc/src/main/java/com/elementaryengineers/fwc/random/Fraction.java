//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class       :  Fraction
//  Author      :  Eric Holm
//  Version     :  1.0.0
//  Description :  Class to contain a fraction
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//  Package Declaration
//------------------------------------------------------------------------------
package com.elementaryengineers.fwc.random;
//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
public final class Fraction
{
    //  Class Variables  //
    //==========================================================================
    private int numerator;                      //  Contains the numerator
    private int denominator;                    //  Contains the denominator
    //==========================================================================
    
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //  Creates a Fraction based on the input given to the class
    public Fraction(int numerator, int denominator)
    {
        setFraction(numerator, denominator);
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  setFraction  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Sets the values of the numerator and denominator
    public void setFraction (int numerator, int denominator)
    {
        this.numerator   = numerator;
        this.denominator = denominator;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  getNumerator  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Return the fraction numerator
    public int getNumerator()
    {
        return numerator;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  getDenominator  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Return the fraction denominator
    public int getDenominator()
    {
        return denominator;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  lowestTerms  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Reduces a fraction to lowest terms
    public void lowestTerms()
    {
        
        int tempNum = numerator;
        int tempDen = denominator;
        int gcf = 1;
        
        //  Use modulus to determine the gcf
        while (tempNum > 0)
        {
            gcf = tempNum;
            tempNum = tempDen % tempNum;
            tempDen = gcf;
        }
        
        //  Divide both numerator and denominator by gcf to put faction into
        //  lowest terms.
        numerator = numerator / gcf;
        denominator = denominator / gcf;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //  toString  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public String toString()
    {
        return String.format("%d / %d", numerator, denominator);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
//  End class Fraction
//------------------------------------------------------------------------------
