//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class       :  WS_Beginniner_Pie
//  Author      :  Eric Holm
//  Version     :  1.1.0 (FINAL)
//  Description :  Class for Beginner Pie Worksheets
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//  Package Declaration
//------------------------------------------------------------------------------
package com.elementaryengineers.fwc.random;
//------------------------------------------------------------------------------

//  Imports  //
//------------------------------------------------------------------------------
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Iterator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
public class WS_Beginner_Pie extends WS_Master
{
    //  Class Variables  //
    //==========================================================================
    //  All variables are extended from WS_Master
    //==========================================================================
    
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public WS_Beginner_Pie (int seedValue, int num_fractions, 
                                           int min_num, int max_num, 
                                           int min_den, int max_den,
                                           int gen_masterFlag)
    {
        //  Obtain the needed fractions from the generator.
        super(seedValue, num_fractions, 
                         min_num, max_num, 
                         min_den, max_den, 
                         gen_masterFlag);
        
        //  Prepare the worksheet object for use
        document = new PDDocument();
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  genExample  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    @Override
    protected void genExample(PDPageContentStream contentStream) 
                              throws IOException
    {
        contentStream.drawLine(10, 550, 600, 550);
        
        int imageX = 10;
        int imageY = 560;
        
        String filename = 
                String.format("src/main/resources/images/BeginnerExample1.jpg"); 
            
        //  Add the example image to the document
        PDXObjectImage image = new PDJpeg(document, 
                                          new FileInputStream(filename));
        contentStream.drawXObject(image, imageX, imageY, 600, 120);
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genProblems  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    @Override
    protected void genProblems(PDPageContentStream contentStream, 
                               int answerFlag) 
                               throws IOException
    {
        //  Method Variables  //
        //======================================================================
        Iterator<Fraction> pies = fractions.iterator();
        int fractionCount = 0;
        PDFont font = PDType1Font.COURIER_BOLD;
        //======================================================================
        
        //  IMAGES  //
//        System.out.println("Printing IMAGES");
        int imageX = 60;
        int imageY = 440;
        
        //  Print the pies for each problem.
        while (pies.hasNext())
        {
            //  Move to a 2nd column after the first five problems
            if (fractionCount == 5)
            {
                imageX = 360;
                imageY = 440;
            }
            
            //  Grab the next pie and the associated picture
            Fraction thisPie = pies.next();
        
            String filename = 
                String.format("src/main/resources/images/%d_%d.jpg", 
                               thisPie.getNumerator(),
                               thisPie.getDenominator());
            
            //  Add the pie to the document and prep for the next one
            PDXObjectImage image = new PDJpeg(document, 
                                              new FileInputStream(filename));
            contentStream.drawXObject(image, imageX, imageY, 100, 100);
            imageY = imageY - 100;
            fractionCount++;
        }
        
        //  Reset variables for the TEXT pass
        pies = fractions.iterator();
        fractionCount = 0;
        
        //  TEXT  //
//        System.out.println("Printing TEXT");
        contentStream.beginText();
        
        //  Starting location for the problem numbers and equal signs
        contentStream.moveTextPositionByAmount(30, 500);
        
        //  Print the problem number text and the operators
        while (pies.hasNext())
        {
            //  Makes a 2nd column of problem text after the first 5.
            if (fractionCount == 5)
            {
                contentStream.moveTextPositionByAmount(300, 500);
            }
            
            //  Grab the next fractional value for the pie
            Fraction thisPie = pies.next();
            
            //  Parameters for printing the problem numbers
            contentStream.setFont(font, 20);                    // Size
            contentStream.setNonStrokingColor(0, 0, 255);       // Color - Blue
            
            //  Print the problem number
            contentStream.drawString(String.format("%d)", fractionCount + 1));
            
            //  Parameters for printing the equals signs
            contentStream.setFont(font, 20);                    // Size
            contentStream.setNonStrokingColor(0, 0, 0);         // Color - Black
            
            //  Move the cursor and print the equals sign
            contentStream.moveTextPositionByAmount(135, -15);
            contentStream.drawString(String.format("="));
            
            //  Determines if the answers should be printed
            if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
            {
                //  Parameters for printing the answer fraction
                contentStream.setFont(font, 20);                // Size
                contentStream.setNonStrokingColor(255, 0, 0);   // Color - Red
                
                //  Print the values for the answer fraction
                contentStream.moveTextPositionByAmount(40, 10);
                contentStream.drawString(String.format("%d", 
                                                thisPie.getNumerator()));
                contentStream.moveTextPositionByAmount(0, -20);
                contentStream.drawString(String.format("%d", 
                                                thisPie.getDenominator()));
                //  Move cursor back
                contentStream.moveTextPositionByAmount(-40, 10);
            }

            //  Move the cursor for the next line
            contentStream.moveTextPositionByAmount(-135, -85);

            //  Increment the answer number value
            fractionCount++;
        }
        
        //  END OF TEXT  //
        contentStream.endText();
        
        //  LINES  //
//        System.out.println("Printing LINES");
        
        //  Print all of the lines between fractions
        //  Starting locations for the lines
        int startX = 205;
        int endX   = 227;
        int lineY  = 490;

        //  Draw lines for the answersheet fractions
        //  Draw both columns at the same time
        if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
        {
            for (int count = 0; count < fractionCount / 2; count++)
            {
                contentStream.setStrokingColor(255, 0, 0);
                contentStream.drawLine(startX, lineY, endX, lineY);
                contentStream.drawLine(startX + 300, lineY, endX + 300, lineY);
                
                //  Move to the next line
                lineY = lineY - 100;
            }
        }
    }    
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
}
//------------------------------------------------------------------------------
//  End class WS_Beginner_Pie