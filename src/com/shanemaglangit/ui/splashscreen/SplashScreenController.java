package com.shanemaglangit.ui.splashscreen;

import com.shanemaglangit.navigation.Navigation;
import com.shanemaglangit.util.Util;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class SplashScreenController {
    private SplashScreenView view;

    public SplashScreenController(SplashScreenView view) {
        this.view = view;
        startSplashScreen();
    }

    private void showView() {
        // Show the view
        view.pack();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    private void startSplashScreen() {
        showView();

        // Create an artificial delay for the splash screen
        try {
            TimeUnit.SECONDS.sleep(5);
        }
        // Create a log message if the delay is interrupted
        catch (InterruptedException e) {
            Util.log(Level.WARNING, e.getMessage());
        }
        // Stop the splash screen and navigate to login
        finally {
            Navigation.login();
            view.dispose();
        }
    }
}
