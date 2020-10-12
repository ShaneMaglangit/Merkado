package com.shanemaglangit.ui.listing;

import javax.swing.*;

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
        view.setExtendedState(JFrame.MAXIMIZED_BOTH);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}
