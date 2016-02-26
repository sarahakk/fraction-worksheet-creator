//  Package Declaration
package Package1;

//  Imports  //
//------------------------------------------------------------------------------

//  Class   :  Fraction
//  Author  :  Eric Holm
//  Version :  1.0.0

//  Class to generate a contain a fraction
//------------------------------------------------------------------------------
public class Fraction
{
    //  Class Variables  //
    //==========================================================================
    private int numerator;         //  Contains the numerator
    private int denominator;       //  Contains the denominator
    
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public Fraction()
    {
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
