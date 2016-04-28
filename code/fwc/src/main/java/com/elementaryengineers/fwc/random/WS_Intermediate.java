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
//  Class        :  WS_Intermediate
//  Author       :  Eric Holm
//  Version      :  1.1.0 (FINAL)
//  Description  :  Class for all Intermediate Level Worksheets
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
public class WS_Intermediate extends WS_Master
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
    private final char worksheetType;
    //==========================================================================
                
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public WS_Intermediate (int seedValue, int num_fractions, 
                                         int min_num, int max_num, 
                                         int min_den, int max_den,
                                         int gen_masterFlag,
                                         char worksheetType)
    {
        //  Obtain the needed fractions from the generator.
        super(seedValue, num_fractions, 
              min_num, max_num, min_den, max_den, 
              gen_masterFlag);
        
        //  Intermediate Worksheets can all be created from this one class.
        //  The worksheetType flag differentiates the three.
        this.worksheetType = worksheetType;
        
        //  Create the equations and set up the operator to be used
        for (int count = 0; count < num_fractions; )
        {
            //  Mulitplication and Division share a worksheet
            //  This ensures 10 of each type.
            if (count == 20 & worksheetType == '*')
            {
                worksheetType = '/';
            }
            
            //  Create the equations using two fractions at a time
            Equation newEq = new Equation(fractions.get(count), 
                                          fractions.get(count+1), 
                                          worksheetType);
            equations.add(newEq);
            count = count + 2;
        }
        
        //  Convert answers to lowest terms
        Iterator<Equation> problems = equations.iterator();
        
        while (problems.hasNext())
        {
            Equation problem = problems.next();
            Fraction thisAnswer = problem.getFraction(ANSWER);
            thisAnswer.convertLowestTerms();
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
        String filename = null;
        
        if (worksheetType == '+')
        {
            filename = 
            String.format("src/main/resources/images/IntermediateExample1.jpg"); 
        }
        
        if (worksheetType == '-')
        {
            filename = 
            String.format("src/main/resources/images/IntermediateExample2.jpg"); 
        }
        
        if ((worksheetType == '*') || (worksheetType == '/'))
        {
            filename = 
            String.format("src/main/resources/images/IntermediateExample3.jpg"); 
        }
            
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
        char operator = worksheetType;
        PDFont font = PDType1Font.COURIER_BOLD;
        //======================================================================
        
        //  TEXT  //
        contentStream.beginText();
        
        //  Starting location for the text portion
        contentStream.moveTextPositionByAmount(80, 520);
        
        //  Print all the Fractions for each problem.
        while (problems.hasNext())
        {
            //  Makes a 2nd column of problems after the first 10.
            if (equationCount == 10)
            {
                contentStream.moveTextPositionByAmount(300, 500);
            }
            
            //  Grab the next equation
            Equation thisEq = problems.next();
            
            //  Parameters for printing the problem fractions
            contentStream.setFont(font, 20);                    // Size
            contentStream.setNonStrokingColor(0, 0, 0);         // Color - Black
                        
            //  Print the values for the first fraction
            Fraction thisFr = thisEq.getFraction(FRACTION1);
            contentStream.drawString(String.format("%d", 
                                     thisFr.getNumerator()));
            contentStream.moveTextPositionByAmount(0, -20);
            contentStream.drawString(String.format("%d", 
                                     thisFr.getDenominator()));
            
            //  Move the cursor 
            contentStream.moveTextPositionByAmount(65, 20);
            
            //  Print the values for the second fraction
            thisFr = thisEq.getFraction(FRACTION2);
            contentStream.drawString(String.format("%d", 
                                     thisFr.getNumerator()));
            contentStream.moveTextPositionByAmount(0, -20);
            contentStream.drawString(String.format("%d", 
                                     thisFr.getDenominator()));
                    
            //  Determines if the answers should be printed
            if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
            {
                //  Parameters for printing the answer fraction
                contentStream.setNonStrokingColor(255, 0, 0);   // Color - Red
                
                //  Move the cursor
                contentStream.moveTextPositionByAmount(55, 20);
                
                //  Print the values for the answer fraction
                thisFr = thisEq.getFraction(ANSWER);
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
                    contentStream.moveTextPositionByAmount(40, 10);
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
                    contentStream.moveTextPositionByAmount(-40, -10);
                }
                
                //  Move the cursor back
                contentStream.moveTextPositionByAmount(-55, 0);
            }
            
            //  Move the cursor for the next problem
            contentStream.moveTextPositionByAmount(-65, -30);
            
            //  Counts the number equations to track when a column break
            //  needs to occur
            equationCount++;
        }
        
        //  Reset the cursor closing and opening the contentStream
        contentStream.endText();
        contentStream.beginText();
        
        //  Starting location for the problem numbers
        contentStream.moveTextPositionByAmount(30, 520);
        
        //  Print the problem numbers
        for (int count = 0; count < equationCount; count++)
        {
            //  Makes a 2nd column of problems numbers after the first 10.
            if (count == 10)
            {
                contentStream.moveTextPositionByAmount(290, 500);
            }
            
            //  Parameters for printing the problem numbers
            contentStream.setFont(font, 20);                    // Size
            contentStream.setNonStrokingColor(0, 0, 255);       // Color - Blue
            
            //  Print the problem number
            contentStream.drawString(String.format("%d)", count + 1));
            
            //  Move the cursor
            contentStream.moveTextPositionByAmount(0, -50);
        }
        
        //  Reset the cursor closing and opening the contentStream
        contentStream.endText();
        contentStream.beginText();
        
        //  Starting location for the operators and equal signs
        contentStream.moveTextPositionByAmount(115, 510);
        
        //  Print all of the operators and equal signs
        for (int count = 0; count < equationCount; count++)
        {
            //  Makes a 2nd column of operators and equal signes after the first 10.
            if (count == 10)
            {
                contentStream.moveTextPositionByAmount(300, 500);
                
                //  For multiplication/division worksheets
                //  The operator needs to be changed as well
                if (operator == '*')
                {
                    operator = 247;
                }
            }
            
            //  Parameters for printing the operators and equal signs
            contentStream.setFont(font, 28);                    // Size
            contentStream.setNonStrokingColor(0, 0, 0);         // Color - Black
            
            //  Draw the operator
            contentStream.drawString(String.format("%c", operator));
            
            //  Draw the equal sign
            contentStream.moveTextPositionByAmount(60, 0);
            contentStream.drawString("=");
            
            //  Move the cursor for the next problem
            contentStream.moveTextPositionByAmount(-60, -50);
        }
        
        //  END OF TEXT  //
        contentStream.endText();
        
        //  LINES  //
        
        //  Print all of the lines between fractions
        //  Starting locations for the lines
        int startX = 80;
        int endX   = 102;
        int lineY  = 515;

        //  Draw lines for everything on one line.
        //  This includes four fractions and two answers (if answersheet)
        for (int count = 0; count < equationCount / 2; count++)
        {
            contentStream.setStrokingColor(0, 0, 0);
            contentStream.drawLine(startX, lineY, endX, lineY);
            contentStream.drawLine(startX + 65, lineY, endX + 65, lineY);
            contentStream.drawLine(startX + 300, lineY, endX + 300, lineY);
            contentStream.drawLine(startX + 365, lineY, endX + 365, lineY);
            
            if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
            {
                contentStream.setStrokingColor(255, 0, 0);
                contentStream.drawLine(startX + 120, lineY, endX + 120, lineY);
                contentStream.drawLine(startX + 420, lineY, endX + 420, lineY);
            }
 
            //  Move to the next line
            lineY = lineY - 50;
        }
        
        //  Reset variables for LINES pass (Mixed Fraction Lines)
        problems = equations.iterator();
        equationCount = 0;
        
        if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
        {
            //  Reset line variables
            startX = 281;
            endX   = 291;
            lineY  = 515;
        
            //  Draw lines for the mixed fractions
            while (problems.hasNext())
            {
                if (equationCount == 10)
                {
                    startX = 581;
                    endX = 591;
                    lineY = 515;
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
                lineY = lineY - 50;
            
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
//  End class WS_Intermediate