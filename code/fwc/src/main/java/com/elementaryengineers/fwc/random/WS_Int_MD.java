//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class        :  WS_Int_MD
//  Author       :  Eric Holm
//  Version      :  1.0.0
//  Description  :  Multiplication / Division Worksheet (Intermediate)
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//  Package Declaration
//------------------------------------------------------------------------------
package com.elementaryengineers.fwc.random;
//------------------------------------------------------------------------------

//  Imports  //
//------------------------------------------------------------------------------
import java.util.List;
import java.util.ArrayList;
//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
public class WS_Int_MD extends WS_Master
{
    //  Class Variables  //
    //==========================================================================
    private final List<Equation> equations = new ArrayList<>();
    //==========================================================================
                    
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public WS_Int_MD (long seedValue, int num_fractions, 
                                       int min_num, int max_num, 
                                       int min_den, int max_den,
                                       int gen_denom_flag,
                                       int gen_whole_flag)
    {
        super(seedValue, num_fractions, min_num, max_num, min_den, max_den, 
              gen_denom_flag, gen_whole_flag);
        
        for (int count = 0; count < num_fractions / 2; )
        {
            Equation newEq = new Equation(fractions.get(count), fractions.get(count+1), '*');
            equations.add(newEq);
            count = count + 2;
        }
        for (int count = 0; count < num_fractions / 2; )
        {
            Equation newEq = new Equation(fractions.get(count), fractions.get(count+1), '/');
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
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
//------------------------------------------------------------------------------
//  End class WS_Int_MD