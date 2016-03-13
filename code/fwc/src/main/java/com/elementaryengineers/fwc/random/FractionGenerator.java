//  Package Declaration
package com.elementaryengineers.fwc.random;

//  Class   :  FractionGenerator
//  Author  :  Eric Holm
//  Version :  1.0.0

//  Imports  //
//------------------------------------------------------------------------------
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

//  Class to generate random fraction values
//------------------------------------------------------------------------------
public class FractionGenerator
{
    //  Class Variables  //
    //==========================================================================
    //  Contains the master seed value
    private final long seedValue;

    //  Contains the parameters for the fractions produced.
    private final int num_fractions;
    private final int min_num;
    private final int max_num;
    private final int min_den;
    private final int max_den;
    
    //  Contains the RNG for all functions in the class
    Random fracRNG;
    
    //  Contains all the fractions any of the worksheets will need
    private final List<Fraction> fractions = new ArrayList<>();
    
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //  Builds the fractions needed for a worksheet
    public FractionGenerator(long seedValue, int num_fractions,
                                             int min_num, int max_num, 
                                             int min_den, int max_den,
                                             int gen_denom_flag,
                                             int gen_whole_flag)
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //  If the Fraction Generator is passed a zero seedValue
        //  A new SEED needs to be generated.
        if (seedValue == 0)
        {
            this.seedValue = newSeedGenerator();
        }
        else
        {
            this.seedValue = seedValue;
        }
        
        //  Set the number of fractions needed
        this.num_fractions = num_fractions;
        
        //  Set the min/max values
        this.min_num = min_num;
        this.max_num = max_num;
        this.min_den = min_den;
        this.max_den = max_den;
        
        //  Set the RNG up with the seedValue
        fracRNG = new Random(seedValue);
        
        //  Set all the fractions
        setFractions();
        
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
    
    //  newSeedGenerator  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Creates a new seed value for new worksheets
    private long newSeedGenerator()
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //  Create the Random class object to make the new seed value
        Random seedRNG = new Random();
        
        //  Get the next long value from the RNG
        long tempSeed = seedRNG.nextLong();
        
        //  All seeds will be positive values for ease of use.
        //  This converts negative values to positive ones
        if (tempSeed < 0)
        {
            tempSeed = tempSeed * -1;
        }
        
        //  If we reach here... we have a valid seed value
        return tempSeed;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  setFractions  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Main algorithm for creating the fractions based on the generation flags
    private void setFractions()
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
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
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  genMatchDenoms  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Matches the denominator of the fractions
    private void genMatchDenoms()
    {
        //  Loop through the ArrayList setting the denominator of the 2nd
        //  of each pair to the same value as the 1st.
        for (int count = 0; count < num_fractions; )
        {
            int tempDen1 = fractions.get(count).getDenominator();
            int tempNum2 = fractions.get(count + 1).getNumerator();
            
            fractions.get(count + 1).setFraction(tempNum2, tempDen1);
            
            count = count + 2;
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  genRemoveWholeNums  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Reduces the fractions to be less than 1
    private void genRemoveWholeNums()
    {
        //  Loop through the ArrayList looking for numerators that are higher
        //  then the denominators.
        for (int count = 0; count < num_fractions; count++)
        {
            int tempNum1 = fractions.get(count).getNumerator();
            int tempDen1 = fractions.get(count).getDenominator();
            
            if (tempNum1 >= tempDen1)
            {
                //  Generate a new numerator based on the denominator.
                tempNum1 = fracRNG.nextInt(tempDen1);
                fractions.get(count).setFraction(tempNum1, tempDen1);
            }
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  getSeedValue  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Obtain the seed value of the FractionGenerator
    public long getSeedValue()
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        return seedValue;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  getFractions  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Export the ArrayList of fractions for use in a worksheet
    public List<Fraction> getFractions()
    {
        return fractions;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  printFractions  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Display a listing of all the fractions generated
    public void printFractions()
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        for (int count = 0; count < num_fractions; count++)
        {
            Fraction thisFrac = fractions.get(count);
            System.out.printf("%s \n", thisFrac.toString());
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
//------------------------------------------------------------------------------
//  End class FractionGenerator