//  Package Declaration
package com.elementaryengineers.fwc.random;

//  Imports  //
//------------------------------------------------------------------------------
import java.io.IOException;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.*;
import org.apache.pdfbox.pdmodel.edit.*;
import org.apache.pdfbox.exceptions.COSVisitorException;

//  Class   :  PDFTester
//  Author  :  Eric Holm
//  Version :  1.0.0

//  Tester Class for the PDFBox API
//------------------------------------------------------------------------------
public class PDFTester
{
    //  Main  //
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static void main(String[] args) throws IOException, 
                                                  COSVisitorException
    {
        PDDocument document = new PDDocument();
        
        PDPage page = new PDPage();
        document.addPage(page);
            
        PDFont font = PDType1Font.HELVETICA_BOLD;
            
        PDPageContentStream contentStream = 
            new PDPageContentStream(document, page);
            
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 700);
        contentStream.drawString("Hello World");
        contentStream.endText();
        
        contentStream.close();
            
        document.save("HelloWorld.pdf");
        document.close();
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
//------------------------------------------------------------------------------
//  End class PDFTester
