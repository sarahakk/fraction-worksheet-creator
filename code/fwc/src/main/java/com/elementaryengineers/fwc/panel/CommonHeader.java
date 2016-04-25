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
 * TODO
 **/
public class CommonHeader extends JPanel {

    private JPanel pnButton;
    private JLabel lblTitle;
    private ImageButton btnHelp, btnAccount, btnLogout, btnExit;
    private UserMenu menu;

    public CommonHeader() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        buildButtons();
        buildTitleLabel();

        add(pnButton, BorderLayout.NORTH);
        add(lblTitle, BorderLayout.CENTER);
    }

    private void buildButtons() {
        // Create right-aligned top panel of buttons
        pnButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnButton.setBackground(Color.WHITE);

        btnHelp = new ImageButton("Help", FWCConfigurator.HELP_IMG, 150, 50);
        btnAccount = new ImageButton("My Account", FWCConfigurator.ACCOUNT_IMG, 150, 50);
        btnLogout = new ImageButton("Logout", FWCConfigurator.LOGOUT_IMG, 150, 50);
        btnExit = new ImageButton("Exit", FWCConfigurator.EXIT_IMG, 150, 50);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FWCConfigurator.getDbConn().closeConnection();
                System.exit(0);
            }
        });

        btnAccount.setVisible(false);
        btnLogout.setVisible(false);

        pnButton.add(btnHelp);
        pnButton.add(btnAccount);
        pnButton.add(btnLogout);
        pnButton.add(btnExit);
    }

    private void buildTitleLabel() {
        boolean imgRead = true;

        try {
            URL imgURL = CommonHeader.class.getClassLoader().getResource("images/" + FWCConfigurator.TITLE_IMG);
            BufferedImage imgBuff = ImageIO.read(imgURL);

            if (imgURL != null) {
                lblTitle = new JLabel(new ImageIcon(imgBuff.getScaledInstance(500, 150, Image.SCALE_SMOOTH)));
            }
            else {
                System.out.println("Could not load " + FWCConfigurator.TITLE_IMG + ".");
                imgRead = false;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            imgRead = false;
        }

        // If error occurs in fetching title image, use text instead
        if (!imgRead) {
            lblTitle = new JLabel("Fraction Worksheet Creator");
            lblTitle.setFont(new Font("Calibri", Font.BOLD, 32));
            lblTitle.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 5, true));
        }
    }

    public void setMenu(UserMenu menu) {
        this.menu = menu;
        this.add(menu, BorderLayout.SOUTH);
    }

    public void hideMenu() {
        this.menu.setVisible(false);
    }

    public void showButtonsLoggedIn() {
        btnAccount.setVisible(true);
        btnLogout.setVisible(true);
        btnExit.setVisible(false);
    }

    public void showButtonsLoggedOut() {
        btnAccount.setVisible(false);
        btnLogout.setVisible(false);
        btnExit.setVisible(true);
    }

    public void setLogoutListener(ActionListener listener) {
        this.btnLogout.addActionListener(listener);
    }
}