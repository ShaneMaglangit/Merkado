package com.shanemaglangit.ui.listing;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.Order;
import com.shanemaglangit.data.SinglyLinkedList;
import com.shanemaglangit.navigation.Navigation;
import com.shanemaglangit.repository.Repository;
import com.shanemaglangit.util.ItemOverflowException;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;

public class ListingController {
    private Repository repository;
    private ListingView view;

    /**
     * Constructor
     * @param view view attached to this controller
     */
    public ListingController(ListingView view) {
        this.view = view;
        this.repository = Repository.getInstance();
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
        view.getBtnCheckout().addActionListener(e -> {
            Navigation.checkout(view);
            toggleCartVisibility();
        });
        view.getProductList().setClickListener(product -> Navigation.addToCart(view, product));
    }

    /**
     * Set the product to the product list component
     */
    private void setProducts() {
        try {
            view.getProductList().setProducts(repository.getProductList(0));
        } catch (ItemOverflowException e) {
            Util.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Set the order to the order list component
     */
    private void setOrders() {
        SinglyLinkedList<Order> orderList = repository.getOrderList();
        double subTotal = Util.computeOrderSubTotal(orderList);
        double total = subTotal + Config.SERVICE_FEE;

        view.getOrderList().setOrders(orderList);
        view.getLblSubTotal().setText("PHP " + String.valueOf(subTotal));
        view.getLblTotal().setText("PHP " + String.valueOf(total));
    }

    /**
     * Toggles the visibility of the cart panel modal / overlay
     */
    private void toggleCartVisibility() {
        JPanel pnlOverlay = view.getPnlOverlay();
        if(!pnlOverlay.isVisible()) setOrders();
        pnlOverlay.setVisible(!pnlOverlay.isVisible());
    }
}
