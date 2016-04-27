//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class        :  WS_Beginniner_LG
//  Author       :  Eric Holm
//  Version      :  1.1.0 (FINAL)
//  Description  :  Class for Beginner Least to Greatest Worksheets
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//  Package Declaration
//------------------------------------------------------------------------------
package com.elementaryengineers.fwc.random;
//------------------------------------------------------------------------------

//  Imports  //
//------------------------------------------------------------------------------

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
public class WS_Beginner_LG extends WS_Master
{
    //  Class Variables  //
    //==========================================================================
    int[][] answerKey = new int[10][5];
    //==========================================================================
                
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public WS_Beginner_LG (int seedValue, int num_fractions, 
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
        
        //  Create answer key
        createAnswerKey();
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  createAnswerKey  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    private void createAnswerKey()
    {
        //  Method Variables  //
        //======================================================================
        Iterator<Fraction> pies = fractions.iterator();
        int problem = 0;
        //======================================================================
        
        //  Run though all the pies in groups of five.
        while (pies.hasNext())
        {
            //  Use arrays to store the decimal data.
            double[] pieDecimal = new double[5];
            
            //  Grab five pies and convert fraction to decimal
            for (int count = 0; count < 5; count++)
            {
                Fraction pie = pies.next();
                pieDecimal[count] = (double)pie.getNumerator() / 
                                    (double)pie.getDenominator();
//                System.out.printf("%d - %d/%d = %.2f\n", count, 
//                                                         pie.getNumerator(), 
//                                                         pie.getDenominator(),
//                                                         pieDecimal[count]);
            }
            
            //  Seed sorter with a value greater than 1 since all values 
            //  are 1 or less.
            double least = 1.1;
            //  The previousLeast should be smaller then any value to start.
            //  Thus a negative value is used since all other values tested
            //  will be positive.
            double previousLeast = -1;
            
            //  Plug the answers into the key by finding the least value
            //  during each pass of the array.
            for (int answer = 0; answer < 5; answer++)
            {
                for (int count = 0; count < 5; count++)
                {
                    if (pieDecimal[count] < least && 
                        pieDecimal[count] > previousLeast)
                    {
                        answerKey[problem][answer] = count;
                        least = pieDecimal[count];
                    }
                }
                
                //  Setup for next run
                previousLeast = least;
                least = 1.1;
            }
            
            //  Tester Code
//            System.out.printf("Problem %d - \n", problem);
//            for (int count = 0; count < 5; count++)
//            {
//                System.out.printf("%d", answerKey[problem][count]);
//            }
//            System.out.println();
            
            //  Move to next problem.
            problem++;
        }
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
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
                String.format("src/main/resources/images/BeginnerExample3.jpg"); 
            
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
        int problemCount = 0;
        PDFont font = PDType1Font.COURIER_BOLD;
        //======================================================================
        
        //  IMAGES  //
        int imageX = 60;
        int imageY = 470;
        
        //  Print the pies for each problem (5 per problem)
        while (pies.hasNext())
        {
            //  Move to a 2nd column after the first five problems
            if (problemCount == 5)
            {
                imageX = 360;
                imageY = 470;
            }
            
            //  Grab the next five pies and the associated pictures
            for (int pieCount = 0; pieCount < 5; pieCount++)
            {
                Fraction thisPie = pies.next();
                
                String filename =
                    String.format("src/main/resources/images/%d_%d.jpg",
                                   thisPie.getNumerator(),
                                   thisPie.getDenominator());
                
                //  Add the pie to the document and prep for the next one
                PDXObjectImage image = new PDJpeg(document, 
                                       new FileInputStream(filename));
                contentStream.drawXObject(image, 
                                          imageX + pieCount * 45, imageY, 
                                          45, 45);
            }
            
            imageY = imageY - 100;
            problemCount++;
        }
        
        //  Reset variables for the TEXT pass
        pies = fractions.iterator();
        problemCount = 0;
        
        //  TEXT  //
//        System.out.println("Printing TEXT");
        contentStream.beginText();
        
        //  Starting location for the problem numbers and equal signs
        contentStream.moveTextPositionByAmount(30, 500);
        
        //  Print the problem number text
        while (problemCount < 10)
        {
            //  Makes a 2nd column of problem text after the first 5.
            if (problemCount == 5)
            {
                contentStream.moveTextPositionByAmount(300, 500);
            }
            
            //  Parameters for printing the problem numbers
            contentStream.setFont(font, 16);                    // Size
            contentStream.setNonStrokingColor(0, 0, 255);       // Color - Blue
            
            //  Print the problem number
            contentStream.drawString(String.format("%d)", problemCount + 1));
            
            //  Move the cursor for the next line
            contentStream.moveTextPositionByAmount(0, -100);

            //  Increment the answer number value
            problemCount++;
        }
        
        //  END OF TEXT  //
        contentStream.endText();
        
        //  LINES  //
        //  A place for students to put their answers
        
        int startX = 67;
        int endX   = 92;
        int lineY  = 440;
        
        //  Generate lines for each problem presented
        for (int problem = 0; problem < 10; problem++)
        {
            if (problem == 5)
            {
                startX = 365;
                endX   = 395;
                lineY  = 440;
            }
            
            //  Five lines for each problem
            contentStream.setStrokingColor(0, 0, 0);
            contentStream.drawLine(startX, lineY, endX, lineY);
            contentStream.drawLine(startX + 45, lineY, endX + 45, lineY);
            contentStream.drawLine(startX + 90, lineY, endX + 90, lineY);
            contentStream.drawLine(startX + 135, lineY, endX + 135, lineY);
            contentStream.drawLine(startX + 180, lineY, endX + 180, lineY);
        
            //  Next Line
            lineY = lineY - 100;
        }
        
        //  ANSWERS  //
        //  Reset Counter
        problemCount = 0;
        
        //  Print the answers
        //  Determines if the answers should be printed
        if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
        {
            while (problemCount < 10)
            {
                int problemColumn = 75;
                int problemLine = 450 - (100 * problemCount);
                
                if (problemCount > 4)
                {
                    problemColumn = 375;
                    problemLine += 500;
                }
                
                for (int answerCount = 0; answerCount < 5; answerCount++)
                {
                    int value = answerKey[problemCount][answerCount];

                    contentStream.beginText();
                    contentStream.setFont(font, 20);                // Size
                    contentStream.setNonStrokingColor(255, 0, 0);   // Red
                    contentStream.moveTextPositionByAmount(
                            (45 * value) + problemColumn, 
                            problemLine);
                    contentStream.drawString(String.format("%d", 
                                                           answerCount + 1));
                    contentStream.endText();
                }
                
                problemCount++;
            }
        }
    }    
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
}
//------------------------------------------------------------------------------
//  End class WS_Beginner_LG