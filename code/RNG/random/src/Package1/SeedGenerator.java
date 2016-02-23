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
    //  Class Variables  //
    //==========================================================================
    private long seedValue;
    
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public SeedGenerator()
    {
        //  Constants  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        final long MIN_SEED = 1000000000000000000L;
        
        //  Variables  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        long tempSeed = 0;

        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Random seedRNG = new Random();
        
        while (tempSeed < MIN_SEED)
        {
            tempSeed = seedRNG.nextLong();
        
            if (tempSeed < 0)
            tempSeed = tempSeed * -1;
        }
        
        seedValue = tempSeed;
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
    
    //  getParsedValue  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public int getParsedSeedValue(int parseSection)
    {
        //  Code  //
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        String test = Long.toString(seedValue);
        return Integer.parseInt((test.substring(0 + parseSection, 2 + parseSection)));
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
}
//------------------------------------------------------------------------------
//  End class SeedGenerator
