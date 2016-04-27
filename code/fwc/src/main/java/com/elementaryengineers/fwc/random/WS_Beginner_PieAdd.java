//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class       :  WS_Beginner_PieAdd
//  Author      :  Eric Holm
//  Version     :  1.1.0 (FINAL)
//  Description :  Class for Beginner Pie Addition Worksheets
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
            Equation newEq = new Equation(fractions.get(count), 
                                          fractions.get(count+1), '+');
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
    protected void genExample(PDPageContentStream contentStream) 
                              throws IOException
    {
        contentStream.drawLine(10, 550, 600, 550);
        
        int imageX = 0;
        int imageY = 560;
        
        String filename = 
                String.format("src/main/resources/images/BeginnerExample2.jpg"); 
            
        //  Add the example image to the document
        PDXObjectImage image = new PDJpeg(document, 
                                          new FileInputStream(filename));
        contentStream.drawXObject(image, imageX, imageY, 600, 120);
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
        int imageX = 50;
        int imageY = 450;

        //  Print all the Fractions for each problem.
        while (problems.hasNext())
        {
            //  Move to a 2nd column after the first five problems
            if (equationCount == 5)
            {
                imageX = 350;
                imageY = 450;
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
            contentStream.drawXObject(image1, imageX, imageY, 70, 70);
            contentStream.drawXObject(image2, imageX + 80, imageY, 70, 70);
            imageY = imageY - 100;
            equationCount++;
        }
        
        //  Reset variables for TEXT pass
        problems = equations.iterator();
        equationCount = 0;
        
        //  TEXT  //
//        System.out.println("Printing TEXT");
        contentStream.beginText();

        //  Starting location for the problem numbers and equal signs
        contentStream.moveTextPositionByAmount(20, 500);
        
        //  Print the problem number text and the operators
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
            contentStream.moveTextPositionByAmount(99, -20);
            contentStream.drawString(String.format("+"));
            
            //  Move the cursor & print the equal sign
            contentStream.moveTextPositionByAmount(80, 0);
            contentStream.drawString(String.format("="));

            //  Determines if the answers should be printed
            if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
            {
                //  Parameters for printing the answer fraction
                contentStream.setFont(font, 20);                // Size
                contentStream.setNonStrokingColor(255, 0, 0);   // Color - Red
                
                //  Print the values for the answer fraction
                contentStream.moveTextPositionByAmount(20, 10);
                Fraction thisFr = thisEq.getFraction(ANSWER);
                contentStream.drawString(String.format("%d", 
                                         thisFr.getNumerator()));
                contentStream.moveTextPositionByAmount(0, -20);
                contentStream.drawString(String.format("%d", 
                                         thisFr.getDenominator()));
                
                //  Print mixed fraction values for answer as well
                if (thisFr.getMixedWhole() > 0)
                {
                    //  Parameters for printing the answer fraction
                    contentStream.setFont(font, 14);                // Size
                    contentStream.setNonStrokingColor(255, 0, 0);   // Red
                    
                    //  Print the whole portion
                    contentStream.moveTextPositionByAmount(30, 10);
                    contentStream.drawString(String.format("or %d",
                                             thisFr.getMixedWhole()));
                    
                    //  If there is an additional fractional portion
                    if (thisFr.getMixedNumerator() > 0)
                    {
                        //  Print numerator and denominator
                        contentStream.moveTextPositionByAmount(40, 10);
                        contentStream.drawString(String.format("%d",
                                                 thisFr.getMixedNumerator()));
                        contentStream.moveTextPositionByAmount(0, -20);
                        contentStream.drawString(String.format("%d",
                                                 thisFr.getMixedDenominator()));
                        
                        //  Move cursor back (Fractional Value)
                        contentStream.moveTextPositionByAmount (-40, 10);
                    }
                    
                    //  Move cursor back (Whole Value)
                    contentStream.moveTextPositionByAmount(-30, -10);
                }
                
                //  Move cursor back (Answers)
                contentStream.moveTextPositionByAmount(-20, 10);
                
            }

            //  Move the cursor for the next line
            contentStream.moveTextPositionByAmount(-179, -80);
            
            //  Increment the answer number value
            equationCount++;
        }
        
        //  END OF TEXT  //
        contentStream.endText();
        
        //  LINES  //
//        System.out.println("Printing LINES");
                
        //  Print all of the lines between fractions
        //  Starting locations for the lines
        int startX = 218;
        int endX   = 240;
        int lineY  = 485;

        //  Draw lines for both answers on one line
        if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
        {
            for (int count = 0; count < equationCount / 2; count++)
            {
                contentStream.setStrokingColor(255, 0, 0);
                contentStream.drawLine(startX, lineY, endX, lineY);
                contentStream.drawLine(startX + 300, lineY, endX + 300, lineY);
                
                //  Move to the next line
                lineY = lineY - 100;
            }
        }
        
        //  Reset variables for LINES pass (Mixed Fraction Lines)
        problems = equations.iterator();
        equationCount = 0;
        
        if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
        {
            //  Reset line variables
            startX = 288;
            endX   = 298;
            lineY  = 485;
        
            //  Draw lines for the mixed fractions
            while (problems.hasNext())
            {
                if (equationCount == 5)
                {
                    startX = 588;
                    endX = 598;
                    lineY = 485;
                }
            
                //  Grab the next equation and just the answer
                Equation thisEq = problems.next();
                Fraction thisFr = thisEq.getFraction(ANSWER);
            
                //  Draw only if there is a mixed fraction that requires it
                if ((thisFr.getMixedWhole() > 0) && 
                    (thisFr.getMixedNumerator() > 0))
                {
                    contentStream.setStrokingColor(255, 0, 0);
                    contentStream.drawLine(startX, lineY, endX, lineY);
                }
            
                //  Move to the next line
                lineY = lineY - 100;
            
                equationCount++;
            }
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