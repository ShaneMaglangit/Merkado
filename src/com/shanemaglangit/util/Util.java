package com.shanemaglangit.util;

import com.shanemaglangit.data.Order;
import com.shanemaglangit.data.SinglyLinkedList;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Util {
    private static final Logger LOGGER = Logger.getLogger(Util.class.getName());

    public static void log(Level level, String message) {
        LOGGER.log(level, message);
    }

    public static ImageIcon createImageIcon(Object object, String url) {
        URL imgUrl = object.getClass().getResource(url);
        return new ImageIcon(imgUrl);
    }

    public static ImageIcon createImageIcon(Object object, String url, int width, int height) {
        ImageIcon imgIcon = createImageIcon(object, url);
        Image scaledImg = imgIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    public static double computeOrderSubTotal(SinglyLinkedList<Order> orderList) {
        double subTotal = 0;
        for(int i = 0; i < orderList.getSize(); i++)
            subTotal += orderList.get(i).getTotal();
        return  subTotal;
    }

    public static void showErrorDialog(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "An error has occured", JOptionPane.ERROR_MESSAGE);
    }

    public static void showMessageDialog(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "", JOptionPane.PLAIN_MESSAGE);
    }

    public static String[] stringListToArr(SinglyLinkedList<String> list) {
        String[] arr = new String[list.getSize() + 1];
        arr[0] = "";
        for(int i = 0; i < list.getSize(); i++) arr[i + 1] = list.get(i);
        return arr;
    }
}
