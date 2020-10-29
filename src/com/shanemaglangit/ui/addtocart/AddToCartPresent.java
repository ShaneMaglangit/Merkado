package com.shanemaglangit.ui.addtocart;

import com.shanemaglangit.data.Order;
import com.shanemaglangit.data.Product;
import com.shanemaglangit.navigation.Navigation;
import com.shanemaglangit.repository.Repository;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddToCartPresent {
    private AddToCartView view;
    private Repository repository;

    private Order order;

    /**
     * Constructor
     * @param view view attached to this controller
     */
    public AddToCartPresent(AddToCartView view, Product product) {
        this.view = view;
        this.repository = Repository.getInstance();
        this.order = new Order(product, 1);
        updateContent();
        attachListeners();
        showView();
    }

    private void updateContent() {
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
