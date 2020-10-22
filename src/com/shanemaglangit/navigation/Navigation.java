package com.shanemaglangit.navigation;

import com.shanemaglangit.data.Product;
import com.shanemaglangit.ui.addtocart.AddToCartController;
import com.shanemaglangit.ui.addtocart.AddToCartView;
import com.shanemaglangit.ui.checkout.CheckoutController;
import com.shanemaglangit.ui.checkout.CheckoutView;
import com.shanemaglangit.ui.listing.ListingController;
import com.shanemaglangit.ui.listing.ListingView;
import com.shanemaglangit.ui.login.LoginController;
import com.shanemaglangit.ui.login.LoginView;
import com.shanemaglangit.ui.splashscreen.SplashScreenController;
import com.shanemaglangit.ui.splashscreen.SplashScreenView;

import java.awt.*;

public abstract class Navigation {

    public static void splashScreen() {
        SplashScreenView splashScreen = new SplashScreenView();
        SplashScreenController splashScreenController = new SplashScreenController(splashScreen);
    }

    public static void login() {
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginView);
    }

    public static void listing() {
        ListingView listingView = new ListingView();
        ListingController listingController = new ListingController(listingView);
    }

    public static void addToCart(Frame owner, Product product) {
        AddToCartView addToCartView = new AddToCartView(owner, product);
        AddToCartController addToCartController = new AddToCartController(addToCartView);
    }

    public static void checkout(Frame owner) {
        CheckoutView checkoutView = new CheckoutView(owner);
        CheckoutController checkoutController = new CheckoutController(checkoutView);
    }
}