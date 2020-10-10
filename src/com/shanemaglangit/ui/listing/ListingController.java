package com.shanemaglangit.ui.listing;

public class ListingController {
    private ListingView view;

    /**
     * Constructor
     * @param view view attached to this controller
     */
    public ListingController(ListingView view) {
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
