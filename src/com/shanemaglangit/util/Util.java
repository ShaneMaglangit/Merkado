package com.shanemaglangit.util;

import com.shanemaglangit.data.FontWeight;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Util {
    private static final Logger LOGGER = Logger.getLogger(Util.class.getName());

    public static void log(Level level, String message) {
        LOGGER.log(level, message);
    }
}
