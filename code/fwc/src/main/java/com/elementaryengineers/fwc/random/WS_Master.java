//  Package Declaration
package com.elementaryengineers.fwc.random;

//  Imports  //
//------------------------------------------------------------------------------
import java.util.List;
import java.util.ArrayList;

//------------------------------------------------------------------------------

//  Class   :  WS_Master
//  Author  :  Eric Holm
//  Version :  1.0.0

//  Abstract Class for the Worksheets
//------------------------------------------------------------------------------
abstract class WS_Master
{
    //  Class Constants  //
    //==========================================================================
        
    //  Class Variables  //
    //==========================================================================
    private final List<Fraction> fractions; 
                
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public WS_Master (long seedValue, int num_fractions, 
                                      int min_num, int max_num, 
                                      int min_den, int max_den,
                                      int gen_denom_flag,
                                      int gen_whole_flag)
    {
        //  Variables  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        FractionGenerator fRNG = new FractionGenerator(seedValue, num_fractions, 
                                        min_num, max_num, min_den, max_den, 
                                        gen_denom_flag,
                                        gen_whole_flag);

        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //  Obtain the needed fractions from the generator.
        fractions = fRNG.getFractions();
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
}
//------------------------------------------------------------------------------
//  End class WS_Master