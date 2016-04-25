package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.db.FWCDatabaseConnection;
import com.elementaryengineers.fwc.model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO
 **/
public class FWCMainFrame extends JFrame {

    private FWCDatabaseConnection dbConn;
    private CardLayout cardLayout;
    private CommonHeader header;
    private AdminRegistration adminReg;
    private Login login;
    private ForgotPassword forgotPass;
    private AdminResetPassword adminResetPass;
    private AdminHome adminHome;
    private TeacherHome teacherHome;
    private TeacherMenu teacherMenu;
    private JPanel pnCard;

    /**
     *
     **/
    public FWCMainFrame() {
        super("Fraction Worksheet Creator");

        dbConn = FWCConfigurator.connectToDatabase();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        buildPanels();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buildPanels() {
        header = new CommonHeader();
        header.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        header.setLogoutListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch back to login page
                header.showButtonsLoggedOut();
                header.hideMenu();

                switch(FWCConfigurator.getUserType()) {
                    case TEACHER:
                        removeTeacherPanels();
                        break;
                    case STUDENT:
                        removeStudentPanels();
                        break;
                    case ADMIN:
                        removeAdminPanels();
                        break;
                }

                cardLayout.show(pnCard, "Login");
                pack();
                setLocationRelativeTo(null);
                FWCConfigurator.logout();
            }
        });

        // Use card layout for central area of frame
        cardLayout = new CardLayout();
        pnCard = new JPanel(cardLayout); // Set card layout for card panel
        pnCard.setBackground(Color.WHITE);
        pnCard.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(5, 0, 0, 0, Color.BLACK),
                new EmptyBorder(10, 0, 0, 0))
        );

        login = new Login();
        login.setLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameText = login.getUsernameText(),
                        passwordText = login.getPasswordText();

                if (usernameText.equals("") || passwordText.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please check your username or password.", "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                User user = dbConn.getUser(usernameText);

                if (user != null) { // If user exists
                    if (user.verifyLogin(passwordText)) {
                        // Build panels specific to the type of user that logged in
                        switch (user.getType()) {
                            case TEACHER: {
                                FWCConfigurator.setTeacher((Teacher) user);
                                buildTeacherPanels();
                                cardLayout.show(pnCard, "TeacherHome");
                                break;
                            }

                            case STUDENT: {
                                FWCConfigurator.setStudent((Student) user);
                                buildStudentPanels();
                                cardLayout.show(pnCard, "StudentHome");
                                break;
                            }

                            case ADMIN: {
                                FWCConfigurator.setAdmin((Admin) user);
                                buildAdminPanels();
                                cardLayout.show(pnCard, "AdminHome");
                                break;
                            }
                        }

                        // Reset components to display home page
                        header.showButtonsLoggedIn();
                        setSize(new Dimension(1000, 800));
                        setLocationRelativeTo(null);
                        login.clearFields();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Please check your password and try again.", "Login Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please check your username and try again.", "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        login.setForgotPassListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create forgot password panel if first time clicking it
                if (forgotPass == null) {
                    forgotPass = new ForgotPassword();
                    forgotPass.setBackListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(pnCard, "Login");
                            pnCard.remove(forgotPass);
                            pack();
                            setLocationRelativeTo(null);
                            forgotPass.clearFields();
                        }
                    });

                    forgotPass.setSubmitListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String usernameText = forgotPass.getUsernameText();

                            if (usernameText.equals("")) {
                                JOptionPane.showMessageDialog(null, "Please check your username.", "Username Error",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            User user = dbConn.getUser(usernameText);

                            if (user != null) { // If user exists
                                switch (user.getType()) {
                                    case TEACHER: { // Set reset password is requested to true
                                        Teacher teacher = (Teacher) user;
                                        teacher.setResetPassRequested();
                                        dbConn.updateTeacher(teacher); // Update teacher in DB
                                        break;
                                    }

                                    case STUDENT: { // Set reset password is requested to true
                                        Student student = (Student) user;
                                        student.setResetPassRequested();
                                        dbConn.updateStudent(student); // Update student in DB
                                        break;
                                    }

                                    case ADMIN: {
                                        Admin admin = (Admin) user;

                                        adminResetPass = new AdminResetPassword(admin);
                                        adminResetPass.setBackListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                cardLayout.show(pnCard, "Login");
                                                pnCard.remove(adminResetPass);
                                                pack();
                                                setLocationRelativeTo(null);
                                            }
                                        });

                                        adminResetPass.setSubmitListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                // Go back to login panel if password reset was successful
                                                if (adminResetPass.verifyAndPerformReset()) {
                                                    cardLayout.show(pnCard, "Login");
                                                    pnCard.remove(adminResetPass);
                                                    pack();
                                                    setLocationRelativeTo(null);
                                                }
                                            }
                                        });

                                        pnCard.add(adminResetPass, "AdminResetPassword");

                                        // Switch to admin reset password panel
                                        cardLayout.show(pnCard, "AdminResetPassword");
                                        pack();
                                        setLocationRelativeTo(null);
                                        login.clearFields();
                                        break;
                                    }
                                }
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Please check your username and try again.", "Login Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                }

                pnCard.add(forgotPass, "ForgotPassword");

                // Switch to forgot password panel
                cardLayout.show(pnCard, "ForgotPassword");
                pack();
                setLocationRelativeTo(null);
                login.clearFields();
            }
        });

        // Add panels to card layout

        // TESTS
        //pnCard.add(new AdminRegistration(), "AdminRegistration");
        //pnCard.add(new AdminResetPassword(new Admin(0, "testUsername", "", "", "", "")), "AdminResetPassword");
        //FWCConfigurator.setAdmin(new Admin("test", "test", "test", "test", "test", "test", "test"));
        //pnCard.add(new AdminHome(), "AdminHome");
        //pnCard.add(new TeacherProfile(), "TeacherProfile");

        pnCard.add(login, "Login");

        this.add(header, BorderLayout.NORTH); // Add common header panel to top of frame
        this.add(pnCard, BorderLayout.CENTER); // Add card panel to center of frame
    }

    private void buildTeacherPanels() {
        teacherHome = new TeacherHome();
        pnCard.add(teacherHome, "TeacherHome");

        // Add other teacher panels

        teacherMenu = new TeacherMenu();
        teacherMenu.setHistoryListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        header.setMenu(teacherMenu);
    }

    private void buildStudentPanels() {

    }

    private void buildAdminPanels() {

    }

    private void removeTeacherPanels() {
        pnCard.remove(teacherHome);
    }

    private void removeStudentPanels() {

    }

    private void removeAdminPanels() {

    }
}