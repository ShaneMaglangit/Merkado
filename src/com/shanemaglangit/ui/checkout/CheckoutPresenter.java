package com.shanemaglangit.ui.checkout;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.Order;
import com.shanemaglangit.data.SinglyLinkedList;
import com.shanemaglangit.data.Transaction;
import com.shanemaglangit.navigation.Navigation;
import com.shanemaglangit.repository.Repository;
import com.shanemaglangit.util.Util;

import java.awt.*;

public class CheckoutPresenter {
    private Repository repository;
    private CheckoutView view;

    /**
     * Constructor
     * @param view view attached to this controller
     */
    public CheckoutPresenter(CheckoutView view) {
        this.view = view;
        this.repository = Repository.getInstance();
        updateContents();
        attachListeners();
        showView();
    }

    /**
     * Used to update the contents of the view components
     */
    private void updateContents() {
        double subTotal = Util.computeOrderSubTotal(repository.getOrderList());
        view.getLblTotal().setText(String.valueOf(subTotal + Config.SERVICE_FEE));
    }

    /**
     * Used to attach listeners to the views
     */
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

    /**
     * Used to validate the fields
     */
    private void validateFields() {
        // Save the values
        String address = view.getTxtAddress().getText();
        String phoneNumber = view.getTxtPhoneNumber().getText();
        boolean isCOD = view.getRbtnCOD().isSelected();
        String cardNumber = view.getTxtCardNumber().getText();
        String expiration = view.getTxtExpiration().getText();
        String CVV = view.getTxtCVV().getText();

        try {
            // Validate the fields
            if(address.isEmpty()) throw new Exception("Address cannot be empty");
            else if(phoneNumber.isEmpty()) throw new Exception("Phone number cannot be empty");
            else if(!isCOD) {
                if(!validateCardNumber(cardNumber)) throw new Exception("Invalid card number");
                if(!expiration.matches("\\d\\d/\\d\\d")) throw new Exception("Expiration must follow the format mm/yy");
                if(CVV.length() != 3) throw new Exception("CVV must be 3 digits");
            }

            // Save the orders
            checkout(address, phoneNumber, isCOD, cardNumber, expiration, CVV);
        } catch (Exception e) {
            Util.showErrorDialog(view, e.getMessage());
        }
    }

    /**
     * Used to pass confirm and record the purchase
     * @param address address of the user
     * @param phoneNumber phone number of the user
     * @param isCOD flag for the payment method
     * @param cardNumber card number of the user
     * @param expiration expiration of the card
     * @param CVV cvv of the card
     */
    private void checkout(String address, String phoneNumber, boolean isCOD, String cardNumber, String expiration, String CVV) {
        // Initialize the lists and values
        SinglyLinkedList<Order> orders = repository.getOrderList();
        SinglyLinkedList<Transaction> transactions = new SinglyLinkedList<>();
        Transaction transaction = new Transaction(address, phoneNumber, isCOD, cardNumber, expiration, CVV, null);
        double totalFee = Config.SERVICE_FEE;

        // Iterate through the orders and save a corresponding transaction
        for(int i = 0; i < orders.getSize(); i++) {
            transactions.add(transaction.copyWithNewOrder(orders.get(i)));
            totalFee += orders.get(i).getTotal();
        }

        // Save the transactions to the repository
        repository.addTransactionList(transactions);

        // Close the dialog
        view.dispose();

        // Show the receipt
        Navigation.receipt((Frame) view.getOwner(), transaction, totalFee);
    }

    /**
     * Used to validate the card number
     * @param cardNumber card number
     * @return flag if card number is valid
     */
    private boolean validateCardNumber(String cardNumber) {
        String strippedCardNumber = cardNumber.replaceAll("[ -]", "");
        return strippedCardNumber.matches(Config.CARD_PATTERN);
    }
}