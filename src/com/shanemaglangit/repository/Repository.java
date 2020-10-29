package com.shanemaglangit.repository;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.*;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import java.io.*;
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

    public void addOrder(Order order) {
        for(int i = 0; i < orderList.getSize(); i++) {
            Order currentOrder = orderList.get(i);
            if(currentOrder.getProduct().getProductId() == order.getProduct().getProductId()) {
                currentOrder.setQuantity(currentOrder.getQuantity() + order.getQuantity());
                writeToCSV(orderList, Resources.ORDER_FILE);
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

    public void addTransactionList(SinglyLinkedList<Transaction> transactions) {
        SwingUtilities.invokeLater(() -> appendToCSV(transactions, Transaction.class, Resources.getTransactionFile()));
        clearOrders();
    }

    public PagedLinkedList<Product> getProductList() {
        return productList;
    }

    public SinglyLinkedList<Order> getOrderList() {
        return orderList;
    }

    public SinglyLinkedList<String> getCategoryList() {
        SinglyLinkedList<String> categoryList = new SinglyLinkedList<>();
        for(int i = 0; i < productList.getSize(); i++) {
            String category = productList.get(i).getCategory();
            if(!categoryList.contains(category)) categoryList.add(category);
        }
        return categoryList;
    }

    public SinglyLinkedList<String> getMarketList() {
        SinglyLinkedList<String> marketList = new SinglyLinkedList<>();
        for(int i = 0; i < productList.getSize(); i++) {
            String category = productList.get(i).getMarket();
            if(!marketList.contains(category)) marketList.add(category);
        }
        return marketList;
    }

    private <T extends CSVEntity & Comparable<T>> void loadFromCSV(SinglyLinkedList<T> list, Class<T> clazz, String filepath) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath))) {
            String row;
            // Skip the header
            bufferedReader.readLine();
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
            if(list.getSize() > 0) stringBuilder.append(list.get(0).getCSVHeader()).append("\n");
            for(int i = 0; i < list.getSize(); i++) {
                stringBuilder.append(list.get(i).toCSV()).append("\n");
            }
            fileOutput.write(stringBuilder.toString().trim().getBytes());
        } catch (IOException e) {
            Util.log(Level.SEVERE, "An error occurred while trying to write to " + filepath);
        }
    }

    private <T extends CSVEntity & Comparable<T>> void appendToCSV(SinglyLinkedList<T> list, Class<T> clazz, String filepath) {
        SinglyLinkedList<T> currentList = new SinglyLinkedList<>();
        loadFromCSV(list, clazz, filepath);
        currentList.addAll(list);
        writeToCSV(list, filepath);
    }
}
