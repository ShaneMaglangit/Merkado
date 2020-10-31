package com.shanemaglangit.ui.addtocart;

import com.shanemaglangit.data.Order;
import com.shanemaglangit.data.Product;
import com.shanemaglangit.repository.Repository;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class AddToCartPresenter {
    private AddToCartView view;
    private Repository repository;

    private Order order;

    /**
     * Constructor
     * @param view view attached to this controller
     */
    public AddToCartPresenter(AddToCartView view, Product product) {
        this.view = view;
        this.repository = Repository.getInstance();
        this.order = new Order(product, 1);
        updateContent();
        attachListeners();
        showView();
    }

    /**
     * Used to update the contents of the view components
     */
    private void updateContent() {
        ImageIcon imgIcon = Util.createImageIcon(this, "../.." + Resources.PRODUCT_IMG_PATH + order.getProduct().getImagePath(), 50, 50);
        if(imgIcon.getImageLoadStatus() == MediaTracker.ABORTED || imgIcon.getImageLoadStatus() == MediaTracker.ERRORED)
            imgIcon = Util.createImageIcon(this, "../.." + Resources.LOGO_PATH);

        view.getLblProductImg().setIcon(imgIcon);
        view.getLblPrice().setText("PHP " + order.getTotal());
        view.getLblProductName().setText(order.getProduct().getName());
    }

    /**
     * Attach listeners to the view components
     */
    private void attachListeners() {
        view.getTxtQuantity().getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) {
                order.setQuantity(Integer.parseInt(view.getTxtQuantity().getText()));
                updateContent();
            }
            @Override public void removeUpdate(DocumentEvent e) {}
            @Override public void changedUpdate(DocumentEvent e) {}
        });
        view.getBtnConfirm().addActionListener(e -> {
            repository.addOrder(order);
            view.dispose();
        });
    }

    /**
     * Shows the view
     */
    private void showView() {
        view.pack();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}
