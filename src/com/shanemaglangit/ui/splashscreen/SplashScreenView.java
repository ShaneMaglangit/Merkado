package com.shanemaglangit.ui.splashscreen;

import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SplashScreenView extends JFrame {
    private JLabel lblImage;
    private JLabel lblMessage;

    /**
     * Constructor where all of the components of the frame are created
     * @throws HeadlessException
     */
    public SplashScreenView() {
        // Set the frame preferences
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setUndecorated(true);
        this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().setBackground(Color.WHITE);

        // Set the frame logo
        this.setIconImage(Util.createImageIcon(this, "../.." + Resources.LOGO_PATH).getImage());

        // Add a JLabel containing the gif to the content pane.
        lblImage = new JLabel(Util.createImageIcon(this, "../.." + Resources.SPLASH_PATH));
        lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(lblImage);

        // Add a JLabel containing the message to the content pane.
        lblMessage = new JLabel("Please wait while we're settings things up...");
        lblMessage.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        lblMessage.setBorder(new EmptyBorder(0, 10, 18, 10));
        lblMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(lblMessage);
    }
}
