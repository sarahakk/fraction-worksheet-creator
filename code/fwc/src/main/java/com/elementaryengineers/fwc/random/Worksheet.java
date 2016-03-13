//  Package Declaration
package com.elementaryengineers.fwc.random;

//  Imports  //
//------------------------------------------------------------------------------
import java.util.List;

//------------------------------------------------------------------------------

//  Class   :  Worksheet
//  Author  :  Eric Holm
//  Version :  1.0.0

//  Class to generate a specific worksheet
//------------------------------------------------------------------------------
public class Worksheet
{
    //  Class Constants  //
    //==========================================================================
        
    //  Class Variables  //
    //==========================================================================
    private final List<Fraction> fractions;
            
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public Worksheet(long seedValue, int min_num, int max_num,
                                            int min_den, int max_den,
                                            int frac_gen_flag,
                                            int frac_whole_num_flag)
    {
        //  Variables  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        FractionGenerator fRNG1 = new FractionGenerator(seedValue, 
                                                        min_num, max_num,
                                                        min_den, max_den,
                                                        frac_gen_flag,
                                                        frac_whole_num_flag);
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        fractions = fRNG1.getFractions();
        
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  intermediateAddition  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void intermediateAddition()
    {
        
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
//------------------------------------------------------------------------------
//  End class FractionGeneratorSystem