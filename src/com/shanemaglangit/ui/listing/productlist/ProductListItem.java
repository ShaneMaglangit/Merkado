package com.shanemaglangit.ui.listing.productlist;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.Product;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProductListItem extends JPanel {
    private Product product;
    private JLabel lblImage;
    private JLabel lblName;
    private JLabel lblPrice;

    public ProductListItem(Product product) {
        ImageIcon imgIcon;

        // Set the panel preferences
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(6, 6, 6, 6));
        this.setBackground(Color.WHITE);

        // Add the product image
        imgIcon = Util.createImageIcon(this, "../../.." + Resources.PRODUCT_IMG_PATH + product.getImagePath(), 50, 50);
        if(imgIcon.getImageLoadStatus() == MediaTracker.ABORTED || imgIcon.getImageLoadStatus() == MediaTracker.ERRORED)
            imgIcon = Util.createImageIcon(this, "../../.." + Resources.LOGO_PATH);

        lblImage = new JLabel(imgIcon);
        lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblImage.setHorizontalAlignment(JLabel.CENTER);
        lblImage.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        lblImage.setBorder(new EmptyBorder(8, 8, 8, 8));
        this.add(lblImage);

        this.add(Box.createRigidArea(new Dimension(0, 4)));

        // Add the product name
        lblName = new JLabel(product.getName());
        lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblName.setHorizontalAlignment(JLabel.LEFT);
        lblName.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, 10));
        lblName.setBorder(new EmptyBorder(0, 10, 0, 10));
        lblName.setFont(Resources.createPoppinsFont(Resources.FontWeight.MEDIUM, 12));
        this.add(lblName);

        // Add the product name
        lblPrice = new JLabel("PHP " + product.getPrice());
        lblPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPrice.setHorizontalAlignment(JLabel.LEFT);
        lblPrice.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, 10));
        lblPrice.setBorder(new EmptyBorder(0, 10, 0, 10));
        lblPrice.setFont(Resources.createPoppinsFont(Resources.FontWeight.MEDIUM, 12));
        lblPrice.setForeground(Resources.PRIMARY);
        this.add(lblPrice);

        this.add(Box.createRigidArea(new Dimension(0, 4)));
    }

    public Product getProduct() {
        return product;
    }
}
