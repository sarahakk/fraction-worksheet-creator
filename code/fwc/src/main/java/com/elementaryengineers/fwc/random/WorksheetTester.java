//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class        :  WorksheetTester
//  Author       :  Eric Holm
//  Version      :  1.0.0
//  Description  :  Tester Class for the FractionGenerator
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//  Package Declaration
//------------------------------------------------------------------------------
package com.elementaryengineers.fwc.random;
//------------------------------------------------------------------------------

//  Imports  //
//------------------------------------------------------------------------------
import java.io.IOException;
import java.util.Scanner;
import org.apache.pdfbox.exceptions.COSVisitorException;
//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
public class WorksheetTester
{
    //  Main  //
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static void main(String[] args) throws IOException, COSVisitorException
    {
        //  Main Variables  //
        //======================================================================
        //======================================================================
               
        //  Main Code  //
        //======================================================================
        Menu();
        //======================================================================
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    //  Menu  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static void Menu () throws IOException, COSVisitorException
    {
        //  Menu Constants  //
        //======================================================================
        //  Flags for fraction generation
        //  These signal if a pair of fractions should have matching denominators
        final int GEN_DENOM_UNMATCHED = 0;
        final int GEN_DENOM_MATCHED = 1;
    
        //  These signal if fractions are allowed to evaluate to 1 or greater
        final int GEN_WHOLENUM_YES = 0;
        final int GEN_WHOLENUM_NO = 1;
        
        //  Flags for the answersheet generation
        //  Flags for the answersheet generation
        final int WORKSHEET_ONLY = 1;
        final int ANSWER_SHEET = 2;
        final int ANSWER_ONLY = 3;
        //======================================================================

        //  Menu Variables  //
        //======================================================================
        Scanner input = new Scanner(System.in);
        int selection = -1;
        int seedValue = 0;
        int answerFlag = WORKSHEET_ONLY;
        //======================================================================
        
        //  Menu Code //
        //======================================================================
        while (selection != 0)
        {
            System.out.printf("------   Main Menu   -----\n");
            System.out.printf("--------------------------\n");
            System.out.printf(" 1)  Enter Seed Value     \n");
            System.out.printf(" 2)  Beg_PicFrac          \n");
            System.out.printf(" 3)  Beg_PicAdd           \n");
            System.out.printf(" 4)  Int_Add              \n");
            System.out.printf(" 5)  Int_Sub              \n");
            System.out.printf(" 6)  Int_MD               \n");
            System.out.printf("                          \n");
            System.out.printf(" 9)  Answer Flag          \n");
            System.out.printf(" 0)  Exit                 \n");
            System.out.printf("--------------------------\n");
            System.out.printf("Current Seed: %d\n", seedValue);
            System.out.printf("Answer Flag : %d\n", answerFlag);
            System.out.printf("--------------------------\n");
            System.out.printf("Selection : ");

            selection = input.nextInt();
            
            switch (selection)
            {
                case 1:
                {
                    System.out.println();
                    System.out.printf("--------------------------\n");
                    System.out.printf("New Seed Value: ");
                    seedValue = input.nextInt();
                    System.out.println();
                    System.out.println();
                }
                break;

                case 2:
                {
                    System.out.println();
                    System.out.printf("--------------------------\n");
                    System.out.printf("---  Beg PicFrac Test  ---\n");
                    System.out.printf("--------------------------\n");
                    WS_Beginner_Pie worksheet = 
                                   new WS_Beginner_Pie (seedValue, 10, 
                                           1, 10, 
                                           3, 10,
                                           GEN_DENOM_UNMATCHED,
                                           GEN_WHOLENUM_NO);
                    worksheet.PrintFractions();
                    System.out.println();
                    System.out.println();
                    
                    if (seedValue == 0)
                    {
                        seedValue = worksheet.getSeed();
                    }
                    
                    worksheet.CreateWorksheet(answerFlag);

                }
                break;  
                    
                case 3:
                {
                    System.out.println();
                    System.out.printf("--------------------------\n");
                    System.out.printf("---  Beg PicAdd Test   ---\n");
                    System.out.printf("--------------------------\n");
                    WS_Beginner_PieAdd worksheet = 
                                   new WS_Beginner_PieAdd (seedValue, 20, 
                                           1, 10, 
                                           2, 10,
                                           GEN_DENOM_MATCHED,
                                           GEN_WHOLENUM_NO);
                    worksheet.PrintEquations();
                    System.out.println();
                    System.out.println();
                    
                    if (seedValue == 0)
                    {
                        seedValue = worksheet.getSeed();
                    }
                    
                    worksheet.CreateWorksheet(answerFlag);

                }
                break;
                    
                case 4:
                {
                    System.out.println();
                    System.out.printf("--------------------------\n");
                    System.out.printf("---    Int_Add Test    ---\n");
                    System.out.printf("--------------------------\n");
                    WS_Intermediate worksheet = 
                                    new WS_Intermediate(seedValue, 40, 
                                           1, 16, 
                                           2, 16,
                                           GEN_DENOM_MATCHED,
                                           GEN_WHOLENUM_NO,
                                           '+');
                    worksheet.PrintEquations();
                    System.out.println();
                    System.out.println();
                    
                    if (seedValue == 0)
                    {
                        seedValue = worksheet.getSeed();
                    }
                    
                    worksheet.CreateWorksheet(answerFlag);
                }
                break;  
                    
                 case 5:
                {
                    System.out.println();
                    System.out.printf("--------------------------\n");
                    System.out.printf("---    Int_Sub Test    ---\n");
                    System.out.printf("--------------------------\n");
                    WS_Intermediate worksheet = 
                                    new WS_Intermediate (seedValue, 40, 
                                           1, 16, 
                                           2, 16,
                                           GEN_DENOM_MATCHED,
                                           GEN_WHOLENUM_NO,
                                           '-');
                    worksheet.PrintEquations();
                    System.out.println();
                    System.out.println();
                    
                    if (seedValue == 0)
                    {
                        seedValue = worksheet.getSeed();
                    }
                    
                    worksheet.CreateWorksheet(answerFlag);
                }
                break; 
                     
                case 6:
                {
                    System.out.println();
                    System.out.printf("--------------------------\n");
                    System.out.printf("---    Int_MD Test     ---\n");
                    System.out.printf("--------------------------\n");
                    WS_Intermediate worksheet = 
                                    new WS_Intermediate (seedValue, 40, 
                                           1, 16, 
                                           2, 16,
                                           GEN_DENOM_MATCHED,
                                           GEN_WHOLENUM_NO,
                                           '*');
                    worksheet.PrintEquations();
                    System.out.println();
                    System.out.println();
                    
                    if (seedValue == 0)
                    {
                        seedValue = worksheet.getSeed();
                    }
                    
                    worksheet.CreateWorksheet(answerFlag);
                }
                break; 
                    
                case 9:
                {
                    if (answerFlag == WORKSHEET_ONLY)
                    {
                        answerFlag = ANSWER_SHEET;
                    }
                    else if (answerFlag == ANSWER_SHEET)
                    {
                        answerFlag = ANSWER_ONLY;
                    }
                    else if (answerFlag == ANSWER_ONLY)
                    {
                        answerFlag = WORKSHEET_ONLY;
                    }
                }
                break;
            }
        }

        //======================================================================
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
//------------------------------------------------------------------------------
//  End class WorksheetTester
