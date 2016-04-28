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
//  Class       :  WS_Master
//  Author      :  Eric Holm
//  Version     :  1.1.0 (FINAL)
//  Description :  Abstract Class for all Worksheets
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//  Package Declaration
//------------------------------------------------------------------------------
package com.elementaryengineers.fwc.random;
//------------------------------------------------------------------------------

//  Imports  //
//------------------------------------------------------------------------------

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
abstract class WS_Master
{
    //  Class Constants  //
    //==========================================================================
    //  Flags for the answersheet generation
    protected static final int WORKSHEET_ONLY = 1;
    protected static final int ANSWER_SHEET = 2;
    protected static final int ANSWER_ONLY = 3;
    //==========================================================================
    
    //  Class Variables  //
    //==========================================================================
    protected List<Fraction> fractions; 
    protected int seed;
    protected PDDocument document;
    //==========================================================================
                
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public WS_Master (int seedValue, int num_fractions, 
                                     int min_num, int max_num, 
                                     int min_den, int max_den,
                                     int gen_masterFlag)
    {
        //  Method Variables  //
        //======================================================================
        FractionGenerator fRNG = new FractionGenerator(seedValue, num_fractions, 
                                                       min_num, max_num, 
                                                       min_den, max_den, 
                                                       gen_masterFlag);
        //======================================================================

        //  Obtain the needed fractions from the generator.
        fractions = fRNG.getFractions();
        //  Grab the seedValue used in the fRNG
        seed = fRNG.getSeedValue();
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  getSeed  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Returns the seed value
    public int getSeed()
    {
        return seed;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
       
    //  PrintFractions  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //  Used for testing purposes to show raw fraction generation
    public void PrintFractions()
    {
        for (Fraction fraction : fractions) 
        {
            System.out.printf("%s\n", fraction.toString());
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  CreateWorksheet  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void CreateWorksheet(int answerFlag) throws IOException, 
                                                       COSVisitorException
    {
        //  Creates the worksheet
        if ((answerFlag == WORKSHEET_ONLY) || (answerFlag == ANSWER_SHEET))
        {
            //  Make the PDF document for the worksheet page
            PDPage worksheet = new PDPage();
            document.addPage(worksheet);
 
            //  Populate the four components of the page.
            try (PDPageContentStream contentStream = 
             new PDPageContentStream(document, worksheet)) 
            {
                genHeader(contentStream);
                genExample(contentStream);
                genProblems(contentStream, WORKSHEET_ONLY);
                genFooter(contentStream);
            }
        }
        
        //  Creates the answersheet
        if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
        {
            //  Made the PDF document for the answersheet page
            PDPage answerSheet = new PDPage();
            document.addPage(answerSheet);
            
            //  Populate the four components of the page.
            try (PDPageContentStream contentStream = 
             new PDPageContentStream(document, answerSheet)) 
            {
                genHeader(contentStream);
                genExample(contentStream);
                genProblems(contentStream, answerFlag);
                genFooter(contentStream);
            }
        }

        //  Save the document in a temp location for viewing
        //  OS - MAC OS X
        if (System.getProperty("os.name").equals("Mac OS X"))
            document.save("./Temp.pdf");
        //  OS - Windows
        else
            document.save("C:/Temp/Temp.pdf");

        //  Close the document
        document.close();
        
        //  Automatically open the document
        if (Desktop.isDesktopSupported()) 
        {
            try 
            {
                File myFile = null;

                //  Open the document from the temp location
                //  OS - MAC OS X
                if (System.getProperty("os.name").equals("Mac OS X"))
                    myFile = new File("./Temp.pdf");
                //  OS - Windows
                else
                    myFile = new File("C:/Temp/Temp.pdf");

                //  Open the document
                Desktop.getDesktop().open(myFile);
            } 
            //  Adobe Reader is not installed
            catch (IOException ex) 
            {
            }
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  genHeader  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Creates the worksheet header
    private void genHeader(PDPageContentStream contentStream) 
                             throws IOException
    {
        //  Font Selection
        PDFont font = PDType1Font.COURIER_BOLD;
        
        //  TEXT  //
        contentStream.beginText();
        contentStream.setFont(font, 16);
        contentStream.setNonStrokingColor(0, 0, 0);
        contentStream.moveTextPositionByAmount(40, 750);
        contentStream.drawString("Name   : _________________");
        contentStream.moveTextPositionByAmount(275, 0);
        contentStream.drawString("Score  : _________________");
        contentStream.moveTextPositionByAmount(-275, -30);
        contentStream.drawString("Teacher: _________________");
        contentStream.moveTextPositionByAmount(275, 0);
        contentStream.drawString("Date   : _________________");
        contentStream.endText();
        
        //  End Line  //
        contentStream.setStrokingColor(0, 0, 0);
        contentStream.drawLine(10, 690, 600, 690);
    }    
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genExample  //
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    //  Example must be created by each worksheet type
    abstract protected void genExample (PDPageContentStream contentStream) 
                                        throws IOException;
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    
    //  genProblems  //
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    //  Problems must be created by each worksheet type
    abstract protected void genProblems (PDPageContentStream contentStream, 
                            int answerFlag) 
                            throws IOException;
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    
    //  genFooter  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Creates the worksheet footer
    private void genFooter(PDPageContentStream contentStream) throws IOException
    {
        //  Beginning Line
        contentStream.setStrokingColor(0, 0, 0);
        contentStream.drawLine(10, 30, 600, 30);
        
        //  Font Selection
        PDFont font  = PDType1Font.COURIER_BOLD;
        
        //  TEXT  //
        contentStream.beginText();
        contentStream.setFont(font, 8);
        contentStream.setNonStrokingColor(0, 0, 0);
        contentStream.moveTextPositionByAmount(10, 10);
        contentStream.drawString(String.format("Worksheet #%d", seed));
        contentStream.moveTextPositionByAmount(430, 0);
        contentStream.drawString("Fraction Worksheet Creator - 2016");
        contentStream.endText();
    }    
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
}
//------------------------------------------------------------------------------
//  End class WS_Master