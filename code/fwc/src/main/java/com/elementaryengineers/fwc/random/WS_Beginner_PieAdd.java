//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class        :  WS_Beginner_PieAdd
//  Author       :  Eric Holm
//  Version      :  1.0.0
//  Description  :  Class for Beginner Pie Addition Worksheets
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//  Package Declaration
//------------------------------------------------------------------------------
package com.elementaryengineers.fwc.random;
//------------------------------------------------------------------------------

//  Imports  //
//------------------------------------------------------------------------------
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
public class WS_Beginner_PieAdd extends WS_Master
{
    //  Class Constants  //
    //==========================================================================
    //  Flags for fraction retrieval
    protected static final int FRACTION1 = 1;
    protected static final int FRACTION2 = 2;
    protected static final int ANSWER = 3;
    //==========================================================================
    
    //  Class Variables  //
    //==========================================================================
    private final List<Equation> equations = new ArrayList<>();
    //==========================================================================
                
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public WS_Beginner_PieAdd (int seedValue, int num_fractions, 
                                              int min_num, int max_num, 
                                              int min_den, int max_den,
                                              int gen_masterFlag)
    {
        //  Obtain the needed fractions from the generator.
        super(seedValue, num_fractions, 
                         min_num, max_num, 
                         min_den, max_den, 
                         gen_masterFlag);
        
        //  Create the equations and set up the operator to be used
        //  Two fractions are consumed with each pass through the loop
        for (int count = 0; count < num_fractions; )
        {
            Equation newEq = new Equation(fractions.get(count), fractions.get(count+1), '+');
            equations.add(newEq);
            count = count + 2;
        }
        
        //  Prepare the document object for use to make the worksheet and 
        //  answersheet.
        document = new PDDocument();
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  genExample  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Creates the example section
    @Override
    protected void genExample(PDPageContentStream contentStream) throws IOException
    {
        contentStream.drawLine(10, 550, 600, 550);
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genProblems  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Creates the problem section
    @Override
    protected void genProblems(PDPageContentStream contentStream, 
                               int answerFlag) 
                               throws IOException
    {
        //  Method Variables  //
        //======================================================================
        Iterator<Equation> problems = equations.iterator();
        int equationCount = 0;
        PDFont font = PDType1Font.COURIER_BOLD;
        //======================================================================
        
        //  IMAGES  //
//        System.out.println("Printing IMAGES");
        int imageX = 60;
        int imageY = 440;

        //  Print all the Fractions for each problem.
        while (problems.hasNext())
        {
            //  Makes a 2nd column of problems after the first 5.
            if (equationCount == 5)
            {
                imageX = 360;
                imageY = 440;
            }
            
            //  Grab the next equation and the associated pictures
            Equation thisEq = problems.next();
            
            String filename1 = 
                String.format("src/main/resources/images/%d_%d.jpg", 
                               thisEq.getFraction(FRACTION1).getNumerator(),
                               thisEq.getFraction(FRACTION1).getDenominator());
            
            String filename2 = 
                String.format("src/main/resources/images/%d_%d.jpg", 
                               thisEq.getFraction(FRACTION2).getNumerator(),
                               thisEq.getFraction(FRACTION2).getDenominator());
            
            //  Add both pies to the document and prep for the next equation
            PDXObjectImage image1 = new PDJpeg(document, 
                                               new FileInputStream(filename1));
            PDXObjectImage image2 = new PDJpeg(document, 
                                               new FileInputStream(filename2));
            contentStream.drawXObject(image1, imageX, imageY, 75, 75);
            contentStream.drawXObject(image2, imageX + 100, imageY, 75, 75);
            imageY = imageY - 100;
            equationCount++;
        }
        
        //  Reset variables for 2nd pass
        problems = equations.iterator();
        equationCount = 0;
        
        //  TEXT  //
//        System.out.println("Printing TEXT");
        contentStream.beginText();

        //  Starting location for the text portion
        contentStream.moveTextPositionByAmount(30, 500);
        
        //  Print the problem number text
        while (problems.hasNext())
        {
            //  Makes a 2nd column of problem text after the first 5.
            if (equationCount == 5)
            {
                contentStream.moveTextPositionByAmount(300, 500);
            }
            
            //  Grab the next equation
            Equation thisEq = problems.next();
            
            //  Parameters for printing the problem numbers
            contentStream.setFont(font, 20);                    // Size
            contentStream.setNonStrokingColor(0, 0, 255);       // Color - Blue
            
            //  Print the problem number
            contentStream.drawString(String.format("%d)", equationCount + 1));
            
            //  Parameters for printing the operator and equals signs
            contentStream.setFont(font, 20);                    // Size
            contentStream.setNonStrokingColor(0, 0, 0);         // Color - Black
            
            //  Move the cursor & print the operator
            contentStream.moveTextPositionByAmount(110, -27);
            contentStream.drawString(String.format("+"));
            
            //  Move the cursor & print the equal sign
            contentStream.moveTextPositionByAmount(95, 0);
            contentStream.drawString(String.format("="));

            //  Determines if the answers should be printed
            if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
            {
                //  Parameters for printing the answer fraction
                contentStream.setNonStrokingColor(255, 0, 0);   // Color - Red
                
                //  Print the values for the answer fraction
                contentStream.moveTextPositionByAmount(30, 10);
                Fraction thisFr = thisEq.getFraction(ANSWER);
                contentStream.drawString(String.format("%d", 
                                         thisFr.getNumerator()));
                contentStream.moveTextPositionByAmount(0, -20);
                contentStream.drawString(String.format("%d", 
                                         thisFr.getDenominator()));
            }

            //  Determines how far to move the cursor for the next problem.
            //  Based on if the answers are being printed or not.
            if (answerFlag == WORKSHEET_ONLY)
            {
                contentStream.moveTextPositionByAmount(-195, -73);
            }
            else
            {
                contentStream.moveTextPositionByAmount(-235, -63);
            }

            //  Increment the answer number value
            equationCount++;
        }
        
        //  END OF TEXT  //
        contentStream.endText();
        
        //  LINES  //
        
        //  Print all of the lines between fractions
        //  Starting locations for the lines
        int startX = 135;
        int endX   = 157;
        int lineY  = 478;

        //  Draw lines for everything on one line.
        //  This includes four fractions and two answers (if answersheet)
        for (int count = 0; count < equationCount / 2; count++)
        {
            if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
            {
                contentStream.setStrokingColor(255, 0, 0);
                contentStream.drawLine(startX + 130, lineY, endX + 130, lineY);
                contentStream.drawLine(startX + 430, lineY, endX + 430, lineY);
            }
 
            //  Move to the next line
            lineY = lineY - 100;
        }
    }      
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  PrintEquations  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Used to test equation creation
    public void PrintEquations()
    {
        for (Equation equation : equations) 
        {
            System.out.printf("Test: %s\n", equation.toString());
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
//------------------------------------------------------------------------------
//  End class WS_Beginniner_PieAdd