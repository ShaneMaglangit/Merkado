package com.shanemaglangit.ui.login;

public class LoginController {
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
        showView();
    }

    private void showView() {
        // Show the view
        view.pack();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}
