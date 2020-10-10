package com.shanemaglangit.ui.login;

public class LoginController {
    private LoginView view;

    /**
     * Constructor
     * @param view view attached to this controller
     */
    public LoginController(LoginView view) {
        this.view = view;
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
}
