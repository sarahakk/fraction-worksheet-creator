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


package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.db.FWCConfigurator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by sarahakk on 4/26/16.
 */
public class Help extends JFrame {

    private JPanel pnTitle, pnClose;
    private JLabel lblTitle, lblHelp;
    private JTextArea helpText;
    private JScrollPane scrollPane;
    private ImageButton btnClose;
    private static final int width = 400, height = 350;

    public Help () {
        super("Help");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setBackground(FWCConfigurator.bgColor);

        lblHelp = new JLabel();

        try {
            URL imgURL = CommonHeader.class.getClassLoader().getResource
                    ("images/" + FWCConfigurator.CUTE_HELP_IMG);
            BufferedImage imgBuff = ImageIO.read(imgURL);

            if (imgURL != null) {
                lblHelp.setIcon(new ImageIcon(imgBuff.getScaledInstance(112,
                        96, Image.SCALE_SMOOTH)));
            }
            else {
                // Use text instead
                lblHelp.setText("help.png");
                lblHelp.setFont(new Font("Calibri", Font.BOLD, 32));
                lblHelp.setBorder(BorderFactory.createLineBorder
                                                (Color.ORANGE, 5, true));
            }
        }
        catch (IOException e) {
            e.printStackTrace();

            // Use text instead
            lblHelp.setText("help.png");
            lblHelp.setFont(new Font("Calibri", Font.BOLD, 32));
            lblHelp.setBorder(BorderFactory.createLineBorder
                                            (Color.ORANGE, 5, true));
        }

        makeHelpText();

        pnClose = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnClose.setBackground(FWCConfigurator.bgColor);
        btnClose = new ImageButton("Close", FWCConfigurator.CLOSE_IMG, 150, 50);
        btnClose.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pnClose.add(btnClose);

        this.add(pnTitle, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(pnClose, BorderLayout.SOUTH);

        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void makeHelpText() {
        pnTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnTitle.setBackground(FWCConfigurator.bgColor);

        helpText = new JTextArea();
        helpText.setEnabled(false);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        switch (FWCConfigurator.getCurrentPage()) {
            case ADMIN_REGISTRATION:
   lblTitle = new JLabel("Administrator Registration", 
                        SwingConstants.CENTER);
   helpText.setText("Since the software is freshly installed, an administrator "
    + "needs to register the software in order to create accounts for "
    + "teachers. This page allows an administrator to create his/her "
    + "account. The administrator should be a school employee and not a "
    + "student. The administrator has the ability to reset forgotten passwords "
    + "of teachers. An administrator can also be a teacher, but will need to "
    + "create a separate teacher account for himself/herself later in order "
    + "to create worksheets.");
                break;
            case LOGIN:
    lblTitle = new JLabel("Login", SwingConstants.CENTER);
    helpText.setText("Login to the Fraction Worksheet Creator using the username"
    + " and password that an administrator or your teacher gave you. "
    + "If you forgot your password, "
    + "click on the Forgot Password button.");
                break;
            case FORGOT_PASSWORD:
    lblTitle = new JLabel("Forgot Password", SwingConstants.CENTER);
    helpText.setText("Enter the username that was given to you by an "
    + "administrator or your teacher, then click submit. A request will be sent"
    + " to your administrator or teacher's account to reset your password and "
    + "give you a new one.");
                break;
            case ADMIN_RESET_PASSWORD:
                lblTitle = new JLabel("Administrator Password Reset", 
                        SwingConstants.CENTER);
                helpText.setText("Administrators can reset their master "
                        + "password"
                        + " by answering the security questions " +
                        "that were asked when they first registered their "
                        + "account. If an administrator has forgotten " +
                        "the answers to the security questions, please "
                        + "reinstall"
                        + " the software to recreate the " +
                        "administrator account. Fraction Worksheet Creator "
                        + "data and accounts will be lost.");
                break;
            case MY_ACCOUNT:
                lblTitle = new JLabel("My Account", SwingConstants.CENTER);
                helpText.setText("On this page, you can view and edit you "
                        + "account information, as well as reset your " +
                        "password. Be sure to click Submit after making your "
                        + "changes to save.");
                break;
            case ADMIN_HOME:
                lblTitle = new JLabel("Administrator Home Page", 
                        SwingConstants.CENTER);
                helpText.setText("Your home page is where you can view "
                        + "the list of all the teacher accounts " +
                        "registered in the Fraction Worksheet Creator. "
                        + "To view a certain teacher's profile, you can " +
                        "search for the teacher by typing his/her name or "
                        + "username in the search bar, and then " +
                        "selecting the teacher from the table before clicking "
                        + "Profile.");
                break;
            case TEACHER_PROFILE:
                lblTitle = new JLabel("Teacher Profile", SwingConstants.CENTER);
                helpText.setText("On this page, you are viewing a particular "
                        + "teacher's account information. " +
                        "You can edit his/her information, clicking Submit "
                        + "to save changes, reset his/her password " +
                        "on-demand, or delete his/her account. This will also "
                        + "delete all of his/her worksheets, " +
                        "classes, and students.");
                break;
            case ADMIN_MANAGE_PASSWORDS:
                lblTitle = new JLabel("Reset Teacher Passwords", 
                        SwingConstants.CENTER);
                helpText.setText("On this page, you can view the list "
                        + "of teachers that have forgotten their " +
                        "passwords and requested you to reset them. "
                        + "You can select a teacher from the table and " +
                        "click Reset Selected to reset just that teacher's "
                        + "password, or you can click Reset All " +
                        "to satisfy all the password reset requests. "
                        + "A dialog box will appear, displaying all of " +
                        "the teacher usernames and their new passwords.");
                break;
            case TEACHER_REGISTRATION:
                lblTitle = new JLabel("Teacher Registration", 
                        SwingConstants.CENTER);
                helpText.setText("On this page, you can create a new teacher "
                        + "account. Please be sure to share with " +
                        "the teacher his/her new username and password so"
                        + " he/she can login to the Fraction " +
                        "Worksheet Creator. Please be sure to click Submit "
                        + "after all the information has been " +
                        "entered to register the teacher.");
                break;
            case TEACHER_HOME:
                lblTitle = new JLabel("Teacher Home Page", 
                        SwingConstants.CENTER);
                helpText.setText("On this page, you can generate random "
                        + "fraction worksheets by clicking any of the " +
                        "buttons below! They are separated by difficulty "
                        + "level and type of exercise. The worksheet " +
                        "will be opened in another window from where you can "
                        + "save the worksheet to a folder on your " +
                        "computer and/or print copies to a nearby printer. "
                        + "The answer sheet will be on the second " +
                        "page of the generated worksheet.");
                break;
            case TUTORIALS:
                lblTitle = new JLabel("Tutorials", SwingConstants.CENTER);
                helpText.setText("On this page, you can watch videos that will "
                        + "show you how to solve the questions " +
                        "on the fractions worksheets. You can watch a tutorial "
                        + "video about any difficulty and any " +
                        "kind of exercise, like addition, subtraction, "
                        + "and others. The video will pop-up in another " +
                        "window.");
                break;
            case TEACHER_HISTORY:
                lblTitle = new JLabel("Worksheet History", SwingConstants.CENTER);
                helpText.setText("On this page, you can view a list of all "
                        + "the worksheets that you created in the " +
                        "past and view them again to save/print them, view "
                        + "just their answer sheets to save/print, " +
                        "or delete them from your history if you don't need "
                        + "them anymore.");
                break;
            case CLASSES:
                lblTitle = new JLabel("Classes", SwingConstants.CENTER);
                helpText.setText("On this page, you can view a list of your"
                        + " classes. To create student accounts on " +
                        "the Fraction Worksheet Creator, you first must create"
                        + " a class to assign them to. You can " +
                        "do so by clicking the New Class button. Then, you can "
                        + "click the Add Student button to " +
                        "register a new student account. From here, you can "
                        + "select a class from the list, and then " +
                        "click Roster to view and edit the  students in that "
                        + "class or to edit the class by clicking " +
                        "the Edit button.");
                break;
            case NEW_CLASS:
                lblTitle = new JLabel("Create New Class", 
                        SwingConstants.CENTER);
                helpText.setText("You can create a new class on this page "
                        + "by simply entering a name for the class. " +
                        "Then, you can create and assign students to the new "
                        + "class from the Classes page.");
                break;
            case STUDENT_REGISTRATION:
                lblTitle = new JLabel("Student Registration", 
                        SwingConstants.CENTER);
                helpText.setText("On this page, you can register a new "
                        + "student account. You must assign a difficulty " +
                        "level for the new student, which will restrict them "
                        + "to printing only fraction worksheets " +
                        "of that difficulty. You must also assign new students "
                        + "to an existing Class that you have " +
                        "created. Please be sure to click Submit to register "
                        + "the new student, and don't forget to " +
                        "provide your student with his/her new username and "
                        + "password to login to the Fraction " +
                        "Worksheet Creator.");
                break;
            case EDIT_CLASS:
                lblTitle = new JLabel("Edit Class", SwingConstants.CENTER);
                helpText.setText("From here, you can edit a class' name or"
                        + " delete the class. But be careful: " +
                        "deleting a class will delete all student accounts "
                        + "assigned to that class, along with all of " +
                        "their worksheets. Please reassign your students to"
                        + " different classes if you want to keep " +
                        "their accounts before deleting a class. Be sure to "
                        + "click Submit to save your change to the " +
                        "class' name.");
                break;
            case CLASS_ROSTER:
                lblTitle = new JLabel("Class Roster", SwingConstants.CENTER);
                helpText.setText("This page shows the list of students that are"
                        + " assigned to a particular class. " +
                        "From here, you can search for a student in the class "
                        + "by typing a keyword in the search " +
                       "bar then select a student from the list to view his/her"
                        + " profile or worksheet history.");
                break;
            case STUDENT_PROFILE:
                lblTitle = new JLabel("Student Profile", SwingConstants.CENTER);
                helpText.setText("On this page, you can see and change"
                        + " a particular student's account information. " +
                        "You can reset a student's password on-demand, "
                        + "view a student's worksheet history, or delete " +
                        "the student's account, which would also delete all "
                        + "of his/her worksheets from the Fraction " +
                        "Worksheet Creator. Please be sure to click Submit "
                        + "to save any changes to a student's " +
                        "information on this page.");
                break;
            case TEACHER_STUDENT_HISTORY:
                lblTitle = new JLabel("Student History", SwingConstants.CENTER);
                helpText.setText("On this page, you can view a list of all "
                        + "the worksheets that were created in the " +
                        "past by a particular student, view them to save/print "
                        + "or view just their answer sheets to " +
                        "save/print. Rest assured that your students cannot see"
                        + " the answer sheets to the worksheets " +
                        "that they create. Only you have access to them from "
                        + "this page.");
                break;
            case TEACHER_MANAGE_PASSWORDS:
                lblTitle = new JLabel("Reset Student Passwords", 
                        SwingConstants.CENTER);
                helpText.setText("On this page, you can view the list of "
                        + "students that have forgotten their " +
                        "passwords and requested you to reset them. You can "
                        + "select a student from the table and " +
                        "click Reset Selected to reset just that student's "
                        + "password, or you can click Reset All " +
                        "to satisfy all the password reset requests. "
                        + "A dialog box will appear, displaying all of " +
                        "the student usernames and their new passwords.");
                break;
            case STUDENT_HOME:
              lblTitle = new JLabel("Student Home Page", SwingConstants.CENTER);
                helpText.setText("On this page, you can create fraction "
                        + "worksheets by clicking any of the " +
                        "buttons below! Click the button for the type of "
                        + "exercise that you want. The worksheet " +
                        "will be pop up in another window, where you can "
                        + "save the worksheet to a folder on your " +
                        "computer or print copies to a nearby printer. As "
                        + "your teacher raises your difficulty " +
                        "level, you will be able to print harder and harder"
                        + " fraction worksheets!");
                break;
            case STUDENT_HISTORY:
             lblTitle = new JLabel("Worksheet History", SwingConstants.CENTER);
                helpText.setText("On this page, you can view a list of all "
                        + "the worksheets that you created " +
                        "and open them again " +
                        "or delete them from your history if you don't need "
                        + "them anymore.");
                break;
        }

        scrollPane = new JScrollPane(helpText, JScrollPane
                .VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBackground(FWCConfigurator.bgColor);

        lblTitle.setBackground(FWCConfigurator.bgColor);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        pnTitle.add(lblTitle);
        pnTitle.add(lblHelp);
    }
}