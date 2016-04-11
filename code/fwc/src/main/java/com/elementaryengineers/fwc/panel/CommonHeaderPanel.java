package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;

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
public class CommonHeaderPanel extends JPanel {

    private JPanel pnTitle, pnButton;
    private JLabel lblTitle;
    private ImageButton btnHelp, btnAccount, btnLogout;
    private JButton btnExit;
    private UserMenu menu;
    private static final String TITLE_IMG = "title.png";
    private static final String HELP_IMG = "help.png";
    private static final String ACCOUNT_IMG = "myaccount.png";
    private static final String LOGOUT_IMG = "logout.png";

    public CommonHeaderPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // TODO Add padding

        buildButtons();
        buildTitleLabel();

        add(pnButton, BorderLayout.NORTH);
        add(pnTitle, BorderLayout.CENTER);
    }

    private void buildButtons() {
        // TODO each image needs to be checked for existence before building the buttons

        // Create right-aligned top panel of buttons
        pnButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnButton.setBackground(Color.WHITE);

        btnHelp = new ImageButton("images/" + HELP_IMG, 150, 50);
        btnAccount = new ImageButton("images/" + ACCOUNT_IMG, 150, 50);
        btnLogout = new ImageButton("images/" + LOGOUT_IMG, 150, 50);
        btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Calibri", Font.BOLD, 16));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        pnTitle = new JPanel(new GridLayout(1, 2, 40, 0));
        pnTitle.setBackground(Color.WHITE);

        try {
            URL imgURL = CommonHeaderPanel.class.getClassLoader().getResource("images/" + TITLE_IMG);
            BufferedImage imgBuff = ImageIO.read(imgURL);

            if (imgURL != null) {
                lblTitle = new JLabel(new ImageIcon(imgBuff.getScaledInstance(500, 150, Image.SCALE_SMOOTH)));
            }
            else {
                System.out.println("imgURL is null.");
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

        pnTitle.add(lblTitle, BorderLayout.WEST);
    }

    public void setMenu(UserMenu menu) {
        this.menu = menu;
        this.add(menu, BorderLayout.SOUTH);
    }

    public void hideMenu() {
        this.menu.setVisible(false);
    }

    public void showButtonsLoggedIn() {
        btnExit.setVisible(false);
        btnAccount.setVisible(true);
        btnLogout.setVisible(true);
    }

    public void showButtonsLoggedOut() {
        btnExit.setVisible(true);
        btnAccount.setVisible(false);
        btnLogout.setVisible(false);
    }

    public void setLogoutListener(ActionListener listener) {
        this.btnLogout.addActionListener(listener);
    }
}