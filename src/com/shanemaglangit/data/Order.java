package com.shanemaglangit.data;

import java.util.Arrays;
import java.util.UUID;

public class Order extends CSVEntity implements Comparable<Order> {
    private UUID orderId;
    private Product product;
    private int quantity;

    public Order(String CSV) {
        this(CSV.split(","));
    }

    public Order(String[] values) {
        this.orderId = UUID.fromString(values[0]);
        this.product = new Product(Arrays.copyOfRange(values, 1, values.length - 2));
        this.quantity = Integer.parseInt(values[values.length - 1]);
    }

    public Order(Product product, int quantity) {
        this(UUID.randomUUID(), product, quantity);
    }

    public Order(UUID orderId, Product product, int quantity) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return getQuantity() * product.getPrice();
    }

    @Override
    public int compareTo(Order order) {
        return order.product.compareTo(product);
    }

    @Override
    public String toCSV() {
        return String.format("%s,%s,%d", orderId.toString(), product.toCSV(), quantity);
    }
}
