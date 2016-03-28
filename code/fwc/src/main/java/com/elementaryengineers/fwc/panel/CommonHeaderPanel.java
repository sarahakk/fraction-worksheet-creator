package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * TODO
 **/
public class CommonHeaderPanel extends JPanel {

    private ImageButton helpBtn, accountBtn, logoutBtn;
    private JLabel titleLbl, pageLbl;
    private JPanel buttonPanel, titlePanel;
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

        add(buttonPanel, BorderLayout.NORTH);
        add(titlePanel, BorderLayout.CENTER);
    }

    private void buildButtons() {
        // TODO each image needs to be checked for existence before building the buttons

        // Create right-aligned top panel of buttons
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);

        helpBtn = new ImageButton("images/" + HELP_IMG, 288, 70);
        accountBtn = new ImageButton("images/" + ACCOUNT_IMG, 257, 70);
        logoutBtn = new ImageButton("images/" + LOGOUT_IMG, 255, 69);

        buttonPanel.add(helpBtn);
        buttonPanel.add(accountBtn);
        buttonPanel.add(logoutBtn);
    }

    private void buildTitleLabel() {
        boolean imgRead = true;
        titlePanel = new JPanel(new GridLayout(1, 2, 40, 0));
        titlePanel.setBackground(Color.WHITE);

        try {
            URL imgURL = CommonHeaderPanel.class.getClassLoader().getResource("images/" + TITLE_IMG);
            BufferedImage imgBuff = ImageIO.read(imgURL);

            if (imgURL != null) {
                titleLbl = new JLabel(new ImageIcon(imgBuff.getScaledInstance(626, 202, Image.SCALE_SMOOTH)));
                titleLbl.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 20));
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
            titleLbl = new JLabel("Fraction Worksheet Creator");
            titleLbl.setFont(new Font("Calibri", Font.BOLD, 32));
            titleLbl.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 5, true));
        }

        try { // TODO remove this block, for testing
            pageLbl = new JLabel(new ImageIcon(ImageIO.read(CommonHeaderPanel.class.getClassLoader()
                    .getResource("images/page.png"))
                    .getScaledInstance(391, 115, Image.SCALE_SMOOTH)));
            pageLbl.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        titlePanel.add(titleLbl, BorderLayout.WEST);
        titlePanel.add(pageLbl, BorderLayout.EAST); // TODO remove
    }

    // TODO take enum parameter
    public void setPageTitle() {

    }
}