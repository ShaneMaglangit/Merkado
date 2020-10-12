package com.shanemaglangit.ui.login;

import com.shanemaglangit.components.HintTextField;
import com.shanemaglangit.components.RoundedButton;
import com.shanemaglangit.config.Config;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoginView extends JFrame {
    private JPanel pnlMain;
    private JLabel lblHeading;
    private JLabel lblSub;
    private HintTextField txtEmail;
    private HintTextField txtPassword;
    private RoundedButton btnSubmit;

    /**
     * Constructor where all of the components of the frame are created
     * @throws HeadlessException
     */
    public LoginView() throws HeadlessException {
        // Set the frame preferences
        this.setTitle(Config.TITLE);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(Config.WINDOW_WIDTH / 3, 0));

        // Set the frame logo
        this.setIconImage(Util.createImageIcon(this, "../.." +  Resources.LOGO_PATH).getImage());

        // Create the main panel
        pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBackground(Color.WHITE);;
        pnlMain.setBorder(new EmptyBorder(48, 48, 48, 48));
        this.getContentPane().add(pnlMain);

        // Add the heading
        lblHeading = new JLabel("Start shopping now");
        lblHeading.setFont(Resources.createPoppinsFont(Resources.FontWeight.BOLD, 25));
        lblHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlMain.add(lblHeading);

        pnlMain.add(Box.createRigidArea(new Dimension(0, 4)));

        // Add the sub heading
        lblSub = new JLabel("<html><div style='text-align: center;'>Buy products from your local wet market<br />with the comforts of you own home.</div></html>");
        lblSub.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 14));
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSub.setHorizontalAlignment(JLabel.CENTER);
        pnlMain.add(lblSub);

        pnlMain.add(Box.createRigidArea(new Dimension(0, 32)));

        // Add the text field for email
        txtEmail = new HintTextField("Enter email", 16);
        txtEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtEmail.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        txtEmail.setMargin(new Insets(6, 6, 6, 6));
        txtEmail.setMaximumSize(txtEmail.getPreferredSize());
        pnlMain.add(txtEmail);

        pnlMain.add(Box.createRigidArea(new Dimension(0, 6)));

        // Add the text field for password
        txtPassword = new HintTextField("Enter password", 16);
        txtPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtPassword.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        txtPassword.setMargin(new Insets(6, 6, 6, 6));
        txtPassword.setMaximumSize(txtEmail.getPreferredSize());
        pnlMain.add(txtPassword);

        pnlMain.add(Box.createRigidArea(new Dimension(0, 6)));

        // Add the submit button
        btnSubmit = new RoundedButton("LOGIN");
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSubmit.setFocusPainted(false);
        btnSubmit.setFont(Resources.createPoppinsFont(Resources.FontWeight.MEDIUM, 12));
        btnSubmit.setForeground(Resources.LIGHT);
        btnSubmit.setBackground(Resources.PRIMARY);
        btnSubmit.setMaximumSize(txtEmail.getPreferredSize());
        pnlMain.add(btnSubmit);
    }

    /**
     * Accessor for the email input field
     * @return reference to the input field
     */
    public JTextField getTxtEmail() {
        return txtEmail;
    }

    /**
     * Accessor for the password input field
     * @return reference to the input field
     */
    public JTextField getTxtPassword() {
        return txtPassword;
    }

    /**
     * Accessor for the submit button
     * @return reference to the button
     */
    public RoundedButton getBtnSubmit() {
        return btnSubmit;
    }
}
