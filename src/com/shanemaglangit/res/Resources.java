package com.shanemaglangit.res;

import com.shanemaglangit.util.Util;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

public abstract class Resources {
    public enum FontWeight {
        PLAIN, MEDIUM, BOLD
    }

    public static final Color PRIMARY = Color.decode("#2ECC71");
    public static final Color LIGHT = Color.decode("#FFFFFF");
    public static final Color LIGHT_GRAY = new Color(0, 0, 0, (float) 0.40);

    public static final String SPLASH_PATH = "/res/images/splash.gif";
    public static final String LOGO_PATH = "/res/images/logo.png";
    public static final String LOGO_EXPANDED_PATH = "/res/images/logo-expanded.png";
    public static final String LOGO_LIGHT_EXPANDED_PATH = "/res/images/logo-light-expanded.png";
    public static final String IC_CART = "/res/images/ic_cart.png";
    public static final String PRODUCT_PLACEHOLDER = "/res/images/product-placeholder.png";
    public static final String RADIO_DEFAULT = "/res/images/radio-default.png";
    public static final String RADIO_SELECTED = "/res/images/radio-selected.png";
    public static final String IC_RIGHT_ARROW = "/res/images/ic_right_arrow.png";
    public static final String DELIVER = "/res/images/deliver.gif";

    public static final String PRODUCT_IMG_PATH = "/res/images/products/";

    public static final String PRODUCT_FILE = "src/com/shanemaglangit/res/csv/products.csv";
    public static final String ORDER_FILE = "src/com/shanemaglangit/res/csv/orders.csv";
    public static final String TRANSACTIONS_FILE_FORMAT = "src/com/shanemaglangit/res/csv/transactions/%s.csv";

    /**
     * Used to generate a Font based on the given weight and size
     * @param weight font weight
     * @param size font size
     * @return generated Font object
     */
    public static Font createPoppinsFont(FontWeight weight, int size) {
        String fontPath = "";
        int weightCallback = Font.PLAIN;

        switch(weight) {
            case PLAIN:
                fontPath = "../res/fonts/Poppins-Regular.ttf";
                break;
            case MEDIUM:
                fontPath = "../res/fonts/Poppins-Medium.ttf";
                weightCallback = Font.BOLD;
                break;
            case BOLD:
                fontPath = "../res/fonts/Poppins-Bold.ttf";
                weightCallback = Font.BOLD;
                break;
        }

        try(InputStream inputStream = Util.class.getResourceAsStream(fontPath)) {
            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            return font.deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            return new Font("Trebuchet MS", weightCallback, size);
        }
    }

    /**
     * Used to get the path to the transaction file for the current date
     * @return filepath of the transaction file
     */
    public static String getTransactionFile() {
        return getTransactionFile(LocalDate.now());
    }


    /**
     * Used to get the path to the transaction file for the a given date
     * @return filepath of the transaction file
     */
    public static String getTransactionFile(LocalDate date) {
        return String.format(TRANSACTIONS_FILE_FORMAT, date.format(Util.dateFormatter));
    }
}
