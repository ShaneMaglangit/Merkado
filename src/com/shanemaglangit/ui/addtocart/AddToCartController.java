package com.shanemaglangit.ui.addtocart;

import com.shanemaglangit.data.Order;
import com.shanemaglangit.data.Product;
import com.shanemaglangit.navigation.Navigation;
import com.shanemaglangit.repository.Repository;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddToCartController {
    private AddToCartView view;
    private Repository repository;

    private Order order;

    /**
     * Constructor
     * @param view view attached to this controller
     */
    public AddToCartController(AddToCartView view, Product product) {
        this.view = view;
        this.repository = Repository.getInstance();
        this.order = new Order(product, 1);
        attachListeners();
        showView();
    }

    /**
     * Attach listeners to the view components
     */
    private void attachListeners() {
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
