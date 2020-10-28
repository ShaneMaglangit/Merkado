package com.shanemaglangit.ui.checkout;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.Order;
import com.shanemaglangit.data.SinglyLinkedList;
import com.shanemaglangit.data.Transaction;
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
            String address = view.getTxtAddress().getText();
            String phoneNumber = view.getTxtPhoneNumber().getText();
            boolean isCOD = view.getRbtnCOD().isSelected();
            String cardNumber = view.getTxtCardNumber().getText();
            String expiration = view.getTxtExpiration().getText();
            String CVV = view.getTxtCVV().getText();
            if(address.isEmpty()) throw new Exception("Address cannot be empty");
            else if(phoneNumber.isEmpty()) throw new Exception("Phone number cannot be empty");
            else if(!isCOD) {
                if(!validateCardNumber(cardNumber)) throw new Exception("Invalid card number");
                if(!expiration.matches("\\d\\d/\\d\\d")) throw new Exception("Expiration must follow the format mm/yy");
                if(CVV.length() != 3) throw new Exception("CVV must be 3 digits");
            }
            saveOrders(address, phoneNumber, isCOD, cardNumber, expiration, CVV);
            repository.clearOrders();
            view.dispose();
            Util.showMessageDialog(view, "Your order should arrive in a few hours.");
        } catch (Exception e) {
            Util.showErrorDialog(view, e.getMessage());
        }
    }

    private void saveOrders(String address, String phoneNumber, boolean isCOD, String cardNumber, String expiration, String CVV) {
        SinglyLinkedList<Order> orders = repository.getOrderList();
        SinglyLinkedList<Transaction> transactions = new SinglyLinkedList<>();
        for(int i = 0; i < orders.getSize(); i++) {
            transactions.add(new Transaction(address, phoneNumber, isCOD, cardNumber, expiration, CVV, orders.get(i)));
        }
        repository.addTransactionList(transactions);
    }

    private boolean validateCardNumber(String cardNumber) {
        String strippedCardNumber = cardNumber.replaceAll("[ -]", "");
        return strippedCardNumber.matches(Config.CARD_PATTERN);
    }
}