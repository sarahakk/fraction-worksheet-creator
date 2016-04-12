//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class        :  WS_Master
//  Author       :  Eric Holm
//  Version      :  1.0.0
//  Description  :  Abstract Class for all Worksheets
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//  Package Declaration
//------------------------------------------------------------------------------
package com.elementaryengineers.fwc.random;
//------------------------------------------------------------------------------

//  Imports  //
//------------------------------------------------------------------------------
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.edit.*;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
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
                                      int gen_denom_flag,
                                      int gen_whole_flag)
    {
        //  Method Variables  //
        //======================================================================
        FractionGenerator fRNG = new FractionGenerator(seedValue, num_fractions, 
                                        min_num, max_num, min_den, max_den, 
                                        gen_denom_flag,
                                        gen_whole_flag);
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
    
    //  genHeader  //
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    //  Creates the worksheet header
    protected void genHeader(PDPageContentStream contentStream) throws IOException
    {
        //  Font Selection
        PDFont font = PDType1Font.COURIER_BOLD;
        
        //  TEXT  //
        contentStream.beginText();
        contentStream.setFont(font, 16);
        contentStream.setNonStrokingColor(0, 0, 0);
        contentStream.moveTextPositionByAmount(50, 750);
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
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    
    //  genExample  //
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    //  Example must be created by each worksheet type
    abstract protected void genExample (PDPageContentStream contentStream) throws IOException;
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    
    //  genProblems  //
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    //  Problems must be created by each worksheet type
    abstract protected void genProblems (PDPageContentStream contentStream, int answerFlag) throws IOException;
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    
    //  genFooter  //
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    //  Creates the worksheet footer
    protected void genFooter(PDPageContentStream contentStream) throws IOException
    {
        //  Beginning Line
        contentStream.setStrokingColor(0, 0, 0);
        contentStream.drawLine(10, 30, 600, 30);
        
        //  Font Selection
        PDFont font = PDType1Font.COURIER_BOLD;

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
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
}
//------------------------------------------------------------------------------
//  End class WS_Master