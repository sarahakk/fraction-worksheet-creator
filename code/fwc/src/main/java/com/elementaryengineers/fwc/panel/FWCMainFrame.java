package com.elementaryengineers.fwc.panel;

import javax.swing.*;
import java.awt.*;

/**
 * TODO
 **/
public class FWCMainFrame extends JFrame {

    private CardLayout cardLayout;
    private CommonHeaderPanel headerPanel;
    private LoginPanel loginPanel;
    private JPanel cardPanel;

    /**
     *
     **/
    public FWCMainFrame() {
        super("Fraction Worksheet Creator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
        //setSize(500, 400);
        setSize(1000, 950);
        setResizable(false);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        buildPanels();
        setVisible(true);
    }

    private void buildPanels() {
        headerPanel = new CommonHeaderPanel();
        headerPanel.setSize(1000, 300);

        // Use card layout for central area of frame
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout); // Set card layout for card panel

        loginPanel = new LoginPanel();
        /* TODO: Add action listener here for login panel components
        * The action listener will be defined here so that it has access to
        * other parts of the main frame.
        */

        cardPanel.add(loginPanel); // Add loginPanel to card layout

        this.add(headerPanel, BorderLayout.NORTH); // Add common header panel to top of frame
        //this.add(cardPanel, BorderLayout.CENTER); // Add card panel to center of frame
    }
}