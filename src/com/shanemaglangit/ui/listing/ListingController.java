package com.shanemaglangit.ui.listing;

import com.shanemaglangit.data.Product;
import com.shanemaglangit.util.ItemOverflowException;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;

public class ListingController {
    private ListingView view;

    /**
     * Constructor
     * @param view view attached to this controller
     */
    public ListingController(ListingView view) {
        this.view = view;
        showView();
        setProducts();
        attachListeners();
    }

    /**
     * Shows the view
     */
    private void showView() {
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    /**
     * Attach listeners to the view components
     */
    private void attachListeners() {
        view.getBtnCart().addActionListener(e -> toggleCartVisibility());
        view.getBtnClose().addActionListener(e -> toggleCartVisibility());
        view.getPnlFiller().addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) { toggleCartVisibility(); }
            @Override public void mousePressed(MouseEvent e) { }
            @Override public void mouseReleased(MouseEvent e) { }
            @Override public void mouseEntered(MouseEvent e) { }
            @Override public void mouseExited(MouseEvent e) { }
        });
    }

    /**
     * Set the product to the product list component
     */
    private void setProducts() {
        try {
            Product[] products = new Product[15];
            for(int i = 0; i < 15; i++) products[i] = new Product();
            view.getProductList().setProducts(products);
        } catch (ItemOverflowException e) {
            Util.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Toggles the visibility of the cart panel modal / overlay
     */
    private void toggleCartVisibility() {
        JPanel pnlOverlay = view.getPnlOverlay();
        pnlOverlay.setVisible(!pnlOverlay.isVisible());
    }
}
