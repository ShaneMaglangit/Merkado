package com.shanemaglangit.ui.checkout;

import com.shanemaglangit.components.HintTextField;
import com.shanemaglangit.components.RoundedButton;
import com.shanemaglangit.config.Config;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CheckoutView extends JFrame {
    private JPanel pnlMain;

    private JLabel lblCheckout;

    private HintTextField txtAddress;

    private HintTextField txtPhoneNumber;

    private JPanel pnlPaymentOption;
    private ButtonGroup bgPaymentOption;
    private JRadioButton rbtnCard;
    private JRadioButton rbtnCOD;

    private HintTextField txtCardNumber;
    private JPanel pnlCCDetails;
    private HintTextField txtExpiration;
    private HintTextField txtCVV;

    private JLabel lblTotal;

    private RoundedButton btnConfirm;

    /**
     * Constructor where all of the components of the frame are created
     * @throws HeadlessException
     */
    public CheckoutView() throws HeadlessException {
        GridLayout layoutCCDetails, layoutPaymentOption;

        // Set the frame preferences
        this.setTitle(Config.TITLE);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Set the frame logo
        this.setIconImage(Util.createImageIcon(this, "../.." +  Resources.LOGO_PATH).getImage());

        // Create the main panel
        pnlMain = new JPanel();
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBorder(new EmptyBorder(18, 18, 18, 18));
        this.getContentPane().add(pnlMain);

        // Create checkout label
        lblCheckout = new JLabel("Checkout");
        lblCheckout.setFont(Resources.createPoppinsFont(Resources.FontWeight.BOLD, 25));
        lblCheckout.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlMain.add(lblCheckout);

        pnlMain.add(Box.createRigidArea(new Dimension(0, 6)));

        // Create address field
        txtAddress = new HintTextField("Address", 18);
        txtAddress.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtAddress.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        txtAddress.setMargin(new Insets(6, 6, 6, 6));
        pnlMain.add(txtAddress);

        pnlMain.add(Box.createRigidArea(new Dimension(0, 6)));

        // Create phone number field
        txtPhoneNumber = new HintTextField("Phone Number", 18);
        txtPhoneNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtPhoneNumber.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        txtPhoneNumber.setMargin(new Insets(6, 6, 6, 6));
        pnlMain.add(txtPhoneNumber);

        pnlMain.add(Box.createRigidArea(new Dimension(0, 6)));

        // Create payment option
        layoutPaymentOption = new GridLayout(1, 2);
        layoutPaymentOption.setHgap(6);

        pnlPaymentOption = new JPanel();
        pnlPaymentOption.setLayout(layoutPaymentOption);
        pnlPaymentOption.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlPaymentOption.setBackground(Color.WHITE);
        pnlMain.add(pnlPaymentOption);

        // Create card payment option
        rbtnCard = new JRadioButton("Debit / Credit Card");
        rbtnCard.setFocusPainted(false);
        rbtnCard.setBackground(Color.WHITE);
        rbtnCard.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        rbtnCard.setIcon(Util.createImageIcon(this, "../.." +  Resources.RADIO_DEFAULT));
        rbtnCard.setSelectedIcon(Util.createImageIcon(this, "../.." +  Resources.RADIO_SELECTED));
        rbtnCard.setSelected(true);
        pnlPaymentOption.add(rbtnCard);

        // Create cash on delivery option
        rbtnCOD = new JRadioButton("CoD");
        rbtnCOD.setFocusPainted(false);
        rbtnCOD.setBackground(Color.WHITE);
        rbtnCOD.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        rbtnCOD.setIcon(Util.createImageIcon(this, "../.." +  Resources.RADIO_DEFAULT));
        rbtnCOD.setSelectedIcon(Util.createImageIcon(this, "../.." +  Resources.RADIO_SELECTED));
        rbtnCOD.setSelected(true);
        pnlPaymentOption.add(rbtnCOD);

        // Add options to button group
        bgPaymentOption = new ButtonGroup();
        bgPaymentOption.add(rbtnCOD);
        bgPaymentOption.add(rbtnCard);

        pnlMain.add(Box.createRigidArea(new Dimension(0, 6)));

        // Create card number field
        txtCardNumber = new HintTextField("Card Number", 18);
        txtCardNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtCardNumber.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        txtCardNumber.setMargin(new Insets(6, 6, 6, 6));
        pnlMain.add(txtCardNumber);

        pnlMain.add(Box.createRigidArea(new Dimension(0, 6)));

        // Create additional card details
        layoutCCDetails = new GridLayout(1, 2);
        layoutCCDetails.setHgap(6);

        pnlCCDetails = new JPanel();
        pnlCCDetails.setLayout(layoutCCDetails);
        pnlCCDetails.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlCCDetails.setBackground(Color.WHITE);
        pnlMain.add(pnlCCDetails);

        // Create the card expiration field
        txtExpiration = new HintTextField("Expiration", 9);
        txtExpiration.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtExpiration.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        txtExpiration.setMargin(new Insets(6, 6, 6, 6));
        pnlCCDetails.add(txtExpiration);

        // Create the card cvv field
        txtCVV = new HintTextField("CCV", 9);
        txtCVV.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtCVV.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        txtCVV.setMargin(new Insets(6, 6, 6, 6));
        pnlCCDetails.add(txtCVV);

        pnlMain.add(Box.createRigidArea(new Dimension(0, 12)));

        // Create the total summary
        lblTotal = new JLabel("Total: PHP 0.00");
        lblTotal.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTotal.setFont(Resources.createPoppinsFont(Resources.FontWeight.MEDIUM, 12));
        pnlMain.add(lblTotal);

        pnlMain.add(Box.createRigidArea(new Dimension(0, 12)));

        // Create the confirmation button
        btnConfirm = new RoundedButton("CONFIRM");
        btnConfirm.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConfirm.setFocusPainted(false);
        btnConfirm.setFont(Resources.createPoppinsFont(Resources.FontWeight.MEDIUM, 12));
        btnConfirm.setForeground(Resources.LIGHT);
        btnConfirm.setBackground(Resources.PRIMARY);
        btnConfirm.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        pnlMain.add(btnConfirm);
    }
}
