package com.shanemaglangit.navigation;

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

    public static void addToCart() {
        AddToCartView addToCartView = new AddToCartView();
        AddToCartController addToCartController = new AddToCartController(addToCartView);
    }

    public static void checkout() {
        CheckoutView checkoutView = new CheckoutView();
        CheckoutController checkoutController = new CheckoutController(checkoutView);
    }
}