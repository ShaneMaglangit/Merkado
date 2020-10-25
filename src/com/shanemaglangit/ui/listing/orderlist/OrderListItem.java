package com.shanemaglangit.ui.listing.orderlist;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.Order;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class OrderListItem extends JPanel {
    private Order order;

    private JLabel lblImage;

    private JPanel pnlContent;
    private JLabel lblOrderTitle;
    private JLabel lblOrderPrice;

    public OrderListItem(Order order) {
        this.order = order;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(Color.WHITE);

        // Create the image
        lblImage = new JLabel(Util.createImageIcon(this, "../../.." + Resources.LOGO_PATH));
        lblImage.setBackground(Color.WHITE);
        lblImage.setBorder(new EmptyBorder(8, 8, 8, 16));
        this.add(lblImage);

        // Create the right panel
        pnlContent = new JPanel();
        pnlContent.setBackground(Color.WHITE);
        pnlContent.setAlignmentY(Component.CENTER_ALIGNMENT);
        pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.Y_AXIS));
        this.add(pnlContent);

        lblOrderTitle = new JLabel("Order Title (1)");
        lblOrderTitle.setFont(Resources.createPoppinsFont(Resources.FontWeight.BOLD, 12));
        pnlContent.add(lblOrderTitle);

        lblOrderPrice = new JLabel("PHP 0.00");
        lblOrderPrice.setFont(Resources.createPoppinsFont(Resources.FontWeight.MEDIUM, 12));
        pnlContent.add(lblOrderPrice);
    }
}
