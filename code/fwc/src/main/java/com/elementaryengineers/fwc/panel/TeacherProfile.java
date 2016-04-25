package com.elementaryengineers.fwc.panel;

import com.elementaryengineers.fwc.custom.ImageButton;
import com.elementaryengineers.fwc.custom.TitleLabel;
import com.elementaryengineers.fwc.db.FWCConfigurator;
import com.elementaryengineers.fwc.model.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by sarahakk on 4/25/16.
 */
public class TeacherProfile extends JPanel {

    private JPanel pnNorth, pnFields;
    private TitleLabel lblTitle;
    private JLabel lblFirst, lblLast, lblUser;
    private JTextField txtFirst, txtLast, txtUser;
    private ImageButton btnSubmit, btnDelete, btnReset;

    public TeacherProfile() {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Build title and north panel
        pnNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNorth.setBackground(Color.WHITE);
        lblTitle = new TitleLabel("Teacher Profile", FWCConfigurator.TEACHER_PROFILE_IMG);
        pnNorth.add(lblTitle);

        // Build fields and center panel
        lblFirst = new JLabel("Fist name:", SwingConstants.RIGHT);
        lblFirst.setFont(new Font("Calibri", Font.BOLD, 18));
        lblLast = new JLabel("Last name:", SwingConstants.RIGHT);
        lblLast.setFont(new Font("Calibri", Font.BOLD, 18));
        lblUser = new JLabel("Username:", SwingConstants.RIGHT);
        lblUser.setFont(new Font("Calibri", Font.BOLD, 18));
        txtFirst = new JTextField(24);
        txtLast = new JTextField(24);
        txtUser = new JTextField(24);

        // Use GridBagLayout
        pnFields = new JPanel(new GridBagLayout());
        pnFields.setBackground(Color.WHITE);
        pnFields.setBorder(BorderFactory.createEmptyBorder(10, 150, 20, 150));
        GridBagConstraints c = new GridBagConstraints();
        c.ipady = 5;

        // First name
        c.anchor = GridBagConstraints.EAST;
        pnFields.add(lblFirst, c);

        c.gridx = 1;
        c.insets = new Insets(0, 10, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnFields.add(txtFirst, c);

        // Last name
        c.gridy = 1;
        c.gridx = 0;
        c.insets.left = 0;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        pnFields.add(lblLast, c);

        c.gridx = 1;
        c.insets.left = 10;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnFields.add(txtLast, c);

        // Username
        c.gridy = 2;
        c.gridx = 0;
        c.insets.left = 0;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        pnFields.add(lblUser, c);

        c.gridx = 1;
        c.insets.left = 10;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnFields.add(txtUser, c);

        // Build buttons
        btnSubmit = new ImageButton("Submit", FWCConfigurator.SUBMIT_IMG, 150, 50);
        btnReset = new ImageButton("Submit", FWCConfigurator.RESET_PASSW_IMG, 150, 50);
        btnDelete = new ImageButton("Submit", FWCConfigurator.DEL_TEACHER_IMG, 150, 50);

        c.gridwidth = 2;
        c.weightx = 0.5;
        c.gridy = 3;
        c.gridx = 0;
        c.insets.left = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnFields.add(btnSubmit, c);

        c.gridy = 4;
        pnFields.add(btnReset, c);

        c.gridy = 5;
        pnFields.add(btnDelete, c);

        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnFields, BorderLayout.CENTER);
    }

    public void populateFields(Teacher teacher) {
        txtFirst.setText(teacher.getFirstName());
        txtLast.setText(teacher.getLastName());
        txtUser.setText(teacher.getUsername());
    }

    public String getFirstName() {
        return txtFirst.getText();
    }

    public String getLastName() {
        return txtLast.getText();
    }

    public String getUsername() {
        return txtUser.getText();
    }

    public void setSubmitListener(ActionListener submitListener) {
        btnSubmit.addActionListener(submitListener);
    }

    public void setDeleteListener(ActionListener deleteListener) {
        btnDelete.addActionListener(deleteListener);
    }

    public void setResetListener(ActionListener resetListener) {
        btnReset.addActionListener(resetListener);
    }
}