//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class        :  FractionGenerator
//  Author       :  Eric Holm
//  Version      :  1.1.0
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
    
    private int genMatchDen = 0;            //  Parameters for generation
    private int genWholeNum = 0;
    private int genUnique5  = 0;
    
    private final Random fracRNG;           //  RNG for fractions
    
    //  Used for Unique 5 fraction creation
    private int[][] exclusionMatrix = new int[11][11];
    
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
            genWholeNum = 1;
        }
        
        //  If matching denominators are needed and whole numbers are restricted
        if (gen_masterFlag == 6)
        {
            genWholeNum = 1;
            genMatchDen = 1;
        }
        
        //  Testers for flags
        System.out.printf("MatchDen - %d\n", genMatchDen);
        System.out.printf("WholeNum - %d\n", genWholeNum);
        System.out.printf("Unique5  - %d\n", genUnique5);
        
        //  Generate all the fractions
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
            
        //  Loop until a positve value is generated for the numerator
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
    //  Generate a list of fraction of the given number
    private void genFractionList(int num_fractions)
    {
        for (int count = 0; count < num_fractions;)
        {
            //  Ensures pairs of fractions have matching denominators
            //  Ensures fractions generated are less than 1
            if ((genMatchDen == 1) && (genWholeNum == 1))
            {
                int lockDen = -1;
                
                while (lockDen < min_den)
                {
                    lockDen = fracRNG.nextInt(max_den) + 1;
                }
                
                fractions.add(genFraction(min_num, lockDen - 1, lockDen, lockDen));
                fractions.add(genFraction(min_num, lockDen - 1, lockDen, lockDen));
                count = count + 2;
            }
            
            //  Ensures fractions generated are less than 1
            else if (genWholeNum == 1)
            {
                int lockDen = -1;
                
                while (lockDen < min_den)
                {
                    lockDen = fracRNG.nextInt(max_den) + 1;
                }
                
                fractions.add(genFraction(min_num, lockDen - 1, lockDen, lockDen));
                count++;
            }
            
            //  Ensures five unique values are generated
            //  No common lowest value
            else if (genUnique5 == 1)
            {
                if (count % 5 == 0)
                {
                    formatExclusionMatrix();
                }
                
                Fraction tempFrac = genFraction(min_num, max_num, min_den, max_den);
                
                while (exclusionMatrix[tempFrac.getNumerator()][tempFrac.getDenominator()] == 1)
                {
                    tempFrac = genFraction(min_num, max_num, min_den, max_den);
                }
                
                fractions.add(tempFrac);
                System.out.printf("%s\n", tempFrac.toString());
                addExclusion(tempFrac.getNumerator(), tempFrac.getDenominator());
                
                count++;
                printExclusionMatrix();
            }
        }
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  formatExclusionMatrix  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    private void formatExclusionMatrix()
    {
        for (int matrixNum = 0; matrixNum <= 10; matrixNum++)
        {
            for (int matrixDen = 0; matrixDen <= 10; matrixDen++)
            {
                if (matrixNum >= matrixDen)
                {
                    exclusionMatrix[matrixNum][matrixDen] = 1;
                }
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
    private void addExclusion(int num, int den)
    {
        Fraction newFrac = new Fraction(num, den);
        newFrac.convertLowestTerms();
        int newNum = newFrac.getNumerator();
        int newDen = newFrac.getDenominator();
        
        if (newNum == 1 && newDen == 2)
        {
            exclusionMatrix[1][2] = 1;
            exclusionMatrix[2][4] = 1;
            exclusionMatrix[3][6] = 1;
            exclusionMatrix[4][8] = 1;
            exclusionMatrix[5][10] = 1;
        }
        else if (newNum == 1 && newDen == 3)
        {
            exclusionMatrix[1][3] = 1;
            exclusionMatrix[2][6] = 1;
            exclusionMatrix[3][9] = 1;
        }
        else if (newNum == 1 && newDen == 4)
        {
            exclusionMatrix[1][4] = 1;
            exclusionMatrix[2][8] = 1;
        }
        else if (newNum == 1 && newDen == 5)
        {
            exclusionMatrix[1][5] = 1;
            exclusionMatrix[2][10] = 1;
        }
        else if (newNum == 2 && newDen == 3)
        {
            exclusionMatrix[2][3] = 1;
            exclusionMatrix[4][6] = 1;
            exclusionMatrix[6][9] = 1;
        }
        else if (newNum == 2 && newDen == 5)
        {
            exclusionMatrix[2][5] = 1;
            exclusionMatrix[4][10] = 1;
        }
        else if (newNum == 3 && newDen == 4)
        {
            exclusionMatrix[3][4] = 1;
            exclusionMatrix[6][8] = 1;
        }
        else if (newNum == 4 && newDen == 5)
        {
            exclusionMatrix[4][5] = 1;
            exclusionMatrix[8][10] = 1;
        }
        else
        {
            exclusionMatrix[num][den] = 1;
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
    ////xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
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