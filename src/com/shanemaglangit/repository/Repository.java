package com.shanemaglangit.repository;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.*;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import java.util.logging.Level;

public class Repository {
    private static Repository instance;

    private PagedLinkedList<Product> productList;
    private SinglyLinkedList<Order> orderList;

    private Repository() {
        this.productList = new PagedLinkedList<>(Config.PRODUCT_PER_PAGE);
        this.orderList = new SinglyLinkedList<>();

        loadFromCSV(orderList, Order.class, Resources.ORDER_FILE);
        loadFromCSV(productList, Product.class, Resources.PRODUCT_FILE);
        writeToCSV(productList, Resources.PRODUCT_FILE);
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
        for(int i = 0; i < orderList.getSize(); i++) {
            Order currentOrder = orderList.get(i);
            if(currentOrder.getProduct().getProductId() == order.getProduct().getProductId()) {
                currentOrder.setQuantity(currentOrder.getQuantity() + order.getQuantity());
                return;
            }
        }
        orderList.add(order);
        writeToCSV(orderList, Resources.ORDER_FILE);
    }

    public void deleteOrder(UUID uuid) {
        for(int i = 0; i < orderList.getSize(); i++) {
            if(orderList.get(i).getOrderId().equals(uuid)) {
                orderList.remove(i);
                writeToCSV(orderList, Resources.ORDER_FILE);
                break;
            }
        }
    }

    public void clearOrders() {
        orderList.removeAll();
        writeToCSV(orderList, Resources.ORDER_FILE);
    }

    private <T extends CSVEntity & Comparable<T>> void loadFromCSV(SinglyLinkedList<T> list, Class<T> clazz, String filepath) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath))) {
            String row;
            while((row = bufferedReader.readLine()) != null) {
                list.add(clazz.getDeclaredConstructor(String.class).newInstance(row));
            }
        } catch (IOException e) {
            Util.log(Level.WARNING, "An error occurred while trying to read " + filepath);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            Util.log(Level.WARNING, "An error occurred while trying to parse csv row to " + clazz.getName());
        }
    }

    private <T extends CSVEntity & Comparable<T>> void writeToCSV(SinglyLinkedList<T> list, String filepath) {
        try (FileOutputStream fileOutput = new FileOutputStream(filepath)) {
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i < list.getSize(); i++) {
                stringBuilder.append(list.get(i).toCSV()).append("\n");
            }
            fileOutput.write(stringBuilder.toString().trim().getBytes());
        } catch (IOException e) {
            Util.log(Level.SEVERE, "An error occurred while trying to write to " + filepath);
        }
    }
}
