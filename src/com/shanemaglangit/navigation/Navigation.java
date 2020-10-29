package com.shanemaglangit.navigation;

import com.shanemaglangit.data.Product;
import com.shanemaglangit.data.Transaction;
import com.shanemaglangit.ui.addtocart.AddToCartPresent;
import com.shanemaglangit.ui.addtocart.AddToCartView;
import com.shanemaglangit.ui.checkout.CheckoutPresenter;
import com.shanemaglangit.ui.checkout.CheckoutView;
import com.shanemaglangit.ui.listing.ListingPresenter;
import com.shanemaglangit.ui.listing.ListingView;
import com.shanemaglangit.ui.login.LoginPresenter;
import com.shanemaglangit.ui.login.LoginView;
import com.shanemaglangit.ui.receipt.ReceiptPresenter;
import com.shanemaglangit.ui.receipt.ReceiptView;
import com.shanemaglangit.ui.splashscreen.SplashScreenPresenter;
import com.shanemaglangit.ui.splashscreen.SplashScreenView;

import java.awt.*;

public abstract class Navigation {

    public static void splashScreen() {
        SplashScreenView splashScreen = new SplashScreenView();
        SplashScreenPresenter splashScreenPresenter = new SplashScreenPresenter(splashScreen);
    }

    public static void login() {
        LoginView loginView = new LoginView();
        LoginPresenter loginPresenter = new LoginPresenter(loginView);
    }

    public static void listing() {
        ListingView listingView = new ListingView();
        ListingPresenter listingPresenter = new ListingPresenter(listingView);
    }

    public static void addToCart(Frame owner, Product product) {
        AddToCartView addToCartView = new AddToCartView(owner);
        AddToCartPresent addToCartPresent = new AddToCartPresent(addToCartView, product);
    }

    public static void checkout(Frame owner) {
        CheckoutView checkoutView = new CheckoutView(owner);
        CheckoutPresenter checkoutPresenter = new CheckoutPresenter(checkoutView);
    }

    public static void receipt(Frame owner, Transaction transaction, double totalFee) {
        ReceiptView receiptView = new ReceiptView(owner);
        ReceiptPresenter receiptPresenter = new ReceiptPresenter(receiptView, transaction, totalFee);
    }
}