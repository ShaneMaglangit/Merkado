package com.shanemaglangit.ui.receipt;

import com.shanemaglangit.data.Transaction;

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

    /**
     * Attach listeners to the view components
     */
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

    /**
     * Used to load the receipt details to the views
     * @param transaction reference to the transaction
     * @param totalFee total fee for the transaction
     */
    private void setContents(Transaction transaction, double totalFee) {
        // Identify the payment method
        String paymentMethod;
        if(transaction.isCOD()) paymentMethod = "Cash on Delivery";
        else paymentMethod = "Card";

        // Set the appropriate contents for the view labels
        view.getLblTransactionId().setText("Transaction ID: " + transaction.getTransactionId().toString());
        view.getLblAddress().setText("Address: " + transaction.getAddress());
        view.getLblPhoneNumber().setText("Phone Number: " + transaction.getPhoneNumber());
        view.getLblPaymentMethod().setText("Payment Method: " + paymentMethod);
        view.getLblTotalFee().setText("Total Fee: " + totalFee);
    }
}
