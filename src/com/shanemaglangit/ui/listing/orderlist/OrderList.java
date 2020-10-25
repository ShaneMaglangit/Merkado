package com.shanemaglangit.ui.listing.orderlist;

import com.shanemaglangit.data.Order;

import javax.swing.*;

public class OrderList extends JPanel {
    public OrderList() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void setOrders(Order[] orders) {
        this.removeAll();

        for(Order order : orders) this.add(new OrderListItem(order));
    }
}