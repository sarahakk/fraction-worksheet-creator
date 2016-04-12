//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class        :  WS_Beginniner_Pie
//  Author       :  Eric Holm
//  Version      :  1.0.0
//  Description  :  Class for Beginner Pie Worksheets
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

import java.util.Iterator;

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
public class WS_Beginner_Pie extends WS_Master
{
    //  Class Constants  //
    //==========================================================================
    //==========================================================================
    
    //  Class Variables  //
    //==========================================================================
    //==========================================================================
                
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public WS_Beginner_Pie (int seedValue, int num_fractions, 
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
        
        //  Prepare the worksheet object for use
        document = new PDDocument();
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  CreateWorksheet  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void CreateWorksheet(int answerFlag) throws IOException, COSVisitorException
    {
        if ((answerFlag == WORKSHEET_ONLY) || (answerFlag ==  ANSWER_SHEET))
        {
            PDPage worksheet = new PDPage();
            document.addPage(worksheet);
        
            try (PDPageContentStream contentStream = new PDPageContentStream(document, worksheet)) 
            {
                super.genHeader(contentStream);
                genExample(contentStream);
                genProblems(contentStream, WORKSHEET_ONLY);
                super.genFooter(contentStream);
            }
        }
        
        if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
        {
            PDPage answerSheet = new PDPage();
            document.addPage(answerSheet);
            
            try (PDPageContentStream contentStream = new PDPageContentStream(document, answerSheet)) 
            {
                super.genHeader(contentStream);
                genExample(contentStream);
                genProblems(contentStream, answerFlag);
                super.genFooter(contentStream);
            }
        }

        if (System.getProperty("os.name").equals("Mac OS X"))
            document.save("./Temp.pdf");
        else
            document.save("C:/Temp/Temp.pdf");

        document.close();
        
        if (Desktop.isDesktopSupported()) 
        {
            try 
            {
                File myFile = null;

                if (System.getProperty("os.name").equals("Mac OS X"))
                    myFile = new File("./Temp.pdf");
                else
                    myFile = new File("C:/Temp/Temp.pdf");

                Desktop.getDesktop().open(myFile);
            } 
            catch (IOException ex) 
            {
        // no application registered for PDFs
            }
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  genExample  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    @Override
    protected void genExample(PDPageContentStream contentStream) throws IOException
    {
        contentStream.drawLine(10, 550, 600, 550);
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  genProblems  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    @Override
    protected void genProblems(PDPageContentStream contentStream, int answerFlag) throws IOException
    {
        //  Method Variables  //
        //======================================================================
        Iterator<Fraction> pies = fractions.iterator();
        int fractionCount = 0;
        int imageX = 60;
        int imageY = 440;
        //======================================================================
        
        //  Font Selection  //
        PDFont font = PDType1Font.COURIER_BOLD;
        
        //  Print the pies for each problem.
        while (pies.hasNext())
        {
            if (fractionCount == 5)
            {
                imageX = 360;
                imageY = 440;
            }
            
            Fraction thisPie = pies.next();
        
            String filename = String.format("src/main/resources/images/%d_%d.jpg", thisPie.getNumerator(),
                                                             thisPie.getDenominator());
            
            PDXObjectImage image = new PDJpeg(document, new FileInputStream(filename));
            contentStream.drawXObject(image, imageX, imageY, 100, 100);
            imageY = imageY - 100;
            fractionCount++;
        }
        
        //  TEXT  //
        contentStream.beginText();
        
        //  Starting location for the problem numbers and equal signs
        contentStream.moveTextPositionByAmount(30, 485);
        
        //  Reset variables for 2nd pass
        pies = fractions.iterator();
        fractionCount = 0;
        
        //  Print the problem text
        while (pies.hasNext())
        {
            //  Makes a 2nd column of problem text after the first 5.
            if (fractionCount == 5)
            {
                contentStream.moveTextPositionByAmount(290, 500);
            }
            
            Fraction thisPie = pies.next();
            
            //  Parameters for printing the problem numbers
            contentStream.setFont(font, 24);                    // Size
            contentStream.setNonStrokingColor(0, 0, 255);       // Color - Blue
            
            //  Print the problem number
            contentStream.drawString(String.format("#%d", fractionCount + 1));
            
            //  Move the cursor
            contentStream.moveTextPositionByAmount(140, 0);
            
            //  Parameters for printing the equals signs
            contentStream.setFont(font, 20);                    // Size
            contentStream.setNonStrokingColor(0, 0, 0);         // Color - Black
            
            //  Print the equal sign
            contentStream.drawString(String.format("="));
            
            //  Determines if the answers should be printed
            if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
            {
                //  Parameters for printing the answer fraction
                contentStream.setNonStrokingColor(255, 0, 0);   // Color - Red
                
                //  Print the values for the answer fraction
                contentStream.moveTextPositionByAmount(40, 10);
                contentStream.drawString(String.format("%d", thisPie.getNumerator()));
                contentStream.moveTextPositionByAmount(0, -20);
                contentStream.drawString(String.format("%d", thisPie.getDenominator()));
            }
            
            //  Determines how far to move the cursor for the next problem.
            //  Based on if the answers are being printed or not.
            if (answerFlag == WORKSHEET_ONLY)
            {
                contentStream.moveTextPositionByAmount(-140, -100);
            }
            else
            {
                contentStream.moveTextPositionByAmount(-180, -90);
            }

            fractionCount++;
        }
        
        contentStream.endText();
        
        //  LINES  //
        
        //  Print all of the lines between fractions
        //  Starting locations for the lines
        int startX = 80;
        int endX   = 102;
        int lineY  = 490;

        //  Draw lines for everything on one line.
        //  This includes four fractions and two answers (if answersheet)
        if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
        {
            for (int count = 0; count < fractionCount / 2; count++)
            {
                contentStream.setStrokingColor(255, 0, 0);
                contentStream.drawLine(startX + 130, lineY, endX + 130, lineY);
                contentStream.drawLine(startX + 420, lineY, endX + 420, lineY);
                
                //  Move to the next line
                lineY = lineY - 100;
            }
        }
        
            
        
        
    }    
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
}
//------------------------------------------------------------------------------
//  End class WS_Beginner_Pie