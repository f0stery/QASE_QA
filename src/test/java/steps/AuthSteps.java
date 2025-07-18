package steps;

import io.qameta.allure.Step;
import pages.HomePage;
import pages.LoginPage;

public class AuthSteps {
    private final LoginPage loginPage;

    public AuthSteps(LoginPage loginPage, HomePage homePage) {
        this.loginPage = loginPage;
    }

    @Step("Authorization")
    public HomePage login(String email, String password) {
        return loginPage.openPage()
                .isPageOpened()
                .login(email, password)
                .isPageOpened();
    }
}
