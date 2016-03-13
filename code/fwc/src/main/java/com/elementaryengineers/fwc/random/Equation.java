//  Package Declaration
package com.elementaryengineers.fwc.random;

//  Imports  //
//------------------------------------------------------------------------------
import java.util.Random;

//  Class   :  Equation
//  Author  :  Eric Holm
//  Version :  1.0.0

//  Class to build equations used for the worksheets
//------------------------------------------------------------------------------
public class Equation
{
    //  Class Constants  //
    //==========================================================================
    
    //  Class Variables  //
    //==========================================================================
    //  Each equation will contain two fractions, an operator, and an answer
    private final Fraction fraction1;
    char operator;
    private final Fraction fraction2;
    private Fraction answer;
    
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //  Builds the equation based on the data passed to it
    public Equation(Fraction frac1, Fraction frac2, char operator,
                    int gen_denom_flag, int gen_wholenum_flag)
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        fraction1 = frac1;
        fraction2 = frac2;
        this.operator = operator;
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  setAnswer  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void setAnswer (Fraction answer)
    {
        this.answer = answer;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
//------------------------------------------------------------------------------
//  End class Equation