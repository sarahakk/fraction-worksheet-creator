//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class        :  PDFTester
//  Author       :  Eric Holm
//  Version      :  1.0.0
//  Description  :  Tester Class for the PDFBox API
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
public class PDFTester
{
    //  Main  //
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static void main(String[] args) throws IOException, COSVisitorException
    {
        //  Main Constants  //
        //======================================================================
        //  Flags for fraction generation
        //  These signal if a pair of fractions should have matching denominators
        final int GEN_DENOM_UNMATCHED = 0;
        final int GEN_DENOM_MATCHED = 1;
    
        //  These signal if fractions are allowed to evaluate to 1 or greater
        final int GEN_WHOLENUM_YES = 0;
        final int GEN_WHOLENUM_NO = 1;
        
        //  Flags for the answersheet generation
        final int WORKSHEET_ONLY = 1;
        final int ANSWER_SHEET = 2;
        
        //  Main Variables  //
        //======================================================================
        Scanner input = new Scanner(System.in);
               
        //  Main Code  //
        //======================================================================
        //  Request Seed Value from User
//        System.out.printf("Enter Seed Value: ");
//        int seedValue = input.nextInt();
        //  Get Number of questions from User
//        System.out.printf("Number of Questions: ");
//        int numQuestions = input.nextInt();
        
        //  Worksheet Test
        WS_Int_Add iaWS = new WS_Int_Add(100, 20 * 2, 
                                         1, 24, 2, 24, 
                                         GEN_DENOM_MATCHED,
                                         GEN_WHOLENUM_NO);
        
        iaWS.CreateWorksheet(ANSWER_SHEET);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
//------------------------------------------------------------------------------
//  End class PDFTester
