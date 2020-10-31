package com.shanemaglangit.repository;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.*;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import java.util.logging.Level;

public class Repository {
    private static Repository instance;

    // List for the objects
    private PagedLinkedList<Product> productList;
    private SinglyLinkedList<Order> orderList;

    /**
     * Constructor for the singleton lazy initialization
     */
    private Repository() {
        this.productList = new PagedLinkedList<>(Config.PRODUCT_PER_PAGE);
        this.orderList = new SinglyLinkedList<>();
        loadFromCSV(orderList, Order.class, Resources.ORDER_FILE);
        loadFromCSV(productList, Product.class, Resources.PRODUCT_FILE);
        writeToCSV(productList, Resources.PRODUCT_FILE);
    }

    /**
     * Used to lazy initialize the Repository
     * @return instance of the Repository
     */
    public static Repository getInstance() {
        if(instance == null) instance = new Repository();
        return instance;
    }

    /**
     * Used to save an order
     * @param order order to be added
     */
    public void addOrder(Order order) {
        // Iterate and find if an order with the same product already exists
        for(int i = 0; i < orderList.getSize(); i++) {
            Order currentOrder = orderList.get(i);
            // Increase the quantity of the existing order if the product matches
            if(currentOrder.getProduct().getProductId() == order.getProduct().getProductId()) {
                currentOrder.setQuantity(currentOrder.getQuantity() + order.getQuantity());
                // Save the changes to the order file
                writeToCSV(orderList, Resources.ORDER_FILE);
                return;
            }
        }
        // Add the order to the orderList
        orderList.add(order);
        // Save the changes to the order file
        writeToCSV(orderList, Resources.ORDER_FILE);
    }

    /**
     * Used to remove an order
     * @param uuid uuid of the order
     */
    public void deleteOrder(UUID uuid) {
        // Find the order and remove it
        for(int i = 0; i < orderList.getSize(); i++) {
            if(orderList.get(i).getOrderId().equals(uuid)) {
                orderList.remove(i);
                // Save changes to the order file
                writeToCSV(orderList, Resources.ORDER_FILE);
                break;
            }
        }
    }

    /**
     * Used to remove all of the orders from the list and the file
     */
    public void clearOrders() {
        orderList.removeAll();
        writeToCSV(orderList, Resources.ORDER_FILE);
    }

    /**
     * Used to save a transaction
     * @param transactions list of transactions to be added
     */
    public void addTransactionList(SinglyLinkedList<Transaction> transactions) {
        // Append the transaction to the existing transactions for the given date
        SwingUtilities.invokeLater(() -> appendToCSV(transactions, Transaction.class, Resources.getTransactionFile()));
        // Clear the orders after saving the transaction/purchase
        clearOrders();
    }

    /**
     * Accessor for the product list
     * @return product list
     */
    public PagedLinkedList<Product> getProductList() {
        return productList;
    }

    /**
     * Accessor for the order list
     * @return order list
     */
    public SinglyLinkedList<Order> getOrderList() {
        return orderList;
    }

    /**
     * Used to extract a unique list of categories from the product list
     * @return list of categories
     */
    public SinglyLinkedList<String> getCategoryList() {
        SinglyLinkedList<String> categoryList = new SinglyLinkedList<>();
        for(int i = 0; i < productList.getSize(); i++) {
            String category = productList.get(i).getCategory();
            if(!categoryList.contains(category)) categoryList.add(category);
        }
        return categoryList;
    }


    /**
     * Used to extract a unique list of markets from the product list
     * @return list of markets
     */
    public SinglyLinkedList<String> getMarketList() {
        SinglyLinkedList<String> marketList = new SinglyLinkedList<>();
        for(int i = 0; i < productList.getSize(); i++) {
            String category = productList.get(i).getMarket();
            if(!marketList.contains(category)) marketList.add(category);
        }
        return marketList;
    }

    /**
     * Used to load data from a csv file and parse them into an object
     * @param list list of csv entity
     * @param clazz class where the data will be converted
     * @param filepath csv file
     */
    private <E extends CSVEntity & Comparable<E>> void loadFromCSV(SinglyLinkedList<E> list, Class<E> clazz, String filepath) {
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

    /**
     * Used to save a CSVEntity to a csv file
     * @param list list of csv entity
     * @param filepath csv file
     */
    private <E extends CSVEntity & Comparable<E>> void writeToCSV(SinglyLinkedList<E> list, String filepath) {
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

    /**
     * Used to append a list of CSVEntity to a file
     * @param list list of CSVEntities
     * @param clazz class where the data will be converted
     * @param filepath csv file
     */
    private <E extends CSVEntity & Comparable<E>> void appendToCSV(SinglyLinkedList<E> list, Class<E> clazz, String filepath) {
        SinglyLinkedList<E> currentList = new SinglyLinkedList<>();
        loadFromCSV(list, clazz, filepath);
        currentList.addAll(list);
        writeToCSV(list, filepath);
    }
}
