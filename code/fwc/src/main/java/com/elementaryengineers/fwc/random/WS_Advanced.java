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
//  Class        :  WS_Advanced
//  Author       :  Eric Holm
//  Version      :  1.1.0 (FINAL)
//  Description  :  Class for all Advanced Level Worksheets
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

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
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
    
    String[] problemText = new String[10];
    //==========================================================================
                
    //  Constructor  //
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public WS_Advanced (int seedValue, int num_fractions, 
                                         int min_num, int max_num, 
                                         int min_den, int max_den,
                                         int gen_masterFlag,
                                         char worksheetType) throws IOException
    {
        //  Obtain the needed fractions from the generator.
        super(seedValue, num_fractions, 
              min_num, max_num, min_den, max_den, 
              gen_masterFlag);
        
        //  Advanced Worksheets can all be created from this one class.
        //  The worksheetType flag differentiates the three.
        this.worksheetType = worksheetType;
        
        //  Create the equations and set up the operator to be used
        for (int count = 0; count < num_fractions; )
        {
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
        
        //  Form the text for the problems
        formProblems();
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
            String.format("src/main/resources/images/AdvancedExample1.jpg"); 
        }
        
        if (worksheetType == '-')
        {
            filename = 
            String.format("src/main/resources/images/AdvancedExample2.jpg"); 
        }
        
        if ((worksheetType == '*') || (worksheetType == '/'))
        {
            filename = 
            String.format("src/main/resources/images/AdvancedExample3.jpg"); 
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
        PDFont font = PDType1Font.TIMES_ROMAN;
        //======================================================================
        
        //  Problem Text Generation  //
        
        //  Variables that control text placement
        float fontSize = 11;
        float leading = 1.5f * fontSize;
        int margin = 10;
        int width = 270 - 2 * margin;
        int startX = 20 + margin;
        int startY = 540 - margin;

        //  Print all the Fractions for each problem.
        for (int count = 0; count < problemText.length; count++)
        {
            //  Makes a 2nd column of problems after the first 5.
            if (equationCount == 5)
            {
                startX = 330 + margin;
                startY = 540 - margin;
            }
            
            //  Grab the raw problem text
            String text = String.format ("#%d - %s", count + 1, 
                                          problemText[count]);
            
            //  Variables to control each line in the text.
            List<String> lines = new ArrayList<>();
            int lastSpace = -1;
            
            //  Cycle until there is no text left to manipulate
            while (text.length() > 0)
            {
                //  Find the next space in the text
                int spaceIndex = text.indexOf(' ', lastSpace + 1);
                //  Last word.
                if (spaceIndex < 0)
                {    
                    spaceIndex = text.length();
                }
                
                //  Grab the next word and determine if it will fit in the
                //  space available
                String subString = text.substring(0, spaceIndex);
                float size = fontSize * font.getStringWidth(subString) / 1000;
                //  Not enough room... finish this line... start next line.
                if (size > width)
                {
                    if (lastSpace < 0)
                    {
                        lastSpace = spaceIndex;
                    }
                    subString = text.substring(0, lastSpace);
                    lines.add(subString);
                    text = text.substring(lastSpace).trim();
                    lastSpace = -1;
                }
                //  Enough room... add the line as is
                else if (spaceIndex == text.length())
                {
                    lines.add(text);
                    text = "";
                }
                else
                {
                    lastSpace = spaceIndex;
                }
            }

            //  Now write each line to the PDF
            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.moveTextPositionByAmount(startX, startY);            
            for (String line: lines)
            {
                contentStream.drawString(line);
                contentStream.moveTextPositionByAmount(0, -leading);
            }
            
            contentStream.endText();
            startY -= 100;
            equationCount++;
        }
        
        //  Determines if the answers should be printed
        if ((answerFlag == ANSWER_SHEET) || (answerFlag == ANSWER_ONLY))
        {
            //  Starting location for the answers
            contentStream.beginText();
            startX = 195;
            startY = 465;
            contentStream.moveTextPositionByAmount(startX, startY);
            equationCount = 0;
            
            while(problems.hasNext())
            {
                if (equationCount == 5)
                {
                    contentStream.moveTextPositionByAmount(300, 500);
                }
                
                //  This Problem
                Equation thisEq = problems.next();
                //  Parameters for printing the answer fraction
                font = PDType1Font.COURIER_BOLD;
                contentStream.setFont(font, 12);
                contentStream.setNonStrokingColor(255, 0, 0);   // Color - Red
                
                //  Print the values for the answer fraction
                Fraction thisFr = thisEq.getFraction(ANSWER);
                contentStream.drawString(String.format("%d", 
                                         thisFr.getNumerator()));
                contentStream.moveTextPositionByAmount(0, -15);
                contentStream.drawString(String.format("%d", 
                                         thisFr.getDenominator()));
                
                //  Print mixed fraction values for answer as well
                if (thisFr.getMixedWhole() > 0)
                {
                    //  Parameters for printing the answer fraction
                    contentStream.setFont(font, 14);                // Size
                    contentStream.setNonStrokingColor(255, 0, 0);   // Red
                    
                    //  Print the whole portion
                    contentStream.moveTextPositionByAmount(30, 8);
                    contentStream.drawString(String.format("or %d",
                                             thisFr.getMixedWhole()));
                    
                    //  If there is an additional fractional portion
                    if (thisFr.getMixedNumerator() > 0)
                    {
                        //  Print numerator and denominator
                        contentStream.moveTextPositionByAmount(40, 5);
                        contentStream.drawString(String.format("%d",
                                                 thisFr.getMixedNumerator()));
                        contentStream.moveTextPositionByAmount(0, -15);
                        contentStream.drawString(String.format("%d",
                                                 thisFr.getMixedDenominator()));
                        
                        //  Move cursor back (Fractional Value)
                        contentStream.moveTextPositionByAmount (-40, 10);
                    }
                    
                    //  Move cursor back (Whole Value)
                    contentStream.moveTextPositionByAmount(-30, -8);
                }
                
                //  Move the cursor back
                contentStream.moveTextPositionByAmount(0, -85);
                
                //  Counts the number equations to track when a column break
                //  needs to occur
                equationCount++;
            }    
            contentStream.endText();
            
            //  LINES  //
        
            int beginX = 194;
            int endX   = 209;
            int lineY  = 462;

            //  Draw lines for everything on one line.
            for (int count = 0; count < equations.size() ; count = count+2)
            {
                contentStream.setStrokingColor(255, 0, 0);
                contentStream.drawLine(beginX, lineY, endX, lineY);
                contentStream.drawLine(beginX + 300, lineY, endX + 300, lineY);
                
                lineY = lineY - 100;
            }
            
            //  Reset variables for LINES pass (Mixed Fraction Lines)
            problems = equations.iterator();
            equationCount = 0;
            beginX = 266;
            endX = 281;
            lineY = 460;
        
            //  Draw lines for the mixed fractions
            while (problems.hasNext())
            {
                //System.out.printf("%d\n", equationCount);
                if (equationCount == 5)
                {
                    beginX = 566;
                    endX = 581;
                    lineY = 460;
                }
            
                //  Grab the next equation and just the answer
                Equation thisEq = problems.next();
                Fraction thisFr = thisEq.getFraction(ANSWER);
            
                //  Draw only if there is a mixed fraction that requires it
                if ((thisFr.getMixedWhole() > 0) && 
                    (thisFr.getMixedNumerator() > 0))
                {
                    contentStream.setStrokingColor(255, 0, 0);
                    contentStream.drawLine(beginX, lineY, endX, lineY);
                }
                //  Move to the next line
                lineY = lineY - 100;
                equationCount++;
            }
        }
    }    
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    //  formProblems  //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //  Creates the problems used in the worksheet
    private void formProblems() throws FileNotFoundException, IOException
    {
        //  Method Variables  //
        //======================================================================
        String[] masterText  = new String[20];
        String[] names       = new String[10];
        String[] colors      = new String[10];
        String[] subjects    = new String[10];
        File problemFile     = null;
        File nameFile        = new File ("src/main/resources/text/names.txt");
        File colorFile       = new File ("src/main/resources/text/color.txt");
        File subjectFile     = new File ("src/main/resources/text/subject.txt");
        //======================================================================
        
        //  Problems are generated randomly.  Using the same seed as used
        //  to create the fractions.
        Random probRNG = new Random(seed);

        //  Determine where to grab text for problems based on worksheet type
        if (worksheetType == '+')
        {
            problemFile = new File("src/main/resources/text/add.txt");
        }
        if (worksheetType == '-')
        {
            problemFile = new File("src/main/resources/text/sub.txt");
        }
        if (worksheetType == '*' || worksheetType == '/')
        {
            problemFile = new File("src/main/resources/text/multi.txt");
        }
        
        //  Grab all the text in the problem file
        try (BufferedReader read = 
                          new BufferedReader(new FileReader(problemFile)))
        {
            for (int count = 0; count < 20; count++)
            {
                masterText[count] = read.readLine();
                
            }
        }
        
        //  Grab all the names in the names file
        try (BufferedReader read = 
                          new BufferedReader(new FileReader(nameFile)))
        {
            for (int count = 0; count < 10; count++)
            {
                names[count] = read.readLine();
            }
        }
        
        //  Grab all the colors in the color file
        try (BufferedReader read = 
                          new BufferedReader(new FileReader(colorFile)))
        {
            for (int count = 0; count < 10; count++)
            {
                colors[count] = read.readLine();
            }
        }
        
        //  Grab all the subjects in the subject file
        try (BufferedReader read = 
                          new BufferedReader(new FileReader(subjectFile)))
        {
            for (int count = 0; count < 10; count++)
            {
                subjects[count] = read.readLine();
            }
        }
        
        //  Select 10 of the word problems at random using the seed 
        //  used to create the fractions.
        int[] problemSelector = new int[20];
        for (int count = 0; count < 10; )
        {
            int select = probRNG.nextInt(20);
            
            if (problemSelector[select] != 1)
            {
                problemText[count] = masterText[select];
                problemSelector[select] = 1;
                count++;
            }
        }
        
        //  Convert equation data into text data for the problems.
        //  Assume equation could be plugged into any problem.
        int problemNum = 0;
        
        Iterator<Equation> problems = equations.iterator();
        
        while (problems.hasNext())
        {
            Equation problem = problems.next();
            Fraction frac1 = problem.getFraction(FRACTION1);
            Fraction frac2 = problem.getFraction(FRACTION2);
            Fraction answer = problem.getFraction(ANSWER);
            int commonDenModifier = 1;
            
            double greaterCheck = 
                (double)frac1.getNumerator() / (double)frac1.getDenominator() +
                (double)frac2.getNumerator() / (double)frac2.getDenominator();
            
            
            //  Since some questions assume a total of "1"...
            //  We modify those equations to make sense in a word problem.
            if ((problemText[problemNum].contains("%common_den%")) &&
                (greaterCheck >= 1.0))
               
            {
                answer.setFraction(answer.getNumerator(), 
                                   answer.getDenominator() * 2);
                commonDenModifier = 2;
            }
            
            //  Convert fractions into word form
            String wordFraction1 = frac1.convertWord();
            String wordFraction2 = frac2.convertWord();
            
            //  Convert fractions to all have common denominators.
            //  Convert numerators to match.
            String commonDen = null;
            String commonNum1 = null;
            String commonNum2 = null;
            
            if (frac1.getDenominator() == frac2.getDenominator())
            {
                commonDen = String.format("%d", frac1.getDenominator() *
                                                commonDenModifier);
                commonNum1 = String.format("%d", frac1.getNumerator());
                commonNum2 = String.format("%d", frac2.getNumerator());
            }
            else
            {
                commonDen = String.format("%d", frac1.getDenominator() * 
                                                frac2.getDenominator() *
                                                commonDenModifier);
                commonNum1 = String.format("%d", frac1.getNumerator() * 
                                                 frac2.getDenominator());
                commonNum2 = String.format("%d", frac2.getNumerator() * 
                                                 frac1.getDenominator());
            }
                                    
            //  Grab special text for each problem
            int rand1 = probRNG.nextInt(10);
            int rand2 = probRNG.nextInt(10);
            
            while (rand1 == rand2)
            {
                rand2 = probRNG.nextInt(10);
            }
            
            String person1  = names[rand1];
            String person2  = names[rand2];
            String color1   = colors[rand1];
            String color2   = colors[rand2];
            String subject1 = subjects[rand1];
            String subject2 = subjects[rand2];
            
            //  Convert the entire text to include the appropriate data.
            String tempProb = problemText[problemNum];
            
            if (tempProb.contains("%fraction1%"))
            {
                tempProb = tempProb.replaceAll("%fraction1%", wordFraction1);
            }
            if (tempProb.contains("%fraction2%"))
            {
                tempProb = tempProb.replaceAll("%fraction2%", wordFraction2);
            }
            if (tempProb.contains("%person1%"))
            {
                tempProb = tempProb.replaceAll("%person1%", person1);
            }
            if (tempProb.contains("%person2%"))
            {
                tempProb = tempProb.replaceAll("%person2%", person2);
            }
            if (tempProb.contains("%color1%"))
            {
                tempProb = tempProb.replaceAll("%color1%", color1);
            }
            if (tempProb.contains("%color2%"))
            {
                tempProb = tempProb.replaceAll("%color2%", color2);
            }
            if (tempProb.contains("%subject1%"))
            {
                tempProb = tempProb.replaceAll("%subject1%", subject1);
            }
            if (tempProb.contains("%subject2%"))
            {
                tempProb = tempProb.replaceAll("%subject2%", subject2);
            }
            if (tempProb.contains("%common_den%"))
            {
                tempProb = tempProb.replaceAll("%common_den%", commonDen);
            }
            if (tempProb.contains("%common_num1%") && commonNum1 != null)
            {
                tempProb = tempProb.replaceAll("%common_num1%", commonNum1);
            }
            if (tempProb.contains("%common_num2%") && commonNum2 != null)
            {
                tempProb = tempProb.replaceAll("%common_num2%", commonNum2);           
            }
            
            problemText[problemNum] = tempProb;
            problemNum++;
        }
        
        //  Tester Code
//        System.out.printf("Seed: %d\n", seed);
//        for (int count = 0; count < 10; count++)
//        {
//            System.out.printf("%s\n", problemText[count]);
//        }

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
//  End class WS_Advanced