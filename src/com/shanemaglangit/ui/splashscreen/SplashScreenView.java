package com.shanemaglangit.ui.splashscreen;

import com.shanemaglangit.data.FontWeight;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class SplashScreenView extends JFrame {
    private JLabel lblImage;
    private JLabel lblMessage;

    public SplashScreenView() {
        // Set the frame preferences
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setUndecorated(true);
        this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().setBackground(Color.WHITE);

        // Add a JLabel containing the gif to the content pane.
        lblImage = new JLabel(getGif());
        lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(lblImage);

        // Add a JLabel containing the message to the content pane.
        lblMessage = new JLabel("Please wait while we're settings things up...");
        lblMessage.setFont(Resources.createPoppinsFont(FontWeight.PLAIN, 12));
        lblMessage.setBorder(new EmptyBorder(0, 10, 18, 10));
        lblMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(lblMessage);
    }

    private ImageIcon getGif() {
        URL gifUrl = getClass().getResource("../../res/splash.gif");
        return new ImageIcon(gifUrl);
    }
}
