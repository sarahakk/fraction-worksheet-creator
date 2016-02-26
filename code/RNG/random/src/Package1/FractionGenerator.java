//  Package Declaration
package Package1;

//  Imports  //
//------------------------------------------------------------------------------
import java.util.Random;

//  Class   :  FractionGenerator
//  Author  :  Eric Holm
//  Version :  1.0.0

//  Class to generate a random fraction values
//------------------------------------------------------------------------------
public class FractionGenerator
{
    //  Class Constants  //
    //==========================================================================
    private final int MAX_FRACTIONS = 20;
    
    //  Class Variables  //
    //==========================================================================
    //  Contains the master seed value
    private long seedValue;

    //  Contains the parameters for the fractions produced.
    private int min_num;
    private int max_num;
    private int min_den;
    private int max_den;
    
    //  Contains all the fractions any of the worksheets will need
    private Fraction[] fractions = new Fraction[MAX_FRACTIONS];  
    
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public FractionGenerator(long seedValue, int min_num, int max_num, 
                                             int min_den, int max_den)
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
        
        this.min_num = min_num;
        this.max_num = max_num;
        this.min_den = min_den;
        this.max_den = max_den;
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  newSeedGenerator  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public long newSeedGenerator()
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
    
    //  getSeedValue  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public long getSeedValue()
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        return seedValue;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  setFractions  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void setFractions()
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    }
}
//------------------------------------------------------------------------------
//  End class FractionGenerator
