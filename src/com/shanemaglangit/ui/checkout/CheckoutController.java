package com.shanemaglangit.ui.checkout;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.repository.Repository;
import com.shanemaglangit.ui.addtocart.AddToCartView;
import com.shanemaglangit.util.Util;

public class CheckoutController {
    private Repository repository;
    private CheckoutView view;

    /**
     * Constructor
     * @param view view attached to this controller
     */
    public CheckoutController(CheckoutView view) {
        this.view = view;
        this.repository = Repository.getInstance();
        updateContents();
        attachListeners();
        showView();
    }

    private void updateContents() {
        double subTotal = Util.computeOrderSubTotal(repository.getOrderList());
        view.getLblTotal().setText(String.valueOf(subTotal + Config.SERVICE_FEE));
    }

    private void attachListeners() {
        view.getBtnConfirm().addActionListener(e -> {
            repository.clearOrders();
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
