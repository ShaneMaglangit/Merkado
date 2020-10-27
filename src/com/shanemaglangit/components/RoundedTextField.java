package com.shanemaglangit.components;

import javax.swing.*;
import java.awt.*;

public class RoundedTextField extends JTextField {
    private boolean hasBorder;

    public RoundedTextField() {
        this.hasBorder = true;
    }

    public RoundedTextField(String text) {
        super(text);
        this.hasBorder = true;
    }



    public RoundedTextField(int columns) {
        super(columns);
        this.hasBorder = true;
    }

    public RoundedTextField(String text, int columns) {
        super(text, columns);
        this.hasBorder = true;
    }

    public void setHasBorder(boolean hasBorder) {
        this.hasBorder = hasBorder;
        this.repaint();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getParent().getBackground());
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        g2.drawRect(0, 0, getWidth(), getHeight());

        if(hasBorder) {
            g2.setColor(new Color(112, 112, 112, 127));
            g2.setStroke(new BasicStroke(1));
            g2.drawRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        }
    }
}
