//  Package Declaration
package com.elementaryengineers.fwc.random;

//  Class   :  WS_Master
//  Author  :  Eric Holm
//  Version :  1.0.0

//  Imports  //
//------------------------------------------------------------------------------
import java.util.List;
import java.util.ArrayList;

//  Abstract Class for the Worksheets
//------------------------------------------------------------------------------
public class WS_Int_Add extends WS_Master
{
    //  Class Constants  //
    //==========================================================================
        
    //  Class Variables  //
    //==========================================================================
    private final List<Equation> equations = new ArrayList<>();
                    
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
        
        for (int count = 0; count < num_fractions; )
        {
            Equation newEq = new Equation(fractions.get(count), fractions.get(count+1), '+');
            equations.add(newEq);
            count = count + 2;
        }
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  PrintEquations  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void PrintEquations()
    {
        for (Equation equation : equations) 
        {
            System.out.printf("Test: %s\n", equation.toString());
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  getEquation  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Equation getEquation(int number)
    {
        return equations.get(number);
    }
}
//------------------------------------------------------------------------------
//  End class WS_Int_Add