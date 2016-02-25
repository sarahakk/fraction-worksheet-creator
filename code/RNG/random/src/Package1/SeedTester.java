//  Package Declaration
package Package1;

//  Class   :  SeedTester
//  Author  :  Eric Holm
//  Version :  1.0.0

//  Tester Class for the SeedGenerator
//------------------------------------------------------------------------------
public class SeedTester
{
    //  Main  //
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static void main(String[] args)
    {
        //  Main Variables  //
        //======================================================================
       
        //  Main Code  //
        //======================================================================
        for (int count = 0; count < 10; count++)
        {
            SeedGenerator sRNG = new SeedGenerator();
            
            System.out.printf("%d\n", sRNG.getSeedValue());
            
            sRNG.printParseValues();
        }
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
//------------------------------------------------------------------------------
//  End class SeedTester
