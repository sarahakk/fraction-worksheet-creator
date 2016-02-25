//  Package Declaration
package Package1;

//  Imports  //
//------------------------------------------------------------------------------
import java.util.Random;

//  Class   :  SeedGenerator
//  Author  :  Eric Holm
//  Version :  1.0.0

//  Class to generate a random seed value
//------------------------------------------------------------------------------
public class SeedGenerator
{
    //  Class Constants  //
    //==========================================================================
    //  Maximum number of parsed values
    int MAX_PARSED = 18;
    
    //  Class Variables  //
    //==========================================================================
    private long seedValue;         //  Contains the master seed value
    int[] parsedValues = new int[MAX_PARSED];
    
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public SeedGenerator()
    {
        //  Constants  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //  Forces SEED to be 19 digits long to be consistant for 
        //  the number of parsed values generated.
        final long MIN_SEED = 1000000000000000000L;
        
        //  Variables  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //  Container for the seed value until it meets the length criteria of
        //  MIN_SEED
        long tempSeed = 0;

        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //  Create the Random class object to make the seed values
        Random seedRNG = new Random();
        
        //  Loop to make seeds until the MIN_SEED value is exceeded
        while (tempSeed < MIN_SEED)
        {
            //  Get the next long value from the RNG
            tempSeed = seedRNG.nextLong();
        
            //  All seeds will be positive values for ease of use.
            //  This converts negative values to positive ones
            if (tempSeed < 0)
            tempSeed = tempSeed * -1;
        }
        
        //  If we reach here... we have a value seed value
        seedValue = tempSeed;
        
        //  Parse the seed into a variety of smaller values for fraction
        //  creation
        parseValues();
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  getSeedValue  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public long getSeedValue()
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        return seedValue;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  parseValues  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void parseValues()
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        String test = Long.toString(seedValue);
        
        for (int count = 0; count < MAX_PARSED; count++)
        {
            parsedValues[count] = Integer.parseInt((test.substring(0 + count, 2 + count)));
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
    
    //  printParseValues  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void printParseValues()
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        for (int count = 0; count < MAX_PARSED; count++)
        {
            System.out.printf("%d ", parsedValues[count]);
        }
        System.out.println();
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
//------------------------------------------------------------------------------
//  End class SeedGenerator
