//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class        :  WS_Beginniner_Pie
//  Author       :  Eric Holm
//  Version      :  1.0.0
//  Description  :  Class for all Beginner Pie Worksheets
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
        
        //  Text  //
        contentStream.beginText();
        contentStream.moveTextPositionByAmount(60, 520);
        contentStream.endText();
        
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
    }    
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
}
//------------------------------------------------------------------------------
//  End class WS_Beginner_Pie