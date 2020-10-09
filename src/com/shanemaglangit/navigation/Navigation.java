package com.shanemaglangit.navigation;

import com.shanemaglangit.ui.login.LoginController;
import com.shanemaglangit.ui.login.LoginView;
import com.shanemaglangit.ui.splashscreen.SplashScreenController;
import com.shanemaglangit.ui.splashscreen.SplashScreenView;

public abstract class Navigation {
    public static void login() {
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginView);
    }

    public static void splashScreen() {
        SplashScreenView splashScreen = new SplashScreenView();
        SplashScreenController splashScreenController = new SplashScreenController(splashScreen);
    }
}
