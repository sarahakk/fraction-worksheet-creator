//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class        :  WS_Intermediate
//  Author       :  Eric Holm
//  Version      :  1.0.0
//  Description  :  Class for all Intermediate Level Worksheets
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//  Package Declaration
//------------------------------------------------------------------------------
package com.elementaryengineers.fwc.random;
//------------------------------------------------------------------------------

//  Imports  //
//------------------------------------------------------------------------------
import java.awt.Desktop;

import java.io.File;
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
                                         int gen_denom_flag,
                                         int gen_whole_flag,
                                         char worksheetType)
    {
        //  Obtain the needed fractions from the generator.
        super(seedValue, num_fractions, 
              min_num, max_num, min_den, max_den, 
              gen_denom_flag,
              gen_whole_flag);
        
        this.worksheetType = worksheetType;
        
        for (int count = 0; count < num_fractions; )
        {
            if (count == 10 & worksheetType == '*')
            {
                worksheetType = '/';
            }
            
            Equation newEq = new Equation(fractions.get(count), fractions.get(count+1), worksheetType);
            equations.add(newEq);
            count = count + 2;
        }
        
        //  Prepare the worksheet object for use
        document = new PDDocument();
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  PrintEquations  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void PrintEquations()
    {
        for (Equation equation : equations) 
        {
            System.out.printf("Test: %s\n", equation.toString());
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //  getEquation  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Equation getEquation(int number)
    {
        return equations.get(number);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
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
        Iterator<Equation> problems = equations.iterator();
        int equationCount = 0;
        char operator = worksheetType;
        //======================================================================
        
        //  Font Selection  //
        PDFont font = PDType1Font.COURIER_BOLD;

        //  Text  //
        contentStream.beginText();
        contentStream.moveTextPositionByAmount(60, 520);
        
        //  Print all the Fractions for each problem.
        while (problems.hasNext())
        {
            if (equationCount == 10)
            {
                contentStream.moveTextPositionByAmount(320, 500);
            }
            contentStream.setFont(font, 20);
            contentStream.setNonStrokingColor(0, 0, 0);           
            
            Equation thisEq = problems.next();
            Fraction thisFr = thisEq.getFraction(FRACTION1);
            
            contentStream.drawString(String.format("%d", thisFr.getNumerator()));
            contentStream.moveTextPositionByAmount(0, -20);
            contentStream.drawString(String.format("%d", thisFr.getDenominator()));
            
            thisFr = thisEq.getFraction(FRACTION2);
            
            contentStream.moveTextPositionByAmount(75, 20);
            contentStream.drawString(String.format("%d", thisFr.getNumerator()));
            contentStream.moveTextPositionByAmount(0, -20);
            contentStream.drawString(String.format("%d", thisFr.getDenominator()));
                    
            if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
            {
                thisFr = thisEq.getFraction(ANSWER);
                
                contentStream.setNonStrokingColor(255, 0, 0);
                                
                contentStream.moveTextPositionByAmount(75, 20);
                contentStream.drawString(String.format("%d", thisFr.getNumerator()));
                contentStream.moveTextPositionByAmount(0, -20);
                contentStream.drawString(String.format("%d", thisFr.getDenominator()));
            }
            
            if (answerFlag == WORKSHEET_ONLY)
            {
                contentStream.moveTextPositionByAmount(-75, -30);
            }
            else
            {
                contentStream.moveTextPositionByAmount(-150, -30);
            }
            equationCount++;
        }
        contentStream.endText();
        
        //  Print all of the operators and equal signs
        contentStream.beginText();
        contentStream.moveTextPositionByAmount(95, 510);
        
        for (int count = 0; count < equationCount; count++)
        {
            if (count == 10)
            {
                contentStream.moveTextPositionByAmount(320, 500);
                
                if (operator == '*')
                {
                    operator = 247;
                }
            }
            
            contentStream.setFont(font, 28);
            contentStream.setNonStrokingColor(0, 0, 0);
            
            contentStream.drawString(String.format("%c", operator));
            contentStream.moveTextPositionByAmount(80, 0);
            
            contentStream.drawString("=");
            contentStream.moveTextPositionByAmount(-80, -50);
        }
        contentStream.endText();
        
        //  Print all of the lines between fractions
        int startX = 60;
        int endX   = 82;
        int lineY = 515;
                        
        for (int count = 0; count < equationCount / 2; count++)
        {
            contentStream.setStrokingColor(0, 0, 0);
            contentStream.drawLine(startX, lineY, endX, lineY);
            contentStream.drawLine(startX + 75, lineY, endX + 75, lineY);
            contentStream.drawLine(startX + 320, lineY, endX + 320, lineY);
            contentStream.drawLine(startX + 395, lineY, endX + 395, lineY);
            
            if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
            {
                contentStream.setStrokingColor(255, 0, 0);
                contentStream.drawLine(startX + 150, lineY, endX + 150, lineY);
                contentStream.drawLine(startX + 470, lineY, endX + 470, lineY);
            }
 
            lineY = lineY - 50;
        }
    }    
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
}
//------------------------------------------------------------------------------
//  End class WS_Intermediate