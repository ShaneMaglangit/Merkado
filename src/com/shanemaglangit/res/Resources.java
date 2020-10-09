package com.shanemaglangit.res;

import com.shanemaglangit.data.FontWeight;
import com.shanemaglangit.util.Util;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class Resources {
    public static final Color PRIMARY = Color.decode("#2ECC71");
    public static final Color LIGHT = Color.decode("#FFFFFF");
    public static final Color LIGHT_GRAY = Color.decode("#66000000");

    public static final String SPLASH_PATH = "../../res/images/splash.gif";
    public static final String LOGO_PATH = "../../res/images/logo.png";
    public static final String LOGO_EXPANDED_PATH = "../../res/images/logo-expanded.png";

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
}
