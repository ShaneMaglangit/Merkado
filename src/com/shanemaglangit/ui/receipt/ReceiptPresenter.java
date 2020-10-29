package com.shanemaglangit.ui.receipt;

import com.shanemaglangit.data.Transaction;
import com.shanemaglangit.repository.Repository;
import com.shanemaglangit.ui.splashscreen.SplashScreenView;

public class ReceiptPresenter {
    private ReceiptView view;

    /**
     * Constructor
     * @param view view attached to this controller
     */
    public ReceiptPresenter(ReceiptView view, Transaction transaction, double totalFee) {
        this.view = view;
        setContents(transaction, totalFee);
        attachListeners();
        showView();
    }

    private void setContents(Transaction transaction, double totalFee) {
        String paymentMethod;

        if(transaction.isCOD()) paymentMethod = "Cash on Delivery";
        else paymentMethod = "Card";

        view.getLblTransactionId().setText("Transaction ID: " + transaction.getTransactionId().toString());
        view.getLblAddress().setText("Address: " + transaction.getAddress());
        view.getLblPhoneNumber().setText("Phone Number: " + transaction.getPhoneNumber());
        view.getLblPaymentMethod().setText("Payment Method: " + paymentMethod);
        view.getLblTotalFee().setText("Total Fee: " + totalFee);
    }

    private void attachListeners() {
        view.getBtnOk().addActionListener(e -> view.dispose());
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
