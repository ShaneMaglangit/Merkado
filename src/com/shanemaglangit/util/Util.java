package com.shanemaglangit.util;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.Order;
import com.shanemaglangit.data.SinglyLinkedList;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Util {
    private static final Logger LOGGER = Logger.getLogger(Util.class.getName());

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Config.DATE_TIME_FORMAT);
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Config.DATE_FORMAT);

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

    public static String cipher(String raw) {
        try {
            byte[] encryptedBytes;
            byte[] keyBytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
            byte[] rawBytes = raw.getBytes();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedBytes = cipher.doFinal(rawBytes);
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            Util.log(Level.SEVERE, "An error occurred during encryption");
            return raw;
        }
    }
}
