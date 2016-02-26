//  Package Declaration
package Package1;

//  Class   :  FractionTester
//  Author  :  Eric Holm
//  Version :  1.0.0

//  Tester Class for the FractionGenerator
//------------------------------------------------------------------------------
public class FractionTester
{
    //  Main  //
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static void main(String[] args)
    {
        //  Main Variables  //
        //======================================================================
       
        //  Main Code  //
        //======================================================================
        //  FractionGenerator Test
        FractionGenerator fRNG1 = new FractionGenerator(0, 10, 10, 10, 10);
        System.out.printf("fRNG1: %d\n", fRNG1.getSeedValue());
        FractionGenerator fRNG2 = new FractionGenerator(1234, 10, 10, 10, 10);
        System.out.printf("fRNG2: %d\n", fRNG2.getSeedValue());
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
//------------------------------------------------------------------------------
//  End class FractionTester
