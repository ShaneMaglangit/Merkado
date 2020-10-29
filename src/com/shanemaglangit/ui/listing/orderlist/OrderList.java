package com.shanemaglangit.ui.listing.orderlist;

import com.shanemaglangit.data.Order;
import com.shanemaglangit.data.SinglyLinkedList;

import javax.swing.*;

public class OrderList extends JPanel {
    public OrderList() {
        // Set the layout
        this.setLayout(new SpringLayout());
    }

    /**
     * Used to add order items to the list
     * @param orders
     */
    public void setOrders(SinglyLinkedList<Order> orders) {
        SwingUtilities.invokeLater(() -> {
            SpringLayout layout = (SpringLayout) getLayout();
            this.removeAll();
            for(int i = 0; i < orders.getSize(); i++) {
                OrderListItem current = new OrderListItem(orders.get(i));

                add(current);
                if(i == 0) layout.putConstraint(SpringLayout.NORTH, current, 4, SpringLayout.NORTH, this);
                else layout.putConstraint(SpringLayout.NORTH, current, 4, SpringLayout.SOUTH, getComponent(i - 1));
                layout.putConstraint(SpringLayout.WEST, current, 0, SpringLayout.WEST, this);
                layout.putConstraint(SpringLayout.EAST, current, 0, SpringLayout.EAST, this);
                layout.putConstraint(SpringLayout.SOUTH, this, 0, SpringLayout.SOUTH, current);
            }
        });
    }
}