package com.shanemaglangit.util;

import javax.swing.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Util {
    private static final Logger LOGGER = Logger.getLogger(Util.class.getName());

    public static void log(Level level, String message) {
        LOGGER.log(level, message);
    }

    public static ImageIcon createImageIcon(Object object, String url) {
        URL gifUrl = object.getClass().getResource(url);
        return new ImageIcon(gifUrl);
    }
}
