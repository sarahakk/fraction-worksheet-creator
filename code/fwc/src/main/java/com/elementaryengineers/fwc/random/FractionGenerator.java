//  Package Declaration
package com.elementaryengineers.fwc.random;

//  Imports  //
//------------------------------------------------------------------------------
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

//  Class   :  FractionGenerator
//  Author  :  Eric Holm
//  Version :  1.0.0

//  Class to generate a random fraction values for a worksheet
//------------------------------------------------------------------------------
public class FractionGenerator
{
    //  Class Constants  //
    //==========================================================================
    //  Maximum number of fractions to be generated.
    private static final int MAX_FRACTIONS = 10;
    
    //  Flags for fraction generation
    //  These signal if a pair of fractions should have matching denominators
    private static final int GEN_DENOM_UNMATCHED = 0;
    private static final int GEN_DENOM_MATCHED = 1;
    
    //  These signal if fractions are allowed to evaluate to 1 or greater
    private static final int GEN_WHOLENUM_YES = 0;
    private static final int GEN_WHOLENUM_NO = 1;
    
    //  Class Variables  //
    //==========================================================================
    //  Contains the master seed value
    private final long seedValue;

    //  Contains the parameters for the fractions produced.
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
    public FractionGenerator(long seedValue, int min_num, int max_num, 
                                             int min_den, int max_den,
                                             int gen_denom_flag,
                                             int gen_wholenum_flag)
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
        
        //  Set the min/max values
        this.min_num = min_num;
        this.max_num = max_num;
        this.min_den = min_den;
        this.max_den = max_den;
        
        //  Set the RNG up with the seedValue
        fracRNG = new Random(seedValue);
        
        //  Set all the fractions
        setFractions(gen_denom_flag, gen_wholenum_flag);
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
    private void setFractions(int gen_denom_flag, int gen_wholenum_flag)
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //  Create the fractions using the parameters requested.
        for (int count = 0; count < MAX_FRACTIONS; count++)
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
        
        //  Runs the function that will match the denominator of each pair of
        //  fractions if this flag is set.
        if (gen_denom_flag == GEN_DENOM_MATCHED)
        {
            setMatchDenominators();
        }
        
        //  Runs the function that will reduce the fractions to be less than 1
        //  if this flag is set.
        if (gen_wholenum_flag == GEN_WHOLENUM_NO)
        {
            setRemoveWholeNumbers();
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  setMatchDenominators  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Matches the denominator of each pair of fractions
    private void setMatchDenominators()
    {
        //  Loop through the ArrayList setting the denominator of the 2nd
        //  of each pair to the same value as the 1st.
        for (int count = 0; count < MAX_FRACTIONS;)
        {
            int tempDen1 = fractions.get(count).getDenominator();
            int tempNum2 = fractions.get(count+1).getNumerator();
            fractions.get(count+1).setFraction(tempNum2, tempDen1);
            
            count = count + 2;
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  setRemoveWholeNumbers  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Reduces the fractions to be less than 1
    private void setRemoveWholeNumbers()
    {
        //  Loop through the ArrayList and test each numerator to see if it
        //  is greater than or equal to the denominator.
        for (int count = 0; count < MAX_FRACTIONS; count++)
        {
            int tempDen1 = fractions.get(count).getDenominator();
            
            //  Generate a new numerator based on the denominator.
            int tempNum1 = fracRNG.nextInt(tempDen1);
            
            fractions.get(count).setFraction(tempNum1, tempDen1);
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
    
    //  printFractions  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Display a listing of all the fractions generated
    public void printFractions()
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        for (int count = 0; count < MAX_FRACTIONS; count++)
        {
            Fraction thisFrac = fractions.get(count);
            System.out.printf("%s \n", thisFrac.toString());
        }
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
}
//------------------------------------------------------------------------------
//  End class FractionGeneratorSystem