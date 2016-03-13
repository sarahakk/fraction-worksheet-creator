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
public class WS_Int_Add extends WS_Master
{
    //  Class Constants  //
    //==========================================================================
        
    //  Class Variables  //
    //==========================================================================
                    
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public WS_Int_Add (long seedValue, int num_fractions, 
                                      int min_num, int max_num, 
                                      int min_den, int max_den,
                                      int gen_denom_flag,
                                      int gen_whole_flag)
    {
        super(seedValue, num_fractions, min_num, max_num, min_den, max_den, 
              gen_denom_flag, gen_whole_flag);
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
}
//------------------------------------------------------------------------------
//  End class WS_Int_Add