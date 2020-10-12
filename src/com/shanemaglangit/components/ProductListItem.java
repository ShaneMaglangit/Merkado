package com.shanemaglangit.components;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.Product;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ProductListItem extends JPanel {
    private Product product;
    private JLabel lblImage;
    private JLabel lblName;
    private JLabel lblPrice;

    public ProductListItem() {
        // Set the panel preferences
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(6, 6, 6, 6));

        // Add the product image
        lblImage = new JLabel();
        lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblImage.setHorizontalAlignment(JLabel.CENTER);
        lblImage.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        this.add(lblImage);

        this.add(Box.createRigidArea(new Dimension(0, 4)));

        // Add the product name
        lblName = new JLabel();
        lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblName.setFont(Resources.createPoppinsFont(Resources.FontWeight.MEDIUM, 12));
        this.add(lblName);

        this.add(Box.createRigidArea(new Dimension(0, 4)));

        // Add the product name
        lblPrice = new JLabel();
        lblPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPrice.setFont(Resources.createPoppinsFont(Resources.FontWeight.MEDIUM, 12));
        this.add(lblPrice);

        this.add(Box.createRigidArea(new Dimension(0, 4)));
    }

    public ProductListItem(Product product) {
        this();
        this.product = product;
    }

    public void setProduct(Product product) {
        this.product = product;
        if(product != null) {
            this.setBackground(Color.WHITE);
            lblPrice.setText("PHP 0.00");
            lblName.setText("Product Name");
//            ImageIcon imgProduct = Util.createImageIcon(this, ".." + Resources.PRODUCT_PLACEHOLDER);
//            lblImage.setIcon(imgProduct);
        } else {
            this.setBackground(null);
//            lblImage.setIcon(null);
            lblPrice.setText("");
            lblName.setText("");
        }
    }
}
