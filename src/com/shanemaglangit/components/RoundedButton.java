package com.shanemaglangit.components;

import com.shanemaglangit.res.Resources;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RoundedButton extends JButton {

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);

        setBorder(
            BorderFactory.createCompoundBorder(
                new EmptyBorder(6, 6, 6, 6),
                new LineBorder(Resources.LIGHT, 0, true)
            )
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(this.getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(g);
    }
}
