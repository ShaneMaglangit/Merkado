package com.shanemaglangit.res;

import com.shanemaglangit.data.FontWeight;
import com.shanemaglangit.util.Util;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public abstract class Resources {
    public static final Color PRIMARY = Color.decode("#2ECC71");
    public static final Color LIGHT = Color.decode("#FFFFFF");

    public static Font createPoppinsFont(FontWeight weight, int size) {
        String fontPath = "";
        int weightCallback = Font.PLAIN;

        switch(weight) {
            case PLAIN:
                fontPath = "../res/Poppins-Regular.ttf";
                break;
            case MEDIUM:
                fontPath = "../res/Poppins-Medium.ttf";
                weightCallback = Font.BOLD;
                break;
            case BOLD:
                fontPath = "../res/Poppins-Bold.ttf";
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
