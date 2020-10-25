package com.shanemaglangit.ui.listing.orderlist;

import com.shanemaglangit.data.Order;
import com.shanemaglangit.data.SinglyLinkedList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class OrderList extends JPanel {
    public OrderList() {
        super();
        this.setLayout(new SpringLayout());
    }

    public void setOrders(SinglyLinkedList<Order> orders) {
        SwingUtilities.invokeLater(() -> {
            SpringLayout layout = (SpringLayout) getLayout();
            this.removeAll();
            for(int i = 0; i < orders.getSize(); i++) {
                System.out.println(i);
                OrderListItem current = new OrderListItem(orders.get(i));

                add(current);
                if(i == 0) layout.putConstraint(SpringLayout.NORTH, current, 4, SpringLayout.NORTH, this);
                else layout.putConstraint(SpringLayout.NORTH, current, 4, SpringLayout.SOUTH, getComponent(i - 1));
                layout.putConstraint(SpringLayout.WEST, current, 0, SpringLayout.WEST, this);
                layout.putConstraint(SpringLayout.EAST, current, 0, SpringLayout.EAST, this);
            }
        });
    }
}