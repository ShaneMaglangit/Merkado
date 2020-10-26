package com.shanemaglangit.ui.checkout;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.repository.Repository;
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
        view.getBtnConfirm().addActionListener(e -> validateFields());
    }

    /**
     * Shows the view
     */
    private void showView() {
        view.pack();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    private void validateFields() {
        try {
            if(view.getTxtAddress().getText().isEmpty())
                throw new Exception("Address cannot be empty");
            else if(view.getTxtPhoneNumber().getText().isEmpty())
                throw new Exception("Phone number cannot be empty");
            else if(view.getRbtnCard().isSelected()) {
                if(!validateCardNumber(view.getTxtCardNumber().getText()))
                    throw new Exception("Invalid card number");
                if(!view.getTxtExpiration().getText().matches("\\d\\d/\\d\\d"))
                    throw new Exception("Expiration must follow the format mm/yy");
                if(view.getTxtCVV().getText().length() != 3)
                    throw new Exception("CVV must be 3 digits");
            }
            repository.clearOrders();
            view.dispose();
            Util.showMessageDialog(view, "Your order should arrive in a few hours.");
        } catch (Exception e) {
            Util.showErrorDialog(view, e.getMessage());
        }
    }

    private boolean validateCardNumber(String cardNumber) {
        String strippedCardNumber = cardNumber.replaceAll("[ -]", "");
        return strippedCardNumber.matches(Config.CARD_PATTERN);
    }
}
