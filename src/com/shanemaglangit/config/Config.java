package com.shanemaglangit.config;

import java.awt.*;

public abstract class Config {
    public static final String TITLE = "Merkado";
    public static final int WINDOW_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static final int WINDOW_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

    public final static double SERVICE_FEE = 100;
    public static final int PRODUCT_PER_PAGE = 50;
    public static final int PRODUCT_PER_SCREEN = 20;

    public static final String CARD_PATTERN = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
            "(?<mastercard>5[1-5][0-9]{14})|" +
            "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
            "(?<amex>3[47][0-9]{13})|" +
            "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
            "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";
}
