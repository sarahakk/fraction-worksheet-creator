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
        //  Main Constants  //
        //======================================================================
        int FRAC_GEN_WILD = 0;
        int FRAC_GEN_MATCHDENOMINATORS = 1;
        int FRAC_WHOLE_NUMBERS_OK = 0;
        int FRAC_WHOLE_NUMBERS_NO = 1;
        
        //  Main Variables  //
        //======================================================================
        int seedValue = 20;
        int minN = 1;
        int maxN = 9;
        int minD = 2;
        int maxD = 10;
        int frac_gen_flag = FRAC_GEN_WILD;
        int frac_whole_num_flag =FRAC_WHOLE_NUMBERS_NO;
       
        //  Main Code  //
        //======================================================================
        //  FractionGenerator Test
        FractionGenerator fRNG1 = 
                new FractionGenerator(seedValue, minN, maxN, minD, maxD, 
                                      frac_gen_flag,
                                      frac_whole_num_flag);
        System.out.printf("fRNG1: %d\n", fRNG1.getSeedValue());
        fRNG1.printFractions();
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
//------------------------------------------------------------------------------
//  End class FractionTester
