//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class        :  WS_Advanced
//  Author       :  Eric Holm
//  Version      :  1.0.0
//  Description  :  Class for all Advanced Level Worksheets
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
public class WS_Advanced extends WS_Master
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
    public WS_Advanced (int seedValue, int num_fractions, 
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
            if (count == 10 & worksheetType == '*')
            {
                worksheetType = '/';
            }
            
            //  Create the equations using two fractions at a time
            Equation newEq = new Equation(fractions.get(count), fractions.get(count+1), worksheetType);
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
    }    
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
}
//------------------------------------------------------------------------------
//  End class WS_Advanced