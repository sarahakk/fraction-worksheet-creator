//  Package Declaration
package com.elementaryengineers.fwc.random;

//  Imports  //
//------------------------------------------------------------------------------
import java.io.IOException;
import java.util.Scanner;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.*;
import org.apache.pdfbox.pdmodel.edit.*;
import org.apache.pdfbox.exceptions.COSVisitorException;

//  Class   :  PDFTester
//  Author  :  Eric Holm
//  Version :  1.0.0

//  Tester Class for the PDFBox API
//------------------------------------------------------------------------------
public class PDFTester
{
    //  Main  //
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static void main(String[] args) throws IOException, 
                                                  COSVisitorException
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
        
        //  Main Variables  //
        //======================================================================
        Scanner input = new Scanner(System.in);
               
        //  Main Code  //
        //======================================================================
        //  Request Seed Value from User
        System.out.printf("Enter Seed Value: ");
        int seedValue = input.nextInt();
        //  Get Number of questions from User
        System.out.printf("Number of Questions: ");
        int numQuestions = input.nextInt();
        
        //  Worksheet Test
        WS_Int_Add iaWS = new WS_Int_Add(seedValue, numQuestions * 2, 
                                         1, 24, 2, 24, 
                                         GEN_DENOM_MATCHED,
                                         GEN_WHOLENUM_NO);
        
        PDDocument document = new PDDocument();
        
        PDPage page = new PDPage();
        document.addPage(page);
            
        PDFont font = PDType1Font.HELVETICA_BOLD;
            
        PDPageContentStream contentStream = 
            new PDPageContentStream(document, page);
            
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 500);
        for (int count = 0; count < numQuestions; count++)
        {
            contentStream.moveTextPositionByAmount(0, 25);
            contentStream.drawString(iaWS.getEquation(count).toString());
        }
        contentStream.endText();
        
        contentStream.close();
            
        document.save("Worksheet_Demo.pdf");
        document.close();
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
//------------------------------------------------------------------------------
//  End class PDFTester
