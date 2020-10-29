package com.shanemaglangit.ui.receipt;

import com.shanemaglangit.components.HintTextField;
import com.shanemaglangit.components.RoundedButton;
import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.Transaction;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReceiptView extends JDialog {
    private JPanel pnlMain;

    private JPanel pnlDeliver;
    private JLabel lblDeliver;
    private JLabel lblConfirmation;

    private JPanel pnlSeperator;

    private JPanel pnlDetails;
    private JLabel lblHeader;
    private JLabel lblTransactionId;
    private JLabel lblAddress;
    private JLabel lblPhoneNumber;
    private JLabel lblPaymentMethod;
    private JLabel lblTotalFee;

    private JButton btnOk;

    /**
     * Constructor where all of the components of the frame are created
     * @throws HeadlessException
     */
    public ReceiptView(Frame owner) throws HeadlessException {
        super(owner, true);
        // Set the frame preferences
        this.setTitle(Config.TITLE);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Set the frame logo
        this.setIconImage(Util.createImageIcon(this, "../.." + Resources.LOGO_PATH).getImage());

        // Create the main panel
        pnlMain = new JPanel();
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.X_AXIS));
        pnlMain.setBorder(new EmptyBorder(0, 0, 8, 0));
        this.getContentPane().add(pnlMain);

        // Create the deliver panel
        pnlDeliver = new JPanel();
        pnlDeliver.setBackground(Color.WHITE);
        pnlDeliver.setLayout(new BoxLayout(pnlDeliver, BoxLayout.Y_AXIS));
        pnlDeliver.setBorder(new EmptyBorder(18, 18, 18, 18));
        pnlMain.add(pnlDeliver);

        pnlSeperator = new JPanel();
        pnlSeperator.setBackground(Color.GRAY);
        pnlSeperator.setMaximumSize(new Dimension(1, Config.WINDOW_HEIGHT));
        pnlMain.add(pnlSeperator);

        // Create the deliver image
        lblDeliver = new JLabel(Util.createImageIcon(this, "../.." + Resources.DELIVER));
        lblDeliver.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlDeliver.add(lblDeliver);

        lblConfirmation = new JLabel("Your order should arrive in a few hours");
        lblConfirmation.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblConfirmation.setFont(Resources.createPoppinsFont(Resources.FontWeight.MEDIUM , 14));
        pnlDeliver.add(lblConfirmation);

        // Create the details panel
        pnlDetails = new JPanel();
        pnlDetails.setBackground(Color.WHITE);
        pnlDetails.setLayout(new BoxLayout(pnlDetails, BoxLayout.Y_AXIS));
        pnlDetails.setBorder(new EmptyBorder(18, 18, 18, 18));
        pnlMain.add(pnlDetails);

        lblHeader = new JLabel("Here's your purchase details");
        lblHeader.setFont(Resources.createPoppinsFont(Resources.FontWeight.MEDIUM, 14));
        pnlDetails.add(lblHeader);

        pnlDetails.add(Box.createRigidArea(new Dimension(0, 12)));

        lblTransactionId = new JLabel();
        lblTransactionId.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        pnlDetails.add(lblTransactionId);

        pnlDetails.add(Box.createRigidArea(new Dimension(0, 6)));

        lblAddress = new JLabel();
        lblAddress.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        pnlDetails.add(lblAddress);

        pnlDetails.add(Box.createRigidArea(new Dimension(0, 6)));

        lblPhoneNumber = new JLabel();
        lblPhoneNumber.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        pnlDetails.add(lblPhoneNumber);

        pnlDetails.add(Box.createRigidArea(new Dimension(0, 6)));

        lblPaymentMethod = new JLabel();
        lblPaymentMethod.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        pnlDetails.add(lblPaymentMethod);

        pnlDetails.add(Box.createRigidArea(new Dimension(0, 6)));

        lblTotalFee = new JLabel();
        lblTotalFee.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        pnlDetails.add(lblTotalFee);

        pnlDetails.add(Box.createRigidArea(new Dimension(0, 12)));

        // Create the ok button
        btnOk = new JButton("Ok");
        btnOk.setFocusPainted(false);
        btnOk.setFont(Resources.createPoppinsFont(Resources.FontWeight.MEDIUM, 12));
        btnOk.setForeground(Resources.LIGHT);
        btnOk.setBackground(Resources.PRIMARY);
        pnlDetails.add(btnOk);
    }

    public JLabel getLblTransactionId() {
        return lblTransactionId;
    }

    public JLabel getLblAddress() {
        return lblAddress;
    }

    public JLabel getLblPhoneNumber() {
        return lblPhoneNumber;
    }

    public JLabel getLblPaymentMethod() {
        return lblPaymentMethod;
    }

    public JLabel getLblTotalFee() {
        return lblTotalFee;
    }

    public JButton getBtnOk() {
        return btnOk;
    }
}
