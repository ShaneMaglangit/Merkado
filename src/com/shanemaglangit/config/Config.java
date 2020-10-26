package com.shanemaglangit.config;

import java.awt.*;

public abstract class Config {
    public static final String TITLE = "Merkado";
    public static final int WINDOW_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static final int WINDOW_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

    public final static double SERVICE_FEE = 100;
    public static final int PRODUCT_PER_PAGE = 50;
    public static final int PRODUCT_PER_SCREEN = 20;
}
