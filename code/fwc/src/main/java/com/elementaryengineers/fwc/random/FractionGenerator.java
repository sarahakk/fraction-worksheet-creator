//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class       :  FractionGenerator
//  Author      :  Eric Holm
//  Version     :  1.1.0 (FINAL)
//  Description :  Class to generate random fraction values
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
    
    private int genMatchDen   = 0;          //  Parameters for generation
    private int genNoWholeNum = 0;
    private int genWholeNum   = 0;
    private int genUnique5    = 0;
    
    private final Random fracRNG;           //  RNG for fractions
    
    //  Used for Unique 5 fraction creation
    private final int[][] exclusionMatrix = new int[11][11];
    
    //  Fractions generated
    private final List<Fraction> fractions = new ArrayList<>();
    //==========================================================================
    
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //  Builds the fractions requested
    public FractionGenerator(int seedValue, int num_fractions,
                             int min_num, int max_num, 
                             int min_den, int max_den,
                             int gen_masterFlag)
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
        
        //  If this is for the Least to Greatest worksheet
        if (gen_masterFlag == 1)
        {
            genUnique5 = 1;
        }
        
        //  If whole numbers are restricted...
        if (gen_masterFlag == 2)
        {
            genNoWholeNum = 1;
        }
        
        //  If whole numbers are neccessary...
        if (gen_masterFlag == 0)
        {
            genWholeNum = 1;
        }
        
        //  If matching denominators are needed and whole numbers are restricted
        if (gen_masterFlag == 6)
        {
            genNoWholeNum = 1;
            genMatchDen = 1;
        }
        
        //  Testers for flags
//        System.out.printf("MatchDen - %d\n", genMatchDen);
//        System.out.printf("WholeNum - %d\n", genWholeNum);
//        System.out.printf("Unique5  - %d\n", genUnique5);
        
        //  Generate the fractions as requested
        genFractionList(num_fractions);
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
    
    //  genFraction  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Generates a single fraction
    private Fraction genFraction(int minNum, int maxNum, int minDen, int maxDen)
    {
        //  All fractions will be positive... so set the starting values
        //  as negative
        int temp_num = -1;
        int temp_den = -1;
            
        //  Loop until a positive value is generated for the numerator
        while (temp_num < minNum)
        {
            temp_num = fracRNG.nextInt(maxNum) + 1;
        }
            
        //  Indicator that denominators need to match
        if (minDen == maxDen)
        {
            return new Fraction(temp_num, minDen);
        }
        else
        {
            //  Loop until a postive value is generated for the denominator
            while (temp_den < minDen)
            {
                temp_den = fracRNG.nextInt(maxDen) + 1;
            }
        }
        
        return new Fraction(temp_num, temp_den);
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genFractionList  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Generate a list of fractions based on the passed parameters
    private void genFractionList(int num_fractions)
    {
        for (int count = 0; count < num_fractions;)
        {
            //  Ensures pairs of fractions have matching denominators
            //  Ensures fractions generated are less than 1
            if ((genMatchDen == 1) && (genWholeNum == 1))
            {
                int matchedDen = -1;
                
                //  Generates a valid value for the denominator.
                //  To be used for the next two fractions generated.
                while (matchedDen < min_den)
                {
                    matchedDen = fracRNG.nextInt(max_den) + 1;
                }
                
                fractions.add(genFraction(min_num, matchedDen - 1, 
                                          matchedDen, matchedDen));
                fractions.add(genFraction(min_num, matchedDen - 1, 
                                          matchedDen, matchedDen));
                count = count + 2;
            }
            
            //  Ensures fractions generated are less than 1
            else if (genNoWholeNum == 1)
            {
                int notWholeDen = -1;
                
                //  Generates a valid value for the denominator.
                //  To be used to create a numerator that is smaller
                while (notWholeDen < min_den)
                {
                    notWholeDen = fracRNG.nextInt(max_den) + 1;
                }
                
                fractions.add(genFraction(min_num, notWholeDen - 1, 
                                          notWholeDen, notWholeDen));
                count++;
            }
            
            //  Ensures five unique values are generated
            //  No common lowest value
            else if (genUnique5 == 1)
            {
                //  Reset the ExclusionMatrix after each set of 5 fractions are
                //  created
                if (count % 5 == 0)
                {
                    formatExclusionMatrix();
                }
                
                //  Generate a fraction
                Fraction tempFrac = genFraction(min_num, max_num, 
                                                min_den, max_den);
                
                //  Test against the exclusion matrix.
                //  Eliminates fractions greater than one
                //  Eliminates fractions with the same lowest common terms.
                while (exclusionMatrix[tempFrac.getNumerator()]
                                      [tempFrac.getDenominator()] == 1)
                {
                    tempFrac = genFraction(min_num, max_num, min_den, max_den);
                }

                //  Add fraction to the list and add it to the exclusion matrix
                addExclusion(tempFrac.getNumerator(), 
                             tempFrac.getDenominator());
                fractions.add(tempFrac);
                count++;
//                System.out.printf("\n%s\n", tempFrac.toString());
//                printExclusionMatrix();
            }
        }
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  formatExclusionMatrix  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Resets the values in the Exclusion Matrix for Unique5 requests
    private void formatExclusionMatrix()
    {
        //  Cycle thought the matrix
        for (int matrixNum = 0; matrixNum <= 10; matrixNum++)
        {
            for (int matrixDen = 0; matrixDen <= 10; matrixDen++)
            {
                //  Values > 1 are excluded (set to 1)
                if (matrixNum >= matrixDen)
                {
                    exclusionMatrix[matrixNum][matrixDen] = 1;
                }
                //  Values < 1 are available (set to 0)
                else
                {
                    exclusionMatrix[matrixNum][matrixDen] = 0;
                }
            }
        }
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  addExclusion  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Add an exclusion to the matrix for fractions already created
    private void addExclusion(int num, int den)
    {
        //  Build new fraction so it can be reduced without destroying
        //  the original fraction
        Fraction newFrac = new Fraction(num, den);
        newFrac.convertLowestTerms();
        int newNum = newFrac.getNumerator();
        int newDen = newFrac.getDenominator();
        
        int countNum = newNum;
        int countDen = newDen;

        //  Cycle through matrix adding any multiple of the lowest terms
        //  version of the fraction to the exclusion matrix
        while (countNum <= 10 && countDen <= 10)
        {
            exclusionMatrix[countNum][countDen] = 1;

            countNum += newNum;
            countDen += newDen;
        }
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  printExclusionMatrix  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    public void printExclusionMatrix()
    {
        System.out.print("     0 1 2 3 4 5 6 7 8 9 10\n");
        for (int matrixNum = 0; matrixNum <= 10; matrixNum++)
        {
            System.out.printf("%2d - ", matrixNum);
            for (int matrixDen = 0; matrixDen <= 10; matrixDen++)
            {
                System.out.printf("%d ", exclusionMatrix[matrixNum][matrixDen]);
            }
            System.out.println();
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