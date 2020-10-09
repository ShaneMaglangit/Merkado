package com.shanemaglangit.ui.login;

import com.shanemaglangit.components.HintTextField;
import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.FontWeight;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoginView extends JFrame {
    private JPanel mainPanel;
    private JLabel lblHeading;
    private JLabel lblSub;
    private HintTextField txtEmail;
    private HintTextField txtPassword;
    private JButton btnSubmit;

    public LoginView() throws HeadlessException {
        CompoundBorder btnBorder;

        // Set the frame preferences
        this.setTitle(Config.TITLE);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(Config.WINDOW_WIDTH / 3, 0));

        // Create the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);;
        mainPanel.setBorder(new EmptyBorder(48, 48, 48, 48));
        this.getContentPane().add(mainPanel);

        // Add the heading
        lblHeading = new JLabel("Start shopping now");
        lblHeading.setFont(Resources.createPoppinsFont(FontWeight.BOLD, 28));
        lblHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblHeading);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 4)));

        // Add the sub heading
        lblSub = new JLabel("<html><div style='text-align: center;'>Buy products from your local wet market<br />with the comforts of you own home.</div></html>");
        lblSub.setFont(Resources.createPoppinsFont(FontWeight.PLAIN, 16));
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSub.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(lblSub);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 32)));

        // Add the text field for email
        txtEmail = new HintTextField("Enter email", 16);
        txtEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtEmail.setFont(Resources.createPoppinsFont(FontWeight.PLAIN, 16));
        txtEmail.setMargin(new Insets(8, 8, 8, 8));
        txtEmail.setMaximumSize(txtEmail.getPreferredSize());
        mainPanel.add(txtEmail);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        // Add the text field for password
        txtPassword = new HintTextField("Enter password", 16);
        txtPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtPassword.setFont(Resources.createPoppinsFont(FontWeight.PLAIN, 16));
        txtPassword.setMargin(new Insets(8, 8, 8, 8));
        txtPassword.setMaximumSize(txtEmail.getPreferredSize());
        mainPanel.add(txtPassword);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        // Add the submit button
        btnBorder = BorderFactory.createCompoundBorder(
            new EmptyBorder(8, 8, 8, 8),
            new LineBorder(Resources.LIGHT, 0, true));
        btnSubmit = new JButton("LOGIN");
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSubmit.setFocusPainted(false);
        btnSubmit.setBorder(btnBorder);
        btnSubmit.setFont(Resources.createPoppinsFont(FontWeight.MEDIUM, 16));
        btnSubmit.setForeground(Resources.LIGHT);
        btnSubmit.setBackground(Resources.PRIMARY);
        btnSubmit.setMaximumSize(txtEmail.getPreferredSize());
        mainPanel.add(btnSubmit);
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTextField getTxtPassword() {
        return txtPassword;
    }

    public JButton getBtnSubmit() {
        return btnSubmit;
    }
}
