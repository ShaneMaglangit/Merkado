package com.shanemaglangit.repository;

import com.shanemaglangit.data.Order;
import com.shanemaglangit.data.PagedLinkedList;
import com.shanemaglangit.data.Product;
import com.shanemaglangit.data.SinglyLinkedList;

import java.util.UUID;

public class Repository {
    private static final String PRODUCT_FILE = "";
    private static final String ORDER_FILE = "";

    public static final int PRODUCT_PER_PAGE = 15;

    private static Repository instance;

    private PagedLinkedList<Product> productList;
    private SinglyLinkedList<Order> orderList;

    private Repository() {
        this.productList = new PagedLinkedList<>(PRODUCT_PER_PAGE);
        this.orderList = new SinglyLinkedList<>();

        // Create mock products
        for(int i = 1; i < 50; i++) {
            productList.add(new Product("Fresh Pork " + i + "kg", 50, "Sampaloc Market", null, "Meat"));
        }
    }

    public static Repository getInstance() {
        if(instance == null) instance = new Repository();
        return instance;
    }

    public SinglyLinkedList<Product> getProductList(int page) {
        return productList.getPage(page);
    }

    public SinglyLinkedList<Order> getOrderList() {
        return orderList;
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public void deleteOrder(UUID uuid) {
        for(int i = 0; i < orderList.getSize(); i++) {
            if(orderList.get(i).getOrderId().equals(uuid)) {
                orderList.remove(i);
                break;
            }
        }
    }

    public void clearOrders() {
        orderList.removeAll();
    }
}
