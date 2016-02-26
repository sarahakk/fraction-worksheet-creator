//  Package Declaration
package Package1;

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
    private final int MAX_FRACTIONS = 10;
    
    int FRAC_GEN_WILD = 0;
    int FRAC_GEN_MATCHDENOMINATORS = 1;
    int FRAC_WHOLE_NUMBERS_NO = 1;
    
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
    public FractionGenerator(long seedValue, int min_num, int max_num, 
                                             int min_den, int max_den,
                                             int frac_gen_flag,
                                             int frac_whole_num_flag)
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //  If the Fraction Generator is passed a zero... then a new SEED needs
        //  to be generated.
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
        setFractions(frac_gen_flag, frac_whole_num_flag);
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  newSeedGenerator  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private long newSeedGenerator()
    {
        //  Variables  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //  Temporary container for the seed value
        long tempSeed = 0;

        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //  Create the Random class object to make the seed values
        Random seedRNG = new Random();
        
        //  Get the next long value from the RNG
        tempSeed = seedRNG.nextLong();
        
        //  All seeds will be positive values for ease of use.
        //  This converts negative values to positive ones
        if (tempSeed < 0)
        {
            tempSeed = tempSeed * -1;
        }
        
        //  If we reach here... we have a value seed value
        return tempSeed;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  setFractions  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void setFractions(int frac_gen_flag, int frac_whole_num_flag)
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //  Create the fractions using the parameters requested.
        for (int count = 0; count < MAX_FRACTIONS; count++)
        {
            int temp_num = -1;
            int temp_den = -1;
            
            while (temp_num < min_num)
            {
                temp_num = fracRNG.nextInt(max_num) + 1;
            }
            
            while (temp_den < min_den)
            {
                temp_den = fracRNG.nextInt(max_den) + 1;
            }
            
            fractions.add(new Fraction(temp_num, temp_den));
        }
        
        if (frac_gen_flag == FRAC_GEN_MATCHDENOMINATORS)
        {
            setMatchDenominators();
        }
        
        if (frac_whole_num_flag == FRAC_WHOLE_NUMBERS_NO)
        {
            setRemoveWholeNumbers();
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  setMatchDenominators  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void setMatchDenominators()
    {
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
    private void setRemoveWholeNumbers()
    {
        for (int count = 0; count < MAX_FRACTIONS; count++)
        {
            int tempNum1 = fractions.get(count).getNumerator();
            int tempDen1 = fractions.get(count).getDenominator();
            
            while (tempNum1 >= tempDen1)
            {
                tempNum1 = fracRNG.nextInt(tempDen1) + 1;
            }
            
            fractions.get(count).setFraction(tempNum1, tempDen1);
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  getSeedValue  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public long getSeedValue()
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        return seedValue;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  printFractions  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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
}
//------------------------------------------------------------------------------
//  End class FractionGeneratorSystem