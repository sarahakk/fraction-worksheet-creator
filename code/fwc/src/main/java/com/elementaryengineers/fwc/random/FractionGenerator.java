//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class        :  FractionGenerator
//  Author       :  Eric Holm
//  Version      :  1.0.0
//  Description  :  Class to generate random fraction values
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//  Package Declaration
//------------------------------------------------------------------------------
package com.elementaryengineers.fwc.random;
//------------------------------------------------------------------------------

//  Imports  //
//------------------------------------------------------------------------------
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
public class FractionGenerator
{
    //  Class Variables  //
    //==========================================================================
    private final int seedValue;            //  Master seed value

    private final int min_num;              //  Parameters for the numerator
    private final int max_num;
    private final int min_den;              //  Parameters for the denominator
    private final int max_den;
    
    private final Random fracRNG;           //  RNG for fractions
    
    //  Fractions generated
    private final List<Fraction> fractions = new ArrayList<>();
    //==========================================================================
    
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //  Builds the fractions requested
    public FractionGenerator(int seedValue, int num_fractions,
                                             int min_num, int max_num, 
                                             int min_den, int max_den,
                                             int gen_denom_flag,
                                             int gen_whole_flag)
    {
        //  If the Fraction Generator is passed a zero seedValue
        //  a new SEED needs to be generated.
        if (seedValue == 0)
        {
            this.seedValue = genSeed();
        }
        else
        {
            this.seedValue = seedValue;
        }
        
        //  Set the min/max values
        this.min_num = min_num;
        this.max_num = max_num;
        this.min_den = min_den;
        this.max_den = max_den;
        
        //  Setup the RNG up with the seedValue
        fracRNG = new Random(this.seedValue);
        
        //  Generate all the fractions
        genFractions(num_fractions);
        
        //  If matching denominators are needed...
        if (gen_denom_flag != 0)
        {
            genMatchDenoms();
        }
        
        //  If whole numbers are restricted...
        if (gen_whole_flag != 0)
        {
            genRemoveWholeNums();
        }
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  genSeed  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Generates a new seed value
    private int genSeed()
    {
        //  Create the Random class object to make the new seed value
        Random seedRNG = new Random();
        
        //  Get the next int value from the RNG
        int tempSeed = seedRNG.nextInt();
        
        //  All seeds will be positive values for ease of use.
        //  This converts negative values to positive ones
        if (tempSeed < 0)
        {
            tempSeed = tempSeed * -1;
        }
        
        //  If we reach here... we have a valid seed value
        return tempSeed;
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genFractions  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Main algorithm for creating the fractions based on the generation flags
    private void genFractions(int num_fractions)
    {
        //  Create the fractions using the parameters requested.
        for (int count = 0; count < num_fractions; count++)
        {
            //  All fractions will be positive... so set the starting values
            //  as negative
            int temp_num = -1;
            int temp_den = -1;
            
            //  Loop until a positve value is generated for the numerator
            while (temp_num < min_num)
            {
                temp_num = fracRNG.nextInt(max_num) + 1;
            }
            
            //  Loop until a postive value is generated for the denominator
            while (temp_den < min_den)
            {
                temp_den = fracRNG.nextInt(max_den) + 1;
            }
            
            //  Add this fraction to the ArrayList
            fractions.add(new Fraction(temp_num, temp_den));
        }
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genMatchDenoms  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Matches the denominator of the fractions
    private void genMatchDenoms()
    {
        //  Loop through the ArrayList setting the denominator of the 2nd
        //  of each pair to the same value as the 1st.
        for (int count = 0; count < fractions.size(); )
        {
            int tempDen1 = fractions.get(count).getDenominator();
            int tempNum2 = fractions.get(count + 1).getNumerator();
            
            fractions.get(count + 1).setFraction(tempNum2, tempDen1);
            
            count = count + 2;
        }
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genRemoveWholeNums  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Reduces the fractions to be less than 1
    private void genRemoveWholeNums()
    {
        //  Loop through the ArrayList looking for numerators that are higher
        //  then the denominators.
        for (Fraction fraction : fractions) 
        {
            int tempNum1 = fraction.getNumerator();
            int tempDen1 = fraction.getDenominator();
            
            //  Generate a new numerator based on the denominator.
            while (tempNum1 < min_num || tempNum1 >= tempDen1)
            {
                tempNum1 = fracRNG.nextInt(tempDen1);
            }
                
                fraction.setFraction(tempNum1, tempDen1);
        }
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  getSeedValue  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Obtain the seed value of the FractionGenerator
    public int getSeedValue()
    {
        return seedValue;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  getFractions  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Export the ArrayList of fractions for use in worksheets
    public List<Fraction> getFractions()
    {
        return fractions;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
//  End class FractionGenerator
//------------------------------------------------------------------------------
