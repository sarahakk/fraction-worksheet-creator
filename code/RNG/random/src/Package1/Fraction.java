//  Package Declaration
package Package1;

//  Imports  //
//------------------------------------------------------------------------------

//  Class   :  Fraction
//  Author  :  Eric Holm
//  Version :  1.0.0

//  Class to contain a fraction
//------------------------------------------------------------------------------
public class Fraction
{
    //  Class Variables  //
    //==========================================================================
    private int numerator;         //  Contains the numerator
    private int denominator;       //  Contains the denominator
    
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public Fraction(int numerator, int denominator)
    {
        this.numerator   = numerator;
        this.denominator = denominator;
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  setFraction  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void setFraction (int numerator, int denominator)
    {
        this.numerator   = numerator;
        this.denominator = denominator;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  getNumerator  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public int getNumerator()
    {
        return numerator;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  getDenominator  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public int getDenominator()
    {
        return denominator;
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
//------------------------------------------------------------------------------
//  End class Fraction
