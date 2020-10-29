package com.shanemaglangit.ui.login;

import com.shanemaglangit.navigation.Navigation;

public class LoginPresenter {
    private LoginView view;

    /**
     * Constructor
     * @param view view attached to this controller
     */
    public LoginPresenter(LoginView view) {
        this.view = view;

        // Attach click listener to the view components
        view.getBtnSubmit().addActionListener(e -> login());

        showView();
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
     * Authenticates the user
     */
    private void login() {
        view.dispose();
        Navigation.listing();
    }
}
