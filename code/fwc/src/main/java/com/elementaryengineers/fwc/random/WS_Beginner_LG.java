//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//  Class        :  WS_Beginniner_LG
//  Author       :  Eric Holm
//  Version      :  1.0.0
//  Description  :  Class for Beginner Least to Greatest Worksheets
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
public class WS_Beginner_LG extends WS_Master
{
    //  Class Constants  //
    //==========================================================================
    //==========================================================================
    
    //  Class Variables  //
    //==========================================================================
    //==========================================================================
                
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public WS_Beginner_LG (int seedValue, int num_fractions, 
                                           int min_num, int max_num, 
                                           int min_den, int max_den,
                                           int gen_masterFlag)
    {
        //  Obtain the needed fractions from the generator.
        super(seedValue, num_fractions, 
              min_num, max_num, min_den, max_den, 
              gen_masterFlag);
        
        //  Prepare the worksheet object for use
        document = new PDDocument();
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    //  CreateWorksheet  //
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void CreateWorksheet(int answerFlag) throws IOException, COSVisitorException
    {
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
    }    
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
}
//------------------------------------------------------------------------------
//  End class WS_Beginner_LG