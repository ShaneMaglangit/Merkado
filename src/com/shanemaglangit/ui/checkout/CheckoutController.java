package com.shanemaglangit.ui.checkout;

import com.shanemaglangit.ui.addtocart.AddToCartView;

public class CheckoutController {
    private CheckoutView view;

    /**
     * Constructor
     * @param view view attached to this controller
     */
    public CheckoutController(CheckoutView view) {
        this.view = view;
        showView();
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
