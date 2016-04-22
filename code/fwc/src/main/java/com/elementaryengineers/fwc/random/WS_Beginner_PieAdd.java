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
import java.awt.Desktop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
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
                                         int gen_denom_flag,
                                         int gen_whole_flag)
    {
        //  Obtain the needed fractions from the generator.
        super(seedValue, num_fractions, 
              min_num, max_num, min_den, max_den, 
              gen_denom_flag,
              gen_whole_flag);
        
        //  Create the equations and set up the operator to be used
        for (int count = 0; count < num_fractions; )
        {
            //  Create the equations using two fractions at a time
            Equation newEq = new Equation(fractions.get(count), fractions.get(count+1), 'B');
            equations.add(newEq);
            count = count + 2;
        }
        
        //  Prepare the document object for use to make the worksheet and 
        //  answersheet.
        document = new PDDocument();
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
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
    
    //  CreateWorksheet  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Main method for making the worksheets and answersheets
    public void CreateWorksheet(int answerFlag) throws IOException, COSVisitorException
    {
        //  Determines if the Worksheet needs to be created.
        if ((answerFlag == WORKSHEET_ONLY) || (answerFlag ==  ANSWER_SHEET))
        {
            //  Create a worksheet page
            PDPage worksheet = new PDPage();
            document.addPage(worksheet);
        
            try (PDPageContentStream contentStream = new PDPageContentStream(document, worksheet)) 
            {
                //  Worksheet created in four parts
                super.genHeader(contentStream);
                genExample(contentStream);
                genProblems(contentStream, WORKSHEET_ONLY);
                super.genFooter(contentStream);
            }
        }
        
        //  Determines if the Answersheet needs to be created.
        if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
        {
            //  Create an answersheet page
            PDPage answerSheet = new PDPage();
            document.addPage(answerSheet);
            
            try (PDPageContentStream contentStream = new PDPageContentStream(document, answerSheet)) 
            {
                //  Answersheet create in four parts
                super.genHeader(contentStream);
                genExample(contentStream);
                genProblems(contentStream, answerFlag);
                super.genFooter(contentStream);
            }
        }

        //  A temp location is used to store the PDF that is made.
        if (System.getProperty("os.name").equals("Mac OS X"))
            document.save("./Temp.pdf");
        else
            document.save("C:/Temp/Temp.pdf");

        //  Document is completed.  Close it so that it can be opened.
        document.close();
        
        //  Opens the PDF so that it can be reviewed / printed / saved
        if (Desktop.isDesktopSupported()) 
        {
            try 
            {
                File myFile = null;

                if (System.getProperty("os.name").equals("Mac OS X"))
                    myFile = new File("./Temp.pdf");
                else
                    myFile = new File("C:/Temp/Temp.pdf");

                //  Actual work of opening the file.
                Desktop.getDesktop().open(myFile);
            } 
            catch (IOException ex) 
            {
            }
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
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
    protected void genProblems(PDPageContentStream contentStream, int answerFlag) throws IOException
    {
        //  Method Variables  //
        //======================================================================
        Iterator<Equation> problems = equations.iterator();
        int equationCount = 0;
        int imageX = 60;
        int imageY = 440;
        //======================================================================
        
        //  Font Selection
        PDFont font = PDType1Font.COURIER_BOLD;

        //  Print all the Fractions for each problem.
        while (problems.hasNext())
        {
            //  Makes a 2nd column of problems after the first 5.
            if (equationCount == 5)
            {
                imageX = 360;
                imageY = 440;
            }
            
            //  Grab the next equation
            Equation thisEq = problems.next();
            
            String filename1 = String.format("src/main/resources/images/%d_%d.jpg", 
                                              thisEq.getFraction(FRACTION1).getNumerator(),
                                              thisEq.getFraction(FRACTION1).getDenominator());
            
            String filename2 = String.format("src/main/resources/images/%d_%d.jpg", 
                                              thisEq.getFraction(FRACTION2).getNumerator(),
                                              thisEq.getFraction(FRACTION2).getDenominator());
            
            PDXObjectImage image1 = new PDJpeg(document, new FileInputStream(filename1));
            PDXObjectImage image2 = new PDJpeg(document, new FileInputStream(filename2));
            contentStream.drawXObject(image1, imageX, imageY, 75, 75);
            contentStream.drawXObject(image2, imageX + 100, imageY, 75, 75);
            imageY = imageY - 100;
            equationCount++;
        }
        
        //  TEXT  //
        contentStream.beginText();
        //  Starting location for the text portion
        contentStream.moveTextPositionByAmount(270, 480);
        
        //  Reset variables for 2nd pass
        problems = equations.iterator();
        equationCount = 0;
        
        //  Print the answer text
        while (problems.hasNext())
        {
            //  Makes a 2nd column of problem text after the first 5.
            if (equationCount == 5)
            {
                contentStream.moveTextPositionByAmount(300, 500);
            }
            
            Equation thisEq = problems.next();
            
            //  Parameters for printing the problem fractions
            contentStream.setFont(font, 20);                    // Size
            contentStream.setNonStrokingColor(0, 0, 0);         // Color - Black
                    
            //  Determines if the answers should be printed
            if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
            {
                //  Parameters for printing the answer fraction
                contentStream.setNonStrokingColor(255, 0, 0);   // Color - Red
                
                //  Print the values for the answer fraction
                Fraction thisFr = thisEq.getFraction(ANSWER);
                contentStream.drawString(String.format("%d", thisFr.getNumerator()));
                contentStream.moveTextPositionByAmount(0, -20);
                contentStream.drawString(String.format("%d", thisFr.getDenominator()));
            }
            
            //  Determines how far to move the cursor for the next problem.
            //  Based on if the answers are being printed or not.
            
            contentStream.moveTextPositionByAmount(0, -80);
            
            //  Counts the number equations to track when a column break
            //  needs to occur
            equationCount++;
        }
        
        //  Reset the cursor closing and opening the contentStream
        contentStream.endText();
        contentStream.beginText();
        
        //  Starting location for the problem numbers
        contentStream.moveTextPositionByAmount(20, 475);
        
        //  Print the problem numbers
        for (int count = 0; count < equationCount; count++)
        {
            //  Makes a 2nd column of problems numbers after the first 5.
            if (count == 5)
            {
                contentStream.moveTextPositionByAmount(300, 500);
            }
            
            //  Parameters for printing the problem numbers
            contentStream.setFont(font, 24);                    // Size
            contentStream.setNonStrokingColor(0, 0, 255);       // Color - Blue
            
            //  Print the problem number
            contentStream.drawString(String.format("#%d", count + 1));
            
            //  Move the cursor
            contentStream.moveTextPositionByAmount(0, -100);
        }
        
        //  Reset the cursor closing and opening the contentStream
        contentStream.endText();
        contentStream.beginText();
        
        //  Starting location for the operators and equal signs
        contentStream.moveTextPositionByAmount(135, 470);
        
        //  Print all of the operators and equal signs
        for (int count = 0; count < equationCount; count++)
        {
            //  Makes a 2nd column of operators and equal signes after the first 10.
            if (count == 5)
            {
                contentStream.moveTextPositionByAmount(300, 500);
            }
            
            //  Parameters for printing the operators and equal signs
            contentStream.setFont(font, 28);                    // Size
            contentStream.setNonStrokingColor(0, 0, 0);         // Color - Black
            
            //  Draw the operator
            contentStream.drawString(String.format("%c", '+'));
            
            //  Move the cursor
            contentStream.moveTextPositionByAmount(105, 0);
            
            //  Draw the equal sign
            contentStream.drawString("=");
            
            //  Move the cursor
            contentStream.moveTextPositionByAmount(-105, -100);
        }
        
        //  END OF TEXT  //
        contentStream.endText();
        
        //  LINES  //
        
        //  Print all of the lines between fractions
        //  Starting locations for the lines
        int startX = 140;
        int endX   = 162;
        int lineY  = 475;

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
}
//------------------------------------------------------------------------------
//  End class WS_Beginniner_PieAdd