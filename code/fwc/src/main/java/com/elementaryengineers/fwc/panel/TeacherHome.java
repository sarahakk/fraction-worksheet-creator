package com.elementaryengineers.fwc.panel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sarahakk on 4/10/16.
 */
public class TeacherHome extends JPanel {

    private JPanel pnNorth, pnButtons;
    private JLabel lblTitle;
    private JButton btnBeg1, btnBeg2, btnBeg3,
            btnInt1, btnInt2, btnInt3,
            btnAdv1, btnAdv2, btnAdv3;


    public TeacherHome() {
        super(new BorderLayout());
        this.setBackground(Color.WHITE);

        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new JLabel("Teacher Home");
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 32));
        lblTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        pnNorth.add(lblTitle);

        pnButtons = new JPanel(new GridLayout(3, 3, 5, 10));
        pnButtons.setBackground(Color.WHITE);
        pnButtons.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        btnBeg1 = new JButton("Beginner Pie Charts");
        btnBeg1.setFont(new Font("Calibri", Font.BOLD, 16));
        btnBeg2 = new JButton("Beginner Addition");
        btnBeg2.setFont(new Font("Calibri", Font.BOLD, 16));
        btnBeg3 = new JButton("Beginner Least to Greatest");
        btnBeg3.setFont(new Font("Calibri", Font.BOLD, 16));
        btnInt1 = new JButton("Intermediate Addition");
        btnInt1.setFont(new Font("Calibri", Font.BOLD, 16));
        btnInt2 = new JButton("Intermediate Subtraction");
        btnInt2.setFont(new Font("Calibri", Font.BOLD, 16));
        btnInt3 = new JButton("Intermediate Multiplication & Division");
        btnInt3.setFont(new Font("Calibri", Font.BOLD, 16));
        btnAdv1 = new JButton("Advanced Addition");
        btnAdv1.setFont(new Font("Calibri", Font.BOLD, 16));
        btnAdv2 = new JButton("Advanced Subtraction");
        btnAdv2.setFont(new Font("Calibri", Font.BOLD, 16));
        btnAdv3 = new JButton("Advanced Multiplication & Division");
        btnAdv3.setFont(new Font("Calibri", Font.BOLD, 16));

        pnButtons.add(btnBeg1);
        pnButtons.add(btnInt1);
        pnButtons.add(btnAdv1);
        pnButtons.add(btnBeg2);
        pnButtons.add(btnInt2);
        pnButtons.add(btnAdv2);
        pnButtons.add(btnBeg3);
        pnButtons.add(btnInt3);
        pnButtons.add(btnAdv3);

        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnButtons, BorderLayout.CENTER);
    }
}
