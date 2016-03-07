//  Package Declaration
package com.elementaryengineers.fwc.random;

//  Imports  //
//------------------------------------------------------------------------------
import java.util.Scanner;

//  Class   :  FractionTester
//  Author  :  Eric Holm
//  Version :  1.0.0

//  Tester Class for the FractionGenerator
//------------------------------------------------------------------------------
public class FractionTester
{
    //  Main  //
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static void main(String[] args)
    {
        //  Main Constants  //
        //======================================================================
        int FRAC_GEN_WILD = 0;
        int FRAC_GEN_MATCHDENOMINATORS = 1;
        int FRAC_WHOLE_NUMBERS_OK = 0;
        int FRAC_WHOLE_NUMBERS_NO = 1;
        
        //  Main Variables  //
        //======================================================================
        Scanner input = new Scanner(System.in);
               
        //  Main Code  //
        //======================================================================
        //  Request Seed Value from User
        System.out.printf("Enter Seed Value: ");
        int seedValue = input.nextInt();
        
        //  FractionGenerator Test
        FractionGenerator fRNG1 = 
                new FractionGenerator(seedValue, 1, 24, 2, 24, 
                                      FRAC_GEN_WILD,
                                      FRAC_WHOLE_NUMBERS_OK);
        System.out.printf("fRNG1: %d\n", fRNG1.getSeedValue());
        System.out.printf("Gen - Wild / Whole - OK\n");
        fRNG1.printFractions();
        
        FractionGenerator fRNG2 = 
                new FractionGenerator(seedValue, 1, 24, 2, 24, 
                                      FRAC_GEN_MATCHDENOMINATORS,
                                      FRAC_WHOLE_NUMBERS_OK);
        System.out.printf("fRNG2: %d\n", fRNG2.getSeedValue());
        System.out.printf("Gen - MatchDen / Whole - OK\n");
        fRNG2.printFractions();
        
        FractionGenerator fRNG3 = 
                new FractionGenerator(seedValue, 1, 24, 2, 24, 
                                      FRAC_GEN_WILD,
                                      FRAC_WHOLE_NUMBERS_NO);
        System.out.printf("fRNG3: %d\n", fRNG3.getSeedValue());
        System.out.printf("Gen - Wild / Whole - NO\n");
        fRNG3.printFractions();
        
        FractionGenerator fRNG4 = 
                new FractionGenerator(seedValue, 1, 24, 2, 24, 
                                      FRAC_GEN_MATCHDENOMINATORS,
                                      FRAC_WHOLE_NUMBERS_NO);
        System.out.printf("fRNG4: %d\n", fRNG4.getSeedValue());
        System.out.printf("Gen - MatchDen / Whole - NO\n");
        fRNG4.printFractions();
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
//------------------------------------------------------------------------------
//  End class FractionTester
