package com.shanemaglangit.ui.splashscreen;

import com.shanemaglangit.navigation.Navigation;
import com.shanemaglangit.repository.Repository;
import com.shanemaglangit.util.Util;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class SplashScreenController {
    // Duration of the artificial delay for the splash screen
    private final int DELAY_DURATION = 5;

    private SplashScreenView view;

    /**
     * Constructor
     * @param view view attached to this controller
     */
    public SplashScreenController(SplashScreenView view) {
        this.view = view;
        // Forces repository to start lazy initialization, forcing it to preload the data
        //  even during the splash screen duration.
        Repository.getInstance();
        startSplashScreen();
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
     * Starts the splash screen and navigates to login as xx seconds.
     */
    private void startSplashScreen() {
        showView();

        // Create an artificial delay for the splash screen
        try {
            TimeUnit.SECONDS.sleep(DELAY_DURATION);
        }
        // Create a log message if the delay is interrupted
        catch (InterruptedException e) {
            Util.log(Level.WARNING, e.getMessage());
        }
        // Stop the splash screen and navigate to login
        finally {
            view.dispose();
            Navigation.login();
        }
    }
}
