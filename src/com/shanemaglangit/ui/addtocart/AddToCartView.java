package com.shanemaglangit.ui.addtocart;

import com.shanemaglangit.components.HintTextField;
import com.shanemaglangit.components.RoundedButton;
import com.shanemaglangit.components.RoundedTextField;
import com.shanemaglangit.config.Config;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class AddToCartView extends JFrame {
    private JPanel pnlMain;
    private JLabel lblLogo;
    private JLabel lblProductImg;
    private JLabel lblProductName;
    private JLabel lblPrice;
    private JPanel pnlInput;
    private JFormattedTextField txtQuantity;
    private JButton btnConfirm;

    /**
     * Constructor where all of the components of the frame are created
     * @throws HeadlessException
     */
    public AddToCartView() throws HeadlessException {
        GridLayout pnlInputBorder;
        NumberFormatter numberFormatter;

        // Set the frame preferences
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);

        // Set the frame logo
        this.setIconImage(Util.createImageIcon(this, Resources.LOGO_PATH).getImage());

        // Create the main panel
        pnlMain = new JPanel();
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBorder(new EmptyBorder(18, 18, 18, 18));
        this.getContentPane().add(pnlMain);

        // Create the logo
        lblLogo = new JLabel(Util.createImageIcon(this, Resources.LOGO_EXPANDED_PATH));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlMain.add(lblLogo);

        pnlMain.add(Box.createRigidArea(new Dimension(0, 16)));

        // Create the product image
        lblProductImg = new JLabel(Util.createImageIcon(this, Resources.PRODUCT_PLACEHOLDER));
        lblProductImg.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlMain.add(lblProductImg);

        pnlMain.add(Box.createRigidArea(new Dimension(0, 10)));

        // Create the product image
        lblProductName = new JLabel("No Product Name");
        lblProductName.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblProductName.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        pnlMain.add(lblProductName);

        // Create the price
        lblPrice = new JLabel("PHP 0");
        lblPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPrice.setFont(Resources.createPoppinsFont(Resources.FontWeight.MEDIUM, 12));
        lblPrice.setForeground(Resources.PRIMARY);
        pnlMain.add(lblPrice);

        pnlMain.add(Box.createRigidArea(new Dimension(0, 16)));

        // Create the panel for the user input
        pnlInputBorder = new GridLayout(1, 2);
        pnlInputBorder.setHgap(4);

        pnlInput = new JPanel();
        pnlInput.setLayout(pnlInputBorder);
        pnlInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlInput.setBackground(Color.WHITE);
        pnlInput.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, 50));
        pnlMain.add(pnlInput);

        // Create the quantity field
        numberFormatter = new NumberFormatter(NumberFormat.getIntegerInstance());
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setMinimum(1);
        numberFormatter.setValueClass(Integer.class);

        txtQuantity = new JFormattedTextField(numberFormatter);
        txtQuantity.setValue(1);
        txtQuantity.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        txtQuantity.setMargin(new Insets(6, 6, 6, 6));
        txtQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        pnlInput.add(txtQuantity);

        // Create the confirmation button
        btnConfirm = new JButton("ADD TO CART");
        btnConfirm.setFocusPainted(false);
        btnConfirm.setFont(Resources.createPoppinsFont(Resources.FontWeight.MEDIUM, 12));
        btnConfirm.setForeground(Resources.LIGHT);
        btnConfirm.setBackground(Resources.PRIMARY);
        btnConfirm.setBorder(null);
        pnlInput.add(btnConfirm);
    }

    public JLabel getLblProductName() {
        return lblProductName;
    }

    public JLabel getLblPrice() {
        return lblPrice;
    }

    public JFormattedTextField getTxtQuantity() {
        return txtQuantity;
    }

    public JButton getBtnConfirm() {
        return btnConfirm;
    }
}
