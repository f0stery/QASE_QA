package steps;

import io.qameta.allure.Step;
import pages.HomePage;
import pages.LoginPage;
import tests.ui.PropertyReader;

public class AuthSteps {
    private final LoginPage loginPage;

    public AuthSteps(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    private final String email =
            System.getProperty("email", PropertyReader.getProperty("email"));
    private final String password =
            System.getProperty("password", PropertyReader.getProperty("password"));

    @Step("Authorization")
    public HomePage login() {
        return loginPage.openPage()
                .isPageOpened()
                .login(email, password)
                .isPageOpened();
    }
}
