package com.shanemaglangit.components;

import com.shanemaglangit.data.Order;
import com.shanemaglangit.data.Product;
import com.shanemaglangit.util.ItemOverflowException;

import javax.swing.*;
import java.awt.*;

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