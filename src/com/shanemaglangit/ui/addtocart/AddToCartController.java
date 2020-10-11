package com.shanemaglangit.ui.addtocart;

public class AddToCartController {
    private AddToCartView view;

    /**
     * Constructor
     * @param view view attached to this controller
     */
    public AddToCartController(AddToCartView view) {
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
