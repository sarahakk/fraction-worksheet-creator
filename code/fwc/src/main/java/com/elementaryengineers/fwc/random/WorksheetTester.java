/****************************************************************************
 * Name: Fraction Worksheet Creator
 * Team: Elementary Engineers 
 * Date produced: 04/28/2016
 * ________________________________
 * Purpose of program:
 * The Fraction Worksheet Creator (FWC) is a new stand-alone product 
 * that allows teachers and students to create random exercise worksheets 
 * to practice operations with fractions.The generated worksheets can contain 
 * fraction problems of various difficulty levels, from basic addition and 
 * subtraction problems with visuals and images suitable for small children, 
 * to quite advanced fraction equations. 
 * ****************************************************************************
 */

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

import org.apache.pdfbox.exceptions.COSVisitorException;

import java.io.IOException;
import java.util.Scanner;
//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
public class WorksheetTester
{
    //  Main  //
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static void main(String[] args) throws IOException, 
                                                  COSVisitorException
    {
        Menu();
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    //  Menu  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static void Menu () throws IOException, COSVisitorException
    {
        //  Menu Constants  //
        //======================================================================
        //  Flags for fraction generation
        final int GEN_UNIQUE5       = 1;         // Used for L->G worksheet
        final int GEN_WHOLENUM_NO   = 2;         // Eliminate whole numbers
        final int GEN_DENOM_MATCHED = 4;         // Match denom pairs
        
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
            System.out.printf(" 4)  Beg_LeastGreat       \n");
            System.out.printf(" 5)  Int_Add              \n");
            System.out.printf(" 6)  Int_Sub              \n");
            System.out.printf(" 7)  Int_MD               \n");
            System.out.printf(" 8)  Advanced_Add         \n");
            System.out.printf(" 9)  Advanced_Sub         \n");
            System.out.printf("10)  Advanced_Multi       \n");
            System.out.printf("                          \n");
            System.out.printf(" 99)  Answer Flag         \n");
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
                                           2, 10,
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
                                           GEN_DENOM_MATCHED + GEN_WHOLENUM_NO);
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
                    System.out.printf("---    Beg L->G Test   ---\n");
                    System.out.printf("--------------------------\n");
                    WS_Beginner_LG worksheet = 
                                   new WS_Beginner_LG (seedValue, 50, 
                                           1, 10, 
                                           2, 10,
                                           GEN_UNIQUE5);
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
                    System.out.printf("---    Int_Add Test    ---\n");
                    System.out.printf("--------------------------\n");
                    WS_Intermediate worksheet = 
                                    new WS_Intermediate(seedValue, 40, 
                                           1, 12, 
                                           2, 12,
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
                    
                case 6:
                {
                    System.out.println();
                    System.out.printf("--------------------------\n");
                    System.out.printf("---    Int_Sub Test    ---\n");
                    System.out.printf("--------------------------\n");
                    WS_Intermediate worksheet = 
                                    new WS_Intermediate (seedValue, 40, 
                                           1, 12, 
                                           2, 12,
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

                case 7:
                {
                    System.out.println();
                    System.out.printf("--------------------------\n");
                    System.out.printf("---    Int_MD Test     ---\n");
                    System.out.printf("--------------------------\n");
                    WS_Intermediate worksheet = 
                                    new WS_Intermediate (seedValue, 40, 
                                           1, 12, 
                                           2, 12,
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
                    
                case 8:
                {
                    System.out.println();
                    System.out.printf("--------------------------\n");
                    System.out.printf("---    Adv_Add Test    ---\n");
                    System.out.printf("--------------------------\n");
                    WS_Advanced worksheet = 
                                    new WS_Advanced(seedValue, 20, 
                                           1, 8, 
                                           2, 8,
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
                    
                case 9:
                {
                    System.out.println();
                    System.out.printf("--------------------------\n");
                    System.out.printf("---    Adv_Sub Test    ---\n");
                    System.out.printf("--------------------------\n");
                    WS_Advanced worksheet = 
                                    new WS_Advanced (seedValue, 20, 
                                           1, 8, 
                                           2, 8,
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
                    
                case 10:
                {
                    System.out.println();
                    System.out.printf("--------------------------\n");
                    System.out.printf("---   Adv_Multi Test   ---\n");
                    System.out.printf("--------------------------\n");
                    WS_Advanced worksheet = 
                                    new WS_Advanced (seedValue, 20, 
                                           1, 8, 
                                           2, 8,
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
                    
                case 99:
                {
                    System.out.println();
                    System.out.println();
                    
                    switch (answerFlag) 
                    {
                        case WORKSHEET_ONLY:
                            answerFlag = ANSWER_SHEET;
                            break;
                        case ANSWER_SHEET:
                            answerFlag = ANSWER_ONLY;
                            break;
                        case ANSWER_ONLY:
                            answerFlag = WORKSHEET_ONLY;
                            break;
                        default:
                            break;
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
