package com.shanemaglangit.util;

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
}
