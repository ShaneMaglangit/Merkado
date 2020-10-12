package com.shanemaglangit.ui.listing;

import com.shanemaglangit.data.Product;
import com.shanemaglangit.util.ItemOverflowException;
import com.shanemaglangit.util.Util;

import javax.swing.*;
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
    }

    /**
     * Shows the view
     */
    private void showView() {
        view.pack();
        view.setExtendedState(JFrame.MAXIMIZED_BOTH);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    private void setProducts() {
        try {
            Product[] products = new Product[6];
            for(int i = 0; i < 6; i++) products[i] = new Product();
            view.getProductList().setProducts(products);
        } catch (ItemOverflowException e) {
            Util.log(Level.SEVERE, e.getMessage());
        }
    }
}
