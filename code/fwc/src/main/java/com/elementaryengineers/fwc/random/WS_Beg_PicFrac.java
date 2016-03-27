//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class        :  WS_Beg_PicFrac
//  Author       :  Eric Holm
//  Version      :  1.0.0
//  Description  :  Convert Picture to Fraction (Beginner)
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//  Package Declaration
//------------------------------------------------------------------------------
package com.elementaryengineers.fwc.random;
//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
public class WS_Beg_PicFrac extends WS_Master
{
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public WS_Beg_PicFrac (long seedValue, int num_fractions, 
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
//  End class WS_Beg_PicFrac